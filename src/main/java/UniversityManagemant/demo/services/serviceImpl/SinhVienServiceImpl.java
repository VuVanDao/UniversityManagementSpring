package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateStudentReq;
import UniversityManagemant.demo.dtos.response.StudentResDto;
import UniversityManagemant.demo.models.Student;
import UniversityManagemant.demo.repositories.SinhVienRepository;
import UniversityManagemant.demo.repositories.UserRepository;
import UniversityManagemant.demo.repositories.LopQuanLiRepository;
import UniversityManagemant.demo.services.serviceInterface.SinhVienService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SinhVienServiceImpl implements SinhVienService {
    final SinhVienRepository sinhVienRepository;
    final UserRepository userRepository;
    final LopQuanLiRepository lopQuanLiRepository;

    @Override
    public StudentResDto createSinhVien(CreateStudentReq req) {
        Student sinhVien = Student.builder()
                .user(userRepository.findById(req.getUserId()).orElseThrow())
                .classManagement(lopQuanLiRepository.findById(req.getClassManagementId()).orElseThrow())
                .build();
        Student saved = sinhVienRepository.save(sinhVien);
        return toDto(saved);
    }

    @Override
    public StudentResDto getSinhVienById(Long id) {
        return sinhVienRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("SinhVien not found"));
    }

    @Override
    public List<StudentResDto> getAllSinhVien() {
        return sinhVienRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResDto updateSinhVien(Long id, CreateStudentReq req) {
        Student sinhVien = sinhVienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SinhVien not found"));
        sinhVien.setUser(userRepository.findById(req.getUserId()).orElseThrow());
        sinhVien.setClassManagement(lopQuanLiRepository.findById(req.getClassManagementId()).orElseThrow());
        Student updated = sinhVienRepository.save(sinhVien);
        return toDto(updated);
    }

    @Override
    public void deleteSinhVien(Long id) {
        sinhVienRepository.deleteById(id);
    }

    private StudentResDto toDto(Student sinhVien) {
        return StudentResDto.builder()
                .id(sinhVien.getId())
                .studentCode(sinhVien.getUser() != null ? sinhVien.getUser().getUserCode() : null)
                .gpaPoint(sinhVien.getGPAPoint())
                .userName(sinhVien.getUser() != null ? sinhVien.getUser().getUsername() : null)
                .userCode(sinhVien.getUser() != null ? sinhVien.getUser().getUserCode() : null)
                .classManagementName(sinhVien.getClassManagement() != null ? sinhVien.getClassManagement().getClassManagementName() : null)
                .build();
    }
}
