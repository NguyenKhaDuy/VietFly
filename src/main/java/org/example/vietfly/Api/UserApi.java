package org.example.vietfly.Api;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.example.vietfly.Entity.UserEntity;
import org.example.vietfly.Model.DTO.LoginDTO;
import org.example.vietfly.Model.Request.LoginRequest;
import org.example.vietfly.Model.Response.DataResponse;
import org.example.vietfly.Model.Response.MessageResponse;
import org.example.vietfly.Repository.UserRepository;
import org.example.vietfly.Service.UserService;
import org.example.vietfly.Utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApi {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @GetMapping("/api/me")
    public ResponseEntity<?> getCurrentUser(@CookieValue("token") String token) {
        System.out.println(token);
        String email = jwtTokenUtils.getUsernameFromJWT(token);
        UserEntity user = userRepository.findByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (token == null || !jwtTokenUtils.validateToken(token, user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setMessage("Login success");
        loginDTO.setToken(token);
        loginDTO.setIdUser(user.getIdUser());
        loginDTO.setFullname(user.getFullName());
        loginDTO.setRole(user.getRole());
        loginDTO.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(loginDTO);
    }

    @PostMapping(value = "/api/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        DataResponse dataResponse = new DataResponse();
        Object result = userService.Login(loginRequest);
        if (result instanceof MessageResponse){
            return new ResponseEntity<>(result, ((MessageResponse) result).getStatus());
        }

        dataResponse.setData(result);
        dataResponse.setMessage("Success");
        dataResponse.setStatus(HttpStatus.OK);
        Cookie cookie = new Cookie("token", ((LoginDTO) result).getToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(3 * 24 * 60 * 60);
        response.addCookie(cookie);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
