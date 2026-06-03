package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateUserReq;
import UniversityManagemant.demo.dtos.response.UserResDto;
import UniversityManagemant.demo.models.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ChuyenNganhMapper chuyenNganhMapper;
    
    public User toEntity(CreateUserReq createUserReq) {
        return User.builder()
                .userCode(createUserReq.getUserCode())
                .userName(createUserReq.getUserName())
                .email(createUserReq.getEmail())
                .password(createUserReq.getPassword())
                .dateOfBirth(createUserReq.getDateOfBirth())
                .build();
    }

    public UserResDto toResDto(User user) {
        return UserResDto.builder()
                .id(user.getId())
                .userCode(user.getUserCode())
                .userName(user.getUsername())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender() != null ? (user.getGender()) : null)
                .role(user.getRole())
                .major(user.getMajor() != null ? chuyenNganhMapper.toResDto(user.getMajor()) : null)
                .build();
    }

    public void updateEntityFromDto(CreateUserReq createUserReq, User user) {
        user.setUserCode(createUserReq.getUserCode());
        user.setUserName(createUserReq.getUserName());
        user.setEmail(createUserReq.getEmail());
        user.setPassword(createUserReq.getPassword());
        user.setDateOfBirth(createUserReq.getDateOfBirth());
    }
}
