package org.example.vietfly.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.vietfly.Entity.Role;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String fullname;
    private Role role;
    private UUID idUser;
    private String token;
    private String message;
    private String email;
    private HttpStatus status;
}
