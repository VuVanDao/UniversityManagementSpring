package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateStudentReq;
import UniversityManagemant.demo.dtos.response.StudentResDto;
import UniversityManagemant.demo.models.Student;
import UniversityManagemant.demo.repositories.StudentRepository;
import UniversityManagemant.demo.repositories.UserRepository;
import UniversityManagemant.demo.repositories.ClassManagementRepository;
import UniversityManagemant.demo.services.serviceInterface.StudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    final StudentRepository studentRepository;
    final UserRepository userRepository;
    final ClassManagementRepository classManagementRepository;

    @Override
    public StudentResDto createStudent(CreateStudentReq req) {
        Student student = Student.builder()
                .user(userRepository.findById(req.getUserId()).orElseThrow())
                .classManagement(classManagementRepository.findById(req.getClassManagementId()).orElseThrow())
                .build();
        Student saved = studentRepository.save(student);
        return toDto(saved);
    }

    @Override
    public StudentResDto getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public List<StudentResDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResDto updateStudent(Long id, CreateStudentReq req) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setUser(userRepository.findById(req.getUserId()).orElseThrow());
        student.setClassManagement(classManagementRepository.findById(req.getClassManagementId()).orElseThrow());
        Student updated = studentRepository.save(student);
        return toDto(updated);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    private StudentResDto toDto(Student student) {
        return StudentResDto.builder()
                .id(student.getId())
                .studentCode(student.getUser() != null ? student.getUser().getUserCode() : null)
                .gpaPoint(student.getGPAPoint())
                .userName(student.getUser() != null ? student.getUser().getUsername() : null)
                .userCode(student.getUser() != null ? student.getUser().getUserCode() : null)
                .classManagementName(student.getClassManagement() != null ? student.getClassManagement().getClassManagementName() : null)
                .build();
    }
}
