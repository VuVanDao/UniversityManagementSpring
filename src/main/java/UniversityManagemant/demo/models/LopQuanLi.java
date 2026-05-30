package UniversityManagemant.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class LopQuanLi extends AbstractModel {
    String ma_lop;
    String ten_lop;

    @OneToOne(mappedBy = "lopQuanLi")
    GiangVien giangVien;

    @ManyToOne
    @JoinColumn(name = "chuyen_nganh_id")
    ChuyenNganh chuyenNganh;

    @OneToMany(mappedBy = "lopQuanLi")
    List<SinhVien> sinhViens;
}
