package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
