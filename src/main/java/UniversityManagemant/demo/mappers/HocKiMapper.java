package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateHocKiReq;
import UniversityManagemant.demo.dtos.response.HocKiResDto;
import UniversityManagemant.demo.models.Semester;

@Component
public class HocKiMapper {

    public Semester toEntity(CreateHocKiReq createHocKiReq) {
        return Semester.builder()
                .tenHocKi(createHocKiReq.getTenHocKi())
                .fromTime(createHocKiReq.getFromTime())
                .toTime(createHocKiReq.getToTime())
                .build();
    }

    public HocKiResDto toResDto(Semester hocKi) {
        return HocKiResDto.builder()
                .id(hocKi.getId())
                .tenHocKi(hocKi.getTenHocKi())
                .fromTime(hocKi.getFromTime())
                .toTime(hocKi.getToTime())
                .build();
    }

    public void updateEntityFromDto(CreateHocKiReq createHocKiReq, Semester hocKi) {
        hocKi.setTenHocKi(createHocKiReq.getTenHocKi());
        hocKi.setFromTime(createHocKiReq.getFromTime());
        hocKi.setToTime(createHocKiReq.getToTime());
    }
}
