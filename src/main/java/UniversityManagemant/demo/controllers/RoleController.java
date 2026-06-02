package UniversityManagemant.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UniversityManagemant.demo.dtos.request.CreateRoleReq;
import UniversityManagemant.demo.dtos.response.RoleResDto;
import UniversityManagemant.demo.services.serviceInterface.RoleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RoleController {
    final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResDto> createRole(@RequestBody CreateRoleReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResDto> getRole(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @GetMapping
    public ResponseEntity<List<RoleResDto>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResDto> updateRole(@PathVariable Long id, @RequestBody CreateRoleReq req) {
        return ResponseEntity.ok(roleService.updateRole(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
