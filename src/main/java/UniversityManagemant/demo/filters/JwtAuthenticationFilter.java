package UniversityManagemant.demo.filters;

import UniversityManagemant.demo.utils.JwtProvider;
import UniversityManagemant.demo.repositories.UserRepository;
import UniversityManagemant.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtProvider jwtProvider;
    
    private final UserRepository userRepository;
    
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
            // Extract token from header
            String jwt = extractTokenFromRequest(request);
            
            if (jwt != null && jwtProvider.validateToken(jwt)) {
                // Validate that it's an access token
                String tokenType = jwtProvider.getTokenType(jwt);
                if (jwtProvider.TOKEN_TYPE_ACCESS.equals(tokenType)) {
                    String email = jwtProvider.getUsernameFromToken(jwt);
                    
                    // Load user from database to get authorities and roles
                    User user = userRepository.findByEmailOrUserCode(email, email)
                            .orElse(null);
                    
                    if (user != null) {
                        // Create authentication WITH authorities/roles extracted from user
                        Authentication authentication = new UsernamePasswordAuthenticationToken(
                                email, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Could not set user authentication in security context", e);
        }
        
        filterChain.doFilter(request, response);
    }
    
    /**
     * Extract JWT token from Authorization header
     */
    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }
}
