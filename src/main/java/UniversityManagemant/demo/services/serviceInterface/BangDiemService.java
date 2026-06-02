package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateBangDiemReq;
import UniversityManagemant.demo.dtos.response.BangDiemResDto;

public interface BangDiemService {
    BangDiemResDto createBangDiem(CreateBangDiemReq req);
    BangDiemResDto getBangDiemById(Long id);
    List<BangDiemResDto> getAllBangDiem();
    BangDiemResDto updateBangDiem(Long id, CreateBangDiemReq req);
    void deleteBangDiem(Long id);
}
