package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateMonHocReq;
import UniversityManagemant.demo.dtos.response.MonHocResDto;
import UniversityManagemant.demo.models.Subject;

@Component
public class MonHocMapper {

    public Subject toEntity(CreateMonHocReq createMonHocReq) {
        return Subject.builder()
                .subjectCode(createMonHocReq.getMaMonHoc())
                .subjectName(createMonHocReq.getTenMonHoc())
                .credits(createMonHocReq.getTinChi())
                .build();
    }

    public MonHocResDto toResDto(Subject monHoc) {
        return MonHocResDto.builder()
                .id(monHoc.getId())
                .maMonHoc(monHoc.getSubjectCode())
                .tenMonHoc(monHoc.getSubjectName())
                .tinChi(monHoc.getCredits())
                .build();
    }

    public void updateEntityFromDto(CreateMonHocReq createMonHocReq, Subject monHoc) {
        monHoc.setSubjectCode(createMonHocReq.getMaMonHoc());
        monHoc.setSubjectName(createMonHocReq.getTenMonHoc());
        monHoc.setCredits(createMonHocReq.getTinChi());
    }
}
