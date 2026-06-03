package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateStudentReq;
import UniversityManagemant.demo.dtos.response.StudentResDto;

public interface StudentService {
    StudentResDto createStudent(CreateStudentReq req);
    StudentResDto getStudentById(Long id);
    List<StudentResDto> getAllStudents();
    StudentResDto updateStudent(Long id, CreateStudentReq req);
    void deleteStudent(Long id);
}
