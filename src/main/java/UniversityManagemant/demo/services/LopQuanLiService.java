package UniversityManagemant.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateLopQuanLiReq;
import UniversityManagemant.demo.dtos.response.LopQuanLiResDto;
import UniversityManagemant.demo.models.LopQuanLi;
import UniversityManagemant.demo.repositories.LopQuanLiRepository;
import UniversityManagemant.demo.repositories.ChuyenNganhRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LopQuanLiService {
    final LopQuanLiRepository lopQuanLiRepository;
    final ChuyenNganhRepository chuyenNganhRepository;

    public LopQuanLiResDto createLopQuanLi(CreateLopQuanLiReq req) {
        LopQuanLi lopQuanLi = LopQuanLi.builder()
                .maLop(req.getMaLop())
                .tenLop(req.getTenLop())
                .chuyenNganh(chuyenNganhRepository.findById(req.getChuyenNganhId()).orElseThrow())
                .build();
        LopQuanLi saved = lopQuanLiRepository.save(lopQuanLi);
        return toDto(saved);
    }

    public LopQuanLiResDto getLopQuanLiById(Long id) {
        return lopQuanLiRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("LopQuanLi not found"));
    }

    public List<LopQuanLiResDto> getAllLopQuanLi() {
        return lopQuanLiRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public LopQuanLiResDto updateLopQuanLi(Long id, CreateLopQuanLiReq req) {
        LopQuanLi lopQuanLi = lopQuanLiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LopQuanLi not found"));
        lopQuanLi.setMaLop(req.getMaLop());
        lopQuanLi.setTenLop(req.getTenLop());
        lopQuanLi.setChuyenNganh(chuyenNganhRepository.findById(req.getChuyenNganhId()).orElseThrow());
        LopQuanLi updated = lopQuanLiRepository.save(lopQuanLi);
        return toDto(updated);
    }

    public void deleteLopQuanLi(Long id) {
        lopQuanLiRepository.deleteById(id);
    }

    private LopQuanLiResDto toDto(LopQuanLi lopQuanLi) {
        return LopQuanLiResDto.builder()
                .id(lopQuanLi.getId())
                .maLop(lopQuanLi.getMaLop())
                .tenLop(lopQuanLi.getTenLop())
                .tenChuyenNganh(lopQuanLi.getChuyenNganh() != null ? lopQuanLi.getChuyenNganh().getTenChuyenNganh() : null)
                .build();
    }
}
