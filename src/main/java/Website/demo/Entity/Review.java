
package Website.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "parent_name", nullable = false)
    private String parentName;

    @Column(name = "review_text", nullable = false, columnDefinition = "TEXT")
    private String reviewText;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "published")
    private Boolean published = false;

    @Column(nullable = false)
    private boolean showTestimonial = false;

    @Column(length = 1000)
    private String testimonialText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public boolean isShowTestimonial() {
        return showTestimonial;
    }

    public void setShowTestimonial(boolean showTestimonial) {
        this.showTestimonial = showTestimonial;
    }

    public String getTestimonialText() {
        return testimonialText;
    }

    public void setTestimonialText(String testimonialText) {
        this.testimonialText = testimonialText;
    }
}
