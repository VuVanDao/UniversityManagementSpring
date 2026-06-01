package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateUserReq;
import UniversityManagemant.demo.dtos.response.UserResDto;
import UniversityManagemant.demo.enums.Gender;
import UniversityManagemant.demo.models.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ChuyenNganhMapper chuyenNganhMapper;
    
    public User toEntity(CreateUserReq createUserReq) {
        return User.builder()
                .maNguoiDung(createUserReq.getMa_nguoi_dung())
                .tenNguoiDung(createUserReq.getTen_nguoi_dung())
                .email(createUserReq.getEmail())
                .password(createUserReq.getPassword())
                .ngaySinh(createUserReq.getNgay_sinh())
                .role(createUserReq.getRole())
                .build();
    }

    public UserResDto toResDto(User user) {
        return UserResDto.builder()
                .id(user.getId())
                .maNguoiDung(user.getMaNguoiDung())
                .tenNguoiDung(user.getTenNguoiDung())
                .email(user.getEmail())
                .ngaySinh(user.getNgaySinh())
                .gioiTinh(user.getGioiTinh() != null ? (user.getGioiTinh()) : null)
                .role(user.getRole())
                .chuyenNganh(user.getChuyenNganh() != null ? chuyenNganhMapper.toResDto(user.getChuyenNganh()) : null)
                .build();
    }

    public void updateEntityFromDto(CreateUserReq createUserReq, User user) {
        user.setMaNguoiDung(createUserReq.getMa_nguoi_dung());
        user.setTenNguoiDung(createUserReq.getTen_nguoi_dung());
        user.setEmail(createUserReq.getEmail());
        user.setPassword(createUserReq.getPassword());
        user.setNgaySinh(createUserReq.getNgay_sinh());
        user.setRole(createUserReq.getRole());
    }
}
