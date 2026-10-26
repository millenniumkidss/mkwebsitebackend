package Website.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Website.demo.Entity.Enquiry;



public interface EnquiryRepository extends JpaRepository <Enquiry ,Long>{
	
	List<Enquiry> findByStatus(String status);
	List<Enquiry> findByBranch(String branch);
	

}
