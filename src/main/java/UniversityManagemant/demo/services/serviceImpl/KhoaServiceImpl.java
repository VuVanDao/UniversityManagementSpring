package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateKhoaReq;
import UniversityManagemant.demo.dtos.request.UpdateKhoaReq;
import UniversityManagemant.demo.dtos.response.KhoaResDto;
import UniversityManagemant.demo.mappers.KhoaMapper;
import UniversityManagemant.demo.models.GiangVien;
import UniversityManagemant.demo.models.Khoa;
import UniversityManagemant.demo.models.Role;
import UniversityManagemant.demo.models.User;
import UniversityManagemant.demo.repositories.GiangVienRepository;
import UniversityManagemant.demo.repositories.KhoaRepository;
import UniversityManagemant.demo.repositories.UserRepository;
import UniversityManagemant.demo.services.serviceInterface.KhoaService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class KhoaServiceImpl implements KhoaService {
    final KhoaRepository khoaRepository;
    final KhoaMapper khoaMapper;
    final UserRepository userRepository;
    final GiangVienRepository giangVienRepository;


    @Override
    public KhoaResDto createKhoa(CreateKhoaReq req) {
        Khoa khoa = khoaMapper.toEntity(req);
        validateKhoa(khoa, null);
        Khoa saved = khoaRepository.save(khoa);
        return khoaMapper.toResDto(saved);
    }

    @Override
    public KhoaResDto getKhoaById(Long id) {
        return khoaRepository.findById(id)
                .map(khoaMapper::toResDto)
                .orElseThrow(() -> new RuntimeException("Khoa not found"));
    }

    @Override
    public List<KhoaResDto> getAllKhoa() {
        return khoaRepository.findAll().stream()
                .map(khoaMapper::toResDto)
                .collect(Collectors.toList());
    }

    @Override
    public KhoaResDto updateKhoa(Long id, UpdateKhoaReq req) {
        Khoa khoa = khoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tim thấy khoa với ID: " + id));

        if (req.getGiangVienId() != null) {
            GiangVien giangVien = giangVienRepository.findById(req.getGiangVienId())
                    .orElseThrow(() -> new RuntimeException("Giang Vien with ID: " + req.getGiangVienId() + " not found"));

            Khoa assignedKhoa = khoaRepository.findByTruongKhoa_Id(req.getGiangVienId());
            if (assignedKhoa != null && !assignedKhoa.getId().equals(id)) {
                throw new RuntimeException("Giang Vien with ID: " + req.getGiangVienId()
                        + " is already assigned as truong khoa of another khoa");
            }

            khoa.setTruongKhoa(giangVien);
        }

        khoaMapper.updateEntityFromDto(req, khoa);
        validateKhoa(khoa, id);

        Khoa updated = khoaRepository.save(khoa);
        return khoaMapper.toResDto(updated);
    }

    @Override
    public void deleteKhoa(Long id) {
        khoaRepository.deleteById(id);
    }

    private void validateKhoa(Khoa khoa, Long excludeId) {
        if (khoa.getMaKhoa() != null && khoaRepository.existsByMaKhoaAndIdNot(khoa.getMaKhoa(), excludeId)) {
            throw new RuntimeException("Ma Khoa already exists: " + khoa.getMaKhoa());
        }

        if (khoa.getTenKhoa() != null && khoaRepository.existsByTenKhoaIgnoreCaseAndIdNot(khoa.getTenKhoa(), excludeId)) {
            throw new RuntimeException("Ten Khoa already exists: " + khoa.getTenKhoa());
        }
    }
}
