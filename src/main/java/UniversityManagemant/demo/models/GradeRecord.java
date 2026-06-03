package UniversityManagemant.demo.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class GradeRecord extends AbstractModel {
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    Subject subject;

    BigDecimal TenPointScale;
    BigDecimal FourPointScale;
    String SubjectStatus;
}
