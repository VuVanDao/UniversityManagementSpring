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
                .facultyCode(createKhoaReq.getMaKhoa())
                .facultyName(createKhoaReq.getTenKhoa())
                .build();
    }

    public KhoaResDto toResDto(Faculty khoa) {
        return KhoaResDto.builder()
                .id(khoa.getId())
                .maKhoa(khoa.getFacultyCode())
                .tenKhoa(khoa.getFacultyName())
                .build();
    }

    public void updateEntityFromDto(UpdateKhoaReq updateKhoaReq, Faculty khoa) {
        if (updateKhoaReq.getMaKhoa() != null) {
            khoa.setFacultyCode(updateKhoaReq.getMaKhoa());
        }
        if (updateKhoaReq.getTenKhoa() != null) {
            khoa.setFacultyName(updateKhoaReq.getTenKhoa());
        }
    }
}
