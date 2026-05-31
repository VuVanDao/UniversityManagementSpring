package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.NhomHoc_SinhVien;

@Repository
public interface NhomHocSinhVienRepository extends JpaRepository<NhomHoc_SinhVien, Long> {
}
