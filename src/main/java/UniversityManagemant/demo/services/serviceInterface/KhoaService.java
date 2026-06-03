package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import UniversityManagemant.demo.dtos.request.CreateFacultyReq;
import UniversityManagemant.demo.dtos.request.UpdateFacultyReq;
import UniversityManagemant.demo.dtos.response.FacultyResDto;

public interface KhoaService {
    @PreAuthorize("hasRole('ADMIN')")
    FacultyResDto createKhoa(CreateFacultyReq req);
    FacultyResDto getKhoaById(Long id);

    List<FacultyResDto> getAllKhoa();

    @PreAuthorize("hasRole('ADMIN')")
    FacultyResDto updateKhoa(Long id, UpdateFacultyReq req);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteKhoa(Long id);
}
