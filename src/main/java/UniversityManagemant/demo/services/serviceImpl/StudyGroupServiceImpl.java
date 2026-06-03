package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateStudyGroupReq;
import UniversityManagemant.demo.dtos.response.StudyGroupResDto;
import UniversityManagemant.demo.models.StudyGroup;
import UniversityManagemant.demo.repositories.NhomHocRepository;
import UniversityManagemant.demo.repositories.MonHocRepository;
import UniversityManagemant.demo.repositories.GiangVienRepository;
import UniversityManagemant.demo.repositories.PhongHocRepository;
import UniversityManagemant.demo.services.serviceInterface.StudyGroupService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class StudyGroupServiceImpl implements StudyGroupService {
    final NhomHocRepository nhomHocRepository;
    final MonHocRepository monHocRepository;
    final GiangVienRepository giangVienRepository;
    final PhongHocRepository phongHocRepository;

    @Override
    public StudyGroupResDto createStudyGroup(CreateStudyGroupReq req) {
        StudyGroup nhomHoc = StudyGroup.builder()
                .studyGroupCode(req.getStudyGroupCode())
                .studyGroupName(req.getStudyGroupName())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .fromTime(req.getFromTime())
                .toTime(req.getToTime())
                .numberOfStudents(req.getNumberOfStudents())
                .subject(monHocRepository.findById(req.getSubjectId()).orElseThrow())
                .lecturer(giangVienRepository.findById(req.getLecturerId()).orElseThrow())
                .classroom(phongHocRepository.findById(req.getClassroomId()).orElseThrow())
                .build();
        StudyGroup saved = nhomHocRepository.save(nhomHoc);
        return toDto(saved);
    }

    @Override
    public StudyGroupResDto getStudyGroupById(Long id) {
        return nhomHocRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("NhomHoc not found"));
    }

    @Override
    public List<StudyGroupResDto> getAllStudyGroups() {
        return nhomHocRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudyGroupResDto updateStudyGroup(Long id, CreateStudyGroupReq req) {
        StudyGroup nhomHoc = nhomHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NhomHoc not found"));
        nhomHoc.setStudyGroupCode(req.getStudyGroupCode());
        nhomHoc.setStudyGroupName(req.getStudyGroupName());
        nhomHoc.setStartTime(req.getStartTime());
        nhomHoc.setEndTime(req.getEndTime());
        nhomHoc.setFromTime(req.getFromTime());
        nhomHoc.setToTime(req.getToTime());
        nhomHoc.setNumberOfStudents(req.getNumberOfStudents());
        nhomHoc.setSubject(monHocRepository.findById(req.getSubjectId()).orElseThrow());
        nhomHoc.setLecturer(giangVienRepository.findById(req.getLecturerId()).orElseThrow());
        nhomHoc.setClassroom(phongHocRepository.findById(req.getClassroomId()).orElseThrow());
        StudyGroup updated = nhomHocRepository.save(nhomHoc);
        return toDto(updated);
    }

    @Override
    public void deleteStudyGroup(Long id) {
        nhomHocRepository.deleteById(id);
    }

    private StudyGroupResDto toDto(StudyGroup nhomHoc) {
        return StudyGroupResDto.builder()
                .id(nhomHoc.getId())
                .studyGroupCode(nhomHoc.getStudyGroupCode())
                .studyGroupName(nhomHoc.getStudyGroupName())
                .startTime(nhomHoc.getStartTime())
                .endTime(nhomHoc.getEndTime())
                .fromTime(nhomHoc.getFromTime())
                .toTime(nhomHoc.getToTime())
                .numberOfStudents(nhomHoc.getNumberOfStudents())
                .subjectName(nhomHoc.getSubject() != null ? nhomHoc.getSubject().getSubjectName() : null)
                .lecturerName(nhomHoc.getLecturer() != null && nhomHoc.getLecturer().getUser() != null
                    ? nhomHoc.getLecturer().getUser().getUsername() : null)
                .classroomName(nhomHoc.getClassroom() != null ? nhomHoc.getClassroom().getClassroomName() : null)
                .build();
    }
}
