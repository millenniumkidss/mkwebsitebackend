package Website.demo.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Website.demo.Entity.UserEntity;
import Website.demo.Repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = {
        "http://127.0.0.1:5500",
        "http://localhost:5500"
})
public class UserController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	 @Autowired
	    private UserRepository userRepository;


	    // =========================
	    // ADMIN LOGIN
	    // =========================

	    @PostMapping("/login")
	    public ResponseEntity<?> login(
	            @RequestBody Map<String, String> loginData) {

	        String username = loginData.get("username");
	        String password = loginData.get("password");

	        Optional<UserEntity> userOptional =
	                userRepository.findByUsername(username);

	        if (userOptional.isEmpty()) {

	            return ResponseEntity
	                    .status(HttpStatus.UNAUTHORIZED)
	                    .body(Map.of(
	                            "message",
	                            "Invalid username or password"
	                    ));
	        }

	        UserEntity user = userOptional.get();

	        // Temporary password checking
	        // We will replace this with BCrypt shortly
	        if (!passwordEncoder.matches(password, user.getPassword())) {

	            return ResponseEntity
	                    .status(HttpStatus.UNAUTHORIZED)
	                    .body(Map.of(
	                            "message",
	                            "Invalid username or password"
	                    ));
	        }

	        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {

	            return ResponseEntity
	                    .status(HttpStatus.FORBIDDEN)
	                    .body(Map.of(
	                            "message",
	                            "You are not authorized as admin"
	                    ));
	        }

	        return ResponseEntity.ok(
	                Map.of(
	                        "message", "Login successful",
	                        "username", user.getUsername(),
	                        "role", user.getRole()
	                )
	        );
	    }


	    // =========================
	    // FORGOT PASSWORD
	    // =========================

	    @PostMapping("/forgot-password")
	    public ResponseEntity<?> forgotPassword(
	            @RequestBody Map<String, String> data) {

	        String email = data.get("email");

	        Optional<UserEntity> userOptional =
	                userRepository.findByEmail(email);

	        if (userOptional.isEmpty()) {

	            return ResponseEntity
	                    .status(HttpStatus.NOT_FOUND)
	                    .body(Map.of(
	                            "message",
	                            "Email address not found"
	                    ));
	        }

	        // OTP functionality will be added next

	        return ResponseEntity.ok(
	                Map.of(
	                        "message",
	                        "Email found. OTP process will be added."
	                )
	        );
	    }
}
