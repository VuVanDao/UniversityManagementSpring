## JWT Token Generation Feature - Implementation Guide

### Overview

This guide shows you how to use the new JWT token generation features in your University Management System.

### Components Created:

1. **JwtProvider.java** - Utility class for generating and validating JWT tokens
   - `generateAccessToken(username)` - Creates short-lived access token (15 minutes)
   - `generateRefreshToken(username)` - Creates long-lived refresh token (7 days)
   - `validateToken(token)` - Validates token signature and expiration
   - `getUsernameFromToken(token)` - Extracts username from token
   - `getTokenType(token)` - Identifies if token is "access" or "refresh" type

2. **JwtAuthenticationFilter.java** - Security filter that validates JWT on incoming requests
   - Extracts JWT from Authorization header (Bearer token)
   - Validates token and sets SecurityContext for authenticated requests

3. **AuthTokenResponse.java** - DTO for returning tokens in API responses

4. **securityConfig.java** - Updated to integrate JWT filter into security chain

### Configuration (application.properties)

```properties
jwt.secret=MySecretKeyForJWTThatIsAtLeast32CharactersLongForHS256
jwt.access-token-expiration=900000  # 15 minutes in milliseconds
jwt.refresh-token-expiration=604800000  # 7 days in milliseconds
```

**IMPORTANT:** Change `jwt.secret` to a secure random string in production!

### Usage Example in AuthService.java

```java
package UniversityManagemant.demo.services;

import UniversityManagemant.demo.dtos.request.LoginReq;
import UniversityManagemant.demo.dtos.response.AuthResDto;
import UniversityManagemant.demo.dtos.response.AuthTokenResponse;
import UniversityManagemant.demo.utils.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResDto login(LoginReq loginReq) {
        // Find user by username
        User user = userRepository.findByUsername(loginReq.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Verify password
        if (!passwordEncoder.matches(loginReq.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Generate tokens
        String accessToken = jwtProvider.generateAccessToken(user.getUsername());
        String refreshToken = jwtProvider.generateRefreshToken(user.getUsername());

        // Create response with tokens
        AuthTokenResponse tokenResponse = AuthTokenResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .tokenType("Bearer")
            .expiresIn(900000 / 1000) // in seconds
            .username(user.getUsername())
            .build();

        return tokenResponse; // or map to your AuthResDto if needed
    }

    // Optional: Refresh access token using refresh token
    public AuthTokenResponse refreshAccessToken(String refreshToken) {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid or expired refresh token");
        }

        String tokenType = jwtProvider.getTokenType(refreshToken);
        if (!"refresh".equals(tokenType)) {
            throw new RuntimeException("This is not a refresh token");
        }

        String username = jwtProvider.getUsernameFromToken(refreshToken);
        String newAccessToken = jwtProvider.generateAccessToken(username);

        return AuthTokenResponse.builder()
            .accessToken(newAccessToken)
            .refreshToken(refreshToken)
            .tokenType("Bearer")
            .expiresIn(900000 / 1000)
            .username(username)
            .build();
    }
}
```

### How to Use in API Calls

**Login Request:**

```
POST /api/auth/login
Content-Type: application/json

{
    "username": "john_doe",
    "password": "password123"
}
```

**Login Response:**

```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer",
  "expiresIn": 900,
  "username": "john_doe"
}
```

**Authenticated Request:**

```
GET /api/protected-endpoint
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

### Key Features

✅ **Two-Token System**: Separate access and refresh tokens for security
✅ **Automatic Validation**: JWT filter validates tokens on every request
✅ **Token Type Distinction**: Ensures refresh tokens can't be used as access tokens
✅ **Configurable Expiration**: Change expiration times via properties
✅ **Stateless Authentication**: No session storage needed
✅ **Easy Integration**: Inject `JwtProvider` into any service

### Security Notes

1. **Change the JWT secret** in application.properties to a strong, random value
2. **Use HTTPS** in production to prevent token interception
3. **Implement logout/token blacklist** if needed for revocation
4. **Store refresh token securely** (e.g., HttpOnly cookies)
5. **Access token should be short-lived** (15 min recommended)

### Optional: Add Refresh Endpoint

Add this to your AuthController:

```java
@PostMapping("/refresh")
public ResponseEntity<AuthTokenResponse> refreshToken(@RequestHeader("Authorization") String authHeader) {
    String refreshToken = authHeader.substring(7); // Remove "Bearer "
    AuthTokenResponse response = authService.refreshAccessToken(refreshToken);
    return ResponseEntity.ok(response);
}
```
