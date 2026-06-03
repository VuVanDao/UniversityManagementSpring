package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateGradeRecordReq;
import UniversityManagemant.demo.dtos.response.GradeRecordResDto;
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
    public GradeRecordResDto createBangDiem(CreateGradeRecordReq req) {
        GradeRecord bangDiem = GradeRecord.builder()
                .student(sinhVienRepository.findById(req.getStudentId()).orElseThrow())
                .subject(monHocRepository.findById(req.getSubjectId()).orElseThrow())
                .TenPointScale(req.getTenPointScale())
                .FourPointScale(req.getFourPointScale())
                .SubjectStatus(req.getSubjectStatus())
                .build();
        GradeRecord saved = bangDiemRepository.save(bangDiem);
        return toDto(saved);
    }

    @Override
    public GradeRecordResDto getBangDiemById(Long id) {
        return bangDiemRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("BangDiem not found"));
    }

    @Override
    public List<GradeRecordResDto> getAllBangDiem() {
        return bangDiemRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GradeRecordResDto updateBangDiem(Long id, CreateGradeRecordReq req) {
        GradeRecord bangDiem = bangDiemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BangDiem not found"));
        bangDiem.setStudent(sinhVienRepository.findById(req.getStudentId()).orElseThrow());
        bangDiem.setSubject(monHocRepository.findById(req.getSubjectId()).orElseThrow());
        bangDiem.setTenPointScale(req.getTenPointScale());
        bangDiem.setFourPointScale(req.getFourPointScale());
        bangDiem.setSubjectStatus(req.getSubjectStatus());
        GradeRecord updated = bangDiemRepository.save(bangDiem);
        return toDto(updated);
    }

    @Override
    public void deleteBangDiem(Long id) {
        bangDiemRepository.deleteById(id);
    }

    private GradeRecordResDto toDto(GradeRecord bangDiem) {
        return GradeRecordResDto.builder()
                .id(bangDiem.getId())
                .studentCode(bangDiem.getStudent() != null ? bangDiem.getStudent().getUser().getUserCode() : null)
                .userName(bangDiem.getStudent() != null && bangDiem.getStudent().getUser() != null
                    ? bangDiem.getStudent().getUser().getUsername() : null)
                .subjectName(bangDiem.getSubject() != null ? bangDiem.getSubject().getSubjectName() : null)
                .tenPointScale(bangDiem.getTenPointScale())
                .fourPointScale(bangDiem.getFourPointScale())
                .gradeLetter(bangDiem.getSubjectStatus())
                .subjectStatus(bangDiem.getSubjectStatus())
                .build();
    }
}
