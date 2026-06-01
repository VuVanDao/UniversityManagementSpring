package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateChuyenNganhReq;
import UniversityManagemant.demo.dtos.response.ChuyenNganhResDto;
import UniversityManagemant.demo.models.ChuyenNganh;

@Component
public class ChuyenNganhMapper {

    public ChuyenNganhResDto toResDto(ChuyenNganh chuyenNganh) {
        return ChuyenNganhResDto.builder()
                .id(chuyenNganh.getId())
                .maChuyenNganh(chuyenNganh.getMaChuyenNganh())
                .tenChuyenNganh(chuyenNganh.getTenChuyenNganh())
                .tenKhoa(chuyenNganh.getKhoa() != null ? chuyenNganh.getKhoa().getTenKhoa() : null)
                .build();
    }
}
