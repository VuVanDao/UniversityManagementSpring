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
import UniversityManagemant.demo.services.serviceInterface.ChuyenNganhService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/majors")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MajorController {
    final ChuyenNganhService majorService;

    @PostMapping
    public ResponseEntity<MajorResDto> createChuyenNganh(@RequestBody CreateMajorReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(majorService.createChuyenNganh(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MajorResDto> getChuyenNganh(@PathVariable Long id) {
        return ResponseEntity.ok(majorService.getChuyenNganhById(id));
    }

    @GetMapping
    public ResponseEntity<List<MajorResDto>> getAllChuyenNganh() {
        return ResponseEntity.ok(majorService.getAllChuyenNganh());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MajorResDto> updateChuyenNganh(@PathVariable Long id, @RequestBody CreateMajorReq req) {
        return ResponseEntity.ok(majorService.updateChuyenNganh(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChuyenNganh(@PathVariable Long id) {
        majorService.deleteChuyenNganh(id);
        return ResponseEntity.noContent().build();
    }
}
