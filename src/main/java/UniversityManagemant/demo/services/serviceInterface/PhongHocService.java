package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateClassroomReq;
import UniversityManagemant.demo.dtos.response.ClassroomResDto;

public interface PhongHocService {
    ClassroomResDto createPhongHoc(CreateClassroomReq req);
    ClassroomResDto getPhongHocById(Long id);
    List<ClassroomResDto> getAllPhongHoc();
    ClassroomResDto updatePhongHoc(Long id, CreateClassroomReq req);
    void deletePhongHoc(Long id);
}
