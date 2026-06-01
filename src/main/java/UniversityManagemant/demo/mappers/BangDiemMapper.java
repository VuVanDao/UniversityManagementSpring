package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.response.BangDiemResDto;
import UniversityManagemant.demo.models.BangDiem;

@Component
public class BangDiemMapper {

    public BangDiemResDto toResDto(BangDiem bangDiem) {
        return BangDiemResDto.builder()
                .id(bangDiem.getId())
                .maSinhVien(bangDiem.getSinhVien() != null ? bangDiem.getSinhVien().getMaSinhVien() : null)
                .tenMonHoc(bangDiem.getMonHoc() != null ? bangDiem.getMonHoc().getTenMonHoc() : null)
                .diemHe10(bangDiem.getDiemHe10())
                .diemHe4(bangDiem.getDiemHe4())
                .diemChu(bangDiem.getDiemChu())
                .trangThaiMonHoc(bangDiem.getTrangThaiMonHoc())
                .build();
    }
}
