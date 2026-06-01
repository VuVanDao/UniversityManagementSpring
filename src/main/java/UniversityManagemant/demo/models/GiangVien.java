package UniversityManagemant.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class GiangVien extends AbstractModel {
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToOne
    LopQuanLi lopQuanLi;

    @OneToMany(mappedBy = "giangVien")
    List<NhomHoc> nhomHocs;
}