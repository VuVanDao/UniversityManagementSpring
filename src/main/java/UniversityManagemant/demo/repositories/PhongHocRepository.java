package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.PhongHoc;

@Repository
public interface PhongHocRepository extends JpaRepository<PhongHoc, Long> {
}
