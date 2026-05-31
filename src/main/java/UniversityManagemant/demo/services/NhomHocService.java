package UniversityManagemant.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateNhomHocReq;
import UniversityManagemant.demo.dtos.response.NhomHocResDto;
import UniversityManagemant.demo.models.NhomHoc;
import UniversityManagemant.demo.repositories.NhomHocRepository;
import UniversityManagemant.demo.repositories.MonHocRepository;
import UniversityManagemant.demo.repositories.GiangVienRepository;
import UniversityManagemant.demo.repositories.PhongHocRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class NhomHocService {
    final NhomHocRepository nhomHocRepository;
    final MonHocRepository monHocRepository;
    final GiangVienRepository giangVienRepository;
    final PhongHocRepository phongHocRepository;

    public NhomHocResDto createNhomHoc(CreateNhomHocReq req) {
        NhomHoc nhomHoc = NhomHoc.builder()
                .maNhom(req.getMaNhom())
                .tenNhom(req.getTenNhom())
                .tietBatDau(req.getTietBatDau())
                .tietKetThuc(req.getTietKetThuc())
                .fromTime(req.getFromTime())
                .toTime(req.getToTime())
                .soLuongSinhVien(req.getSoLuongSinhVien())
                .monHoc(monHocRepository.findById(req.getMonHocId()).orElseThrow())
                .giangVien(giangVienRepository.findById(req.getGiangVienId()).orElseThrow())
                .phongHoc(phongHocRepository.findById(req.getPhongHocId()).orElseThrow())
                .build();
        NhomHoc saved = nhomHocRepository.save(nhomHoc);
        return toDto(saved);
    }

    public NhomHocResDto getNhomHocById(Long id) {
        return nhomHocRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("NhomHoc not found"));
    }

    public List<NhomHocResDto> getAllNhomHoc() {
        return nhomHocRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public NhomHocResDto updateNhomHoc(Long id, CreateNhomHocReq req) {
        NhomHoc nhomHoc = nhomHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NhomHoc not found"));
        nhomHoc.setMaNhom(req.getMaNhom());
        nhomHoc.setTenNhom(req.getTenNhom());
        nhomHoc.setTietBatDau(req.getTietBatDau());
        nhomHoc.setTietKetThuc(req.getTietKetThuc());
        nhomHoc.setFromTime(req.getFromTime());
        nhomHoc.setToTime(req.getToTime());
        nhomHoc.setSoLuongSinhVien(req.getSoLuongSinhVien());
        nhomHoc.setMonHoc(monHocRepository.findById(req.getMonHocId()).orElseThrow());
        nhomHoc.setGiangVien(giangVienRepository.findById(req.getGiangVienId()).orElseThrow());
        nhomHoc.setPhongHoc(phongHocRepository.findById(req.getPhongHocId()).orElseThrow());
        NhomHoc updated = nhomHocRepository.save(nhomHoc);
        return toDto(updated);
    }

    public void deleteNhomHoc(Long id) {
        nhomHocRepository.deleteById(id);
    }

    private NhomHocResDto toDto(NhomHoc nhomHoc) {
        return NhomHocResDto.builder()
                .id(nhomHoc.getId())
                .maNhom(nhomHoc.getMaNhom())
                .tenNhom(nhomHoc.getTenNhom())
                .tietBatDau(nhomHoc.getTietBatDau())
                .tietKetThuc(nhomHoc.getTietKetThuc())
                .fromTime(nhomHoc.getFromTime())
                .toTime(nhomHoc.getToTime())
                .soLuongSinhVien(nhomHoc.getSoLuongSinhVien())
                .tenMonHoc(nhomHoc.getMonHoc() != null ? nhomHoc.getMonHoc().getTenMonHoc() : null)
                .tenGiangVien(nhomHoc.getGiangVien() != null && nhomHoc.getGiangVien().getUser() != null
                    ? nhomHoc.getGiangVien().getUser().getTenNguoiDung() : null)
                .tenPhongHoc(nhomHoc.getPhongHoc() != null ? nhomHoc.getPhongHoc().getTenPhongHoc() : null)
                .build();
    }
}
