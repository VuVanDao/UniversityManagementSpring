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

import UniversityManagemant.demo.dtos.request.CreateClassManagementReq;
import UniversityManagemant.demo.dtos.response.ClassManagementResDto;
import UniversityManagemant.demo.services.serviceInterface.ClassManagementService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/class-managements")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ClassManagementController {
    final ClassManagementService classManagementService;

    @PostMapping
    public ResponseEntity<ClassManagementResDto> createClassManagement(@RequestBody CreateClassManagementReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classManagementService.createClassManagement(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassManagementResDto> getClassManagement(@PathVariable Long id) {
        return ResponseEntity.ok(classManagementService.getClassManagementById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassManagementResDto>> getAllClassManagements() {
        return ResponseEntity.ok(classManagementService.getAllClassManagements());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassManagementResDto> updateClassManagement(@PathVariable Long id, @RequestBody CreateClassManagementReq req) {
        return ResponseEntity.ok(classManagementService.updateClassManagement(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassManagement(@PathVariable Long id) {
        classManagementService.deleteClassManagement(id);
        return ResponseEntity.noContent().build();
    }
}
