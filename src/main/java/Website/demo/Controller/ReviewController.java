
package Website.demo.Controller;

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
import org.springframework.web.bind.annotation.RestController;

import Website.demo.Entity.Review;
import Website.demo.Repository.ReviewRepository;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = {
    "http://127.0.0.1:5500",
    "http://localhost:5500",
    "http://127.0.0.1:5501",
    "http://localhost:5501"
})
public class ReviewController {

    @Autowired
    private ReviewRepository repository;

    // GET ALL REVIEWS FOR ADMIN
    @GetMapping
    public List<Review> getAllReviews() {
        return repository.findAll();
    }

    // GET ONLY PUBLISHED REVIEWS FOR WEBSITE
    @GetMapping("/testimonials")
    public List<Review> getTestimonials() {
        return repository.findByPublishedTrue();
    }

    // GET SINGLE REVIEW
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ADD REVIEW
    @PostMapping
    public Review createReview(@RequestBody Review review) {
        if (review.getPublished() == null) {
            review.setPublished(false);
        }
        return repository.save(review);
    }

    // UPDATE REVIEW
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long id,
            @RequestBody Review review) {

        return repository.findById(id)
                .map(existingReview -> {

                    if (review.getParentName() != null) {
                        existingReview.setParentName(
                                review.getParentName());
                    }

                    if (review.getReviewText() != null) {
                        existingReview.setReviewText(
                                review.getReviewText());
                    }

                    if (review.getRating() != null) {
                        existingReview.setRating(
                                review.getRating());
                    }

                    if (review.getPublished() != null) {
                        existingReview.setPublished(
                                review.getPublished());
                    }

                    Review updated = repository.save(existingReview);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE REVIEW
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.ok("Review deleted successfully");
    }
}
