package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateUserReq;
import UniversityManagemant.demo.dtos.response.UserResDto;
import UniversityManagemant.demo.mappers.UserMapper;
import UniversityManagemant.demo.models.Lecturer;
import UniversityManagemant.demo.models.Major;
import UniversityManagemant.demo.models.Role;
import UniversityManagemant.demo.models.Student;
import UniversityManagemant.demo.models.User;
import UniversityManagemant.demo.repositories.LecturerRepository;
import UniversityManagemant.demo.repositories.MajorRepository;
import UniversityManagemant.demo.repositories.RoleRepository;
import UniversityManagemant.demo.repositories.StudentRepository;
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
    final RoleRepository roleRepository;
    final MajorRepository majorRepository;
    final LecturerRepository lecturerRepository;
    final StudentRepository studentRepository;

    @Override
    public UserResDto createUser(CreateUserReq createUserReq) {
        if (userRepository.existsByEmail(createUserReq.getEmail())) {
                    throw new RuntimeException("Email đã tồn tại");
        }
        User user = userMapper.toEntity(createUserReq);
        Role role = roleRepository.findById(createUserReq.getRole_id())
                .orElseThrow(() -> new RuntimeException("Không tìm tháy role với id " + createUserReq.getRole_id()));
        if ("ADMIN".equalsIgnoreCase(role.getRoleName())) {
            throw new RuntimeException("Đã có 1 admin, không thể tạo thêm admin khác");
        }
        user.setRole(role);

        Major major = majorRepository.findById(createUserReq.getMajorId())
                .orElseThrow(() -> new RuntimeException("Không tìm tháy chuyên ngành với id " + createUserReq.getMajorId()));
        user.setMajor(major);
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        switch (role.getRoleName()) {
            case "LECTURER":
                Lecturer lecturer = Lecturer.builder()
                        .user(user)
                        .build();
                lecturerRepository.save(lecturer);
                break;
            case "STUDENT":
                Student student = Student.builder()
                        .user(user)
                        .build();
                studentRepository.save(student);
                break;
            default:
                break;
        }
        return userMapper.toResDto(savedUser);
    }

    @Override
    public UserResDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm tháy người dùng với id " + id));
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
                .orElseThrow(() -> new RuntimeException("Không tìm tháy người dùng với id " + id));
        Role role = roleRepository.findById(createUserReq.getRole_id())
                .orElseThrow(() -> new RuntimeException("Không tìm tháy role với id " + createUserReq.getRole_id()));

        if ("ADMIN".equalsIgnoreCase(role.getRoleName())) {
            throw new RuntimeException("Đã có 1 admin, không thể tạo thêm admin khác");
        }
        user.setRole(role);

        Major major = majorRepository.findById(createUserReq.getMajorId())
                .orElseThrow(() -> new RuntimeException("Không tìm tháy chuyên ngành với id " + createUserReq.getMajorId()));
        user.setMajor(major);

        userMapper.updateEntityFromDto(createUserReq, user);
        User updatedUser = userRepository.save(user);

        if (user.getRole().getId() != createUserReq.getRole_id()) {
            switch (role.getRoleName()) {
                case "LECTURER":
                    Student studentToDelete = studentRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với ID: " + id));
                    studentRepository.delete(studentToDelete);
                    Lecturer lecturer = Lecturer.builder()
                            .user(user)
                            .build();
                    lecturerRepository.save(lecturer);
                    break;
                case "STUDENT":
                    Lecturer lecturerToDelete = lecturerRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với ID: " + id));
                    lecturerRepository.delete(lecturerToDelete);
                    Student student = Student.builder()
                            .user(user)
                            .build();
                    studentRepository.save(student);
                    break;
                default:
                    break;
            }
        }

        return userMapper.toResDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm tháy người dùng với id " + id));
        userRepository.delete(user);
    }
}
