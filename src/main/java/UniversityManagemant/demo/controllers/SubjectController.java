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
import UniversityManagemant.demo.services.serviceInterface.SubjectService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/subjects")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SubjectController {
    final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectResDto> createSubject(@RequestBody CreateSubjectReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.createSubject(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResDto> getSubject(@PathVariable Long id) {
        return ResponseEntity.ok(subjectService.getSubjectById(id));
    }

    @GetMapping
    public ResponseEntity<List<SubjectResDto>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResDto> updateSubject(@PathVariable Long id, @RequestBody CreateSubjectReq req) {
        return ResponseEntity.ok(subjectService.updateSubject(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }
}
