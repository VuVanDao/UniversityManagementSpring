package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateMajorReq;
import UniversityManagemant.demo.dtos.response.MajorResDto;

public interface ChuyenNganhService {
    MajorResDto createChuyenNganh(CreateMajorReq req);
    MajorResDto getChuyenNganhById(Long id);
    List<MajorResDto> getAllChuyenNganh();
    MajorResDto updateChuyenNganh(Long id, CreateMajorReq req);
    void deleteChuyenNganh(Long id);
}
