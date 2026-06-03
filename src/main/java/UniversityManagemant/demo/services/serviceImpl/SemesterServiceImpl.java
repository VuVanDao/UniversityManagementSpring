package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateSemesterReq;
import UniversityManagemant.demo.dtos.response.SemesterResDto;
import UniversityManagemant.demo.models.Semester;
import UniversityManagemant.demo.repositories.SemesterRepository;
import UniversityManagemant.demo.services.serviceInterface.SemesterService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SemesterServiceImpl implements SemesterService {
    final SemesterRepository semesterRepository;

    @Override
    public SemesterResDto createSemester(CreateSemesterReq req) {
        Semester semester = Semester.builder()
                .semesterName(req.getSemesterName())
                .fromTime(req.getFromTime())
                .toTime(req.getToTime())
                .build();
        Semester saved = semesterRepository.save(semester);
        return toDto(saved);
    }

    @Override
    public SemesterResDto getSemesterById(Long id) {
        return semesterRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Semester not found"));
    }

    @Override
    public List<SemesterResDto> getAllSemesters() {
        return semesterRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SemesterResDto updateSemester(Long id, CreateSemesterReq req) {
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Semester not found"));
        semester.setSemesterName(req.getSemesterName());
        semester.setFromTime(req.getFromTime());
        semester.setToTime(req.getToTime());
        Semester updated = semesterRepository.save(semester);
        return toDto(updated);
    }

    @Override
    public void deleteSemester(Long id) {
        semesterRepository.deleteById(id);
    }

    private SemesterResDto toDto(Semester semester) {
        return SemesterResDto.builder()
                .id(semester.getId())
                .semesterName(semester.getSemesterName())
                .fromTime(semester.getFromTime())
                .toTime(semester.getToTime())
                .build();
    }
}
