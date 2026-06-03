package UniversityManagemant.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UniversityManagemant.demo.dtos.request.CreateFacultyReq;
import UniversityManagemant.demo.dtos.request.UpdateFacultyReq;
import UniversityManagemant.demo.dtos.response.FacultyResDto;
import UniversityManagemant.demo.services.serviceInterface.FacultyService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/faculties")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FacultyController {
    final FacultyService facultyService;

    @PostMapping
    public ResponseEntity<FacultyResDto> createFaculty(@RequestBody CreateFacultyReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(facultyService.createFaculty(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyResDto> getFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.getFacultyById(id));
    }

    @GetMapping
    public ResponseEntity<List<FacultyResDto>> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FacultyResDto> updateFaculty(@PathVariable Long id, @RequestBody UpdateFacultyReq req) {
        return ResponseEntity.ok(facultyService.updateFaculty(id, req));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }
}
