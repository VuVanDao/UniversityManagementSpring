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

import UniversityManagemant.demo.dtos.request.CreateMonHocReq;
import UniversityManagemant.demo.dtos.response.MonHocResDto;
import UniversityManagemant.demo.services.serviceInterface.MonHocService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/mon-hocs")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MonHocController {
    final MonHocService monHocService;

    @PostMapping
    public ResponseEntity<MonHocResDto> createMonHoc(@RequestBody CreateMonHocReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(monHocService.createMonHoc(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonHocResDto> getMonHoc(@PathVariable Long id) {
        return ResponseEntity.ok(monHocService.getMonHocById(id));
    }

    @GetMapping
    public ResponseEntity<List<MonHocResDto>> getAllMonHoc() {
        return ResponseEntity.ok(monHocService.getAllMonHoc());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonHocResDto> updateMonHoc(@PathVariable Long id, @RequestBody CreateMonHocReq req) {
        return ResponseEntity.ok(monHocService.updateMonHoc(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonHoc(@PathVariable Long id) {
        monHocService.deleteMonHoc(id);
        return ResponseEntity.noContent().build();
    }
}
