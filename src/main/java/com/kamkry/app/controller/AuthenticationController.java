package com.kamkry.app.controller;


import com.kamkry.app.model.AppUser;
import com.kamkry.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody AppUser user){
        if(userService.get(user.getUsername())!=null)
            throw new UserAlreadyExistsException(user.getUsername());


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
    }
}
