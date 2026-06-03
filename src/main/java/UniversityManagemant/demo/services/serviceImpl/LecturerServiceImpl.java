package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateLecturerReq;
import UniversityManagemant.demo.dtos.response.LecturerResDto;
import UniversityManagemant.demo.mappers.GiangVienMapper;
import UniversityManagemant.demo.models.Lecturer;
import UniversityManagemant.demo.repositories.LecturerRepository;
import UniversityManagemant.demo.repositories.UserRepository;
import UniversityManagemant.demo.repositories.ClassManagementRepository;
import UniversityManagemant.demo.services.serviceInterface.LecturerService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LecturerServiceImpl implements LecturerService {
    final LecturerRepository lecturerRepository;
    final UserRepository userRepository;
    final ClassManagementRepository classManagementRepository;
    final GiangVienMapper giangVienMapper;

    @Override
    public LecturerResDto createLecturer(CreateLecturerReq req) {
        Lecturer lecturer = Lecturer.builder()
                .user(userRepository.findById(req.getUserId()).orElseThrow())
                .classManagement(classManagementRepository.findById(req.getClassManagementId()).orElseThrow())
                .build();
        Lecturer saved = lecturerRepository.save(lecturer);
        return giangVienMapper.toResDto(saved);
    }

    @Override
    public LecturerResDto getLecturerById(Long id) {
        return lecturerRepository.findById(id)
                .map(giangVienMapper::toResDto)
                .orElseThrow(() -> new RuntimeException("Lecturer not found"));
    }

    @Override
    public List<LecturerResDto> getAllLecturers() {
        return lecturerRepository.findAll().stream()
                .map(giangVienMapper::toResDto)
                .collect(Collectors.toList());
    }

    @Override
    public LecturerResDto updateLecturer(Long id, CreateLecturerReq req) {
        Lecturer lecturer = lecturerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lecturer not found"));
        lecturer.setUser(userRepository.findById(req.getUserId()).orElseThrow());
        lecturer.setClassManagement(classManagementRepository.findById(req.getClassManagementId()).orElseThrow());
        Lecturer updated = lecturerRepository.save(lecturer);
        return giangVienMapper.toResDto(updated);
    }

    @Override
    public void deleteLecturer(Long id) {
        lecturerRepository.deleteById(id);
    }
}
