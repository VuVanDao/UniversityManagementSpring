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
import UniversityManagemant.demo.dtos.request.CreateNhomHocReq;
import UniversityManagemant.demo.dtos.response.NhomHocResDto;
import UniversityManagemant.demo.services.NhomHocService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/nhom-hocs")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class NhomHocController {
    final NhomHocService nhomHocService;

    @PostMapping
    public ResponseEntity<NhomHocResDto> createNhomHoc(@RequestBody CreateNhomHocReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(nhomHocService.createNhomHoc(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NhomHocResDto> getNhomHoc(@PathVariable Long id) {
        return ResponseEntity.ok(nhomHocService.getNhomHocById(id));
    }

    @GetMapping
    public ResponseEntity<List<NhomHocResDto>> getAllNhomHoc() {
        return ResponseEntity.ok(nhomHocService.getAllNhomHoc());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NhomHocResDto> updateNhomHoc(@PathVariable Long id, @RequestBody CreateNhomHocReq req) {
        return ResponseEntity.ok(nhomHocService.updateNhomHoc(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNhomHoc(@PathVariable Long id) {
        nhomHocService.deleteNhomHoc(id);
        return ResponseEntity.noContent().build();
    }
}
