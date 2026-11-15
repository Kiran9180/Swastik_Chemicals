package com.example.Swastik.Chemicals.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.example.Swastik.Chemicals.DAO.UsersDAO;
import com.example.Swastik.Chemicals.Model.Users;
import com.example.Swastik.Chemicals.Util.DBUtil;

@Repository
public class UsersDAOImpl implements UsersDAO {

    @Override
    public Users login(
            String email,
            String password) {

        Users user = null;

        try {

            Connection con =
                    DBUtil.getConnection();

            String sql =
                    "SELECT * " +
                    "FROM users " +
                    "WHERE email=? " +
                    "AND password_hash=? " +
                    "AND is_active=true";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    email
            );

            ps.setString(
                    2,
                    password
            );

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                user = new Users();

                user.setId(
                        rs.getLong("id"));

                user.setName(
                        rs.getString("name"));

                user.setEmail(
                        rs.getString("email"));

          

               
                user.setAccess(
                        rs.getString("access"));

               

            }

            rs.close();
            ps.close();

        }
        catch(Exception e) {

            e.printStackTrace();

        }

        return user;
        
       

    }
    
    
    
    
    @Override
    public Users findByMobileOrEmail(
            String mobile,
            String email) {

        Users user = null;

        try {

            Connection con =
                    DBUtil.getConnection();

            String sql =
                    """
                    SELECT *
                    FROM users
                    WHERE mobile = ?
                       OR email = ?
                    LIMIT 1
                    """;

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, mobile);
            ps.setString(2, email);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                user = new Users();

                user.setId(
                        rs.getLong("id"));

                user.setName(
                        rs.getString("name"));

                user.setEmail(
                        rs.getString("email"));

                user.setMobile(
                        rs.getString("mobile"));

                user.setShopName(
                        rs.getString("shop_name"));

                user.setAddress(
                        rs.getString("address"));

                user.setTaluka(
                        rs.getString("taluka"));

                user.setDistrict(
                        rs.getString("district"));

                user.setPincode(
                        rs.getString("pincode"));

                user.setAccess(
                        rs.getString("access"));
            }

            rs.close();
            ps.close();

        }
        catch(Exception e) {

            e.printStackTrace();
        }

        return user;
    }
    
    
    @Override
    public Long saveCustomer(
            Users user) {

        Long id = null;

        try {

            Connection con =
                    DBUtil.getConnection();

            String sql =
                    """
                    INSERT INTO users
                    (
                        name,
                        email,
                        mobile,
                        shop_name,
                        address,
                        taluka,
                        district,
                        pincode,
                        gst_no,
                        access,
                        password_hash,
                        is_active
                    )
                    VALUES
                    (
                        ?,?,?,?,?,?,?,?,?,?,
                        '',true
                    )
                    """;

            PreparedStatement ps =
                    con.prepareStatement(
                            sql,
                            PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1,user.getName());
            ps.setString(2,user.getEmail());
            ps.setString(3,user.getMobile());
            ps.setString(4,user.getShopName());
            ps.setString(5,user.getAddress());
            ps.setString(6,user.getTaluka());
            ps.setString(7,user.getDistrict());
            ps.setString(8,user.getPincode());
            ps.setString(9,user.getGstNo());

            ps.setString(
                    10,
                    "CUSTOMER"
            );

            ps.executeUpdate();

            ResultSet rs =
                    ps.getGeneratedKeys();

            if(rs.next()) {

                id = rs.getLong(1);
            }

            rs.close();
            ps.close();

        }
        catch(Exception e) {

            e.printStackTrace();
        }

        return id;
    }

}