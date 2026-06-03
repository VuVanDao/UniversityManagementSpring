package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateSemesterReq;
import UniversityManagemant.demo.dtos.response.SemesterResDto;
import UniversityManagemant.demo.models.Semester;
import UniversityManagemant.demo.repositories.HocKiRepository;
import UniversityManagemant.demo.services.serviceInterface.SemesterService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SemesterServiceImpl implements SemesterService {
    final HocKiRepository hocKiRepository;

    @Override
    public SemesterResDto createSemester(CreateSemesterReq req) {
        Semester hocKi = Semester.builder()
                .semesterName(req.getSemesterName())
                .fromTime(req.getFromTime())
                .toTime(req.getToTime())
                .build();
        Semester saved = hocKiRepository.save(hocKi);
        return toDto(saved);
    }

    @Override
    public SemesterResDto getSemesterById(Long id) {
        return hocKiRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("HocKi not found"));
    }

    @Override
    public List<SemesterResDto> getAllSemesters() {
        return hocKiRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SemesterResDto updateSemester(Long id, CreateSemesterReq req) {
        Semester hocKi = hocKiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HocKi not found"));
        hocKi.setSemesterName(req.getSemesterName());
        hocKi.setFromTime(req.getFromTime());
        hocKi.setToTime(req.getToTime());
        Semester updated = hocKiRepository.save(hocKi);
        return toDto(updated);
    }

    @Override
    public void deleteSemester(Long id) {
        hocKiRepository.deleteById(id);
    }

    private SemesterResDto toDto(Semester hocKi) {
        return SemesterResDto.builder()
                .id(hocKi.getId())
                .semesterName(hocKi.getSemesterName())
                .fromTime(hocKi.getFromTime())
                .toTime(hocKi.getToTime())
                .build();
    }
}
