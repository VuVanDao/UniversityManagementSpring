package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.response.MajorResDto;
import UniversityManagemant.demo.models.Major;

@Component
public class ChuyenNganhMapper {

    public MajorResDto toResDto(Major chuyenNganh) {
        return MajorResDto.builder()
                .id(chuyenNganh.getId())
                .majorCode(chuyenNganh.getMajorCode())
                .majorName(chuyenNganh.getMajorName())
                .facultyName(chuyenNganh.getFaculty() != null ? chuyenNganh.getFaculty().getFacultyName() : null)
                .build();
    }
}
