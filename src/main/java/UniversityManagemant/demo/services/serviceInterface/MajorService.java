package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateMajorReq;
import UniversityManagemant.demo.dtos.response.MajorResDto;

public interface MajorService {
    MajorResDto createMajor(CreateMajorReq req);
    MajorResDto getMajorById(Long id);
    List<MajorResDto> getAllMajors();
    MajorResDto updateMajor(Long id, CreateMajorReq req);
    void deleteMajor(Long id);
}
