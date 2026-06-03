package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateStudentReq;
import UniversityManagemant.demo.dtos.response.StudentResDto;
import UniversityManagemant.demo.models.Student;

@Component
public class SinhVienMapper {

    public StudentResDto toResDto(Student sinhVien) {
        return StudentResDto.builder()
                .id(sinhVien.getId())
                .studentCode(sinhVien.getUser().getUserCode())
                .gpaPoint(sinhVien.getGPAPoint())
                .userName(sinhVien.getUser() != null ? sinhVien.getUser().getUsername() : null)
                .userCode(sinhVien.getUser() != null ? sinhVien.getUser().getUserCode() : null)
                .classManagementName(sinhVien.getClassManagement() != null ? sinhVien.getClassManagement().getClassManagementName() : null)
                .build();
    }

    public void updateEntityFromDto(CreateStudentReq createStudentReq, Student sinhVien) {
        sinhVien.getUser().setUserCode(createStudentReq.getStudentCode());
    }
}
