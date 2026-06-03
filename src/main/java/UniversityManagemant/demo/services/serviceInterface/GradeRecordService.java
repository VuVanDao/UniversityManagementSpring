package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateGradeRecordReq;
import UniversityManagemant.demo.dtos.response.GradeRecordResDto;

public interface GradeRecordService {
    GradeRecordResDto createGradeRecord(CreateGradeRecordReq req);
    GradeRecordResDto getGradeRecordById(Long id);
    List<GradeRecordResDto> getAllGradeRecords();
    GradeRecordResDto updateGradeRecord(Long id, CreateGradeRecordReq req);
    void deleteGradeRecord(Long id);
}
