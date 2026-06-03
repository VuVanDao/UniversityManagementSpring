package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateSubjectReq;
import UniversityManagemant.demo.dtos.response.SubjectResDto;
import UniversityManagemant.demo.models.Subject;
import UniversityManagemant.demo.repositories.SubjectRepository;
import UniversityManagemant.demo.services.serviceInterface.SubjectService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    final SubjectRepository subjectRepository;

    @Override
    public SubjectResDto createSubject(CreateSubjectReq req) {
        Subject subject = Subject.builder()
                .subjectCode(req.getSubjectCode())
                .subjectName(req.getSubjectName())
                .credits(req.getCredits())
                .build();
        Subject saved = subjectRepository.save(subject);
        return toDto(saved);
    }

    @Override
    public SubjectResDto getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
    }

    @Override
    public List<SubjectResDto> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectResDto updateSubject(Long id, CreateSubjectReq req) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        subject.setSubjectCode(req.getSubjectCode());
        subject.setSubjectName(req.getSubjectName());
        subject.setCredits(req.getCredits());
        Subject updated = subjectRepository.save(subject);
        return toDto(updated);
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    private SubjectResDto toDto(Subject subject) {
        return SubjectResDto.builder()
                .id(subject.getId())
                .subjectCode(subject.getSubjectCode())
                .subjectName(subject.getSubjectName())
                .credits(subject.getCredits())
                .build();
    }
}
