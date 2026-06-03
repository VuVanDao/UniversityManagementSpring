package UniversityManagemant.demo.dtos.request;

import java.time.LocalDate;

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
    String userCode;
    String userName;
    String email;
    String password;
    LocalDate dateOfBirth;
    Long role_id;
    Long majorId;
}
