package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.response.BangDiemResDto;
import UniversityManagemant.demo.models.GradeRecord;

@Component
public class BangDiemMapper {

    public BangDiemResDto toResDto(GradeRecord bangDiem) {
        return BangDiemResDto.builder()
                .id(bangDiem.getId())
                .maSinhVien(bangDiem.getStudent() != null ? bangDiem.getStudent().getUser().getUserCode() : null)
                .tenMonHoc(bangDiem.getSubject() != null ? bangDiem.getSubject().getSubjectName() : null)
                .diemHe10(bangDiem.getTenPointScale())
                .diemHe4(bangDiem.getFourPointScale())
                .trangThaiMonHoc(bangDiem.getSubjectStatus())
                .build();
    }
}
