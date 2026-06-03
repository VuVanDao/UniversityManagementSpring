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

import UniversityManagemant.demo.dtos.request.CreateMajorReq;
import UniversityManagemant.demo.dtos.response.MajorResDto;
import UniversityManagemant.demo.services.serviceInterface.MajorService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/majors")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MajorController {
    final MajorService majorService;

    @PostMapping
    public ResponseEntity<MajorResDto> createMajor(@RequestBody CreateMajorReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(majorService.createMajor(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MajorResDto> getMajor(@PathVariable Long id) {
        return ResponseEntity.ok(majorService.getMajorById(id));
    }

    @GetMapping
    public ResponseEntity<List<MajorResDto>> getAllMajors() {
        return ResponseEntity.ok(majorService.getAllMajors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MajorResDto> updateMajor(@PathVariable Long id, @RequestBody CreateMajorReq req) {
        return ResponseEntity.ok(majorService.updateMajor(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMajor(@PathVariable Long id) {
        majorService.deleteMajor(id);
        return ResponseEntity.noContent().build();
    }
}
