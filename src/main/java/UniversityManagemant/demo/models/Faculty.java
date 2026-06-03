package UniversityManagemant.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Faculty extends AbstractModel {
    String facultyCode;
    String facultyName;

    @OneToOne(mappedBy = "faculty")
    Lecturer dean;// trưởng khoa

    @OneToMany(mappedBy = "faculty")
    @JsonIgnore
    List<Major> majors;
}