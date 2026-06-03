package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateRoleReq;
import UniversityManagemant.demo.dtos.response.RoleResDto;
import UniversityManagemant.demo.models.Role;

@Component
public class RoleMapper {

    public Role toEntity(CreateRoleReq createRoleReq) {
        return Role.builder()
                .roleName(createRoleReq.getRoleName())
                .description(createRoleReq.getDescription())
                .build();
    }

    public RoleResDto toResDto(Role role) {
        return RoleResDto.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .build();
    }

    public void updateEntityFromDto(CreateRoleReq createRoleReq, Role role) {
        role.setRoleName(createRoleReq.getRoleName());
        role.setDescription(createRoleReq.getDescription());
    }
}
