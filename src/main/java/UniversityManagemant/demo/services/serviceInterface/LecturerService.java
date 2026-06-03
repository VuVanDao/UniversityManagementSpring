package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateLecturerReq;
import UniversityManagemant.demo.dtos.response.LecturerResDto;

public interface LecturerService {
    LecturerResDto createLecturer(CreateLecturerReq req);
    LecturerResDto getLecturerById(Long id);
    List<LecturerResDto> getAllLecturers();
    LecturerResDto updateLecturer(Long id, CreateLecturerReq req);
    void deleteLecturer(Long id);
}
