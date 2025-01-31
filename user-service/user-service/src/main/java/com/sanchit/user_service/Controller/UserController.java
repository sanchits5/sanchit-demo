package com.sanchit.user_service.Controller;

import com.sanchit.user_service.Model.Users;
import com.sanchit.user_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/userDetails")
    public ResponseEntity<List<Users>> getUserDetails()
    {
        List<Users> usersDetails=userService.getUserDetails();
        return new ResponseEntity<>(usersDetails,HttpStatus.OK);
    }

    @PostMapping("/user/createUser")
    public ResponseEntity<Users> createUser(@RequestBody Users users)
    {
        return new ResponseEntity<Users>(userService.createUser(users), HttpStatus.CREATED);
    }
}
