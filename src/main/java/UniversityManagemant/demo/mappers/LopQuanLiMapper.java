package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;
import UniversityManagemant.demo.dtos.response.ClassManagementResDto;
import UniversityManagemant.demo.models.ClassManagement;

@Component
public class LopQuanLiMapper {

    public ClassManagementResDto toResDto(ClassManagement lopQuanLi) {
        return ClassManagementResDto.builder()
                .id(lopQuanLi.getId())
                .classManagementCode(lopQuanLi.getClassManagementCode())
                .classManagementName(lopQuanLi.getClassManagementName())
                .majorName(lopQuanLi.getMajor() != null ? lopQuanLi.getMajor().getMajorName() : null)
                .build();
    }
}
