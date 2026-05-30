package UniversityManagemant.demo.dtos.request;

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
public class CreateUserReq {
    String ma_nguoi_dung;
    String ten_nguoi_dung;
    String email;
    String password;
    String ngay_sinh;
    Role role;
    Long chuyen_nganh_id;
}
