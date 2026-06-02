package UniversityManagemant.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import UniversityManagemant.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findByTenNguoiDung(String tenNguoiDung);
    Optional<User> findByEmailOrMaNguoiDung(String email, String maNguoiDung);
    Optional<User> findByEmail(String email);
}
