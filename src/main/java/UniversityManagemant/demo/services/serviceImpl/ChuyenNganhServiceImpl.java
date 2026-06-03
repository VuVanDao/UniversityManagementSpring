package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateMajorReq;
import UniversityManagemant.demo.dtos.response.MajorResDto;
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
    public MajorResDto createChuyenNganh(CreateMajorReq req) {
        Major chuyenNganh = Major.builder()
                .MajorCode(req.getMajorCode())
                .MajorName(req.getMajorName())
                .faculty(khoaRepository.findById(req.getFacultyId()).orElseThrow())
                .build();
        Major saved = chuyenNganhRepository.save(chuyenNganh);
        return toDto(saved);
    }

    @Override
    public MajorResDto getChuyenNganhById(Long id) {
        return chuyenNganhRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("ChuyenNganh not found"));
    }

    @Override
    public List<MajorResDto> getAllChuyenNganh() {
        return chuyenNganhRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MajorResDto updateChuyenNganh(Long id, CreateMajorReq req) {
        Major chuyenNganh = chuyenNganhRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ChuyenNganh not found"));
        chuyenNganh.setMajorCode(req.getMajorCode());
        chuyenNganh.setMajorName(req.getMajorName());
        chuyenNganh.setFaculty(khoaRepository.findById(req.getFacultyId()).orElseThrow());
        Major updated = chuyenNganhRepository.save(chuyenNganh);
        return toDto(updated);
    }

    @Override
    public void deleteChuyenNganh(Long id) {
        chuyenNganhRepository.deleteById(id);
    }

    private MajorResDto toDto(Major chuyenNganh) {
        return MajorResDto.builder()
                .id(chuyenNganh.getId())
                .majorCode(chuyenNganh.getMajorCode())
                .majorName(chuyenNganh.getMajorName())
                .facultyName(chuyenNganh.getFaculty() != null ? chuyenNganh.getFaculty().getFacultyName() : null)
                .build();
    }
}
