package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.ChuyenNganh;

@Repository
public interface ChuyenNganhRepository extends JpaRepository<ChuyenNganh, Long> {
}
