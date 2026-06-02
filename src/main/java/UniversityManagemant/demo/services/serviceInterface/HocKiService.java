package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateHocKiReq;
import UniversityManagemant.demo.dtos.response.HocKiResDto;

public interface HocKiService {
    HocKiResDto createHocKi(CreateHocKiReq req);
    HocKiResDto getHocKiById(Long id);
    List<HocKiResDto> getAllHocKi();
    HocKiResDto updateHocKi(Long id, CreateHocKiReq req);
    void deleteHocKi(Long id);
}
