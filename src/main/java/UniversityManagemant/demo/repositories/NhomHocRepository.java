package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.NhomHoc;

@Repository
public interface NhomHocRepository extends JpaRepository<NhomHoc, Long> {
}
