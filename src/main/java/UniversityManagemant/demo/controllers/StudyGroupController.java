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
import UniversityManagemant.demo.services.serviceInterface.StudyGroupService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/study-groups")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class StudyGroupController {
    final StudyGroupService studyGroupService;

    @PostMapping
    public ResponseEntity<StudyGroupResDto> createStudyGroup(@RequestBody CreateStudyGroupReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studyGroupService.createStudyGroup(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyGroupResDto> getStudyGroup(@PathVariable Long id) {
        return ResponseEntity.ok(studyGroupService.getStudyGroupById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudyGroupResDto>> getAllStudyGroups() {
        return ResponseEntity.ok(studyGroupService.getAllStudyGroups());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudyGroupResDto> updateStudyGroup(@PathVariable Long id, @RequestBody CreateStudyGroupReq req) {
        return ResponseEntity.ok(studyGroupService.updateStudyGroup(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudyGroup(@PathVariable Long id) {
        studyGroupService.deleteStudyGroup(id);
        return ResponseEntity.noContent().build();
    }
}
