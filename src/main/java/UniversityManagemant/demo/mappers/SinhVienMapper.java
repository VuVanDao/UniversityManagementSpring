package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateSinhVienReq;
import UniversityManagemant.demo.dtos.response.SinhVienResDto;
import UniversityManagemant.demo.models.Student;

@Component
public class SinhVienMapper {

    public SinhVienResDto toResDto(Student sinhVien) {
        return SinhVienResDto.builder()
                .id(sinhVien.getId())
                .maSinhVien(sinhVien.getUser().getUserCode())
                .diemGPA(sinhVien.getGPAPoint())
                .tenNguoiDung(sinhVien.getUser() != null ? sinhVien.getUser().getUsername() : null)
                .maNguoiDung(sinhVien.getUser() != null ? sinhVien.getUser().getUserCode() : null)
                .tenLopQuanLi(sinhVien.getClassManagement() != null ? sinhVien.getClassManagement().getClassManagementName() : null)
                .build();
    }

    public void updateEntityFromDto(CreateSinhVienReq createSinhVienReq, Student sinhVien) {
        sinhVien.getUser().setUserCode(createSinhVienReq.getMaSinhVien());
    }
}
