package UniversityManagemant.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateKhoaReq;
import UniversityManagemant.demo.dtos.response.KhoaResDto;
import UniversityManagemant.demo.models.Khoa;
import UniversityManagemant.demo.repositories.KhoaRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class KhoaService {
    final KhoaRepository khoaRepository;

    public KhoaResDto createKhoa(CreateKhoaReq req) {
        Khoa khoa = Khoa.builder()
                .maKhoa(req.getMaKhoa())
                .tenKhoa(req.getTenKhoa())
                .build();
        Khoa saved = khoaRepository.save(khoa);
        return toDto(saved);
    }

    public KhoaResDto getKhoaById(Long id) {
        return khoaRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Khoa not found"));
    }

    public List<KhoaResDto> getAllKhoa() {
        return khoaRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public KhoaResDto updateKhoa(Long id, CreateKhoaReq req) {
        Khoa khoa = khoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khoa not found"));
        khoa.setMaKhoa(req.getMaKhoa());
        khoa.setTenKhoa(req.getTenKhoa());
        Khoa updated = khoaRepository.save(khoa);
        return toDto(updated);
    }

    public void deleteKhoa(Long id) {
        khoaRepository.deleteById(id);
    }

    private KhoaResDto toDto(Khoa khoa) {
        return KhoaResDto.builder()
                .id(khoa.getId())
                .maKhoa(khoa.getMaKhoa())
                .tenKhoa(khoa.getTenKhoa())
                .build();
    }
}
