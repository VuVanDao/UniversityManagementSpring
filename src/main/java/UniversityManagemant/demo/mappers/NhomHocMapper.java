package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateNhomHocReq;
import UniversityManagemant.demo.dtos.response.NhomHocResDto;
import UniversityManagemant.demo.models.NhomHoc;

@Component
public class NhomHocMapper {

    public NhomHocResDto toResDto(NhomHoc nhomHoc) {
        return NhomHocResDto.builder()
                .id(nhomHoc.getId())
                .maNhom(nhomHoc.getMaNhom())
                .tenNhom(nhomHoc.getTenNhom())
                .tietBatDau(nhomHoc.getTietBatDau())
                .tietKetThuc(nhomHoc.getTietKetThuc())
                .fromTime(nhomHoc.getFromTime())
                .toTime(nhomHoc.getToTime())
                .soLuongSinhVien(nhomHoc.getSoLuongSinhVien())
                .tenMonHoc(nhomHoc.getMonHoc() != null ? nhomHoc.getMonHoc().getTenMonHoc() : null)
                .tenGiangVien(nhomHoc.getGiangVien() != null && nhomHoc.getGiangVien().getUser() != null ? nhomHoc.getGiangVien().getUser().getTenNguoiDung() : null)
                .tenPhongHoc(nhomHoc.getPhongHoc() != null ? nhomHoc.getPhongHoc().getTenPhongHoc() : null)
                .build();
    }
}
