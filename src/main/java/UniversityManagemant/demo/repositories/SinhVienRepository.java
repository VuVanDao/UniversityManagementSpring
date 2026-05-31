package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.SinhVien;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
}
