package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import UniversityManagemant.demo.dtos.request.CreateFacultyReq;
import UniversityManagemant.demo.dtos.request.UpdateFacultyReq;
import UniversityManagemant.demo.dtos.response.FacultyResDto;

public interface FacultyService {
    @PreAuthorize("hasRole('ADMIN')")
    FacultyResDto createFaculty(CreateFacultyReq req);
    FacultyResDto getFacultyById(Long id);

    List<FacultyResDto> getAllFaculties();

    @PreAuthorize("hasRole('ADMIN')")
    FacultyResDto updateFaculty(Long id, UpdateFacultyReq req);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteFaculty(Long id);
}
