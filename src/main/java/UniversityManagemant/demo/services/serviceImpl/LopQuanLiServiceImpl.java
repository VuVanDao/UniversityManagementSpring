package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateClassManagementReq;
import UniversityManagemant.demo.dtos.response.ClassManagementResDto;
import UniversityManagemant.demo.models.ClassManagement;
import UniversityManagemant.demo.repositories.LopQuanLiRepository;
import UniversityManagemant.demo.repositories.ChuyenNganhRepository;
import UniversityManagemant.demo.services.serviceInterface.LopQuanLiService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LopQuanLiServiceImpl implements LopQuanLiService {
    final LopQuanLiRepository lopQuanLiRepository;
    final ChuyenNganhRepository chuyenNganhRepository;

    @Override
    public ClassManagementResDto createLopQuanLi(CreateClassManagementReq req) {
        ClassManagement lopQuanLi = ClassManagement.builder()
                .classManagementCode(req.getClassManagementCode())
                .classManagementName(req.getClassManagementName())
                .major(chuyenNganhRepository.findById(req.getMajorId()).orElseThrow())
                .build();
        ClassManagement saved = lopQuanLiRepository.save(lopQuanLi);
        return toDto(saved);
    }

    @Override
    public ClassManagementResDto getLopQuanLiById(Long id) {
        return lopQuanLiRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("LopQuanLi not found"));
    }

    @Override
    public List<ClassManagementResDto> getAllLopQuanLi() {
        return lopQuanLiRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClassManagementResDto updateLopQuanLi(Long id, CreateClassManagementReq req) {
        ClassManagement lopQuanLi = lopQuanLiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LopQuanLi not found"));
        lopQuanLi.setClassManagementCode(req.getClassManagementCode());
        lopQuanLi.setClassManagementName(req.getClassManagementName());
        lopQuanLi.setMajor(chuyenNganhRepository.findById(req.getMajorId()).orElseThrow());
        ClassManagement updated = lopQuanLiRepository.save(lopQuanLi);
        return toDto(updated);
    }

    @Override
    public void deleteLopQuanLi(Long id) {
        lopQuanLiRepository.deleteById(id);
    }

    private ClassManagementResDto toDto(ClassManagement lopQuanLi) {
        return ClassManagementResDto.builder()
                .id(lopQuanLi.getId())
                .classManagementCode(lopQuanLi.getClassManagementCode())
                .classManagementName(lopQuanLi.getClassManagementName())
                .majorName(lopQuanLi.getMajor() != null ? lopQuanLi.getMajor().getMajorName() : null)
                .build();
    }
}
