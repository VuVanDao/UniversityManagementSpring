package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.response.NhomHoc_SinhVienResDto;
import UniversityManagemant.demo.models.StudyGroupStudent;

@Component
public class NhomHoc_SinhVienMapper {

    public NhomHoc_SinhVienResDto toResDto(StudyGroupStudent nhomHoc_SinhVien) {
        return NhomHoc_SinhVienResDto.builder()
                // .id(nhomHoc_SinhVien.getId())
                // .maNhom(nhomHoc_SinhVien.getNhomHoc() != null ? nhomHoc_SinhVien.getNhomHoc().getMaNhom() : null)
                // .tenNhom(nhomHoc_SinhVien.getNhomHoc() != null ? nhomHoc_SinhVien.getNhomHoc().getTenNhom() : null)
                // .maSinhVien(nhomHoc_SinhVien.getSinhVien() != null ? nhomHoc_SinhVien.getSinhVien().getMaSinhVien() : null)
                // .tenNguoiDung(nhomHoc_SinhVien.getSinhVien() != null && nhomHoc_SinhVien.getSinhVien().getUser() != null 
                //         ? nhomHoc_SinhVien.getSinhVien().getUser().getTenNguoiDung() : null)
                .build();
    }
}
