package Website.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//import Website.demo.Entity.Enquiry;
import Website.demo.Entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>  {
	 List<Review> findByPublishedTrue();
}
