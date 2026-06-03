package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateSinhVienReq;
import UniversityManagemant.demo.dtos.response.SinhVienResDto;
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
    public SinhVienResDto createSinhVien(CreateSinhVienReq req) {
        Student sinhVien = Student.builder()
                .maSinhVien(req.getMaSinhVien())
                .user(userRepository.findById(req.getUserId()).orElseThrow())
                .lopQuanLi(lopQuanLiRepository.findById(req.getLopQuanLiId()).orElseThrow())
                .build();
        Student saved = sinhVienRepository.save(sinhVien);
        return toDto(saved);
    }

    @Override
    public SinhVienResDto getSinhVienById(Long id) {
        return sinhVienRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("SinhVien not found"));
    }

    @Override
    public List<SinhVienResDto> getAllSinhVien() {
        return sinhVienRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SinhVienResDto updateSinhVien(Long id, CreateSinhVienReq req) {
        Student sinhVien = sinhVienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SinhVien not found"));
        sinhVien.setMaSinhVien(req.getMaSinhVien());
        sinhVien.setUser(userRepository.findById(req.getUserId()).orElseThrow());
        sinhVien.setLopQuanLi(lopQuanLiRepository.findById(req.getLopQuanLiId()).orElseThrow());
        Student updated = sinhVienRepository.save(sinhVien);
        return toDto(updated);
    }

    @Override
    public void deleteSinhVien(Long id) {
        sinhVienRepository.deleteById(id);
    }

    private SinhVienResDto toDto(Student sinhVien) {
        return SinhVienResDto.builder()
                .id(sinhVien.getId())
                .maSinhVien(sinhVien.getMaSinhVien())
                .diemGPA(sinhVien.getDiemGPA())
                .tenNguoiDung(sinhVien.getUser() != null ? sinhVien.getUser().getTenNguoiDung() : null)
                .maNguoiDung(sinhVien.getUser() != null ? sinhVien.getUser().getMaNguoiDung() : null)
                .tenLopQuanLi(sinhVien.getLopQuanLi() != null ? sinhVien.getLopQuanLi().getTenLop() : null)
                .build();
    }
}
