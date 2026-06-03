package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateClassManagementReq;
import UniversityManagemant.demo.dtos.response.ClassManagementResDto;

public interface LopQuanLiService {
    ClassManagementResDto createLopQuanLi(CreateClassManagementReq req);
    ClassManagementResDto getLopQuanLiById(Long id);
    List<ClassManagementResDto> getAllLopQuanLi();
    ClassManagementResDto updateLopQuanLi(Long id, CreateClassManagementReq req);
    void deleteLopQuanLi(Long id);
}
