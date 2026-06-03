package UniversityManagemant.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Subject extends AbstractModel {
    String subjectCode;
    String subjectName;
    Integer credits;

    // @OneToMany(mappedBy = "monHoc")
    // List<BangDiem> bangDiems;

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    List<StudyGroup> studyGroups;
}
