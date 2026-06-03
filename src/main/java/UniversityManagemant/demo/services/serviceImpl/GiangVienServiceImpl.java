package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateGiangVienReq;
import UniversityManagemant.demo.dtos.response.GiangVienResDto;
import UniversityManagemant.demo.mappers.GiangVienMapper;
import UniversityManagemant.demo.models.Lecturer;
import UniversityManagemant.demo.repositories.GiangVienRepository;
import UniversityManagemant.demo.repositories.UserRepository;
import UniversityManagemant.demo.repositories.LopQuanLiRepository;
import UniversityManagemant.demo.services.serviceInterface.GiangVienService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GiangVienServiceImpl implements GiangVienService {
    final GiangVienRepository giangVienRepository;
    final UserRepository userRepository;
    final LopQuanLiRepository lopQuanLiRepository;
    final GiangVienMapper giangVienMapper;

    @Override
    public GiangVienResDto createGiangVien(CreateGiangVienReq req) {
        Lecturer giangVien = Lecturer.builder()
                .user(userRepository.findById(req.getUserId()).orElseThrow())
                .classManagement(lopQuanLiRepository.findById(req.getLopQuanLiId()).orElseThrow())
                .build();
        Lecturer saved = giangVienRepository.save(giangVien);
        return giangVienMapper.toResDto(saved);
    }

    @Override
    public GiangVienResDto getGiangVienById(Long id) {
        return giangVienRepository.findById(id)
                .map(giangVienMapper::toResDto)
                .orElseThrow(() -> new RuntimeException("GiangVien not found"));
    }

    @Override
    public List<GiangVienResDto> getAllGiangVien() {
        return giangVienRepository.findAll().stream()
                .map(giangVienMapper::toResDto)
                .collect(Collectors.toList());
    }

    @Override
    public GiangVienResDto updateGiangVien(Long id, CreateGiangVienReq req) {
        Lecturer giangVien = giangVienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GiangVien not found"));
        giangVien.setUser(userRepository.findById(req.getUserId()).orElseThrow());
        giangVien.setClassManagement(lopQuanLiRepository.findById(req.getLopQuanLiId()).orElseThrow());
        Lecturer updated = giangVienRepository.save(giangVien);
        return giangVienMapper.toResDto(updated);
    }

    @Override
    public void deleteGiangVien(Long id) {
        giangVienRepository.deleteById(id);
    }
}
