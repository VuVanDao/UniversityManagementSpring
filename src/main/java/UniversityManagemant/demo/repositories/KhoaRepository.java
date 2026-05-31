package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.Khoa;

@Repository
public interface KhoaRepository extends JpaRepository<Khoa, Long> {
}
