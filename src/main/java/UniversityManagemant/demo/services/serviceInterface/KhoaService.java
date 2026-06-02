package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateKhoaReq;
import UniversityManagemant.demo.dtos.response.KhoaResDto;

public interface KhoaService {
    KhoaResDto createKhoa(CreateKhoaReq req);
    KhoaResDto getKhoaById(Long id);
    List<KhoaResDto> getAllKhoa();
    KhoaResDto updateKhoa(Long id, CreateKhoaReq req);
    void deleteKhoa(Long id);
}
