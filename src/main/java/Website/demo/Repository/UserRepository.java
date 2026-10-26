package Website.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Website.demo.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>  {
	 Optional<UserEntity> findByUsername(String username);

	    Optional<UserEntity> findByEmail(String email);

}
