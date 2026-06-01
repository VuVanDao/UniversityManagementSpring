package UniversityManagemant.demo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityConfig {
    private final String[] publicPostEndpoints = { "/auth/login", "/auth/register" };
    private final String[] Public_View_Api_Endpoint = { "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html" };
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, publicPostEndpoints).permitAll()
                .requestMatchers(Public_View_Api_Endpoint).permitAll()
                .anyRequest().authenticated());
        http.csrf(csrf -> csrf.disable());
        
        return http.build();
    };
}
