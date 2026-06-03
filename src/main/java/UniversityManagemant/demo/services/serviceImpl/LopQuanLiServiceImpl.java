package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateLopQuanLiReq;
import UniversityManagemant.demo.dtos.response.LopQuanLiResDto;
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
    public LopQuanLiResDto createLopQuanLi(CreateLopQuanLiReq req) {
        ClassManagement lopQuanLi = ClassManagement.builder()
                .maLop(req.getMaLop())
                .tenLop(req.getTenLop())
                .chuyenNganh(chuyenNganhRepository.findById(req.getChuyenNganhId()).orElseThrow())
                .build();
        ClassManagement saved = lopQuanLiRepository.save(lopQuanLi);
        return toDto(saved);
    }

    @Override
    public LopQuanLiResDto getLopQuanLiById(Long id) {
        return lopQuanLiRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("LopQuanLi not found"));
    }

    @Override
    public List<LopQuanLiResDto> getAllLopQuanLi() {
        return lopQuanLiRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LopQuanLiResDto updateLopQuanLi(Long id, CreateLopQuanLiReq req) {
        ClassManagement lopQuanLi = lopQuanLiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LopQuanLi not found"));
        lopQuanLi.setMaLop(req.getMaLop());
        lopQuanLi.setTenLop(req.getTenLop());
        lopQuanLi.setChuyenNganh(chuyenNganhRepository.findById(req.getChuyenNganhId()).orElseThrow());
        ClassManagement updated = lopQuanLiRepository.save(lopQuanLi);
        return toDto(updated);
    }

    @Override
    public void deleteLopQuanLi(Long id) {
        lopQuanLiRepository.deleteById(id);
    }

    private LopQuanLiResDto toDto(ClassManagement lopQuanLi) {
        return LopQuanLiResDto.builder()
                .id(lopQuanLi.getId())
                .maLop(lopQuanLi.getMaLop())
                .tenLop(lopQuanLi.getTenLop())
                .tenChuyenNganh(lopQuanLi.getChuyenNganh() != null ? lopQuanLi.getChuyenNganh().getTenChuyenNganh() : null)
                .build();
    }
}
