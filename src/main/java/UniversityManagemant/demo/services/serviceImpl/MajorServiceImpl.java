package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateMajorReq;
import UniversityManagemant.demo.dtos.response.MajorResDto;
import UniversityManagemant.demo.models.Major;
import UniversityManagemant.demo.repositories.MajorRepository;
import UniversityManagemant.demo.repositories.FacultyRepository;
import UniversityManagemant.demo.services.serviceInterface.MajorService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MajorServiceImpl implements MajorService {
    final MajorRepository majorRepository;
    final FacultyRepository facultyRepository;

    @Override
    public MajorResDto createMajor(CreateMajorReq req) {
        Major major = Major.builder()
                .MajorCode(req.getMajorCode())
                .MajorName(req.getMajorName())
                .faculty(facultyRepository.findById(req.getFacultyId()).orElseThrow())
                .build();
        Major saved = majorRepository.save(major);
        return toDto(saved);
    }

    @Override
    public MajorResDto getMajorById(Long id) {
        return majorRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Major not found"));
    }

    @Override
    public List<MajorResDto> getAllMajors() {
        return majorRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MajorResDto updateMajor(Long id, CreateMajorReq req) {
        Major major = majorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Major not found"));
        major.setMajorCode(req.getMajorCode());
        major.setMajorName(req.getMajorName());
        major.setFaculty(facultyRepository.findById(req.getFacultyId()).orElseThrow());
        Major updated = majorRepository.save(major);
        return toDto(updated);
    }

    @Override
    public void deleteMajor(Long id) {
        majorRepository.deleteById(id);
    }

    private MajorResDto toDto(Major major) {
        return MajorResDto.builder()
                .id(major.getId())
                .majorCode(major.getMajorCode())
                .majorName(major.getMajorName())
                .facultyName(major.getFaculty() != null ? major.getFaculty().getFacultyName() : null)
                .build();
    }
}
