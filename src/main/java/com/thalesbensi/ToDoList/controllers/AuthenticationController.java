package com.thalesbensi.ToDoList.controllers;

import com.thalesbensi.ToDoList.dtos.AuthenticationDTO;
import com.thalesbensi.ToDoList.dtos.LoginResponseDTO;
import com.thalesbensi.ToDoList.dtos.RegisterDTO;
import com.thalesbensi.ToDoList.entities.User;
import com.thalesbensi.ToDoList.repositories.UserRepository;
import com.thalesbensi.ToDoList.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@Tag(name = "Endpoints For Authentication")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private TokenService tokenService;


    @Operation(summary = "Makes Login and Return a Token for Authenticate Requests ", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK / Successful Login  "),
            @ApiResponse(responseCode = "400", description = "Bad Request / Validation Error"),
            @ApiResponse(responseCode = "404", description = "Not Found / User Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized / Invalid Credentials")
    })
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(summary = "Register a User in Database", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created / User Created With Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request / Validation Error"),
            @ApiResponse(responseCode = "409", description = "Conflict / User With This Username Already Exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")

    })
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
