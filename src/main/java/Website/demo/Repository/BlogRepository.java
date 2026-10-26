package Website.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Website.demo.Entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {
	List<Blog> findByPublishedTrueOrderByCreatedAtDesc();
}
