package lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lab5.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
