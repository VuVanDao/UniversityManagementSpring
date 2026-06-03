package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;
import UniversityManagemant.demo.dtos.response.LopQuanLiResDto;
import UniversityManagemant.demo.models.ClassManagement;

@Component
public class LopQuanLiMapper {

    public LopQuanLiResDto toResDto(ClassManagement lopQuanLi) {
        return LopQuanLiResDto.builder()
                .id(lopQuanLi.getId())
                .maLop(lopQuanLi.getClassManagementCode())
                .tenLop(lopQuanLi.getClassManagementCode())
                .tenChuyenNganh(lopQuanLi.getMajor() != null ? lopQuanLi.getMajor().getMajorName() : null)
                .build();
    }
}
