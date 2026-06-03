package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateStudyGroupReq;
import UniversityManagemant.demo.dtos.response.StudyGroupResDto;
import UniversityManagemant.demo.models.StudyGroup;
import UniversityManagemant.demo.repositories.StudyGroupRepository;
import UniversityManagemant.demo.repositories.SubjectRepository;
import UniversityManagemant.demo.repositories.LecturerRepository;
import UniversityManagemant.demo.repositories.ClassroomRepository;
import UniversityManagemant.demo.services.serviceInterface.StudyGroupService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class StudyGroupServiceImpl implements StudyGroupService {
    final StudyGroupRepository studyGroupRepository;
    final SubjectRepository subjectRepository;
    final LecturerRepository lecturerRepository;
    final ClassroomRepository classroomRepository;

    @Override
    public StudyGroupResDto createStudyGroup(CreateStudyGroupReq req) {
        StudyGroup studyGroup = StudyGroup.builder()
                .studyGroupCode(req.getStudyGroupCode())
                .studyGroupName(req.getStudyGroupName())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .fromTime(req.getFromTime())
                .toTime(req.getToTime())
                .numberOfStudents(req.getNumberOfStudents())
                .subject(subjectRepository.findById(req.getSubjectId()).orElseThrow())
                .lecturer(lecturerRepository.findById(req.getLecturerId()).orElseThrow())
                .classroom(classroomRepository.findById(req.getClassroomId()).orElseThrow())
                .build();
        StudyGroup saved = studyGroupRepository.save(studyGroup);
        return toDto(saved);
    }

    @Override
    public StudyGroupResDto getStudyGroupById(Long id) {
        return studyGroupRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("StudyGroup not found"));
    }

    @Override
    public List<StudyGroupResDto> getAllStudyGroups() {
        return studyGroupRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudyGroupResDto updateStudyGroup(Long id, CreateStudyGroupReq req) {
        StudyGroup studyGroup = studyGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudyGroup not found"));
        studyGroup.setStudyGroupCode(req.getStudyGroupCode());
        studyGroup.setStudyGroupName(req.getStudyGroupName());
        studyGroup.setStartTime(req.getStartTime());
        studyGroup.setEndTime(req.getEndTime());
        studyGroup.setFromTime(req.getFromTime());
        studyGroup.setToTime(req.getToTime());
        studyGroup.setNumberOfStudents(req.getNumberOfStudents());
        studyGroup.setSubject(subjectRepository.findById(req.getSubjectId()).orElseThrow());
        studyGroup.setLecturer(lecturerRepository.findById(req.getLecturerId()).orElseThrow());
        studyGroup.setClassroom(classroomRepository.findById(req.getClassroomId()).orElseThrow());
        StudyGroup updated = studyGroupRepository.save(studyGroup);
        return toDto(updated);
    }

    @Override
    public void deleteStudyGroup(Long id) {
        studyGroupRepository.deleteById(id);
    }

    private StudyGroupResDto toDto(StudyGroup studyGroup) {
        return StudyGroupResDto.builder()
                .id(studyGroup.getId())
                .studyGroupCode(studyGroup.getStudyGroupCode())
                .studyGroupName(studyGroup.getStudyGroupName())
                .startTime(studyGroup.getStartTime())
                .endTime(studyGroup.getEndTime())
                .fromTime(studyGroup.getFromTime())
                .toTime(studyGroup.getToTime())
                .numberOfStudents(studyGroup.getNumberOfStudents())
                .subjectName(studyGroup.getSubject() != null ? studyGroup.getSubject().getSubjectName() : null)
                .lecturerName(studyGroup.getLecturer() != null && studyGroup.getLecturer().getUser() != null
                    ? studyGroup.getLecturer().getUser().getUsername() : null)
                .classroomName(studyGroup.getClassroom() != null ? studyGroup.getClassroom().getClassroomName() : null)
                .build();
    }
}
