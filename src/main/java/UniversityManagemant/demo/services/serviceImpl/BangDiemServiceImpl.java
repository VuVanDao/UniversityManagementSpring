package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateBangDiemReq;
import UniversityManagemant.demo.dtos.response.BangDiemResDto;
import UniversityManagemant.demo.models.GradeRecord;
import UniversityManagemant.demo.repositories.BangDiemRepository;
import UniversityManagemant.demo.repositories.SinhVienRepository;
import UniversityManagemant.demo.repositories.MonHocRepository;
import UniversityManagemant.demo.services.serviceInterface.BangDiemService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BangDiemServiceImpl implements BangDiemService {
    final BangDiemRepository bangDiemRepository;
    final SinhVienRepository sinhVienRepository;
    final MonHocRepository monHocRepository;

    @Override
    public BangDiemResDto createBangDiem(CreateBangDiemReq req) {
        GradeRecord bangDiem = GradeRecord.builder()
                .student(sinhVienRepository.findById(req.getSinhVienId()).orElseThrow())
                .subject(monHocRepository.findById(req.getMonHocId()).orElseThrow())
                .TenPointScale(req.getDiemHe10())
                .FourPointScale(req.getDiemHe4())
                .SubjectStatus(req.getTrangThaiMonHoc())
                .build();
        GradeRecord saved = bangDiemRepository.save(bangDiem);
        return toDto(saved);
    }

    @Override
    public BangDiemResDto getBangDiemById(Long id) {
        return bangDiemRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("BangDiem not found"));
    }

    @Override
    public List<BangDiemResDto> getAllBangDiem() {
        return bangDiemRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BangDiemResDto updateBangDiem(Long id, CreateBangDiemReq req) {
        GradeRecord bangDiem = bangDiemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BangDiem not found"));
        bangDiem.setStudent(sinhVienRepository.findById(req.getSinhVienId()).orElseThrow());
        bangDiem.setSubject(monHocRepository.findById(req.getMonHocId()).orElseThrow());
        bangDiem.setTenPointScale(req.getDiemHe10());
        bangDiem.setFourPointScale(req.getDiemHe4());
        bangDiem.setSubjectStatus(req.getTrangThaiMonHoc());
        GradeRecord updated = bangDiemRepository.save(bangDiem);
        return toDto(updated);
    }

    @Override
    public void deleteBangDiem(Long id) {
        bangDiemRepository.deleteById(id);
    }

    private BangDiemResDto toDto(GradeRecord bangDiem) {
        return BangDiemResDto.builder()
                .id(bangDiem.getId())
                .maSinhVien(bangDiem.getStudent() != null ? bangDiem.getStudent().getUser().getUserCode() : null)
                .tenNguoiDung(bangDiem.getStudent() != null && bangDiem.getStudent().getUser() != null
                    ? bangDiem.getStudent().getUser().getUsername() : null)
                .tenMonHoc(bangDiem.getSubject() != null ? bangDiem.getSubject().getSubjectName() : null)
                .diemHe10(bangDiem.getTenPointScale())
                .diemHe4(bangDiem.getFourPointScale())
                .diemChu(bangDiem.getSubjectStatus())
                .trangThaiMonHoc(bangDiem.getSubjectStatus())
                .build();
    }
}
