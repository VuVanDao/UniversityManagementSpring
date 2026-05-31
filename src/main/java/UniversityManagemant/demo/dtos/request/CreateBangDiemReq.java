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
public class CreateBangDiemReq {
    Long sinhVienId;
    Long monHocId;
    BigDecimal diemHe10;
    BigDecimal diemHe4;
    String diemChu;
    String trangThaiMonHoc;
}
