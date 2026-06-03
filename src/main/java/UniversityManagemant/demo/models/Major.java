package UniversityManagemant.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Major extends AbstractModel {
    String MajorCode;
    String MajorName;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    Faculty faculty;

    @OneToMany(mappedBy = "major")
    @JsonIgnore
    List<User> users;

    @OneToMany(mappedBy = "major")
    @JsonIgnore
    List<ClassManagement> lopQuanLis;
}
