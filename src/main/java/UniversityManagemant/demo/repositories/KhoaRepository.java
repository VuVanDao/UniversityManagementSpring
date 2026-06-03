package UniversityManagemant.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import UniversityManagemant.demo.models.Lecturer;
import UniversityManagemant.demo.models.Faculty;

@Repository
public interface KhoaRepository extends JpaRepository<Faculty, Long> {
    Boolean existsByFacultyCode(String facultyCode);
    boolean existsByFacultyCodeAndIdNot(String facultyCode, Long id);
    boolean existsByFacultyNameIgnoreCaseAndIdNot(String facultyName, Long id);
    Faculty findByDean_Id(Long deanId);
}
