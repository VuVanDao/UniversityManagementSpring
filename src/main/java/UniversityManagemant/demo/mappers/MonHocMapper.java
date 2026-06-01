package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateMonHocReq;
import UniversityManagemant.demo.dtos.response.MonHocResDto;
import UniversityManagemant.demo.models.MonHoc;

@Component
public class MonHocMapper {

    public MonHoc toEntity(CreateMonHocReq createMonHocReq) {
        return MonHoc.builder()
                .maMonHoc(createMonHocReq.getMaMonHoc())
                .tenMonHoc(createMonHocReq.getTenMonHoc())
                .tinChi(createMonHocReq.getTinChi())
                .build();
    }

    public MonHocResDto toResDto(MonHoc monHoc) {
        return MonHocResDto.builder()
                .id(monHoc.getId())
                .maMonHoc(monHoc.getMaMonHoc())
                .tenMonHoc(monHoc.getTenMonHoc())
                .tinChi(monHoc.getTinChi())
                .build();
    }

    public void updateEntityFromDto(CreateMonHocReq createMonHocReq, MonHoc monHoc) {
        monHoc.setMaMonHoc(createMonHocReq.getMaMonHoc());
        monHoc.setTenMonHoc(createMonHocReq.getTenMonHoc());
        monHoc.setTinChi(createMonHocReq.getTinChi());
    }
}
