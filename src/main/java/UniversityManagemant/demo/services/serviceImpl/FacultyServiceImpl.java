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
import UniversityManagemant.demo.repositories.LecturerRepository;
import UniversityManagemant.demo.repositories.FacultyRepository;
import UniversityManagemant.demo.repositories.UserRepository;
import UniversityManagemant.demo.services.serviceInterface.FacultyService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    final FacultyRepository facultyRepository;
    final KhoaMapper khoaMapper;
    final UserRepository userRepository;
    final LecturerRepository lecturerRepository;

    @Override
    public FacultyResDto createFaculty(CreateFacultyReq req) {
        Faculty faculty = khoaMapper.toEntity(req);
        validateFaculty(faculty, null);
        Faculty saved = facultyRepository.save(faculty);
        return khoaMapper.toResDto(saved);
    }

    @Override
    public FacultyResDto getFacultyById(Long id) {
        return facultyRepository.findById(id)
                .map(khoaMapper::toResDto)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
    }

    @Override
    public List<FacultyResDto> getAllFaculties() {
        return facultyRepository.findAll().stream()
                .map(khoaMapper::toResDto)
                .collect(Collectors.toList());
    }

    @Override
    public FacultyResDto updateFaculty(Long id, UpdateFacultyReq req) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khoa với ID: " + id));

        if (req.getLecturerId() != null) {
            Lecturer lecturer = lecturerRepository.findById(req.getLecturerId())
                    .orElseThrow(() -> new RuntimeException("Lecturer with ID: " + req.getLecturerId() + " not found"));

            Faculty assignedFaculty = facultyRepository.findByDean_Id(req.getLecturerId());
            if (assignedFaculty != null && !assignedFaculty.getId().equals(id)) {
                throw new RuntimeException("Lecturer with ID: " + req.getLecturerId()
                        + " is already assigned as dean of another faculty");
            }
            faculty.setDean(lecturer);
        }

        khoaMapper.updateEntityFromDto(req, faculty);
        validateFaculty(faculty, id);

        Faculty updated = facultyRepository.save(faculty);
        return khoaMapper.toResDto(updated);
    }

    @Override
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    private void validateFaculty(Faculty faculty, Long excludeId) {
        if (faculty.getFacultyCode() != null && facultyRepository.existsByFacultyCodeAndIdNot(faculty.getFacultyCode(), excludeId)) {
            throw new RuntimeException("Faculty Code already exists: " + faculty.getFacultyCode());
        }

        if (faculty.getFacultyName() != null && facultyRepository.existsByFacultyNameIgnoreCaseAndIdNot(faculty.getFacultyName(), excludeId)) {
            throw new RuntimeException("Faculty Name already exists: " + faculty.getFacultyName());
        }
    }
}
