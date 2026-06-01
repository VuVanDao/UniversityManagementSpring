package UniversityManagemant.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateGiangVienReq;
import UniversityManagemant.demo.dtos.response.GiangVienResDto;
import UniversityManagemant.demo.mappers.GiangVienMapper;
import UniversityManagemant.demo.models.GiangVien;
import UniversityManagemant.demo.repositories.GiangVienRepository;
import UniversityManagemant.demo.repositories.UserRepository;
import UniversityManagemant.demo.repositories.LopQuanLiRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GiangVienService {
    final GiangVienRepository giangVienRepository;
    final UserRepository userRepository;
    final LopQuanLiRepository lopQuanLiRepository;
    final GiangVienMapper giangVienMapper;

    public GiangVienResDto createGiangVien(CreateGiangVienReq req) {
        GiangVien giangVien = GiangVien.builder()
                .user(userRepository.findById(req.getUserId()).orElseThrow())
                .lopQuanLi(lopQuanLiRepository.findById(req.getLopQuanLiId()).orElseThrow())
                .build();
        GiangVien saved = giangVienRepository.save(giangVien);
        return giangVienMapper.toResDto(saved);
    }

    public GiangVienResDto getGiangVienById(Long id) {
        return giangVienRepository.findById(id)
                .map(giangVienMapper::toResDto)
                .orElseThrow(() -> new RuntimeException("GiangVien not found"));
    }

    public List<GiangVienResDto> getAllGiangVien() {
        return giangVienRepository.findAll().stream()
                .map(giangVienMapper::toResDto)
                .collect(Collectors.toList());
    }

    public GiangVienResDto updateGiangVien(Long id, CreateGiangVienReq req) {
        GiangVien giangVien = giangVienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GiangVien not found"));
        giangVien.setUser(userRepository.findById(req.getUserId()).orElseThrow());
        giangVien.setLopQuanLi(lopQuanLiRepository.findById(req.getLopQuanLiId()).orElseThrow());
        GiangVien updated = giangVienRepository.save(giangVien);
        return giangVienMapper.toResDto(updated);
    }

    public void deleteGiangVien(Long id) {
        giangVienRepository.deleteById(id);
    }

}
