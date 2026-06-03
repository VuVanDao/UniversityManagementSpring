package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.Major;

@Repository
public interface ChuyenNganhRepository extends JpaRepository<Major, Long> {
}
