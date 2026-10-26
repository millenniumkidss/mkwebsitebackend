package Website.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
	@Table(name = "users")
	public class UserEntity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, unique = true)
	    private String username;

	    @Column(nullable = false)
	    private String password;

	    @Column(nullable = false)
	    private String role;

	    @Column(nullable = false, unique = true)
	    private String email;

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }
	    
	    public Long getId() {
	        return id;
	    }

	    // For forgot password
	    @Column(name = "reset_otp")
	    private String resetOtp;

	    @Column(name = "reset_otp_expiry")
	    private Long resetOtpExpiry;
	    public String getResetOtp() {
			return resetOtp;
		}

		public void setResetOtp(String resetOtp) {
			this.resetOtp = resetOtp;
		}

		public Long getResetOtpExpiry() {
			return resetOtpExpiry;
		}

		public void setResetOtpExpiry(Long resetOtpExpiry) {
			this.resetOtpExpiry = resetOtpExpiry;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getRole() {
	        return role;
	    }

	    public void setRole(String role) {
	        this.role = role;
	    }
	}

