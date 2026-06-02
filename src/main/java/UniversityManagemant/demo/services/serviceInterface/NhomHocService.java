package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateNhomHocReq;
import UniversityManagemant.demo.dtos.response.NhomHocResDto;

public interface NhomHocService {
    NhomHocResDto createNhomHoc(CreateNhomHocReq req);
    NhomHocResDto getNhomHocById(Long id);
    List<NhomHocResDto> getAllNhomHoc();
    NhomHocResDto updateNhomHoc(Long id, CreateNhomHocReq req);
    void deleteNhomHoc(Long id);
}
