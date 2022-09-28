package com.appointemnt.perennial.controller;

import com.appointemnt.perennial.config.JwtTokenUtil;
import com.appointemnt.perennial.dto.JwtRequest;
import com.appointemnt.perennial.dto.JwtResponse;
import com.appointemnt.perennial.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = this.jwtTokenUtil.generateToken(userDetails);
        System.out.println(token);
        return ResponseEntity.ok(new JwtResponse(token));
    }
@GetMapping(value = "/denied")
    public String getAcccessDenied(){
        return "you dont have access to this page";
}
}