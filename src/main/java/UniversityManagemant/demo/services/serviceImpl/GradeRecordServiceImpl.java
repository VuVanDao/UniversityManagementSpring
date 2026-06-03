package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateGradeRecordReq;
import UniversityManagemant.demo.dtos.response.GradeRecordResDto;
import UniversityManagemant.demo.models.GradeRecord;
import UniversityManagemant.demo.repositories.GradeRecordRepository;
import UniversityManagemant.demo.repositories.StudentRepository;
import UniversityManagemant.demo.repositories.SubjectRepository;
import UniversityManagemant.demo.services.serviceInterface.GradeRecordService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GradeRecordServiceImpl implements GradeRecordService {
    final GradeRecordRepository gradeRecordRepository;
    final StudentRepository studentRepository;
    final SubjectRepository subjectRepository;

    @Override
    public GradeRecordResDto createGradeRecord(CreateGradeRecordReq req) {
        GradeRecord gradeRecord = GradeRecord.builder()
                .student(studentRepository.findById(req.getStudentId()).orElseThrow())
                .subject(subjectRepository.findById(req.getSubjectId()).orElseThrow())
                .TenPointScale(req.getTenPointScale())
                .FourPointScale(req.getFourPointScale())
                .SubjectStatus(req.getSubjectStatus())
                .build();
        GradeRecord saved = gradeRecordRepository.save(gradeRecord);
        return toDto(saved);
    }

    @Override
    public GradeRecordResDto getGradeRecordById(Long id) {
        return gradeRecordRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("GradeRecord not found"));
    }

    @Override
    public List<GradeRecordResDto> getAllGradeRecords() {
        return gradeRecordRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GradeRecordResDto updateGradeRecord(Long id, CreateGradeRecordReq req) {
        GradeRecord gradeRecord = gradeRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GradeRecord not found"));
        gradeRecord.setStudent(studentRepository.findById(req.getStudentId()).orElseThrow());
        gradeRecord.setSubject(subjectRepository.findById(req.getSubjectId()).orElseThrow());
        gradeRecord.setTenPointScale(req.getTenPointScale());
        gradeRecord.setFourPointScale(req.getFourPointScale());
        gradeRecord.setSubjectStatus(req.getSubjectStatus());
        GradeRecord updated = gradeRecordRepository.save(gradeRecord);
        return toDto(updated);
    }

    @Override
    public void deleteGradeRecord(Long id) {
        gradeRecordRepository.deleteById(id);
    }

    private GradeRecordResDto toDto(GradeRecord gradeRecord) {
        return GradeRecordResDto.builder()
                .id(gradeRecord.getId())
                .studentCode(gradeRecord.getStudent() != null && gradeRecord.getStudent().getUser() != null 
                    ? gradeRecord.getStudent().getUser().getUserCode() : null)
                .userName(gradeRecord.getStudent() != null && gradeRecord.getStudent().getUser() != null
                    ? gradeRecord.getStudent().getUser().getUsername() : null)
                .subjectName(gradeRecord.getSubject() != null ? gradeRecord.getSubject().getSubjectName() : null)
                .tenPointScale(gradeRecord.getTenPointScale())
                .fourPointScale(gradeRecord.getFourPointScale())
                .gradeLetter(gradeRecord.getSubjectStatus())
                .subjectStatus(gradeRecord.getSubjectStatus())
                .build();
    }
}
