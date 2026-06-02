package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateLopQuanLiReq;
import UniversityManagemant.demo.dtos.response.LopQuanLiResDto;

public interface LopQuanLiService {
    LopQuanLiResDto createLopQuanLi(CreateLopQuanLiReq req);
    LopQuanLiResDto getLopQuanLiById(Long id);
    List<LopQuanLiResDto> getAllLopQuanLi();
    LopQuanLiResDto updateLopQuanLi(Long id, CreateLopQuanLiReq req);
    void deleteLopQuanLi(Long id);
}
