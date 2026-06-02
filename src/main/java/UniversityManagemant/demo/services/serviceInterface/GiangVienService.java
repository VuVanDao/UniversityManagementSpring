package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateGiangVienReq;
import UniversityManagemant.demo.dtos.response.GiangVienResDto;

public interface GiangVienService {
    GiangVienResDto createGiangVien(CreateGiangVienReq req);
    GiangVienResDto getGiangVienById(Long id);
    List<GiangVienResDto> getAllGiangVien();
    GiangVienResDto updateGiangVien(Long id, CreateGiangVienReq req);
    void deleteGiangVien(Long id);
}
