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

import UniversityManagemant.demo.dtos.request.CreateHocKiReq;
import UniversityManagemant.demo.dtos.response.HocKiResDto;
import UniversityManagemant.demo.services.serviceInterface.HocKiService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/hoc-kis")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class HocKiController {
    final HocKiService hocKiService;

    @PostMapping
    public ResponseEntity<HocKiResDto> createHocKi(@RequestBody CreateHocKiReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hocKiService.createHocKi(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HocKiResDto> getHocKi(@PathVariable Long id) {
        return ResponseEntity.ok(hocKiService.getHocKiById(id));
    }

    @GetMapping
    public ResponseEntity<List<HocKiResDto>> getAllHocKi() {
        return ResponseEntity.ok(hocKiService.getAllHocKi());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HocKiResDto> updateHocKi(@PathVariable Long id, @RequestBody CreateHocKiReq req) {
        return ResponseEntity.ok(hocKiService.updateHocKi(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHocKi(@PathVariable Long id) {
        hocKiService.deleteHocKi(id);
        return ResponseEntity.noContent().build();
    }
}
