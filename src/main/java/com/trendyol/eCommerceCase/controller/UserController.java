package com.trendyol.eCommerceCase.controller;

import com.trendyol.eCommerceCase.exceptions.UserNameExistException;
import com.trendyol.eCommerceCase.model.User;
import com.trendyol.eCommerceCase.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/user/sign-up")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws UserNameExistException {
        User newUser = userService.signUp(user);
        return new ResponseEntity<>(newUser,CREATED);
    }
}
