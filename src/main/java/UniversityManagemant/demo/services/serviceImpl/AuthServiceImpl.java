package UniversityManagemant.demo.services.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.LoginReq;
import UniversityManagemant.demo.dtos.response.AuthResDto;
import UniversityManagemant.demo.mappers.RoleMapper;
import UniversityManagemant.demo.mappers.UserMapper;
import UniversityManagemant.demo.models.User;
import UniversityManagemant.demo.repositories.UserRepository;
import UniversityManagemant.demo.services.serviceInterface.AuthService;
import UniversityManagemant.demo.utils.JwtProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final UserMapper userMapper;
    final RoleMapper roleMapper;
    final JwtProvider jwtProvider;

    @Override
    public AuthResDto login(LoginReq loginReq) {
        User user = userRepository.findByEmailOrUserCode(loginReq.getEmailOrUserCode(), 
                                                              loginReq.getEmailOrUserCode())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với email hoặc mã người dùng: " + 
                                                       loginReq.getEmailOrUserCode()));

        if (!passwordEncoder.matches(loginReq.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu không đúng");
        }
        String accessToken = jwtProvider.generateAccessToken(user.getEmail());
        String refreshToken = jwtProvider.generateRefreshToken(user.getEmail());
        return AuthResDto.builder()
                .id(user.getId())
                .userCode(user.getUserCode())
                .userName(user.getUsername())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .role(roleMapper.toResDto(user.getRole()))
                .message("Đăng nhập thành công")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
