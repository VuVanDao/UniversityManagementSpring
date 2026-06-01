package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateHocKiReq;
import UniversityManagemant.demo.dtos.response.HocKiResDto;
import UniversityManagemant.demo.models.HocKi;

@Component
public class HocKiMapper {

    public HocKi toEntity(CreateHocKiReq createHocKiReq) {
        return HocKi.builder()
                .tenHocKi(createHocKiReq.getTenHocKi())
                .fromTime(createHocKiReq.getFromTime())
                .toTime(createHocKiReq.getToTime())
                .build();
    }

    public HocKiResDto toResDto(HocKi hocKi) {
        return HocKiResDto.builder()
                .id(hocKi.getId())
                .tenHocKi(hocKi.getTenHocKi())
                .fromTime(hocKi.getFromTime())
                .toTime(hocKi.getToTime())
                .build();
    }

    public void updateEntityFromDto(CreateHocKiReq createHocKiReq, HocKi hocKi) {
        hocKi.setTenHocKi(createHocKiReq.getTenHocKi());
        hocKi.setFromTime(createHocKiReq.getFromTime());
        hocKi.setToTime(createHocKiReq.getToTime());
    }
}
