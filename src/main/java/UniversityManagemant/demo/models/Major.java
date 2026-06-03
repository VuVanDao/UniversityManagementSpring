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
    String maChuyenNganh;
    String tenChuyenNganh;

    @ManyToOne
    @JoinColumn(name = "khoa_id", nullable = false)
    Faculty khoa;

    @OneToMany(mappedBy = "chuyenNganh")
    @JsonIgnore
    List<User> users;

    @OneToMany(mappedBy = "chuyenNganh")
    @JsonIgnore
    List<ClassManagement> lopQuanLis;
}
