package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateGiangVienReq;
import UniversityManagemant.demo.dtos.response.GiangVienResDto;
import UniversityManagemant.demo.models.GiangVien;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GiangVienMapper {
    private final LopQuanLiMapper lopQuanLiMapper;

    public GiangVienResDto toResDto(GiangVien giangVien) {
        return GiangVienResDto.builder()
                .id(giangVien.getId())
                .tenNguoiDung(giangVien.getUser() != null ? giangVien.getUser().getTenNguoiDung() : null)
                .maNguoiDung(giangVien.getUser() != null ? giangVien.getUser().getMaNguoiDung() : null)
                .email(giangVien.getUser() != null ? giangVien.getUser().getEmail() : null)
                .lopQuanLi(giangVien.getLopQuanLi() != null ? lopQuanLiMapper.toResDto(giangVien.getLopQuanLi()) : null)
                .build();
    }
}
