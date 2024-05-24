package com.nalas.pnccontrollers.controllers;

import com.nalas.pnccontrollers.domain.dtos.GeneralResponse;
import com.nalas.pnccontrollers.domain.dtos.LoginDTO;
import com.nalas.pnccontrollers.domain.dtos.TokenDTO;
import com.nalas.pnccontrollers.domain.dtos.UserRegiserDTO;
import com.nalas.pnccontrollers.domain.entities.Token;
import com.nalas.pnccontrollers.domain.entities.User;
import com.nalas.pnccontrollers.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO info) {
        User user = userService.findByIdentifier(info.getIdentifier());

        if (user == null) {
            return GeneralResponse.builder()
                    .message("User not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        if (!userService.checkPassword(user, info.getPassword())) {
            return GeneralResponse.builder()
                    .status(HttpStatus.NOT_FOUND) // Este status puede ser un 401 unauthorized
                    .message("Incorrect password")
                    .build();
        }

        try {
            Token token = userService.registerToken(user);
            return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // TODO: return status
    }

    @PostMapping("/register")
    public  ResponseEntity<GeneralResponse> register(@RequestBody @Valid UserRegiserDTO info) {
        User user = userService.findByUsernameOrEmail(info.getUsername(), info.getEmail());

        if (user != null) {
            return GeneralResponse.builder()
                    .status(HttpStatus.CONFLICT)
                    .message("User already exists")
                    .build();
        }

        userService.register(info);

        return GeneralResponse.builder()
                .message("User registered successfully")
                .status(HttpStatus.CREATED)
                .build();
    }
}
