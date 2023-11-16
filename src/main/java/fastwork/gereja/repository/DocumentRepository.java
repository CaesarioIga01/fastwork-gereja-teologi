package fastwork.gereja.repository;

import fastwork.gereja.entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Documents, Long> {

    @Query("SELECT new Documents(id, name, size) FROM Documents ORDER BY uploadTime ASC")
    List<Documents> findAll();
}
