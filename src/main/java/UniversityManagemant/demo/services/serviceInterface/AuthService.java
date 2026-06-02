package UniversityManagemant.demo.services.serviceInterface;

import UniversityManagemant.demo.dtos.request.LoginReq;
import UniversityManagemant.demo.dtos.response.AuthResDto;

public interface AuthService {
    AuthResDto login(LoginReq loginReq);
}
