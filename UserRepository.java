package repository;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.users;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<users> findByUsernameAndPassword(String username, String password);
}
