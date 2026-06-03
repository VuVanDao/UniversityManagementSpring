package UniversityManagemant.demo.configurations;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import UniversityManagemant.demo.enums.Gender;
import UniversityManagemant.demo.models.Role;
import UniversityManagemant.demo.models.User;
import UniversityManagemant.demo.repositories.RoleRepository;
import UniversityManagemant.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class StartAppConfig {
    private final PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository,RoleRepository roleRepository) {
        return args -> {
            System.out.println("--------------------- StartAppConfig.ApplicationRunner ---------------------");
            User isAdminAccountExists = userRepository.findByTenNguoiDung("admin");
            if (isAdminAccountExists == null) {
                System.out.println("Admin account does not exist. Creating one...");
                Role isAdminRoleExists = roleRepository.findByTenRole("ADMIN");
                if (isAdminRoleExists == null) {
                    System.out.println("Admin role does not exist. Creating one...");
                    Role adminRole = Role.builder()
                                        .roleName("ADMIN")
                                        .build();
                    roleRepository.save(adminRole);
                    isAdminRoleExists = adminRole;
                }
                User user = User.builder()
                    .userCode("admin code")
                    .userName("admin")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .dateOfBirth(LocalDate.now())
                    .gender(Gender.OTHER)
                    .role(isAdminRoleExists)
                    .build();
                userRepository.save(user);
            } else {
                System.out.println("Admin account already exists.");
            }
        };
    }
}
