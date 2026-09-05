package org.example.vietfly.Service.Impl;

import org.example.vietfly.Entity.UserEntity;
import org.example.vietfly.Model.DTO.LoginDTO;
import org.example.vietfly.Model.Request.LoginRequest;
import org.example.vietfly.Model.Response.MessageResponse;
import org.example.vietfly.Repository.UserRepository;
import org.example.vietfly.Service.UserService;
import org.example.vietfly.Utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Override
    public Object Login(LoginRequest loginRequest) {
        MessageResponse messageResponse = new MessageResponse();
        LoginDTO loginDTO = new LoginDTO();
        try{
            UserEntity userEntity = userRepository.findByEmail(loginRequest.getEmail());
            if (userEntity != null){
                if (!passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())){
                    messageResponse.setMessage("Password incorrect");
                    messageResponse.setStatus(HttpStatus.BAD_REQUEST);
                    return messageResponse;
                }
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword(), userEntity.getAuthorities());
            authenticationManager.authenticate(authenticationToken);
            String token = jwtTokenUtils.generateToken(userEntity);
            loginDTO.setMessage("Login success");
            loginDTO.setToken(token);
            loginDTO.setIdUser(userEntity.getIdUser());
            loginDTO.setFullname(userEntity.getFullName());
            loginDTO.setEmail(userEntity.getEmail());
            loginDTO.setStatus(HttpStatus.OK);
            return loginDTO;
        }catch (NullPointerException ex){
            messageResponse.setMessage("Can not found email");
            messageResponse.setStatus(HttpStatus.BAD_REQUEST);
            return messageResponse;
        }
    }
}
