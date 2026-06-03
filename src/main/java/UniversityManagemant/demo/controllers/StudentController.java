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
import UniversityManagemant.demo.services.serviceInterface.StudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/students")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class StudentController {
    final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResDto> createStudent(@RequestBody CreateStudentReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResDto> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentResDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResDto> updateStudent(@PathVariable Long id, @RequestBody CreateStudentReq req) {
        return ResponseEntity.ok(studentService.updateStudent(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
