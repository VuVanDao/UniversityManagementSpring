package UniversityManagemant.demo.dtos.response;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudyGroupResDto {
    Long id;
    String studyGroupCode;
    String studyGroupName;
    Integer startTime;
    Integer endTime;
    LocalDateTime fromTime;
    LocalDateTime toTime;
    Integer numberOfStudents;
    String subjectName;
    String lecturerName;
    String classroomName;
}
