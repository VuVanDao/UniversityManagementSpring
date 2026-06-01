package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;
import UniversityManagemant.demo.dtos.response.LopQuanLiResDto;
import UniversityManagemant.demo.models.LopQuanLi;

@Component
public class LopQuanLiMapper {

    public LopQuanLiResDto toResDto(LopQuanLi lopQuanLi) {
        return LopQuanLiResDto.builder()
                .id(lopQuanLi.getId())
                .maLop(lopQuanLi.getMaLop())
                .tenLop(lopQuanLi.getTenLop())
                .tenChuyenNganh(lopQuanLi.getChuyenNganh() != null ? lopQuanLi.getChuyenNganh().getTenChuyenNganh() : null)
                .build();
    }
}
