package Website.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="enquiries")
public class Enquiry {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 
	 @Column(name = "enquiry_id")
	    private Long id;
	 
	 
	    @NotBlank(message = "Parent name is required")
	 @Column(name = "parent_name")
	    private String parentName;


	    @NotBlank(message = "Child name is required")
	    @Column(name = "child_name")
	    private String childName;
	    
	    @NotBlank(message = "Mobile number is required")
	    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
	    @Column(name = "mobile_no")
	    private String mobile;
	    
	    @Pattern(regexp = "^$|^[0-9]{10}$", message = "Alternate number must be 10 digits")
	    @Column(name = "alternate_no")
	    private String alternateNo;


	    @Column(name = "branch")
	    private String branch;

	    @Column(name = "class_name")
	    private String className;
	    
	    @Column(name = "enquiry_date")
	    private LocalDate enquiryDate;

	    private String message;
	    
	    @Column(name = "source")
	    private String source;
	    
	    private String email;

	    public String getSource() {
	        return source;
	    }

	    public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setSource(String source) {
	        this.source = source;
	    }
	    
	    @Column(name = "status")
	    private String status;
		public Long getId() {
			return id;
		}
		public String getAlternateNo() {
			return alternateNo;
		}
		public void setAlternateNo(String alternateNo) {
			this.alternateNo = alternateNo;
		}
		public LocalDate getEnquiryDate() {
			return enquiryDate;
		}
		public void setEnquiryDate(LocalDate enquiryDate) {
			this.enquiryDate = enquiryDate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
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
		public String getChildName() {
			return childName;
		}
		public void setChildName(String childName) {
			this.childName = childName;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		
		public String getBranch() {
			return branch;
		}
		public void setBranch(String branch) {
			this.branch = branch;
		}
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
}
