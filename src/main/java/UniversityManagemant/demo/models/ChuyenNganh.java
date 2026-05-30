package UniversityManagemant.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class ChuyenNganh extends AbstractModel {
    String ma_chuyen_nganh;
    String ten_chuyen_nganh;

    @ManyToOne
    @JoinColumn(name = "khoa_id", nullable = false)
    Khoa khoa;

    @OneToMany(mappedBy = "chuyen_nganh")
    List<User> users;

    @OneToMany(mappedBy = "chuyenNganh")
    List<LopQuanLi> lopQuanLis;
}
