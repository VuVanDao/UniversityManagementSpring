package UniversityManagemant.demo.configurations;

import UniversityManagemant.demo.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)  // Enable @PreAuthorize
@RequiredArgsConstructor
public class securityConfig {
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
    private final String[] publicPostEndpoints = { "/auth/login", "/auth/register" };
    private final String[] Public_View_Api_Endpoint = { "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html" };
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, publicPostEndpoints).permitAll()
                .requestMatchers(Public_View_Api_Endpoint).permitAll()
                .anyRequest().authenticated());
        http.csrf(csrf -> csrf.disable());
        
        // Add JWT filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}
