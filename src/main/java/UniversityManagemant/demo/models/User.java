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
import jakarta.persistence.CascadeType;
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
    String userCode;
    String userName;
    String email;
    String password;
    LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    Gender gender;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    Role role;

    @ManyToOne
    @JoinColumn(name = "major_id", nullable = true)
    Major major;

    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    Lecturer lecturer;

    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    Student sinhVien;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + this.getRole().getRoleName());
        Collection<GrantedAuthority> authorities = Collections.singletonList(authority);
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}