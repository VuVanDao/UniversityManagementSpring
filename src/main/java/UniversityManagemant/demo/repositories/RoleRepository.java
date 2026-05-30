package UniversityManagemant.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import UniversityManagemant.demo.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    Role findByTenRole(String tenRole);
}
