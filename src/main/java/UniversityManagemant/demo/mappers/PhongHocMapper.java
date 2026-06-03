package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateClassroomReq;
import UniversityManagemant.demo.dtos.response.ClassroomResDto;
import UniversityManagemant.demo.models.Classroom;

@Component
public class PhongHocMapper {

    public Classroom toEntity(CreateClassroomReq createClassroomReq) {
        return Classroom.builder()
                .classroomName(createClassroomReq.getClassroomName())
                .build();
    }

    public ClassroomResDto toResDto(Classroom phongHoc) {
        return ClassroomResDto.builder()
                .id(phongHoc.getId())
                .classroomName(phongHoc.getClassroomName())
                .build();
    }

    public void updateEntityFromDto(CreateClassroomReq createClassroomReq, Classroom phongHoc) {
        phongHoc.setClassroomName(createClassroomReq.getClassroomName());
    }
}
