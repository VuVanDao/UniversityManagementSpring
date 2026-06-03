package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateNhomHocReq;
import UniversityManagemant.demo.dtos.response.NhomHocResDto;
import UniversityManagemant.demo.models.StudyGroup;

@Component
public class NhomHocMapper {

    public NhomHocResDto toResDto(StudyGroup nhomHoc) {
        return NhomHocResDto.builder()
                .id(nhomHoc.getId())
                .maNhom(nhomHoc.getStudyGroupCode())
                .tenNhom(nhomHoc.getStudyGroupName())
                .tietBatDau(nhomHoc.getStartTime())
                .tietKetThuc(nhomHoc.getEndTime())
                .fromTime(nhomHoc.getFromTime())
                .toTime(nhomHoc.getToTime())
                .soLuongSinhVien(nhomHoc.getNumberOfStudents())
                .tenMonHoc(nhomHoc.getSubject() != null ? nhomHoc.getSubject().getSubjectName() : null)
                .tenGiangVien(nhomHoc.getLecturer() != null && nhomHoc.getLecturer().getUser() != null ? nhomHoc.getLecturer().getUser().getUsername() : null)
                .tenPhongHoc(nhomHoc.getClassroom() != null ? nhomHoc.getClassroom().getClassroomName() : null)
                .build();
    }
}
