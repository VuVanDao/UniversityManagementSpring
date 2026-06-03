package UniversityManagemant.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import UniversityManagemant.demo.models.GiangVien;
import UniversityManagemant.demo.models.Khoa;

@Repository
public interface KhoaRepository extends JpaRepository<Khoa, Long> {
    Boolean existsByMaKhoa(String maKhoa);
    boolean existsByMaKhoaAndIdNot(String maKhoa, Long id);
    boolean existsByTenKhoaIgnoreCaseAndIdNot(String tenKhoa, Long id);
    Khoa findByTruongKhoa_Id(Long truongKhoaId);
}
