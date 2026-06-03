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
import UniversityManagemant.demo.services.serviceInterface.BangDiemService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/bang-diems")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BangDiemController {
    final BangDiemService bangDiemService;

    @PostMapping
    public ResponseEntity<GradeRecordResDto> createBangDiem(@RequestBody CreateGradeRecordReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bangDiemService.createBangDiem(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeRecordResDto> getBangDiem(@PathVariable Long id) {
        return ResponseEntity.ok(bangDiemService.getBangDiemById(id));
    }

    @GetMapping
    public ResponseEntity<List<GradeRecordResDto>> getAllBangDiem() {
        return ResponseEntity.ok(bangDiemService.getAllBangDiem());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeRecordResDto> updateBangDiem(@PathVariable Long id, @RequestBody CreateGradeRecordReq req) {
        return ResponseEntity.ok(bangDiemService.updateBangDiem(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBangDiem(@PathVariable Long id) {
        bangDiemService.deleteBangDiem(id);
        return ResponseEntity.noContent().build();
    }
}
