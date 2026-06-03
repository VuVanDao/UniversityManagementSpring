package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateChuyenNganhReq;
import UniversityManagemant.demo.dtos.response.ChuyenNganhResDto;
import UniversityManagemant.demo.models.Major;
import UniversityManagemant.demo.repositories.ChuyenNganhRepository;
import UniversityManagemant.demo.repositories.KhoaRepository;
import UniversityManagemant.demo.services.serviceInterface.ChuyenNganhService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ChuyenNganhServiceImpl implements ChuyenNganhService {
    final ChuyenNganhRepository chuyenNganhRepository;
    final KhoaRepository khoaRepository;

    @Override
    public ChuyenNganhResDto createChuyenNganh(CreateChuyenNganhReq req) {
        Major chuyenNganh = Major.builder()
                .MajorCode(req.getMaChuyenNganh())
                .MajorName(req.getTenChuyenNganh())
                .faculty(khoaRepository.findById(req.getKhoaId()).orElseThrow())
                .build();
        Major saved = chuyenNganhRepository.save(chuyenNganh);
        return toDto(saved);
    }

    @Override
    public ChuyenNganhResDto getChuyenNganhById(Long id) {
        return chuyenNganhRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("ChuyenNganh not found"));
    }

    @Override
    public List<ChuyenNganhResDto> getAllChuyenNganh() {
        return chuyenNganhRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ChuyenNganhResDto updateChuyenNganh(Long id, CreateChuyenNganhReq req) {
        Major chuyenNganh = chuyenNganhRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ChuyenNganh not found"));
        chuyenNganh.setMajorCode(req.getMaChuyenNganh());
        chuyenNganh.setMajorName(req.getTenChuyenNganh());
        chuyenNganh.setFaculty(khoaRepository.findById(req.getKhoaId()).orElseThrow());
        Major updated = chuyenNganhRepository.save(chuyenNganh);
        return toDto(updated);
    }

    @Override
    public void deleteChuyenNganh(Long id) {
        chuyenNganhRepository.deleteById(id);
    }

    private ChuyenNganhResDto toDto(Major chuyenNganh) {
        return ChuyenNganhResDto.builder()
                .id(chuyenNganh.getId())
                .maChuyenNganh(chuyenNganh.getMajorCode())
                .tenChuyenNganh(chuyenNganh.getMajorName())
                .tenKhoa(chuyenNganh.getFaculty() != null ? chuyenNganh.getFaculty().getFacultyName() : null)
                .build();
    }
}
