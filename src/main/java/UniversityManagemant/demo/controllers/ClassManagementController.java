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
import UniversityManagemant.demo.services.serviceInterface.LopQuanLiService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/class-managements")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ClassManagementController {
    final LopQuanLiService classManagementService;

    @PostMapping
    public ResponseEntity<ClassManagementResDto> createLopQuanLi(@RequestBody CreateClassManagementReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classManagementService.createLopQuanLi(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassManagementResDto> getLopQuanLi(@PathVariable Long id) {
        return ResponseEntity.ok(classManagementService.getLopQuanLiById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassManagementResDto>> getAllLopQuanLi() {
        return ResponseEntity.ok(classManagementService.getAllLopQuanLi());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassManagementResDto> updateLopQuanLi(@PathVariable Long id, @RequestBody CreateClassManagementReq req) {
        return ResponseEntity.ok(classManagementService.updateLopQuanLi(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLopQuanLi(@PathVariable Long id) {
        classManagementService.deleteLopQuanLi(id);
        return ResponseEntity.noContent().build();
    }
}
