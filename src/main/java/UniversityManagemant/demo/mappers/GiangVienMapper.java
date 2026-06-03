package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.response.LecturerResDto;
import UniversityManagemant.demo.models.Lecturer;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GiangVienMapper {
    private final LopQuanLiMapper lopQuanLiMapper;

    public LecturerResDto toResDto(Lecturer giangVien) {
        return LecturerResDto.builder()
                .id(giangVien.getId())
                .userName(giangVien.getUser() != null ? giangVien.getUser().getUsername() : null)
                .userCode(giangVien.getUser() != null ? giangVien.getUser().getUserCode() : null)
                .email(giangVien.getUser() != null ? giangVien.getUser().getEmail() : null)
                .classManagement(giangVien.getClassManagement() != null ? lopQuanLiMapper.toResDto(giangVien.getClassManagement()) : null)
                .build();
    }
}
