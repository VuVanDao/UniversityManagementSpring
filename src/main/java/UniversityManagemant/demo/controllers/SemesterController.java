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
import UniversityManagemant.demo.services.serviceInterface.HocKiService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/semesters")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SemesterController {
    final HocKiService semesterService;

    @PostMapping
    public ResponseEntity<SemesterResDto> createHocKi(@RequestBody CreateSemesterReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(semesterService.createHocKi(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemesterResDto> getHocKi(@PathVariable Long id) {
        return ResponseEntity.ok(semesterService.getHocKiById(id));
    }

    @GetMapping
    public ResponseEntity<List<SemesterResDto>> getAllHocKi() {
        return ResponseEntity.ok(semesterService.getAllHocKi());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SemesterResDto> updateHocKi(@PathVariable Long id, @RequestBody CreateSemesterReq req) {
        return ResponseEntity.ok(semesterService.updateHocKi(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHocKi(@PathVariable Long id) {
        semesterService.deleteHocKi(id);
        return ResponseEntity.noContent().build();
    }
}
