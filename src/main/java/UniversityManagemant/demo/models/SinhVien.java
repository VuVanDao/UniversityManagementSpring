package UniversityManagemant.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;
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
public class SinhVien extends AbstractModel {
    String maSinhVien;
    BigDecimal diemGPA;

    @OneToOne
    User user;

    @ManyToOne
    LopQuanLi lopQuanLi;

    @OneToMany(mappedBy = "sinhVien")
    @JsonIgnore
    List<BangDiem> bangDiems;

    @OneToMany(mappedBy = "sinhVien")
    @JsonIgnore
    List<NhomHoc_SinhVien> nhomHocSinhViens;
}