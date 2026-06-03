package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.response.StudyGroupResDto;
import UniversityManagemant.demo.models.StudyGroup;

@Component
public class NhomHocMapper {

    public StudyGroupResDto toResDto(StudyGroup nhomHoc) {
        return StudyGroupResDto.builder()
                .id(nhomHoc.getId())
                .studyGroupCode(nhomHoc.getStudyGroupCode())
                .studyGroupName(nhomHoc.getStudyGroupName())
                .startTime(nhomHoc.getStartTime())
                .endTime(nhomHoc.getEndTime())
                .fromTime(nhomHoc.getFromTime())
                .toTime(nhomHoc.getToTime())
                .numberOfStudents(nhomHoc.getNumberOfStudents())
                .subjectName(nhomHoc.getSubject() != null ? nhomHoc.getSubject().getSubjectName() : null)
                .lecturerName(nhomHoc.getLecturer() != null && nhomHoc.getLecturer().getUser() != null ? nhomHoc.getLecturer().getUser().getUsername() : null)
                .classroomName(nhomHoc.getClassroom() != null ? nhomHoc.getClassroom().getClassroomName() : null)
                .build();
    }
}
