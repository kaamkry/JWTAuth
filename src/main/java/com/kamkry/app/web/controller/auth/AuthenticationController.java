package com.kamkry.app.web.controller.auth;


import com.kamkry.app.domain.user.AppUser;
import com.kamkry.app.domain.user.UserService;
import com.kamkry.app.web.controller.user.UserAlreadyExistsException;
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
