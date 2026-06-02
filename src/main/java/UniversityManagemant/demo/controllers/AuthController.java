package UniversityManagemant.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UniversityManagemant.demo.dtos.request.LoginReq;
import UniversityManagemant.demo.dtos.response.AuthResDto;
import UniversityManagemant.demo.services.serviceInterface.AuthService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AuthController {
    final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResDto> login(@RequestBody LoginReq loginReq) {
        System.out.println("--------------------- AuthController.login ---------------------");
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginReq));
    }
}
