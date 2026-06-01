package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreatePhongHocReq;
import UniversityManagemant.demo.dtos.response.PhongHocResDto;
import UniversityManagemant.demo.models.PhongHoc;

@Component
public class PhongHocMapper {

    public PhongHoc toEntity(CreatePhongHocReq createPhongHocReq) {
        return PhongHoc.builder()
                .tenPhongHoc(createPhongHocReq.getTenPhongHoc())
                .build();
    }

    public PhongHocResDto toResDto(PhongHoc phongHoc) {
        return PhongHocResDto.builder()
                .id(phongHoc.getId())
                .tenPhongHoc(phongHoc.getTenPhongHoc())
                .build();
    }

    public void updateEntityFromDto(CreatePhongHocReq createPhongHocReq, PhongHoc phongHoc) {
        phongHoc.setTenPhongHoc(createPhongHocReq.getTenPhongHoc());
    }
}
