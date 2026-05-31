package UniversityManagemant.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateRoleReq;
import UniversityManagemant.demo.dtos.response.RoleResDto;
import UniversityManagemant.demo.models.Role;
import UniversityManagemant.demo.repositories.RoleRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RoleService {
    final RoleRepository roleRepository;

    public RoleResDto createRole(CreateRoleReq req) {
        Role role = Role.builder()
                .tenRole(req.getTenRole())
                .moTa(req.getMoTa())
                .build();
        Role saved = roleRepository.save(role);
        return toDto(saved);
    }

    public RoleResDto getRoleById(Long id) {
        return roleRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public List<RoleResDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public RoleResDto updateRole(Long id, CreateRoleReq req) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        role.setTenRole(req.getTenRole());
        role.setMoTa(req.getMoTa());
        Role updated = roleRepository.save(role);
        return toDto(updated);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    private RoleResDto toDto(Role role) {
        return RoleResDto.builder()
                .id(role.getId())
                .tenRole(role.getTenRole())
                .moTa(role.getMoTa())
                .build();
    }
}
