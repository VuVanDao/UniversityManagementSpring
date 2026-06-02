package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateBangDiemReq;
import UniversityManagemant.demo.dtos.response.BangDiemResDto;
import UniversityManagemant.demo.models.BangDiem;
import UniversityManagemant.demo.repositories.BangDiemRepository;
import UniversityManagemant.demo.repositories.SinhVienRepository;
import UniversityManagemant.demo.repositories.MonHocRepository;
import UniversityManagemant.demo.services.serviceInterface.BangDiemService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BangDiemServiceImpl implements BangDiemService {
    final BangDiemRepository bangDiemRepository;
    final SinhVienRepository sinhVienRepository;
    final MonHocRepository monHocRepository;

    @Override
    public BangDiemResDto createBangDiem(CreateBangDiemReq req) {
        BangDiem bangDiem = BangDiem.builder()
                .sinhVien(sinhVienRepository.findById(req.getSinhVienId()).orElseThrow())
                .monHoc(monHocRepository.findById(req.getMonHocId()).orElseThrow())
                .diemHe10(req.getDiemHe10())
                .diemHe4(req.getDiemHe4())
                .diemChu(req.getDiemChu())
                .trangThaiMonHoc(req.getTrangThaiMonHoc())
                .build();
        BangDiem saved = bangDiemRepository.save(bangDiem);
        return toDto(saved);
    }

    @Override
    public BangDiemResDto getBangDiemById(Long id) {
        return bangDiemRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("BangDiem not found"));
    }

    @Override
    public List<BangDiemResDto> getAllBangDiem() {
        return bangDiemRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BangDiemResDto updateBangDiem(Long id, CreateBangDiemReq req) {
        BangDiem bangDiem = bangDiemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BangDiem not found"));
        bangDiem.setSinhVien(sinhVienRepository.findById(req.getSinhVienId()).orElseThrow());
        bangDiem.setMonHoc(monHocRepository.findById(req.getMonHocId()).orElseThrow());
        bangDiem.setDiemHe10(req.getDiemHe10());
        bangDiem.setDiemHe4(req.getDiemHe4());
        bangDiem.setDiemChu(req.getDiemChu());
        bangDiem.setTrangThaiMonHoc(req.getTrangThaiMonHoc());
        BangDiem updated = bangDiemRepository.save(bangDiem);
        return toDto(updated);
    }

    @Override
    public void deleteBangDiem(Long id) {
        bangDiemRepository.deleteById(id);
    }

    private BangDiemResDto toDto(BangDiem bangDiem) {
        return BangDiemResDto.builder()
                .id(bangDiem.getId())
                .maSinhVien(bangDiem.getSinhVien() != null ? bangDiem.getSinhVien().getMaSinhVien() : null)
                .tenNguoiDung(bangDiem.getSinhVien() != null && bangDiem.getSinhVien().getUser() != null
                    ? bangDiem.getSinhVien().getUser().getTenNguoiDung() : null)
                .tenMonHoc(bangDiem.getMonHoc() != null ? bangDiem.getMonHoc().getTenMonHoc() : null)
                .diemHe10(bangDiem.getDiemHe10())
                .diemHe4(bangDiem.getDiemHe4())
                .diemChu(bangDiem.getDiemChu())
                .trangThaiMonHoc(bangDiem.getTrangThaiMonHoc())
                .build();
    }
}
