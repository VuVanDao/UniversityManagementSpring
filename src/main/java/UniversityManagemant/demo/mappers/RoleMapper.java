package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateRoleReq;
import UniversityManagemant.demo.dtos.response.RoleResDto;
import UniversityManagemant.demo.models.Role;

@Component
public class RoleMapper {

    public Role toEntity(CreateRoleReq createRoleReq) {
        return Role.builder()
                .tenRole(createRoleReq.getTenRole())
                .moTa(createRoleReq.getMoTa())
                .build();
    }

    public RoleResDto toResDto(Role role) {
        return RoleResDto.builder()
                .id(role.getId())
                .tenRole(role.getTenRole())
                .moTa(role.getMoTa())
                .build();
    }

    public void updateEntityFromDto(CreateRoleReq createRoleReq, Role role) {
        role.setTenRole(createRoleReq.getTenRole());
        role.setMoTa(createRoleReq.getMoTa());
    }
}
