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

import UniversityManagemant.demo.dtos.request.CreateSubjectReq;
import UniversityManagemant.demo.dtos.response.SubjectResDto;
import UniversityManagemant.demo.services.serviceInterface.MonHocService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/subjects")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SubjectController {
    final MonHocService subjectService;

    @PostMapping
    public ResponseEntity<SubjectResDto> createMonHoc(@RequestBody CreateSubjectReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.createMonHoc(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResDto> getMonHoc(@PathVariable Long id) {
        return ResponseEntity.ok(subjectService.getMonHocById(id));
    }

    @GetMapping
    public ResponseEntity<List<SubjectResDto>> getAllMonHoc() {
        return ResponseEntity.ok(subjectService.getAllMonHoc());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResDto> updateMonHoc(@PathVariable Long id, @RequestBody CreateSubjectReq req) {
        return ResponseEntity.ok(subjectService.updateMonHoc(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonHoc(@PathVariable Long id) {
        subjectService.deleteMonHoc(id);
        return ResponseEntity.noContent().build();
    }
}
