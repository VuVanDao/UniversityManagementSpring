package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreatePhongHocReq;
import UniversityManagemant.demo.dtos.response.PhongHocResDto;
import UniversityManagemant.demo.models.Classroom;

@Component
public class PhongHocMapper {

    public Classroom toEntity(CreatePhongHocReq createPhongHocReq) {
        return Classroom.builder()
                .tenPhongHoc(createPhongHocReq.getTenPhongHoc())
                .build();
    }

    public PhongHocResDto toResDto(Classroom phongHoc) {
        return PhongHocResDto.builder()
                .id(phongHoc.getId())
                .tenPhongHoc(phongHoc.getTenPhongHoc())
                .build();
    }

    public void updateEntityFromDto(CreatePhongHocReq createPhongHocReq, Classroom phongHoc) {
        phongHoc.setTenPhongHoc(createPhongHocReq.getTenPhongHoc());
    }
}
