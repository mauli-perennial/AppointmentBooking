package com.appointemnt.perennial.controller;

import com.appointemnt.perennial.entity.User;
import com.appointemnt.perennial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
