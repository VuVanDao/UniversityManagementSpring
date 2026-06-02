package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import UniversityManagemant.demo.dtos.request.CreateKhoaReq;
import UniversityManagemant.demo.dtos.request.UpdateKhoaReq;
import UniversityManagemant.demo.dtos.response.KhoaResDto;

public interface KhoaService {
    @PreAuthorize("hasRole('ADMIN')")
    KhoaResDto createKhoa(CreateKhoaReq req);
    KhoaResDto getKhoaById(Long id);

    List<KhoaResDto> getAllKhoa();

    @PreAuthorize("hasRole('ADMIN')")
    KhoaResDto updateKhoa(Long id, UpdateKhoaReq req);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteKhoa(Long id);
}
