package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateRoleReq;
import UniversityManagemant.demo.dtos.response.RoleResDto;
import UniversityManagemant.demo.mappers.RoleMapper;
import UniversityManagemant.demo.models.Role;
import UniversityManagemant.demo.repositories.RoleRepository;
import UniversityManagemant.demo.services.serviceInterface.RoleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    final RoleRepository roleRepository;
    final RoleMapper roleMapper;

    @Override
    public RoleResDto createRole(CreateRoleReq req) {
        Role role = roleMapper.toEntity(req);
        Role saved = roleRepository.save(role);
        return roleMapper.toResDto(saved);
    }

    @Override
    public RoleResDto getRoleById(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::toResDto)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public List<RoleResDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toResDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResDto updateRole(Long id, CreateRoleReq req) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        roleMapper.updateEntityFromDto(req, role);
        Role updated = roleRepository.save(role);
        return roleMapper.toResDto(updated);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
