package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreatePhongHocReq;
import UniversityManagemant.demo.dtos.response.PhongHocResDto;

public interface PhongHocService {
    PhongHocResDto createPhongHoc(CreatePhongHocReq req);
    PhongHocResDto getPhongHocById(Long id);
    List<PhongHocResDto> getAllPhongHoc();
    PhongHocResDto updatePhongHoc(Long id, CreatePhongHocReq req);
    void deletePhongHoc(Long id);
}
