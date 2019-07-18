package com.kamkry.app.web.controller.operation;

import com.kamkry.app.domain.operation.Operation;
import com.kamkry.app.domain.operation.OperationService;
import com.kamkry.app.domain.user.User;
import com.kamkry.app.domain.user.UserService;
import com.kamkry.app.web.controller.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }

    @GetMapping("/users/{id}/operations")
    public List<Operation> getUserOperations(@PathVariable Integer id) {
        User user = userService.get(id);
        if (user == null) throw new UserNotFoundException(id);
        return operationService.getByUserId(id);
    }

}
