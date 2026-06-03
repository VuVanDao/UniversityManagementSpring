package UniversityManagemant.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudyGroup extends AbstractModel {
    String studyGroupCode;
    String studyGroupName;
    Integer startTime;
    Integer endTime;
    LocalDateTime fromTime;
    LocalDateTime toTime;
    Integer numberOfStudents;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    Subject subject;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    Lecturer lecturer;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    Classroom classroom;

    @OneToMany(mappedBy = "studyGroup")
    @JsonIgnore
    List<StudyGroupStudent> studyGroupStudents;
}
