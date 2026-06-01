package UniversityManagemant.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateMonHocReq;
import UniversityManagemant.demo.dtos.response.MonHocResDto;
import UniversityManagemant.demo.models.MonHoc;
import UniversityManagemant.demo.repositories.MonHocRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MonHocService {
    final MonHocRepository monHocRepository;

    public MonHocResDto createMonHoc(CreateMonHocReq req) {
        MonHoc monHoc = MonHoc.builder()
                .maMonHoc(req.getMaMonHoc())
                .tenMonHoc(req.getTenMonHoc())
                .tinChi(req.getTinChi())
                .build();
        MonHoc saved = monHocRepository.save(monHoc);
        return toDto(saved);
    }

    public MonHocResDto getMonHocById(Long id) {
        return monHocRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("MonHoc not found"));
    }

    public List<MonHocResDto> getAllMonHoc() {
        return monHocRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public MonHocResDto updateMonHoc(Long id, CreateMonHocReq req) {
        MonHoc monHoc = monHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MonHoc not found"));
        monHoc.setMaMonHoc(req.getMaMonHoc());
        monHoc.setTenMonHoc(req.getTenMonHoc());
        monHoc.setTinChi(req.getTinChi());
        MonHoc updated = monHocRepository.save(monHoc);
        return toDto(updated);
    }

    public void deleteMonHoc(Long id) {
        monHocRepository.deleteById(id);
    }

    private MonHocResDto toDto(MonHoc monHoc) {
        return MonHocResDto.builder()
                .id(monHoc.getId())
                .maMonHoc(monHoc.getMaMonHoc())
                .tenMonHoc(monHoc.getTenMonHoc())
                .tinChi(monHoc.getTinChi())
                .build();
    }
}
