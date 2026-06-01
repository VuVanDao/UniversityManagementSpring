package UniversityManagemant.demo.configurations;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import UniversityManagemant.demo.models.User;
import UniversityManagemant.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserImplConfig implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailOrMaNguoiDung(username,username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng với email hoặc mã người dùng: " + username));
        return user;
    }
     
}
