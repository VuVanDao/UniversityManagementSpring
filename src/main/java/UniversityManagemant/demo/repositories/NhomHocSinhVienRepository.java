package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.StudyGroupStudent;

@Repository
public interface NhomHocSinhVienRepository extends JpaRepository<StudyGroupStudent, Long> {
}
