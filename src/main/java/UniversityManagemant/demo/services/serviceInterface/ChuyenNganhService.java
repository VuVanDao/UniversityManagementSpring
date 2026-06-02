package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateChuyenNganhReq;
import UniversityManagemant.demo.dtos.response.ChuyenNganhResDto;

public interface ChuyenNganhService {
    ChuyenNganhResDto createChuyenNganh(CreateChuyenNganhReq req);
    ChuyenNganhResDto getChuyenNganhById(Long id);
    List<ChuyenNganhResDto> getAllChuyenNganh();
    ChuyenNganhResDto updateChuyenNganh(Long id, CreateChuyenNganhReq req);
    void deleteChuyenNganh(Long id);
}
