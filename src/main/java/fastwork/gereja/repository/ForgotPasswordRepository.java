package fastwork.gereja.repository;

import fastwork.gereja.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
    User findByToken(String token);
}
