package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import UniversityManagemant.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findByTenNguoiDung(String tenNguoiDung);
}
