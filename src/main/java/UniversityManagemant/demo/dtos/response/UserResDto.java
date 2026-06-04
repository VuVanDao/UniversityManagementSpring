package UniversityManagemant.demo.dtos.response;

import java.time.LocalDate;

import UniversityManagemant.demo.enums.Gender;
import UniversityManagemant.demo.models.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
    String userCode;
    String userName;
    String email;
    LocalDate dateOfBirth;
    Gender gender;
    RoleResDto role;
    MajorResDto major;
}
