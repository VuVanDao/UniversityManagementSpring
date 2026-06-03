package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.StudyGroup;

@Repository
public interface NhomHocRepository extends JpaRepository<StudyGroup, Long> {
}
