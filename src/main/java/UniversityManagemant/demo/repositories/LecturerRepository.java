package UniversityManagemant.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import UniversityManagemant.demo.models.Lecturer;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    Optional<Lecturer> findByUserId(Long userId);
}
