package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateLecturerReq;
import UniversityManagemant.demo.dtos.response.LecturerResDto;

public interface GiangVienService {
    LecturerResDto createGiangVien(CreateLecturerReq req);
    LecturerResDto getGiangVienById(Long id);
    List<LecturerResDto> getAllGiangVien();
    LecturerResDto updateGiangVien(Long id, CreateLecturerReq req);
    void deleteGiangVien(Long id);
}
