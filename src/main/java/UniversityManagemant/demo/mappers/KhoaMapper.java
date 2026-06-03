package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.request.CreateFacultyReq;
import UniversityManagemant.demo.dtos.request.UpdateFacultyReq;
import UniversityManagemant.demo.dtos.response.FacultyResDto;
import UniversityManagemant.demo.models.Faculty;
import UniversityManagemant.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KhoaMapper {
    private final UserRepository userRepository;
    public Faculty toEntity(CreateFacultyReq createFacultyReq) {
        return Faculty.builder()
                .facultyCode(createFacultyReq.getFacultyCode())
                .facultyName(createFacultyReq.getFacultyName())
                .build();
    }

    public FacultyResDto toResDto(Faculty khoa) {
        return FacultyResDto.builder()
                .id(khoa.getId())
                .facultyCode(khoa.getFacultyCode())
                .facultyName(khoa.getFacultyName())
                .build();
    }

    public void updateEntityFromDto(UpdateFacultyReq updateFacultyReq, Faculty khoa) {
        if (updateFacultyReq.getFacultyCode() != null) {
            khoa.setFacultyCode(updateFacultyReq.getFacultyCode());
        }
        if (updateFacultyReq.getFacultyName() != null) {
            khoa.setFacultyName(updateFacultyReq.getFacultyName());
        }
    }
}
