package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.Classroom;

@Repository
public interface PhongHocRepository extends JpaRepository<Classroom, Long> {
}
