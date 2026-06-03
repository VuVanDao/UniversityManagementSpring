package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateSubjectReq;
import UniversityManagemant.demo.dtos.response.SubjectResDto;
import UniversityManagemant.demo.models.Subject;
import UniversityManagemant.demo.repositories.MonHocRepository;
import UniversityManagemant.demo.services.serviceInterface.MonHocService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MonHocServiceImpl implements MonHocService {
    final MonHocRepository monHocRepository;

    @Override
    public SubjectResDto createMonHoc(CreateSubjectReq req) {
        Subject monHoc = Subject.builder()
                .subjectCode(req.getSubjectCode())
                .subjectName(req.getSubjectName())
                .credits(req.getCredits())
                .build();
        Subject saved = monHocRepository.save(monHoc);
        return toDto(saved);
    }

    @Override
    public SubjectResDto getMonHocById(Long id) {
        return monHocRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("MonHoc not found"));
    }

    @Override
    public List<SubjectResDto> getAllMonHoc() {
        return monHocRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectResDto updateMonHoc(Long id, CreateSubjectReq req) {
        Subject monHoc = monHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MonHoc not found"));
        monHoc.setSubjectCode(req.getSubjectCode());
        monHoc.setSubjectName(req.getSubjectName());
        monHoc.setCredits(req.getCredits());
        Subject updated = monHocRepository.save(monHoc);
        return toDto(updated);
    }

    @Override
    public void deleteMonHoc(Long id) {
        monHocRepository.deleteById(id);
    }

    private SubjectResDto toDto(Subject monHoc) {
        return SubjectResDto.builder()
                .id(monHoc.getId())
                .subjectCode(monHoc.getSubjectCode())
                .subjectName(monHoc.getSubjectName())
                .credits(monHoc.getCredits())
                .build();
    }
}
