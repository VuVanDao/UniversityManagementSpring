package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateClassroomReq;
import UniversityManagemant.demo.dtos.response.ClassroomResDto;
import UniversityManagemant.demo.models.Classroom;
import UniversityManagemant.demo.repositories.ClassroomRepository;
import UniversityManagemant.demo.services.serviceInterface.ClassroomService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {
    final ClassroomRepository classroomRepository;

    @Override
    public ClassroomResDto createClassroom(CreateClassroomReq req) {
        Classroom classroom = Classroom.builder()
                .classroomName(req.getClassroomName())
                .build();
        Classroom saved = classroomRepository.save(classroom);
        return toDto(saved);
    }

    @Override
    public ClassroomResDto getClassroomById(Long id) {
        return classroomRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Classroom not found"));
    }

    @Override
    public List<ClassroomResDto> getAllClassrooms() {
        return classroomRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClassroomResDto updateClassroom(Long id, CreateClassroomReq req) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classroom not found"));
        classroom.setClassroomName(req.getClassroomName());
        Classroom updated = classroomRepository.save(classroom);
        return toDto(updated);
    }

    @Override
    public void deleteClassroom(Long id) {
        classroomRepository.deleteById(id);
    }

    private ClassroomResDto toDto(Classroom classroom) {
        return ClassroomResDto.builder()
                .id(classroom.getId())
                .classroomName(classroom.getClassroomName())
                .build();
    }
}
