package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.response.ChuyenNganhResDto;
import UniversityManagemant.demo.models.Major;

@Component
public class ChuyenNganhMapper {

    public ChuyenNganhResDto toResDto(Major chuyenNganh) {
        return ChuyenNganhResDto.builder()
                .id(chuyenNganh.getId())
                .maChuyenNganh(chuyenNganh.getMajorCode())
                .tenChuyenNganh(chuyenNganh.getMajorName())
                .tenKhoa(chuyenNganh.getFaculty() != null ? chuyenNganh.getFaculty().getFacultyName() : null)
                .build();
    }
}
