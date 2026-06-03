package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateSubjectReq;
import UniversityManagemant.demo.dtos.response.SubjectResDto;

public interface SubjectService {
    SubjectResDto createSubject(CreateSubjectReq req);
    SubjectResDto getSubjectById(Long id);
    List<SubjectResDto> getAllSubjects();
    SubjectResDto updateSubject(Long id, CreateSubjectReq req);
    void deleteSubject(Long id);
}
