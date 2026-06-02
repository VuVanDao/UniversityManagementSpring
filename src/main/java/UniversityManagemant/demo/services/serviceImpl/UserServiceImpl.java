package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateUserReq;
import UniversityManagemant.demo.dtos.response.UserResDto;
import UniversityManagemant.demo.mappers.UserMapper;
import UniversityManagemant.demo.models.User;
import UniversityManagemant.demo.repositories.UserRepository;
import UniversityManagemant.demo.services.serviceInterface.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final UserMapper userMapper;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserResDto createUser(CreateUserReq createUserReq) {
        User user = userMapper.toEntity(createUserReq);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toResDto(savedUser);
    }

    @Override
    public UserResDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toResDto(user);
    }

    @Override
    public List<UserResDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResDto)
                .toList();
    }

    @Override
    public UserResDto updateUser(Long id, CreateUserReq createUserReq) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userMapper.updateEntityFromDto(createUserReq, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toResDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
