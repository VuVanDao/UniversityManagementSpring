package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateStudyGroupReq;
import UniversityManagemant.demo.dtos.response.StudyGroupResDto;

public interface StudyGroupService {
    StudyGroupResDto createStudyGroup(CreateStudyGroupReq req);
    StudyGroupResDto getStudyGroupById(Long id);
    List<StudyGroupResDto> getAllStudyGroups();
    StudyGroupResDto updateStudyGroup(Long id, CreateStudyGroupReq req);
    void deleteStudyGroup(Long id);
}
