package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateSubjectReq;
import UniversityManagemant.demo.dtos.response.SubjectResDto;
import UniversityManagemant.demo.models.Subject;

@Component
public class MonHocMapper {

    public Subject toEntity(CreateSubjectReq createSubjectReq) {
        return Subject.builder()
                .subjectCode(createSubjectReq.getSubjectCode())
                .subjectName(createSubjectReq.getSubjectName())
                .credits(createSubjectReq.getCredits())
                .build();
    }

    public SubjectResDto toResDto(Subject monHoc) {
        return SubjectResDto.builder()
                .id(monHoc.getId())
                .subjectCode(monHoc.getSubjectCode())
                .subjectName(monHoc.getSubjectName())
                .credits(monHoc.getCredits())
                .build();
    }

    public void updateEntityFromDto(CreateSubjectReq createSubjectReq, Subject monHoc) {
        monHoc.setSubjectCode(createSubjectReq.getSubjectCode());
        monHoc.setSubjectName(createSubjectReq.getSubjectName());
        monHoc.setCredits(createSubjectReq.getCredits());
    }
}
