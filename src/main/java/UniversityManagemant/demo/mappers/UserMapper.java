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
                .userCode(createUserReq.getMa_nguoi_dung())
                .userName(createUserReq.getTen_nguoi_dung())
                .email(createUserReq.getEmail())
                .password(createUserReq.getPassword())
                .dateOfBirth(createUserReq.getNgay_sinh())
                .build();
    }

    public UserResDto toResDto(User user) {
        return UserResDto.builder()
                .id(user.getId())
                .maNguoiDung(user.getUserCode())
                .tenNguoiDung(user.getUsername())
                .email(user.getEmail())
                .ngaySinh(user.getDateOfBirth())
                .gioiTinh(user.getGender() != null ? (user.getGender()) : null)
                .role(user.getRole())
                .chuyenNganh(user.getMajor() != null ? chuyenNganhMapper.toResDto(user.getMajor()) : null)
                .build();
    }

    public void updateEntityFromDto(CreateUserReq createUserReq, User user) {
        user.setUserCode(createUserReq.getMa_nguoi_dung());
        user.setUserName(createUserReq.getTen_nguoi_dung());
        user.setEmail(createUserReq.getEmail());
        user.setPassword(createUserReq.getPassword());
        user.setDateOfBirth(createUserReq.getNgay_sinh());
        // user.setRole(createUserReq.getRole());
    }
}
