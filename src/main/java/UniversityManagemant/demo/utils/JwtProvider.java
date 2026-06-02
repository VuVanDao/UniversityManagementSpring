 package UniversityManagemant.demo.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import UniversityManagemant.demo.configurations.ApplicationProperties;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    private final ApplicationProperties applicationProperties;
    private final SecretKey key;
    private long accessTokenExpiration;
    private long refreshTokenExpiration;

    private JwtProvider(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        System.out.println("đâsdas:"+applicationProperties.getJwtSecret());
        this.key = Keys.hmacShaKeyFor(applicationProperties.getJwtSecret().getBytes());
        this.accessTokenExpiration = applicationProperties.getJwtAccessTokenExpiration();
        this.refreshTokenExpiration = applicationProperties.getJwtRefreshTokenExpiration();
    }
    

    
    public static final String TOKEN_TYPE_ACCESS = "access";
    public static final String TOKEN_TYPE_REFRESH = "refresh";
    /**
     * Generate JWT Access Token
     */
    public String generateAccessToken(String username) {
        return generateToken(username, TOKEN_TYPE_ACCESS, accessTokenExpiration);
    }
    
    /**
     * Generate JWT Refresh Token
     */
    public String generateRefreshToken(String username) {
        return generateToken(username, TOKEN_TYPE_REFRESH, refreshTokenExpiration);
    }
    
    /**
     * Generate JWT Token with custom expiration
     */
    private String generateToken(String username, String tokenType, long expirationTime) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);
        
        return Jwts.builder()
                .subject(username)
                .claim("tokenType", tokenType)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }
    
    /**
     * Extract username from token
     */
    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Validate token
     */
    public boolean validateToken(String token) {
        System.out.println("------jwtProvider.validateToken------------");
        try {
            Jwts
                    .parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get token type (access or refresh)
     */
    public String getTokenType(String token) {
        System.out.println("------jwtProvider.getTokenType------------");
        try {
            return (String) Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get("tokenType");
        } catch (Exception e) {
            return null;
        }
    }
}
