package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateClassroomReq;
import UniversityManagemant.demo.dtos.response.ClassroomResDto;
import UniversityManagemant.demo.models.Classroom;
import UniversityManagemant.demo.repositories.PhongHocRepository;
import UniversityManagemant.demo.services.serviceInterface.PhongHocService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PhongHocServiceImpl implements PhongHocService {
    final PhongHocRepository phongHocRepository;

    @Override
    public ClassroomResDto createPhongHoc(CreateClassroomReq req) {
        Classroom phongHoc = Classroom.builder()
                .classroomName(req.getClassroomName())
                .build();
        Classroom saved = phongHocRepository.save(phongHoc);
        return toDto(saved);
    }

    @Override
    public ClassroomResDto getPhongHocById(Long id) {
        return phongHocRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("PhongHoc not found"));
    }

    @Override
    public List<ClassroomResDto> getAllPhongHoc() {
        return phongHocRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClassroomResDto updatePhongHoc(Long id, CreateClassroomReq req) {
        Classroom phongHoc = phongHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PhongHoc not found"));
        phongHoc.setClassroomName(req.getClassroomName());
        Classroom updated = phongHocRepository.save(phongHoc);
        return toDto(updated);
    }

    @Override
    public void deletePhongHoc(Long id) {
        phongHocRepository.deleteById(id);
    }

    private ClassroomResDto toDto(Classroom phongHoc) {
        return ClassroomResDto.builder()
                .id(phongHoc.getId())
                .classroomName(phongHoc.getClassroomName())
                .build();
    }
}
