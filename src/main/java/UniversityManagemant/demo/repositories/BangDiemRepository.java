package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.BangDiem;

@Repository
public interface BangDiemRepository extends JpaRepository<BangDiem, Long> {
}
