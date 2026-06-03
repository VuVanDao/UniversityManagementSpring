package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateGradeRecordReq;
import UniversityManagemant.demo.dtos.response.GradeRecordResDto;

public interface BangDiemService {
    GradeRecordResDto createBangDiem(CreateGradeRecordReq req);
    GradeRecordResDto getBangDiemById(Long id);
    List<GradeRecordResDto> getAllBangDiem();
    GradeRecordResDto updateBangDiem(Long id, CreateGradeRecordReq req);
    void deleteBangDiem(Long id);
}
