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

import UniversityManagemant.demo.dtos.request.CreateGiangVienReq;
import UniversityManagemant.demo.dtos.response.GiangVienResDto;
import UniversityManagemant.demo.services.GiangVienService;
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
    public ResponseEntity<GiangVienResDto> createGiangVien(@RequestBody CreateGiangVienReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(giangVienService.createGiangVien(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiangVienResDto> getGiangVien(@PathVariable Long id) {
        return ResponseEntity.ok(giangVienService.getGiangVienById(id));
    }

    @GetMapping
    public ResponseEntity<List<GiangVienResDto>> getAllGiangVien() {
        return ResponseEntity.ok(giangVienService.getAllGiangVien());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GiangVienResDto> updateGiangVien(@PathVariable Long id, @RequestBody CreateGiangVienReq req) {
        return ResponseEntity.ok(giangVienService.updateGiangVien(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGiangVien(@PathVariable Long id) {
        giangVienService.deleteGiangVien(id);
        return ResponseEntity.noContent().build();
    }
}
