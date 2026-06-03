package UniversityManagemant.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import UniversityManagemant.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findByUserName(String userName);
    Optional<User> findByEmailOrUserCode(String email, String userCode);
    Optional<User> findByEmail(String email);
}
