package UniversityManagemant.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "nhom_hoc_sinh_vien")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudyGroupStudent extends AbstractModel {
    @ManyToOne
    @JoinColumn(name = "study_group_id")
    StudyGroup studyGroup;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;
}
