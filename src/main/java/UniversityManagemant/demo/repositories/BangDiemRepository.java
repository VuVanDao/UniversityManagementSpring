package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.GradeRecord;

@Repository
public interface BangDiemRepository extends JpaRepository<GradeRecord, Long> {
}
