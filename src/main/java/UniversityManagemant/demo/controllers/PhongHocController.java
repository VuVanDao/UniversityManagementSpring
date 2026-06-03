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

import UniversityManagemant.demo.dtos.request.CreateClassroomReq;
import UniversityManagemant.demo.dtos.response.ClassroomResDto;
import UniversityManagemant.demo.services.serviceInterface.PhongHocService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/phong-hocs")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PhongHocController {
    final PhongHocService phongHocService;

    @PostMapping
    public ResponseEntity<ClassroomResDto> createPhongHoc(@RequestBody CreateClassroomReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(phongHocService.createPhongHoc(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomResDto> getPhongHoc(@PathVariable Long id) {
        return ResponseEntity.ok(phongHocService.getPhongHocById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassroomResDto>> getAllPhongHoc() {
        return ResponseEntity.ok(phongHocService.getAllPhongHoc());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomResDto> updatePhongHoc(@PathVariable Long id, @RequestBody CreateClassroomReq req) {
        return ResponseEntity.ok(phongHocService.updatePhongHoc(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhongHoc(@PathVariable Long id) {
        phongHocService.deletePhongHoc(id);
        return ResponseEntity.noContent().build();
    }
}
