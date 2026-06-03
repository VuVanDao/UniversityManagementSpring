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

import UniversityManagemant.demo.dtos.request.CreateGradeRecordReq;
import UniversityManagemant.demo.dtos.response.GradeRecordResDto;
import UniversityManagemant.demo.services.serviceInterface.GradeRecordService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/grade-records")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GradeRecordController {
    final GradeRecordService gradeRecordService;

    @PostMapping
    public ResponseEntity<GradeRecordResDto> createGradeRecord(@RequestBody CreateGradeRecordReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeRecordService.createGradeRecord(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeRecordResDto> getGradeRecord(@PathVariable Long id) {
        return ResponseEntity.ok(gradeRecordService.getGradeRecordById(id));
    }

    @GetMapping
    public ResponseEntity<List<GradeRecordResDto>> getAllGradeRecords() {
        return ResponseEntity.ok(gradeRecordService.getAllGradeRecords());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeRecordResDto> updateGradeRecord(@PathVariable Long id, @RequestBody CreateGradeRecordReq req) {
        return ResponseEntity.ok(gradeRecordService.updateGradeRecord(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGradeRecord(@PathVariable Long id) {
        gradeRecordService.deleteGradeRecord(id);
        return ResponseEntity.noContent().build();
    }
}
