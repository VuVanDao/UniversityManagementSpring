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
    String ma_nhom;
    String ten_nhom;
    Integer tiet_bat_dau;
    Integer tiet_ket_thuc;
    LocalDateTime from_time;
    LocalDateTime to_time;
    Integer so_luong_sinh_vien;

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
