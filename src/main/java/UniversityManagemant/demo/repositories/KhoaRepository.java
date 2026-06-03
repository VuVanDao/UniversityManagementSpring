package UniversityManagemant.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import UniversityManagemant.demo.models.Lecturer;
import UniversityManagemant.demo.models.Faculty;

@Repository
public interface KhoaRepository extends JpaRepository<Faculty, Long> {
    Boolean existsByMaKhoa(String maKhoa);
    boolean existsByFacultyCodeAndIdNot(String maKhoa, Long id);
    boolean existsByFacultyNameIgnoreCaseAndIdNot(String tenKhoa, Long id);
    Faculty findByTruongKhoa_Id(Long truongKhoaId);
}
