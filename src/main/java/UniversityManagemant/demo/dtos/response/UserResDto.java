package UniversityManagemant.demo.dtos.response;

import UniversityManagemant.demo.enums.Gender;
import UniversityManagemant.demo.models.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResDto {
    Long id;
    String ma_nguoi_dung;
    String ten_nguoi_dung;
    String email;
    String ngay_sinh;
    Gender gioi_tinh;
    Role role;
    String ten_chuyen_nganh;
}
