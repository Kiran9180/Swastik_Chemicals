package com.example.Swastik.Chemicals.Controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Swastik.Chemicals.DAO.UsersDAO;
import com.example.Swastik.Chemicals.Model.Users;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsersController {

    @Autowired
    private UsersDAO usersDAO;

    @GetMapping("/check")
    public Map<String,Object> checkCustomer(

            @RequestParam
            String mobile,

            @RequestParam(
                required = false
            )
            String email) {

        Map<String,Object> response =
                new HashMap<>();

        Users user =
                usersDAO
                        .findByMobileOrEmail(
                                mobile,
                                email);

        if(user == null){

            response.put(
                    "exists",
                    false);

        } else {

            response.put(
                    "exists",
                    true);

            response.put(
                    "user",
                    user);
        }

        return response;
    }
}