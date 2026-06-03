package UniversityManagemant.demo.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import UniversityManagemant.demo.dtos.request.CreateNhomHocReq;
import UniversityManagemant.demo.dtos.response.NhomHocResDto;
import UniversityManagemant.demo.models.StudyGroup;
import UniversityManagemant.demo.repositories.NhomHocRepository;
import UniversityManagemant.demo.repositories.MonHocRepository;
import UniversityManagemant.demo.repositories.GiangVienRepository;
import UniversityManagemant.demo.repositories.PhongHocRepository;
import UniversityManagemant.demo.services.serviceInterface.NhomHocService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class NhomHocServiceImpl implements NhomHocService {
    final NhomHocRepository nhomHocRepository;
    final MonHocRepository monHocRepository;
    final GiangVienRepository giangVienRepository;
    final PhongHocRepository phongHocRepository;

    @Override
    public NhomHocResDto createNhomHoc(CreateNhomHocReq req) {
        StudyGroup nhomHoc = StudyGroup.builder()
                .studyGroupCode(req.getMaNhom())
                .studyGroupName(req.getTenNhom())
                .startTime(req.getTietBatDau())
                .endTime(req.getTietKetThuc())
                .fromTime(req.getFromTime())
                .toTime(req.getToTime())
                .numberOfStudents(req.getSoLuongSinhVien())
                .subject(monHocRepository.findById(req.getMonHocId()).orElseThrow())
                .lecturer(giangVienRepository.findById(req.getGiangVienId()).orElseThrow())
                .classroom(phongHocRepository.findById(req.getPhongHocId()).orElseThrow())
                .build();
        StudyGroup saved = nhomHocRepository.save(nhomHoc);
        return toDto(saved);
    }

    @Override
    public NhomHocResDto getNhomHocById(Long id) {
        return nhomHocRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("NhomHoc not found"));
    }

    @Override
    public List<NhomHocResDto> getAllNhomHoc() {
        return nhomHocRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NhomHocResDto updateNhomHoc(Long id, CreateNhomHocReq req) {
        StudyGroup nhomHoc = nhomHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NhomHoc not found"));
        nhomHoc.setStudyGroupCode(req.getMaNhom());
        nhomHoc.setStudyGroupName(req.getTenNhom());
        nhomHoc.setStartTime(req.getTietBatDau());
        nhomHoc.setEndTime(req.getTietKetThuc());
        nhomHoc.setFromTime(req.getFromTime());
        nhomHoc.setToTime(req.getToTime());
        nhomHoc.setNumberOfStudents(req.getSoLuongSinhVien());
        nhomHoc.setSubject(monHocRepository.findById(req.getMonHocId()).orElseThrow());
        nhomHoc.setLecturer(giangVienRepository.findById(req.getGiangVienId()).orElseThrow());
        nhomHoc.setClassroom(phongHocRepository.findById(req.getPhongHocId()).orElseThrow());
        StudyGroup updated = nhomHocRepository.save(nhomHoc);
        return toDto(updated);
    }

    @Override
    public void deleteNhomHoc(Long id) {
        nhomHocRepository.deleteById(id);
    }

    private NhomHocResDto toDto(StudyGroup nhomHoc) {
        return NhomHocResDto.builder()
                .id(nhomHoc.getId())
                .maNhom(nhomHoc.getStudyGroupCode())
                .tenNhom(nhomHoc.getStudyGroupName())
                .tietBatDau(nhomHoc.getStartTime())
                .tietKetThuc(nhomHoc.getEndTime())
                .fromTime(nhomHoc.getFromTime())
                .toTime(nhomHoc.getToTime())
                .soLuongSinhVien(nhomHoc.getNumberOfStudents())
                .tenMonHoc(nhomHoc.getSubject() != null ? nhomHoc.getSubject().getSubjectName() : null)
                .tenGiangVien(nhomHoc.getLecturer() != null && nhomHoc.getLecturer().getUser() != null
                    ? nhomHoc.getLecturer().getUser().getUsername() : null)
                .tenPhongHoc(nhomHoc.getClassroom() != null ? nhomHoc.getClassroom().getClassroomName() : null)
                .build();
    }
}
