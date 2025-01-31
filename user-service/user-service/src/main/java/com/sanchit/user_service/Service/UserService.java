package com.sanchit.user_service.Service;

import com.sanchit.user_service.Model.Users;
import com.sanchit.user_service.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public Users createUser(Users users) {
        users.setRole("USER");
        users.setPassword(encoder.encode(users.getPassword()));
        return userRepo.save(users);

    }

    public List<Users> getUserDetails() {

        List<Users> listOfNUser= userRepo.findAll();
        List<Users> nUserList=listOfNUser.stream().
                filter(u->u.getRole().equals("USER")).collect(Collectors.toList());
        System.out.println(nUserList);
        return nUserList;
    }


    public List<Users> getAdminUserDetails() {

        List<Users> listOfNUser= userRepo.findAll();
        return listOfNUser;
    }

    public Users createAdminUser(Users user) {
        user.setRole("ADMIN");
        return userRepo.save(user);
    }
}
