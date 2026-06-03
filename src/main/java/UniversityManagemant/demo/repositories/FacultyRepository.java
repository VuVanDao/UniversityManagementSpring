package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Boolean existsByFacultyCode(String facultyCode);
    boolean existsByFacultyCodeAndIdNot(String facultyCode, Long id);
    boolean existsByFacultyNameIgnoreCaseAndIdNot(String facultyName, Long id);
    Faculty findByDean_Id(Long deanId);
}
