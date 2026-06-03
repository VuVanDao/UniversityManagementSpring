package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateGiangVienReq;
import UniversityManagemant.demo.dtos.response.GiangVienResDto;
import UniversityManagemant.demo.models.Lecturer;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GiangVienMapper {
    private final LopQuanLiMapper lopQuanLiMapper;

    public GiangVienResDto toResDto(Lecturer giangVien) {
        return GiangVienResDto.builder()
                .id(giangVien.getId())
                .tenNguoiDung(giangVien.getUser() != null ? giangVien.getUser().getUsername() : null)
                .maNguoiDung(giangVien.getUser() != null ? giangVien.getUser().getUserCode() : null)
                .email(giangVien.getUser() != null ? giangVien.getUser().getEmail() : null)
                .lopQuanLi(giangVien.getClassManagement() != null ? lopQuanLiMapper.toResDto(giangVien.getClassManagement()) : null)
                .build();
    }
}
