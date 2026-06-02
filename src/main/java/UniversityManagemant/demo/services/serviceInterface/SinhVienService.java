package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateSinhVienReq;
import UniversityManagemant.demo.dtos.response.SinhVienResDto;

public interface SinhVienService {
    SinhVienResDto createSinhVien(CreateSinhVienReq req);
    SinhVienResDto getSinhVienById(Long id);
    List<SinhVienResDto> getAllSinhVien();
    SinhVienResDto updateSinhVien(Long id, CreateSinhVienReq req);
    void deleteSinhVien(Long id);
}
