package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateClassManagementReq;
import UniversityManagemant.demo.dtos.response.ClassManagementResDto;

public interface ClassManagementService {
    ClassManagementResDto createClassManagement(CreateClassManagementReq req);
    ClassManagementResDto getClassManagementById(Long id);
    List<ClassManagementResDto> getAllClassManagements();
    ClassManagementResDto updateClassManagement(Long id, CreateClassManagementReq req);
    void deleteClassManagement(Long id);
}
