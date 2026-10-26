package Website.demo.Controller;

import Website.demo.Entity.Blog;
import Website.demo.Repository.BlogRepository;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/api/blogs")
@CrossOrigin(origins = "*")
public class BlogController {
	
    @Autowired
    private BlogRepository blogRepository;


    // Get all blogs - Admin
    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }


    // Get published blogs - Website
    @GetMapping("/published")
    public List<Blog> getPublishedBlogs() {
        return blogRepository.findByPublishedTrueOrderByCreatedAtDesc();
    }


    // Get single blog
    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {

        return blogRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // =========================================
    // CREATE BLOG WITH IMAGE
    // =========================================
    @PostMapping("/upload")
    public ResponseEntity<?> uploadBlog(

            @RequestParam("title")
            String title,

            @RequestParam("category")
            String category,

            @RequestParam("shortDescription")
            String shortDescription,

            @RequestParam("content")
            String content,

            @RequestParam("author")
            String author,

            @RequestParam("image")
            MultipartFile image

    ) {

        try {


            // =========================================
            // CHECK IMAGE
            // =========================================

            if (image == null || image.isEmpty()) {

                return ResponseEntity
                        .badRequest()
                        .body("Please select a blog image.");

            }


            // =========================================
            // CREATE UPLOAD DIRECTORY
            // =========================================

            String uploadDir = "uploads/blogs/";

            File directory =
                    new File(uploadDir);

            if (!directory.exists()) {

                directory.mkdirs();

            }


            // =========================================
            // CREATE UNIQUE FILE NAME
            // =========================================

            String originalFileName =
                    image.getOriginalFilename();

            String fileName =
                    System.currentTimeMillis()
                    + "_"
                    + originalFileName;


            // =========================================
            // SAVE IMAGEa
            // =========================================

            Path filePath =
                    Paths.get(uploadDir + fileName);


            Files.copy(
                    image.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );


            // =========================================
            // CREATE BLOG
            // =========================================

            Blog blog = new Blog();

            blog.setTitle(title);

            blog.setCategory(category);

            blog.setShortDescription(
                    shortDescription
            );

            blog.setContent(content);

            blog.setAuthor(author);

            blog.setImageUrl(
                    "/uploads/blogs/" + fileName
            );

            blog.setPublished(true);


            // =========================================
            // SAVE TO MYSQL
            // =========================================

            Blog savedBlog =
                    blogRepository.save(blog);


            return ResponseEntity.ok(savedBlog);


        }
        catch (Exception e) {


            e.printStackTrace();


            return ResponseEntity
                    .internalServerError()
                    .body(
                        "Blog upload failed: "
                        + e.getMessage()
                    );

        }

    }
    // Create blog
    @PostMapping
    public Blog createBlog(@RequestBody Blog blog) {
        return blogRepository.save(blog);
    }


    // Update blog
    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(
            @PathVariable Long id,
            @RequestBody Blog blog) {

        return blogRepository.findById(id)
                .map(existingBlog -> {

                    existingBlog.setTitle(blog.getTitle());
                    existingBlog.setCategory(blog.getCategory());
                    existingBlog.setShortDescription(
                            blog.getShortDescription()
                    );
                    existingBlog.setContent(blog.getContent());
                    existingBlog.setImageUrl(blog.getImageUrl());
                    existingBlog.setAuthor(blog.getAuthor());
                    existingBlog.setPublished(blog.isPublished());

                    return ResponseEntity.ok(
                            blogRepository.save(existingBlog)
                    );

                })
                .orElse(ResponseEntity.notFound().build());
    }


    // Delete blog
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id) {

        if (!blogRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        blogRepository.deleteById(id);

        return ResponseEntity.ok("Blog deleted successfully");
    }

}
