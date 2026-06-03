package UniversityManagemant.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;
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
public class Student extends AbstractModel {
    BigDecimal GPAPoint;
    String TrainingSystem;// hệ đào tạo

    @OneToOne
    User user;

    @ManyToOne
    ClassManagement classManagement;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    List<GradeRecord> gradeRecords;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    List<StudyGroupStudent> studyGroupStudents;
}