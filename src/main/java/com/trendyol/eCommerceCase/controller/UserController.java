package com.trendyol.eCommerceCase.controller;

import com.trendyol.eCommerceCase.exceptions.UsernameExistException;
import com.trendyol.eCommerceCase.model.User;
import com.trendyol.eCommerceCase.model.UserPrincipal;
import com.trendyol.eCommerceCase.service.UserService;
import com.trendyol.eCommerceCase.utility.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@RestController
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@Valid @RequestBody User user) throws UsernameExistException {
        User newUser = userService.signUp(user);
        return new ResponseEntity<>(newUser,CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        User loginUser = userService.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = jwtTokenProvider.getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeader, OK);
    }

    @RequestMapping(value = "/user/reset", method = RequestMethod.GET)
    public ResponseEntity<String> resetPassword(String ok) {
        return new ResponseEntity<>("ok",CREATED);
    }

}
