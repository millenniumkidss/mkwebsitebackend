package Website.demo.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import Website.demo.Entity.UserEntity;
import Website.demo.Repository.UserRepository;

@Configuration
public class AdminDataInitializer {

    @Bean
    CommandLineRunner createAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            // Check if admin already exists
            if (userRepository.findByUsername("admin").isEmpty()) {

                UserEntity admin = new UserEntity();

                admin.setUsername("admin");

                admin.setEmail("admin@millenniumkidss.com");

                // Password: Admin@123
                admin.setPassword(
                    passwordEncoder.encode("Admin@123")
                );

                admin.setRole("ADMIN");

                userRepository.save(admin);

                System.out.println("================================");
                System.out.println("ADMIN USER CREATED");
                System.out.println("Username: admin");
                System.out.println("Password: Admin@123");
                System.out.println("================================");
            }
        };
    }
}
