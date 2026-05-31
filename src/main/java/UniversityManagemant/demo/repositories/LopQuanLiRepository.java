package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.LopQuanLi;

@Repository
public interface LopQuanLiRepository extends JpaRepository<LopQuanLi, Long> {
}
