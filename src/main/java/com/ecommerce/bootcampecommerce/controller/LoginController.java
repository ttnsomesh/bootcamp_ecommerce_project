package com.ecommerce.bootcampecommerce.controller;

import com.ecommerce.bootcampecommerce.customexception.JsonFormatExceptionClass;
import com.ecommerce.bootcampecommerce.customexception.UserNotActiveException;
import com.ecommerce.bootcampecommerce.entity.User;
import com.ecommerce.bootcampecommerce.jwtmodel.AuthenticationRequest;
import com.ecommerce.bootcampecommerce.jwtmodel.AuthenticationResponse;
import com.ecommerce.bootcampecommerce.repository.UserRepository;
import com.ecommerce.bootcampecommerce.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/customer")
    public ResponseEntity<?> createAuthenticationTokencustomer
            (@RequestBody AuthenticationRequest authenticationRequest,
             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken
                            (authenticationRequest.getUsername(),
                                    authenticationRequest.getPassword())
            );

        } catch (AuthenticationException e) {

            JsonFormatExceptionClass.getJsonMessage("username and password not matched",request,response);
        }


        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwtToken = jwtUtil.generateToken(userDetails);
        User user = userRepository.findByEmail(userDetails.getUsername());
        if(user.isActive() == true){
            return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
        }else{
            throw new UserNotActiveException("customer not activated");
        }
    }

    @PostMapping("/seller")
    public ResponseEntity<?> createAuthenticationTokenseller
            (@RequestBody AuthenticationRequest authenticationRequest,
             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken
                            (authenticationRequest.getUsername(),
                                    authenticationRequest.getPassword())
            );

        } catch (AuthenticationException e) {

            JsonFormatExceptionClass.getJsonMessage("username and password not matched",request,response);
        }


        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwtToken = jwtUtil.generateToken(userDetails);
        User user = userRepository.findByEmail(userDetails.getUsername());
        if(user.isActive() == true){
            return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
        }else{
            throw new UserNotActiveException("seller not activated");
        }
    }
    @PostMapping("/admin")
    public ResponseEntity<?> createAuthenticationTokenadmin
            (@RequestBody AuthenticationRequest authenticationRequest,
             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken
                            (authenticationRequest.getUsername(),
                                    authenticationRequest.getPassword())
            );

        } catch (AuthenticationException e) {

            JsonFormatExceptionClass.getJsonMessage("username and password not matched",request,response);
        }


        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwtToken = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }

}
