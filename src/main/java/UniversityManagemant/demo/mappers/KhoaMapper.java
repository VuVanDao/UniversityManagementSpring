package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateKhoaReq;
import UniversityManagemant.demo.dtos.response.KhoaResDto;
import UniversityManagemant.demo.models.Khoa;

@Component
public class KhoaMapper {

    public Khoa toEntity(CreateKhoaReq createKhoaReq) {
        return Khoa.builder()
                .maKhoa(createKhoaReq.getMaKhoa())
                .tenKhoa(createKhoaReq.getTenKhoa())
                .build();
    }

    public KhoaResDto toResDto(Khoa khoa) {
        return KhoaResDto.builder()
                .id(khoa.getId())
                .maKhoa(khoa.getMaKhoa())
                .tenKhoa(khoa.getTenKhoa())
                .build();
    }

    public void updateEntityFromDto(CreateKhoaReq createKhoaReq, Khoa khoa) {
        khoa.setMaKhoa(createKhoaReq.getMaKhoa());
        khoa.setTenKhoa(createKhoaReq.getTenKhoa());
    }
}
