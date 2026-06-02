# RBAC Implementation Guide - @PreAuthorize Examples

## What We Fixed

### 1. JwtAuthenticationFilter (Primary Fix)

**Problem**: Authorities were `null`, so `@PreAuthorize` couldn't check roles
**Solution**: Load user from database and extract authorities

```java
// Before (❌ WRONG)
Authentication authentication = new UsernamePasswordAuthenticationToken(
    username, null, null);  // null authorities

// After (✅ CORRECT)
User user = userRepository.findByEmailOrMaNguoiDung(email, email).orElse(null);
if (user != null) {
    Authentication authentication = new UsernamePasswordAuthenticationToken(
        email, null, user.getAuthorities());  // authorities from user
}
```

### 2. User.getAuthorities() Method

**Problem**: Used `.toString()` on Role object instead of role name
**Solution**: Use `getTenRole()` and add "ROLE\_" prefix

```java
// Before (❌ WRONG)
GrantedAuthority authority = new SimpleGrantedAuthority(this.getRole().toString());
// Result: "Role@12345" (not a valid role name)

// After (✅ CORRECT)
GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + this.getRole().getTenRole());
// Result: "ROLE_ADMIN", "ROLE_STUDENT", etc.
```

## Database Setup

### Role Table Example

```
| id | tenRole | moTa         |
|----|---------|--------------|
| 1  | ADMIN   | Administrator|
| 2  | TEACHER | Teacher      |
| 3  | STUDENT | Student      |
```

**Important**: Store only the role name ("ADMIN", "TEACHER", "STUDENT")
The system automatically adds "ROLE\_" prefix for Spring Security.

## @PreAuthorize Usage Examples

### Example 1: Single Role (ADMIN Only)

```java
@PostMapping
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<KhoaResDto> createKhoa(@RequestBody CreateKhoaReq req) {
    return ResponseEntity.status(HttpStatus.CREATED).body(khoaService.createKhoa(req));
}
```

### Example 2: Multiple Roles (ADMIN or TEACHER)

```java
@GetMapping
@PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
public ResponseEntity<List<KhoaResDto>> getAllKhoa() {
    return ResponseEntity.ok(khoaService.getAllKhoa());
}
```

### Example 3: Using Authority (with ROLE\_ prefix)

```java
@DeleteMapping("/{id}")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public ResponseEntity<Void> deleteKhoa(@PathVariable Long id) {
    khoaService.deleteKhoa(id);
    return ResponseEntity.noContent().build();
}
```

### Example 4: No Authentication Needed (Public)

```java
@GetMapping("/{id}")
// No @PreAuthorize - public endpoint
public ResponseEntity<KhoaResDto> getKhoa(@PathVariable Long id) {
    return ResponseEntity.ok(khoaService.getKhoaById(id));
}
```

### Example 5: Class-Level Authorization

```java
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")  // Applied to all methods in class
public class AdminController {

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserReq req) {
        // This method requires ADMIN role
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        // This method also requires ADMIN role
    }
}
```

### Example 6: Complex Expressions

```java
// User must be ADMIN AND have email containing "admin"
@PreAuthorize("hasRole('ADMIN') and authentication.principal.contains('admin')")
public ResponseEntity<?> sensitiveOperation() { }

// User is TEACHER or ADMIN
@PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
public ResponseEntity<?> viewGrades() { }
```

## How It Works Now

1. **User logs in** → `AuthService.login()` generates JWT token
2. **Client sends request** with `Authorization: Bearer <token>` header
3. **JwtAuthenticationFilter** intercepts request:
   - Extracts token
   - Gets username from token
   - **Loads user from database** ← NEW!
   - Extracts **user.getAuthorities()** ← NEW!
   - Sets authentication with authorities in SecurityContext
4. **@PreAuthorize checks** if user has required role
   - If yes → endpoint executed ✅
   - If no → returns 403 Forbidden ❌

## Testing

### Test with ADMIN Role

```bash
# Login as admin user
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"emailOrMaNguoiDung":"admin@example.com", "password":"password"}'

# Response: { "accessToken": "eyJhbGc..." }

# Use token to access protected endpoint
curl -X POST http://localhost:8080/khoa \
  -H "Authorization: Bearer eyJhbGc..." \
  -H "Content-Type: application/json" \
  -d '{"tenKhoa":"Khoa CNTT"}'
# Result: ✅ 201 Created
```

### Test with STUDENT Role

```bash
# Login as student user
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"emailOrMaNguoiDung":"student@example.com", "password":"password"}'

# Use token to access ADMIN-only endpoint
curl -X POST http://localhost:8080/khoa \
  -H "Authorization: Bearer eyJhbGc..." \
  -H "Content-Type: application/json" \
  -d '{"tenKhoa":"Khoa CNTT"}'
# Result: ❌ 403 Forbidden
```

## Security Configuration

Make sure your `securityConfig.java` has:

```java
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  // Enable @PreAuthorize
public class SecurityConfig {
    // ... configuration
}
```

Or in Spring Security 6.0+:

```java
@Configuration
@EnableMethodSecurity(prePostEnabled = true)  // Spring 6.0+
public class SecurityConfig {
    // ... configuration
}
```

## Common Mistakes to Avoid

1. ❌ Forgetting `@PreAuthorize` - endpoint is public
2. ❌ Using role name without `hasRole()` - won't work
3. ❌ Not loading user in filter - authorities are null
4. ❌ Using `.toString()` on Role object - creates invalid authority string
5. ❌ Storing "ROLE_ADMIN" in DB instead of just "ADMIN" - creates "ROLE_ROLE_ADMIN"

## Apply to All Controllers

Apply similar pattern to all your controllers:

- **POST/PUT/DELETE**: Restrict to `@PreAuthorize("hasRole('ADMIN')")`
- **GET**: Public or with specific role as needed
