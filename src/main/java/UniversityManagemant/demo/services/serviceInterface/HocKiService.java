package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateSemesterReq;
import UniversityManagemant.demo.dtos.response.SemesterResDto;

public interface HocKiService {
    SemesterResDto createHocKi(CreateSemesterReq req);
    SemesterResDto getHocKiById(Long id);
    List<SemesterResDto> getAllHocKi();
    SemesterResDto updateHocKi(Long id, CreateSemesterReq req);
    void deleteHocKi(Long id);
}
