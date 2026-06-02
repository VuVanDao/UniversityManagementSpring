package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateRoleReq;
import UniversityManagemant.demo.dtos.response.RoleResDto;

public interface RoleService {
    RoleResDto createRole(CreateRoleReq req);
    RoleResDto getRoleById(Long id);
    List<RoleResDto> getAllRoles();
    RoleResDto updateRole(Long id, CreateRoleReq req);
    void deleteRole(Long id);
}
