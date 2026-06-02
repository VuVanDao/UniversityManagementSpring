package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateMonHocReq;
import UniversityManagemant.demo.dtos.response.MonHocResDto;

public interface MonHocService {
    MonHocResDto createMonHoc(CreateMonHocReq req);
    MonHocResDto getMonHocById(Long id);
    List<MonHocResDto> getAllMonHoc();
    MonHocResDto updateMonHoc(Long id, CreateMonHocReq req);
    void deleteMonHoc(Long id);
}
