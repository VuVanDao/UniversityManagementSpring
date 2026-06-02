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

import UniversityManagemant.demo.dtos.request.CreateChuyenNganhReq;
import UniversityManagemant.demo.dtos.response.ChuyenNganhResDto;
import UniversityManagemant.demo.services.serviceInterface.ChuyenNganhService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/chuyen-nganhs")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ChuyenNganhController {
    final ChuyenNganhService chuyenNganhService;

    @PostMapping
    public ResponseEntity<ChuyenNganhResDto> createChuyenNganh(@RequestBody CreateChuyenNganhReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chuyenNganhService.createChuyenNganh(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChuyenNganhResDto> getChuyenNganh(@PathVariable Long id) {
        return ResponseEntity.ok(chuyenNganhService.getChuyenNganhById(id));
    }

    @GetMapping
    public ResponseEntity<List<ChuyenNganhResDto>> getAllChuyenNganh() {
        return ResponseEntity.ok(chuyenNganhService.getAllChuyenNganh());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChuyenNganhResDto> updateChuyenNganh(@PathVariable Long id, @RequestBody CreateChuyenNganhReq req) {
        return ResponseEntity.ok(chuyenNganhService.updateChuyenNganh(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChuyenNganh(@PathVariable Long id) {
        chuyenNganhService.deleteChuyenNganh(id);
        return ResponseEntity.noContent().build();
    }
}
