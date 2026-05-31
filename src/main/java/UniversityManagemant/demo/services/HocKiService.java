package UniversityManagemant.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateHocKiReq;
import UniversityManagemant.demo.dtos.response.HocKiResDto;
import UniversityManagemant.demo.models.HocKi;
import UniversityManagemant.demo.repositories.HocKiRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class HocKiService {
    final HocKiRepository hocKiRepository;

    public HocKiResDto createHocKi(CreateHocKiReq req) {
        HocKi hocKi = HocKi.builder()
                .tenHocKi(req.getTenHocKi())
                .fromTime(req.getFromTime())
                .toTime(req.getToTime())
                .build();
        HocKi saved = hocKiRepository.save(hocKi);
        return toDto(saved);
    }

    public HocKiResDto getHocKiById(Long id) {
        return hocKiRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("HocKi not found"));
    }

    public List<HocKiResDto> getAllHocKi() {
        return hocKiRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public HocKiResDto updateHocKi(Long id, CreateHocKiReq req) {
        HocKi hocKi = hocKiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HocKi not found"));
        hocKi.setTenHocKi(req.getTenHocKi());
        hocKi.setFromTime(req.getFromTime());
        hocKi.setToTime(req.getToTime());
        HocKi updated = hocKiRepository.save(hocKi);
        return toDto(updated);
    }

    public void deleteHocKi(Long id) {
        hocKiRepository.deleteById(id);
    }

    private HocKiResDto toDto(HocKi hocKi) {
        return HocKiResDto.builder()
                .id(hocKi.getId())
                .tenHocKi(hocKi.getTenHocKi())
                .fromTime(hocKi.getFromTime())
                .toTime(hocKi.getToTime())
                .build();
    }
}
