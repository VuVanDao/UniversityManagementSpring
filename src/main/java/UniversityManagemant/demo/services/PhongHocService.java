package UniversityManagemant.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreatePhongHocReq;
import UniversityManagemant.demo.dtos.response.PhongHocResDto;
import UniversityManagemant.demo.models.PhongHoc;
import UniversityManagemant.demo.repositories.PhongHocRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PhongHocService {
    final PhongHocRepository phongHocRepository;

    public PhongHocResDto createPhongHoc(CreatePhongHocReq req) {
        PhongHoc phongHoc = PhongHoc.builder()
                .tenPhongHoc(req.getTenPhongHoc())
                .build();
        PhongHoc saved = phongHocRepository.save(phongHoc);
        return toDto(saved);
    }

    public PhongHocResDto getPhongHocById(Long id) {
        return phongHocRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("PhongHoc not found"));
    }

    public List<PhongHocResDto> getAllPhongHoc() {
        return phongHocRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public PhongHocResDto updatePhongHoc(Long id, CreatePhongHocReq req) {
        PhongHoc phongHoc = phongHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PhongHoc not found"));
        phongHoc.setTenPhongHoc(req.getTenPhongHoc());
        PhongHoc updated = phongHocRepository.save(phongHoc);
        return toDto(updated);
    }

    public void deletePhongHoc(Long id) {
        phongHocRepository.deleteById(id);
    }

    private PhongHocResDto toDto(PhongHoc phongHoc) {
        return PhongHocResDto.builder()
                .id(phongHoc.getId())
                .tenPhongHoc(phongHoc.getTenPhongHoc())
                .build();
    }
}
