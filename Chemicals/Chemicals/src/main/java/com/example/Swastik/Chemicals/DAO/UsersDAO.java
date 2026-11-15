package com.example.Swastik.Chemicals.DAO;

import com.example.Swastik.Chemicals.Model.Users;

public interface UsersDAO {

    Users login(
            String email,
            String password
    );
    
    
    Users findByMobileOrEmail(
            String mobile,
            String email);
    
    

    Long saveCustomer(Users user);
    
}