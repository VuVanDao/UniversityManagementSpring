package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.GiangVien;

@Repository
public interface GiangVienRepository extends JpaRepository<GiangVien, Long> {
}
