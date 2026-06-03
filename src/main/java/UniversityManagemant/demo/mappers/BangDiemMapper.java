package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.response.GradeRecordResDto;
import UniversityManagemant.demo.models.GradeRecord;

@Component
public class BangDiemMapper {

    public GradeRecordResDto toResDto(GradeRecord bangDiem) {
        return GradeRecordResDto.builder()
                .id(bangDiem.getId())
                .studentCode(bangDiem.getStudent() != null ? bangDiem.getStudent().getUser().getUserCode() : null)
                .userName(bangDiem.getStudent() != null && bangDiem.getStudent().getUser() != null ? bangDiem.getStudent().getUser().getUsername() : null)
                .subjectName(bangDiem.getSubject() != null ? bangDiem.getSubject().getSubjectName() : null)
                .tenPointScale(bangDiem.getTenPointScale())
                .fourPointScale(bangDiem.getFourPointScale())
                .gradeLetter(bangDiem.getSubjectStatus())
                .subjectStatus(bangDiem.getSubjectStatus())
                .build();
    }
}
