package fastwork.gereja.repository;

import fastwork.gereja.entity.TataIbadah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TataIbadahRepository extends JpaRepository<TataIbadah, Long> {
}
