package UniversityManagemant.demo.dtos.request;

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
public class CreateGradeRecordReq {
    Long studentId;
    Long subjectId;
    BigDecimal tenPointScale;
    BigDecimal fourPointScale;
    String letterGrade;
    String subjectStatus;
}
