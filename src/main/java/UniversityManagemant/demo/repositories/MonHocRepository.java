package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.MonHoc;

@Repository
public interface MonHocRepository extends JpaRepository<MonHoc, Long> {
}
