package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateStudyGroupReq;
import UniversityManagemant.demo.dtos.response.StudyGroupResDto;

public interface NhomHocService {
    StudyGroupResDto createNhomHoc(CreateStudyGroupReq req);
    StudyGroupResDto getNhomHocById(Long id);
    List<StudyGroupResDto> getAllNhomHoc();
    StudyGroupResDto updateNhomHoc(Long id, CreateStudyGroupReq req);
    void deleteNhomHoc(Long id);
}
