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

import UniversityManagemant.demo.dtos.request.CreateLecturerReq;
import UniversityManagemant.demo.dtos.response.LecturerResDto;
import UniversityManagemant.demo.services.serviceInterface.LecturerService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/lecturers")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LecturerController {
    final LecturerService lecturerService;

    @PostMapping
    public ResponseEntity<LecturerResDto> createLecturer(@RequestBody CreateLecturerReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lecturerService.createLecturer(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LecturerResDto> getLecturer(@PathVariable Long id) {
        return ResponseEntity.ok(lecturerService.getLecturerById(id));
    }

    @GetMapping
    public ResponseEntity<List<LecturerResDto>> getAllLecturers() {
        return ResponseEntity.ok(lecturerService.getAllLecturers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LecturerResDto> updateLecturer(@PathVariable Long id, @RequestBody CreateLecturerReq req) {
        return ResponseEntity.ok(lecturerService.updateLecturer(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLecturer(@PathVariable Long id) {
        lecturerService.deleteLecturer(id);
        return ResponseEntity.noContent().build();
    }
}
