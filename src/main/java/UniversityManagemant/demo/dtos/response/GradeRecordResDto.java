package UniversityManagemant.demo.dtos.response;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GradeRecordResDto {
    Long id;
    String studentCode;
    String userName;
    String subjectName;
    BigDecimal tenPointScale;
    BigDecimal fourPointScale;
    String gradeLetter;
    String subjectStatus;
}
