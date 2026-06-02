package UniversityManagemant.demo.dtos.response;

import java.time.LocalDate;

import UniversityManagemant.demo.enums.Gender;
import UniversityManagemant.demo.models.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthResDto {
    Long id;
    String maNguoiDung;
    String tenNguoiDung;
    String email;
    LocalDate ngaySinh;
    Gender gioiTinh;
    RoleResDto role;
    String message;
    String accessToken;
    String refreshToken;
}
