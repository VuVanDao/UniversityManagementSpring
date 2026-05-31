package UniversityManagemant.demo.dtos.response;

import java.time.LocalDateTime;

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
public class NhomHocResDto {
    Long id;
    String maNhom;
    String tenNhom;
    Integer tietBatDau;
    Integer tietKetThuc;
    LocalDateTime fromTime;
    LocalDateTime toTime;
    Integer soLuongSinhVien;
    String tenMonHoc;
    String tenGiangVien;
    String tenPhongHoc;
}
