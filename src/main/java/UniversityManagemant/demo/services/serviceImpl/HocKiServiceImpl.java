package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateHocKiReq;
import UniversityManagemant.demo.dtos.response.HocKiResDto;
import UniversityManagemant.demo.models.Semester;
import UniversityManagemant.demo.repositories.HocKiRepository;
import UniversityManagemant.demo.services.serviceInterface.HocKiService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class HocKiServiceImpl implements HocKiService {
    final HocKiRepository hocKiRepository;

    @Override
    public HocKiResDto createHocKi(CreateHocKiReq req) {
        Semester hocKi = Semester.builder()
                .semesterName(req.getTenHocKi())
                .fromTime(req.getFromTime())
                .toTime(req.getToTime())
                .build();
        Semester saved = hocKiRepository.save(hocKi);
        return toDto(saved);
    }

    @Override
    public HocKiResDto getHocKiById(Long id) {
        return hocKiRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("HocKi not found"));
    }

    @Override
    public List<HocKiResDto> getAllHocKi() {
        return hocKiRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HocKiResDto updateHocKi(Long id, CreateHocKiReq req) {
        Semester hocKi = hocKiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HocKi not found"));
        hocKi.setSemesterName(req.getTenHocKi());
        hocKi.setFromTime(req.getFromTime());
        hocKi.setToTime(req.getToTime());
        Semester updated = hocKiRepository.save(hocKi);
        return toDto(updated);
    }

    @Override
    public void deleteHocKi(Long id) {
        hocKiRepository.deleteById(id);
    }

    private HocKiResDto toDto(Semester hocKi) {
        return HocKiResDto.builder()
                .id(hocKi.getId())
                .tenHocKi(hocKi.getSemesterName())
                .fromTime(hocKi.getFromTime())
                .toTime(hocKi.getToTime())
                .build();
    }
}
