package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateSubjectReq;
import UniversityManagemant.demo.dtos.response.SubjectResDto;

public interface MonHocService {
    SubjectResDto createMonHoc(CreateSubjectReq req);
    SubjectResDto getMonHocById(Long id);
    List<SubjectResDto> getAllMonHoc();
    SubjectResDto updateMonHoc(Long id, CreateSubjectReq req);
    void deleteMonHoc(Long id);
}
