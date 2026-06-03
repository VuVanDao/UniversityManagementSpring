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

import UniversityManagemant.demo.dtos.request.CreateSemesterReq;
import UniversityManagemant.demo.dtos.response.SemesterResDto;
import UniversityManagemant.demo.services.serviceInterface.SemesterService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/semesters")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SemesterController {
    final SemesterService semesterService;

    @PostMapping
    public ResponseEntity<SemesterResDto> createSemester(@RequestBody CreateSemesterReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(semesterService.createSemester(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemesterResDto> getSemester(@PathVariable Long id) {
        return ResponseEntity.ok(semesterService.getSemesterById(id));
    }

    @GetMapping
    public ResponseEntity<List<SemesterResDto>> getAllSemesters() {
        return ResponseEntity.ok(semesterService.getAllSemesters());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SemesterResDto> updateSemester(@PathVariable Long id, @RequestBody CreateSemesterReq req) {
        return ResponseEntity.ok(semesterService.updateSemester(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSemester(@PathVariable Long id) {
        semesterService.deleteSemester(id);
        return ResponseEntity.noContent().build();
    }
}
