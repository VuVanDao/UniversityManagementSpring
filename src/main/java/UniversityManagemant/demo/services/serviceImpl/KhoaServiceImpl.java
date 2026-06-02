package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateKhoaReq;
import UniversityManagemant.demo.dtos.response.KhoaResDto;
import UniversityManagemant.demo.mappers.KhoaMapper;
import UniversityManagemant.demo.models.Khoa;
import UniversityManagemant.demo.repositories.KhoaRepository;
import UniversityManagemant.demo.services.serviceInterface.KhoaService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class KhoaServiceImpl implements KhoaService {
    final KhoaRepository khoaRepository;
    final KhoaMapper khoaMapper;

    @Override
    public KhoaResDto createKhoa(CreateKhoaReq req) {
        Khoa khoa = khoaMapper.toEntity(req);
        Khoa saved = khoaRepository.save(khoa);
        return khoaMapper.toResDto(saved);
    }

    @Override
    public KhoaResDto getKhoaById(Long id) {
        return khoaRepository.findById(id)
                .map(khoaMapper::toResDto)
                .orElseThrow(() -> new RuntimeException("Khoa not found"));
    }

    @Override
    public List<KhoaResDto> getAllKhoa() {
        return khoaRepository.findAll().stream()
                .map(khoaMapper::toResDto)
                .collect(Collectors.toList());
    }

    @Override
    public KhoaResDto updateKhoa(Long id, CreateKhoaReq req) {
        Khoa khoa = khoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khoa not found"));
        khoaMapper.updateEntityFromDto(req, khoa);
        Khoa updated = khoaRepository.save(khoa);
        return khoaMapper.toResDto(updated);
    }

    @Override
    public void deleteKhoa(Long id) {
        khoaRepository.deleteById(id);
    }
}
