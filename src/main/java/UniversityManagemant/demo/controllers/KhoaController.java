package UniversityManagemant.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UniversityManagemant.demo.dtos.request.CreateKhoaReq;
import UniversityManagemant.demo.dtos.request.UpdateKhoaReq;
import UniversityManagemant.demo.dtos.response.KhoaResDto;
import UniversityManagemant.demo.services.serviceInterface.KhoaService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/khoa")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class KhoaController {
    final KhoaService khoaService;

    @PostMapping
    public ResponseEntity<KhoaResDto> createKhoa(@RequestBody CreateKhoaReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(khoaService.createKhoa(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KhoaResDto> getKhoa(@PathVariable Long id) {
        return ResponseEntity.ok(khoaService.getKhoaById(id));
    }

    @GetMapping
    public ResponseEntity<List<KhoaResDto>> getAllKhoa() {
        return ResponseEntity.ok(khoaService.getAllKhoa());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KhoaResDto> updateKhoa(@PathVariable Long id, @RequestBody UpdateKhoaReq req) {
        return ResponseEntity.ok(khoaService.updateKhoa(id, req));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteKhoa(@PathVariable Long id) {
        khoaService.deleteKhoa(id);
        return ResponseEntity.noContent().build();
    }
}
