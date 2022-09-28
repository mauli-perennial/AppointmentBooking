package com.appointemnt.perennial.controller;

import com.appointemnt.perennial.entity.User;
import com.appointemnt.perennial.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<?> registerUser(@RequestBody @Valid User user) {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }
}
