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
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/all-users")
    public ResponseEntity<List<Users>> getAllUsers()
    {
        List<Users> allUsers=userService.getAdminUserDetails();
        if(allUsers!=null && !allUsers.isEmpty())
        {
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/create-admin-user")
    public Users createAdminUser(@RequestBody Users user)
    {
        Users user2=userService.createAdminUser(user);
        return user2;
    }

}
