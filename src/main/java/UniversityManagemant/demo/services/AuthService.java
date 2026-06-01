package UniversityManagemant.demo.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.LoginReq;
import UniversityManagemant.demo.dtos.response.AuthResDto;
import UniversityManagemant.demo.mappers.RoleMapper;
import UniversityManagemant.demo.mappers.UserMapper;
import UniversityManagemant.demo.models.User;
import UniversityManagemant.demo.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AuthService {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final UserMapper userMapper;
    final RoleMapper roleMapper;

    public AuthResDto login(LoginReq loginReq) {
        User user = userRepository.findByEmailOrMaNguoiDung(loginReq.getEmailOrMaNguoiDung(), 
                                                              loginReq.getEmailOrMaNguoiDung())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với email hoặc mã người dùng: " + 
                                                       loginReq.getEmailOrMaNguoiDung()));

        if (!passwordEncoder.matches(loginReq.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu không đúng");
        }

        return AuthResDto.builder()
                .id(user.getId())
                .maNguoiDung(user.getMaNguoiDung())
                .tenNguoiDung(user.getTenNguoiDung())
                .email(user.getEmail())
                .ngaySinh(user.getNgaySinh())
                .gioiTinh(user.getGioiTinh())
                .role(roleMapper.toResDto(user.getRole()))
                .message("Đăng nhập thành công")
                .build();
    }
}
