package UniversityManagemant.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbstractModel {
    String ma_nguoi_dung;
    String ten_nguoi_dung;
    String email;
    String password;
    String ngay_sinh;
    Boolean gioi_tinh;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    Role role;

    @ManyToOne
    @JoinColumn(name = "chuyen_nganh_id", nullable = false)
    ChuyenNganh chuyen_nganh;

    @OneToOne(mappedBy = "user")
    GiangVien giangVien;

    @OneToOne(mappedBy = "user")
    SinhVien sinhVien;

    // @OneToOne(mappedBy = "truongKhoa")
    // Khoa khoa;
}