package UniversityManagemant.demo.models;

import java.time.LocalDateTime;
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
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhomHoc extends AbstractModel {
    String maNhom;
    String tenNhom;
    Integer tietBatDau;
    Integer tietKetThuc;
    LocalDateTime fromTime;
    LocalDateTime toTime;
    Integer soLuongSinhVien;

    @ManyToOne
    @JoinColumn(name = "mon_hoc_id")
    MonHoc monHoc;

    @ManyToOne
    @JoinColumn(name = "giang_vien_id")
    GiangVien giangVien;

    @ManyToOne
    @JoinColumn(name = "phong_hoc_id")
    PhongHoc phongHoc;

    @OneToMany(mappedBy = "nhomHoc")
    List<NhomHoc_SinhVien> nhomHocSinhViens;
}
