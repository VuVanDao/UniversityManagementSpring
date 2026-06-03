package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateFacultyReq;
import UniversityManagemant.demo.dtos.request.UpdateFacultyReq;
import UniversityManagemant.demo.dtos.response.FacultyResDto;
import UniversityManagemant.demo.mappers.KhoaMapper;
import UniversityManagemant.demo.models.Lecturer;
import UniversityManagemant.demo.models.Faculty;
import UniversityManagemant.demo.repositories.GiangVienRepository;
import UniversityManagemant.demo.repositories.KhoaRepository;
import UniversityManagemant.demo.repositories.UserRepository;
import UniversityManagemant.demo.services.serviceInterface.FacultyService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    final KhoaRepository khoaRepository;
    final KhoaMapper khoaMapper;
    final UserRepository userRepository;
    final GiangVienRepository giangVienRepository;

    @Override
    public FacultyResDto createFaculty(CreateFacultyReq req) {
        Faculty khoa = khoaMapper.toEntity(req);
        validateKhoa(khoa, null);
        Faculty saved = khoaRepository.save(khoa);
        return khoaMapper.toResDto(saved);
    }

    @Override
    public FacultyResDto getFacultyById(Long id) {
        return khoaRepository.findById(id)
                .map(khoaMapper::toResDto)
                .orElseThrow(() -> new RuntimeException("Khoa not found"));
    }

    @Override
    public List<FacultyResDto> getAllFaculties() {
        return khoaRepository.findAll().stream()
                .map(khoaMapper::toResDto)
                .collect(Collectors.toList());
    }

    @Override
    public FacultyResDto updateFaculty(Long id, UpdateFacultyReq req) {
        Faculty khoa = khoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tim thấy khoa với ID: " + id));

        if (req.getLecturerId() != null) {
            Lecturer giangVien = giangVienRepository.findById(req.getLecturerId())
                    .orElseThrow(() -> new RuntimeException("Giang Vien with ID: " + req.getLecturerId() + " not found"));

            Faculty assignedKhoa = khoaRepository.findByDean_Id(req.getLecturerId());
            if (assignedKhoa != null && !assignedKhoa.getId().equals(id)) {
                throw new RuntimeException("Giang Vien with ID: " + req.getLecturerId()
                        + " is already assigned as truong khoa of another khoa");
            }
            khoa.setDean(giangVien);
        }

        khoaMapper.updateEntityFromDto(req, khoa);
        validateKhoa(khoa, id);

        Faculty updated = khoaRepository.save(khoa);
        return khoaMapper.toResDto(updated);
    }

    @Override
    public void deleteFaculty(Long id) {
        khoaRepository.deleteById(id);
    }

    private void validateKhoa(Faculty khoa, Long excludeId) {
        if (khoa.getFacultyCode() != null && khoaRepository.existsByFacultyCodeAndIdNot(khoa.getFacultyCode(), excludeId)) {
            throw new RuntimeException("Ma Khoa already exists: " + khoa.getFacultyCode());
        }

        if (khoa.getFacultyName() != null && khoaRepository.existsByFacultyNameIgnoreCaseAndIdNot(khoa.getFacultyName(), excludeId)) {
            throw new RuntimeException("Ten Khoa already exists: " + khoa.getFacultyName());
        }
    }
}
