package UniversityManagemant.demo.models;

import java.math.BigDecimal;
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
public class SinhVien extends AbstractModel {
    String maSinhVien;
    BigDecimal diemGPA;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "lop_quan_li_id")
    LopQuanLi lopQuanLi;

    @OneToMany(mappedBy = "sinhVien")
    List<BangDiem> bangDiems;

    @OneToMany(mappedBy = "sinhVien")
    List<NhomHoc_SinhVien> nhomHocSinhViens;
}