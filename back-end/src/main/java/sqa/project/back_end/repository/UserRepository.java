package sqa.project.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqa.project.back_end.models.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    boolean existsByUserName(String userName);
}
