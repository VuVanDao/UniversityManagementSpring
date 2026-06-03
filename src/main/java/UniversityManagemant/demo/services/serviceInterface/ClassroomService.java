package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateClassroomReq;
import UniversityManagemant.demo.dtos.response.ClassroomResDto;

public interface ClassroomService {
    ClassroomResDto createClassroom(CreateClassroomReq req);
    ClassroomResDto getClassroomById(Long id);
    List<ClassroomResDto> getAllClassrooms();
    ClassroomResDto updateClassroom(Long id, CreateClassroomReq req);
    void deleteClassroom(Long id);
}
