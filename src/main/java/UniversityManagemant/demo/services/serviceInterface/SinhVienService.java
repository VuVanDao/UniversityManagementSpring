package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateStudentReq;
import UniversityManagemant.demo.dtos.response.StudentResDto;

public interface SinhVienService {
    StudentResDto createSinhVien(CreateStudentReq req);
    StudentResDto getSinhVienById(Long id);
    List<StudentResDto> getAllSinhVien();
    StudentResDto updateSinhVien(Long id, CreateStudentReq req);
    void deleteSinhVien(Long id);
}
