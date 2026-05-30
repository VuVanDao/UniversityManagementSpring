package UniversityManagemant.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateUserReq;
import UniversityManagemant.demo.dtos.response.UserResDto;
import UniversityManagemant.demo.models.User;
import UniversityManagemant.demo.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PUBLIC)
@AllArgsConstructor
public class UserService {
    private final UserRepository _userRepository;
    // User createUser(User user);
    UserResDto createUser(CreateUserReq createUserReq){
        User user = User.builder()
        .maNguoiDung(createUserReq.getMa_nguoi_dung())
        .tenNguoiDung(createUserReq.getTen_nguoi_dung())
        .email(createUserReq.getEmail())
        .password(createUserReq.getPassword())
        .ngaySinh(createUserReq.getNgay_sinh())
        .role(createUserReq.getRole())
        .build();
        if(createUserReq.getChuyen_nganh_id() != null){
            // user.setChuyen_nganh(createUserReq.getChuyen_nganh_id());
        }
        // Save the user to the database
        User savedUser = _userRepository.save(user);
        // Return the response DTO
        return UserResDto.builder()
                .ma_nguoi_dung(savedUser.getMaNguoiDung())
                .ten_nguoi_dung(savedUser.getTenNguoiDung())
                .email(savedUser.getEmail())
                .ngay_sinh(savedUser.getNgaySinh())
                .role(savedUser.getRole())
                .ten_chuyen_nganh(savedUser.getChuyenNganh() != null ? savedUser.getChuyenNganh().getTenChuyenNganh() : null)
                .build();
    }
    // User getUserById(Long id);

    // List<User> getAllUsers();

    // User updateUser(Long id, User user);

    // void deleteUser(Long id);
}
