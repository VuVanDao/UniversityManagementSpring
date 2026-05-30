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
public class BangDiem extends AbstractModel {
    @ManyToOne
    @JoinColumn(name = "sinh_vien_id", nullable = false)
    SinhVien sinhVien;

    @ManyToOne
    @JoinColumn(name = "mon_hoc_id", nullable = false)
    MonHoc monHoc;

    BigDecimal diem_he_10;
    BigDecimal diem_he_4;
    String diem_chu;
    String trang_thai_mon_hoc;
}
