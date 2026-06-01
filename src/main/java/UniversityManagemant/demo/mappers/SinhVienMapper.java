package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateSinhVienReq;
import UniversityManagemant.demo.dtos.response.SinhVienResDto;
import UniversityManagemant.demo.models.SinhVien;

@Component
public class SinhVienMapper {

    public SinhVienResDto toResDto(SinhVien sinhVien) {
        return SinhVienResDto.builder()
                .id(sinhVien.getId())
                .maSinhVien(sinhVien.getMaSinhVien())
                .diemGPA(sinhVien.getDiemGPA())
                .tenNguoiDung(sinhVien.getUser() != null ? sinhVien.getUser().getTenNguoiDung() : null)
                .maNguoiDung(sinhVien.getUser() != null ? sinhVien.getUser().getMaNguoiDung() : null)
                .tenLopQuanLi(sinhVien.getLopQuanLi() != null ? sinhVien.getLopQuanLi().getTenLop() : null)
                .build();
    }

    public void updateEntityFromDto(CreateSinhVienReq createSinhVienReq, SinhVien sinhVien) {
        sinhVien.setMaSinhVien(createSinhVienReq.getMaSinhVien());
    }
}
