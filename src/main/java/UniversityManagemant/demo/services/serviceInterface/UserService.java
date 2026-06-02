package UniversityManagemant.demo.services.serviceInterface;

import java.util.List;

import UniversityManagemant.demo.dtos.request.CreateUserReq;
import UniversityManagemant.demo.dtos.response.UserResDto;

public interface UserService {
    UserResDto createUser(CreateUserReq createUserReq);
    UserResDto getUserById(Long id);
    List<UserResDto> getAllUsers();
    UserResDto updateUser(Long id, CreateUserReq createUserReq);
    void deleteUser(Long id);
}
