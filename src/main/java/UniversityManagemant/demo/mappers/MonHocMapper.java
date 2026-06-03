package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateMonHocReq;
import UniversityManagemant.demo.dtos.response.MonHocResDto;
import UniversityManagemant.demo.models.Subject;

@Component
public class MonHocMapper {

    public Subject toEntity(CreateMonHocReq createMonHocReq) {
        return Subject.builder()
                .maMonHoc(createMonHocReq.getMaMonHoc())
                .tenMonHoc(createMonHocReq.getTenMonHoc())
                .tinChi(createMonHocReq.getTinChi())
                .build();
    }

    public MonHocResDto toResDto(Subject monHoc) {
        return MonHocResDto.builder()
                .id(monHoc.getId())
                .maMonHoc(monHoc.getMaMonHoc())
                .tenMonHoc(monHoc.getTenMonHoc())
                .tinChi(monHoc.getTinChi())
                .build();
    }

    public void updateEntityFromDto(CreateMonHocReq createMonHocReq, Subject monHoc) {
        monHoc.setMaMonHoc(createMonHocReq.getMaMonHoc());
        monHoc.setTenMonHoc(createMonHocReq.getTenMonHoc());
        monHoc.setTinChi(createMonHocReq.getTinChi());
    }
}
