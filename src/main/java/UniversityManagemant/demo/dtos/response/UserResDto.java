package UniversityManagemant.demo.dtos.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import UniversityManagemant.demo.enums.Gender;
import UniversityManagemant.demo.models.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class UserResDto {
    Long id;
    String maNguoiDung;
    String tenNguoiDung;
    String email;
    LocalDate ngaySinh;
    Gender gioiTinh;
    Role role;
    ChuyenNganhResDto chuyenNganh;
}
