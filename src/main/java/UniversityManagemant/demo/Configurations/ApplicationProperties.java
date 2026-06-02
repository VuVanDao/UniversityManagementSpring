package UniversityManagemant.demo.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "app")
@Setter
@Getter
@EnableConfigurationProperties(ApplicationProperties.class)
public class ApplicationProperties {
    private String jwtSecret;
    private long jwtAccessTokenExpiration;
    private long jwtRefreshTokenExpiration;
}
