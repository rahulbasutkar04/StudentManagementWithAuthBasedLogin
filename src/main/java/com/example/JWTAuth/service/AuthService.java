package com.example.JWTAuth.service;

import com.example.JWTAuth.dto.RequestResponse;
import com.example.JWTAuth.entity.OurUsers;
import com.example.JWTAuth.respository.OurUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private OurUserRepository ourUserRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public RequestResponse signUp(RequestResponse resgistrationRequest)
    {
        RequestResponse res=new RequestResponse();
        try{
            OurUsers ourUsers =new OurUsers();
            ourUsers.setEmail(resgistrationRequest.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(resgistrationRequest.getPassword()));
            ourUsers.setRole(resgistrationRequest.getRole());
            OurUsers ourUserResult=ourUserRepository.save(ourUsers);

            if(ourUserResult!=null && ourUserResult.getId()>0)
            {
                res.setMessage("user Saved Successfully");
                res.setOurUsers(ourUserResult);
                res.setStatusCode(200);
            }

        }catch (Exception e)
        {
       res.setStatusCode(500);
       res.setError(e.getMessage());
        }
        return  res;
    }

    public  RequestResponse signIn(RequestResponse signInRequest)
    {
        RequestResponse response=new RequestResponse();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));
            var user=ourUserRepository.findByEmail(signInRequest.getEmail()).orElseThrow();
            System.out.println("USER IS:"+user);
            var jwt=jwtUtils.generateToken(user);
            var refreshToken=jwtUtils.generateRefreshToken(new HashMap<>(),user);
            response.setStatusCode(200);
            response.setTokens(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully sign in");
        }
        catch (Exception e)
        {
             response.setStatusCode(5000);
             response.setError(e.getMessage());
        }

        return  response;
    }

    public RequestResponse refreshToken(RequestResponse refreshTokenRequest) {
        RequestResponse response = new RequestResponse();
        try {
            String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getTokens());
            OurUsers users = ourUserRepository.findByEmail(ourEmail).orElseThrow(() -> new RuntimeException("User not found"));

            if (jwtUtils.isTokenValid(refreshTokenRequest.getTokens(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setStatusCode(200);
                response.setTokens(jwt);
                response.setRefreshToken(refreshTokenRequest.getTokens());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully refreshed Token");
            } else {
                response.setStatusCode(401);
                response.setError("Invalid or expired token.");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError("Failed to refresh token: " + e.getMessage());
        }

        return response;
    }

}
