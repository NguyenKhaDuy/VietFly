package org.example.vietfly.Service;

import org.example.vietfly.Model.Request.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Object Login(LoginRequest loginRequest);
}
