package com.example.JWTAuth.controller;

import com.example.JWTAuth.dto.RequestResponse;
import com.example.JWTAuth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<RequestResponse> signUP(@RequestBody RequestResponse response)
    {
        return ResponseEntity.ok(authService.signUp(response));
    }

    @PostMapping("/signIn")
    public ResponseEntity<RequestResponse> signIn(@RequestBody RequestResponse response)
    {
        return ResponseEntity.ok(authService.signIn(response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RequestResponse>  refreshToken(@RequestBody RequestResponse response )
    {
        return ResponseEntity.ok(authService.refreshToken(response));
    }


}
