package com.example.Swastik.Chemicals.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Swastik.Chemicals.DAO.UsersDAO;
import com.example.Swastik.Chemicals.Model.Users;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private UsersDAO userDAO;

    @PostMapping("/login")
    public Users login(
            @RequestBody Users user) {

 
    	
        return userDAO.login(

                user.getEmail(),

                user.getPasswordHash()

        );

       
        
    }

}