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
import UniversityManagemant.demo.services.serviceInterface.ClassroomService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/classrooms")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ClassroomController {
    final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<ClassroomResDto> createClassroom(@RequestBody CreateClassroomReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classroomService.createClassroom(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomResDto> getClassroom(@PathVariable Long id) {
        return ResponseEntity.ok(classroomService.getClassroomById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassroomResDto>> getAllClassrooms() {
        return ResponseEntity.ok(classroomService.getAllClassrooms());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomResDto> updateClassroom(@PathVariable Long id, @RequestBody CreateClassroomReq req) {
        return ResponseEntity.ok(classroomService.updateClassroom(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.noContent().build();
    }
}
