package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateClassManagementReq;
import UniversityManagemant.demo.dtos.response.ClassManagementResDto;
import UniversityManagemant.demo.models.ClassManagement;
import UniversityManagemant.demo.repositories.ClassManagementRepository;
import UniversityManagemant.demo.repositories.MajorRepository;
import UniversityManagemant.demo.services.serviceInterface.ClassManagementService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ClassManagementServiceImpl implements ClassManagementService {
    final ClassManagementRepository classManagementRepository;
    final MajorRepository majorRepository;

    @Override
    public ClassManagementResDto createClassManagement(CreateClassManagementReq req) {
        ClassManagement classManagement = ClassManagement.builder()
                .classManagementCode(req.getClassManagementCode())
                .classManagementName(req.getClassManagementName())
                .major(majorRepository.findById(req.getMajorId()).orElseThrow())
                .build();
        ClassManagement saved = classManagementRepository.save(classManagement);
        return toDto(saved);
    }

    @Override
    public ClassManagementResDto getClassManagementById(Long id) {
        return classManagementRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("ClassManagement not found"));
    }

    @Override
    public List<ClassManagementResDto> getAllClassManagements() {
        return classManagementRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClassManagementResDto updateClassManagement(Long id, CreateClassManagementReq req) {
        ClassManagement classManagement = classManagementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClassManagement not found"));
        classManagement.setClassManagementCode(req.getClassManagementCode());
        classManagement.setClassManagementName(req.getClassManagementName());
        classManagement.setMajor(majorRepository.findById(req.getMajorId()).orElseThrow());
        ClassManagement updated = classManagementRepository.save(classManagement);
        return toDto(updated);
    }

    @Override
    public void deleteClassManagement(Long id) {
        classManagementRepository.deleteById(id);
    }

    private ClassManagementResDto toDto(ClassManagement classManagement) {
        return ClassManagementResDto.builder()
                .id(classManagement.getId())
                .classManagementCode(classManagement.getClassManagementCode())
                .classManagementName(classManagement.getClassManagementName())
                .majorName(classManagement.getMajor() != null ? classManagement.getMajor().getMajorName() : null)
                .build();
    }
}
