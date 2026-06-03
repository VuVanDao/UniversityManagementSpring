package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateKhoaReq;
import UniversityManagemant.demo.dtos.request.UpdateKhoaReq;
import UniversityManagemant.demo.dtos.response.KhoaResDto;
import UniversityManagemant.demo.models.Faculty;
import UniversityManagemant.demo.models.User;
import UniversityManagemant.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KhoaMapper {
    private final UserRepository userRepository;
    public Faculty toEntity(CreateKhoaReq createKhoaReq) {
        return Faculty.builder()
                .maKhoa(createKhoaReq.getMaKhoa())
                .tenKhoa(createKhoaReq.getTenKhoa())
                .build();
    }

    public KhoaResDto toResDto(Faculty khoa) {
        return KhoaResDto.builder()
                .id(khoa.getId())
                .maKhoa(khoa.getMaKhoa())
                .tenKhoa(khoa.getTenKhoa())
                .build();
    }

    public void updateEntityFromDto(UpdateKhoaReq updateKhoaReq, Faculty khoa) {
        if (updateKhoaReq.getMaKhoa() != null) {
            khoa.setMaKhoa(updateKhoaReq.getMaKhoa());
        }
        if (updateKhoaReq.getTenKhoa() != null) {
            khoa.setTenKhoa(updateKhoaReq.getTenKhoa());
        }
    }
}
