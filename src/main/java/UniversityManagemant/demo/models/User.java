package UniversityManagemant.demo.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import UniversityManagemant.demo.enums.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    String maNguoiDung;
    String tenNguoiDung;
    String email;
    String password;
    LocalDate ngaySinh;
    @Enumerated(EnumType.STRING)
    Gender gioiTinh;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    Role role;

    @ManyToOne
    @JoinColumn(name = "chuyen_nganh_id", nullable = true)
    ChuyenNganh chuyenNganh;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    GiangVien giangVien;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    SinhVien sinhVien;

    // @OneToOne(mappedBy = "truongKhoa")
    // Khoa khoa;
}