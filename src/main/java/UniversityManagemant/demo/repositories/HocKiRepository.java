package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.Semester;

@Repository
public interface HocKiRepository extends JpaRepository<Semester, Long> {
}
