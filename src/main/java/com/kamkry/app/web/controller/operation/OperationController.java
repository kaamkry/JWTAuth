package com.kamkry.app.web.controller.operation;

import com.kamkry.app.domain.operation.Operation;
import com.kamkry.app.domain.operation.OperationService;
import com.kamkry.app.domain.user.User;
import com.kamkry.app.domain.user.UserService;
import com.kamkry.app.web.controller.user.exception.UserNotAuthorizedException;
import com.kamkry.app.web.controller.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @Autowired
    private UserService userService;

    @GetMapping("operations/{id}")
    public Operation getOperation(@PathVariable Integer id) {
        return operationService.get(id);
    }

    @GetMapping("/users/{id}/operations")
    public List<Operation> getUserOperations(@PathVariable Integer id) {
        Integer principalId = Integer.parseInt(getPrincipal());
        if (principalId.equals(id) || isAdmin()) {
            User user = userService.get(id);
            if (user == null) throw new UserNotFoundException(id);
            return operationService.getByUserId(id);
        } else throw new UserNotAuthorizedException();
    }

    private String getPrincipal() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private boolean isAdmin() {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority auth : authorities) {
            if (auth.getAuthority().equals("ROLE_ADMIN")) return true;
        }
        return false;
    }


}
