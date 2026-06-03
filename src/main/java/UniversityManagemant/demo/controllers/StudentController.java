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

import UniversityManagemant.demo.dtos.request.CreateStudentReq;
import UniversityManagemant.demo.dtos.response.StudentResDto;
import UniversityManagemant.demo.services.serviceInterface.SinhVienService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/students")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class StudentController {
    final SinhVienService studentService;

    @PostMapping
    public ResponseEntity<StudentResDto> createSinhVien(@RequestBody CreateStudentReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createSinhVien(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResDto> getSinhVien(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getSinhVienById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentResDto>> getAllSinhVien() {
        return ResponseEntity.ok(studentService.getAllSinhVien());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResDto> updateSinhVien(@PathVariable Long id, @RequestBody CreateStudentReq req) {
        return ResponseEntity.ok(studentService.updateSinhVien(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSinhVien(@PathVariable Long id) {
        studentService.deleteSinhVien(id);
        return ResponseEntity.noContent().build();
    }
}
