package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateSemesterReq;
import UniversityManagemant.demo.dtos.response.SemesterResDto;

public interface SemesterService {
    SemesterResDto createSemester(CreateSemesterReq req);
    SemesterResDto getSemesterById(Long id);
    List<SemesterResDto> getAllSemesters();
    SemesterResDto updateSemester(Long id, CreateSemesterReq req);
    void deleteSemester(Long id);
}
