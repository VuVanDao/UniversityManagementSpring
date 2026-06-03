package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateSemesterReq;
import UniversityManagemant.demo.dtos.response.SemesterResDto;
import UniversityManagemant.demo.models.Semester;

@Component
public class HocKiMapper {

    public Semester toEntity(CreateSemesterReq createSemesterReq) {
        return Semester.builder()
                .semesterName(createSemesterReq.getSemesterName())
                .fromTime(createSemesterReq.getFromTime())
                .toTime(createSemesterReq.getToTime())
                .build();
    }

    public SemesterResDto toResDto(Semester hocKi) {
        return SemesterResDto.builder()
                .id(hocKi.getId())
                .semesterName(hocKi.getSemesterName())
                .fromTime(hocKi.getFromTime())
                .toTime(hocKi.getToTime())
                .build();
    }

    public void updateEntityFromDto(CreateSemesterReq createSemesterReq, Semester hocKi) {
        hocKi.setSemesterName(createSemesterReq.getSemesterName());
        hocKi.setFromTime(createSemesterReq.getFromTime());
        hocKi.setToTime(createSemesterReq.getToTime());
    }
}
