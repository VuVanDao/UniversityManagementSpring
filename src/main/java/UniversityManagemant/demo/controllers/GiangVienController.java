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

import UniversityManagemant.demo.dtos.request.CreateLecturerReq;
import UniversityManagemant.demo.dtos.response.LecturerResDto;
import UniversityManagemant.demo.services.serviceInterface.GiangVienService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/giang-viens")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GiangVienController {
    final GiangVienService giangVienService;

    @PostMapping
    public ResponseEntity<LecturerResDto> createGiangVien(@RequestBody CreateLecturerReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(giangVienService.createGiangVien(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LecturerResDto> getGiangVien(@PathVariable Long id) {
        return ResponseEntity.ok(giangVienService.getGiangVienById(id));
    }

    @GetMapping
    public ResponseEntity<List<LecturerResDto>> getAllGiangVien() {
        return ResponseEntity.ok(giangVienService.getAllGiangVien());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LecturerResDto> updateGiangVien(@PathVariable Long id, @RequestBody CreateLecturerReq req) {
        return ResponseEntity.ok(giangVienService.updateGiangVien(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGiangVien(@PathVariable Long id) {
        giangVienService.deleteGiangVien(id);
        return ResponseEntity.noContent().build();
    }
}
