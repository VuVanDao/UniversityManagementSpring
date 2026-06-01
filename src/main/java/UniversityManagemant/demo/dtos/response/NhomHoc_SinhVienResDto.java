package UniversityManagemant.demo.dtos.response;


import java.time.LocalDateTime;
import java.util.List;

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
public class NhomHoc_SinhVienResDto {
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<SinhVienResDto> sinhViens;
    NhomHocResDto nhomHoc;
    Long id;
}
