package com.technoWeb.Tp.controller;

import com.technoWeb.Tp.configuration.JwtUtil;
import com.technoWeb.Tp.configuration.MyUserDetailsService;
import com.technoWeb.Tp.exception.UnauthorizedException;
import com.technoWeb.Tp.model.AuthenticationResponse;
import com.technoWeb.Tp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new UnauthorizedException("Incorrect username or password");
        }


        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(user.getUserName());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}

