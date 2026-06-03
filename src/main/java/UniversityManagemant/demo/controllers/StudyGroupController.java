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
import UniversityManagemant.demo.dtos.request.CreateStudyGroupReq;
import UniversityManagemant.demo.dtos.response.StudyGroupResDto;
import UniversityManagemant.demo.services.serviceInterface.NhomHocService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/study-groups")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class StudyGroupController {
    final NhomHocService studyGroupService;

    @PostMapping
    public ResponseEntity<StudyGroupResDto> createNhomHoc(@RequestBody CreateStudyGroupReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studyGroupService.createNhomHoc(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyGroupResDto> getNhomHoc(@PathVariable Long id) {
        return ResponseEntity.ok(studyGroupService.getNhomHocById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudyGroupResDto>> getAllNhomHoc() {
        return ResponseEntity.ok(studyGroupService.getAllNhomHoc());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudyGroupResDto> updateNhomHoc(@PathVariable Long id, @RequestBody CreateStudyGroupReq req) {
        return ResponseEntity.ok(studyGroupService.updateNhomHoc(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNhomHoc(@PathVariable Long id) {
        studyGroupService.deleteNhomHoc(id);
        return ResponseEntity.noContent().build();
    }
}
