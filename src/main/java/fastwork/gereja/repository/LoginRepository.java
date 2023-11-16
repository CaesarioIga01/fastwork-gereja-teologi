package fastwork.gereja.repository;

import fastwork.gereja.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    Login findByUsernameAndPassword(String username, String password);
}
