package UniversityManagemant.demo.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User extends AbstractModel implements UserDetails{
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + this.getRole().getTenRole());
        Collection<GrantedAuthority> authorities = Collections.singletonList(authority);
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}