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

import UniversityManagemant.demo.dtos.request.CreateLopQuanLiReq;
import UniversityManagemant.demo.dtos.response.LopQuanLiResDto;
import UniversityManagemant.demo.services.LopQuanLiService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/lop-quan-lis")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LopQuanLiController {
    final LopQuanLiService lopQuanLiService;

    @PostMapping
    public ResponseEntity<LopQuanLiResDto> createLopQuanLi(@RequestBody CreateLopQuanLiReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lopQuanLiService.createLopQuanLi(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LopQuanLiResDto> getLopQuanLi(@PathVariable Long id) {
        return ResponseEntity.ok(lopQuanLiService.getLopQuanLiById(id));
    }

    @GetMapping
    public ResponseEntity<List<LopQuanLiResDto>> getAllLopQuanLi() {
        return ResponseEntity.ok(lopQuanLiService.getAllLopQuanLi());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LopQuanLiResDto> updateLopQuanLi(@PathVariable Long id, @RequestBody CreateLopQuanLiReq req) {
        return ResponseEntity.ok(lopQuanLiService.updateLopQuanLi(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLopQuanLi(@PathVariable Long id) {
        lopQuanLiService.deleteLopQuanLi(id);
        return ResponseEntity.noContent().build();
    }
}
