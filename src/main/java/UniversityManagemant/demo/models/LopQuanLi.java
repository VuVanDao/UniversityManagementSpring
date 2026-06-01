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
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LopQuanLi extends AbstractModel {
    String maLop;
    String tenLop;

    @ManyToOne
    @JoinColumn(name = "chuyen_nganh_id")
    ChuyenNganh chuyenNganh;

    @OneToMany(mappedBy = "lopQuanLi")
    @JsonIgnore
    List<SinhVien> sinhViens;
}
