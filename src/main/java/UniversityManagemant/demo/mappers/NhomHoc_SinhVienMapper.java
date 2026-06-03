package UniversityManagemant.demo.mappers;

import org.springframework.stereotype.Component;

import UniversityManagemant.demo.dtos.response.StudyGroupStudentResDto;
import UniversityManagemant.demo.models.StudyGroupStudent;

@Component
public class NhomHoc_SinhVienMapper {

    public StudyGroupStudentResDto toResDto(StudyGroupStudent nhomHoc_SinhVien) {
        return StudyGroupStudentResDto.builder()
                .build();
    }
}
