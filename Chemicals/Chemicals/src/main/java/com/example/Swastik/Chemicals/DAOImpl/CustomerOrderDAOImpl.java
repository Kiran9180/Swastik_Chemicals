package com.example.Swastik.Chemicals.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Swastik.Chemicals.DAO.CustomerOrderDAO;
import com.example.Swastik.Chemicals.Model.CustomerOrder;
import com.example.Swastik.Chemicals.Util.DBUtil;

@Repository
public class CustomerOrderDAOImpl implements CustomerOrderDAO {

	@Override
	public Long save(CustomerOrder order) {
		
		
		
		

	    Long orderId = null;

	    try {
	    	
	    	
	    	
	    	
	    	
	    	Long userId = order.getUserId();

	    	if(userId == null) {

	    	    Connection con = DBUtil.getConnection();

	    	    String userSql =
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
	    	            access,
	    	            password_hash,
	    	            is_active
	    	        )
	    	        VALUES
	    	        (
	    	            ?,?,?,?,?,?,?,?,?,?,?
	    	        )
	    	        """;

	    	    PreparedStatement userPs =
	    	        con.prepareStatement(
	    	            userSql,
	    	            PreparedStatement.RETURN_GENERATED_KEYS
	    	        );

	    	    userPs.setString(1, order.getCustomerName());
	    	    userPs.setString(2, order.getEmail());
	    	    userPs.setString(3, order.getMobile());
	    	    userPs.setString(4, order.getShopName());
	    	    userPs.setString(5, order.getFullAddress());
	    	    userPs.setString(6, order.getTaluka());
	    	    userPs.setString(7, order.getDistrict());
	    	    userPs.setString(8, order.getPincode());

	    	    userPs.setString(
	    	        9,
	    	        "REGULAR"
	    	    );

	    	    userPs.setString(
	    	        10,
	    	        "newUser1"
	    	    );

	    	    userPs.setInt(
	    	        11,
	    	        1
	    	    );

	    	    userPs.executeUpdate();

	    	    ResultSet userRs =
	    	        userPs.getGeneratedKeys();

	    	    if(userRs.next()) {

	    	        userId =
	    	            userRs.getLong(1);

	    	        order.setUserId(userId);
	    	    }

	    	    userRs.close();
	    	    userPs.close();
	    	}
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	

	        Connection con =
	                DBUtil.getConnection();

	        String sql =
	                """
	                INSERT INTO customer_order
	                (
	                    user_id,
	                    customer_name,
	                    mobile,
	                    email,
	                    shop_name,
	                    taluka,
	                    village,
	                    district,
	                    full_address,
	                    pincode,
	                    remarks,
	                    status,
	                    created_at
	                )
	                VALUES
	                (
	                    ?,?,?,?,?,?,?,?,?,?,
	                    ?,?,NOW()
	                )
	                """;

	        PreparedStatement ps =
	                con.prepareStatement(
	                        sql,
	                        PreparedStatement.RETURN_GENERATED_KEYS);

	        ps.setLong(
	        	    1,
	        	    userId
	        	);

	        ps.setString(
	                2,
	                order.getCustomerName());

	        ps.setString(
	                3,
	                order.getMobile());

	        ps.setString(
	                4,
	                order.getEmail());

	        ps.setString(
	                5,
	                order.getShopName());

	        ps.setString(
	                6,
	                order.getTaluka());

	        ps.setString(
	                7,
	                order.getVillage());

	        ps.setString(
	                8,
	                order.getDistrict());

	        ps.setString(
	                9,
	                order.getFullAddress());

	        ps.setString(
	                10,
	                order.getPincode());

	        ps.setString(
	                11,
	                order.getRemarks());

	        ps.setString(
	                12,
	                "PENDING_APPROVAL");

	        ps.executeUpdate();

	        ResultSet rs =
	                ps.getGeneratedKeys();

	        if(rs.next()) {

	            orderId = rs.getLong(1);
	        }

	        rs.close();
	        ps.close();

	    }
	    catch(Exception e) {

	        e.printStackTrace();
	    }

	    return orderId;
	}

    @Override
    public void update(CustomerOrder order) {

        try {

            Connection con = DBUtil.getConnection();

            String sql = """
                    UPDATE customer_order
                    SET
                        taluka=?,
                        village=?,
                        district=?,
                        full_address=?,
                        status=?
                    WHERE order_id=?
                    """;

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, order.getTaluka());
            ps.setString(2, order.getVillage());
            ps.setString(3, order.getDistrict());
            ps.setString(4, order.getFullAddress());
            ps.setString(5, order.getStatus());
            ps.setLong(6, order.getOrderId());

            ps.executeUpdate();

            ps.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long orderId) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "DELETE FROM customer_order WHERE order_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderId);

            ps.executeUpdate();

            ps.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public CustomerOrder getById(Long orderId) {

        CustomerOrder order = null;

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM customer_order WHERE order_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderId);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                order = new CustomerOrder();

                order.setOrderId(
                        rs.getLong("order_id"));

                order.setTaluka(
                        rs.getString("taluka"));

                order.setVillage(
                        rs.getString("village"));

                order.setDistrict(
                        rs.getString("district"));

                order.setFullAddress(
                        rs.getString("full_address"));

                order.setStatus(
                        rs.getString("status"));
                
                
                
                order.setUserId(
                        rs.getLong("user_id"));

                order.setCustomerName(
                        rs.getString("customer_name"));

                order.setMobile(
                        rs.getString("mobile"));

                order.setEmail(
                        rs.getString("email"));

                order.setShopName(
                        rs.getString("shop_name"));

                order.setPincode(
                        rs.getString("pincode"));

                order.setRemarks(
                        rs.getString("remarks"));
                
                

                if (rs.getTimestamp("created_at") != null) {

                    order.setCreatedAt(
                            rs.getTimestamp("created_at")
                                    .toLocalDateTime());
                }

            }

            rs.close();
            ps.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<CustomerOrder> getAll() {

        List<CustomerOrder> orders =
                new ArrayList<>();

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM customer_order ORDER BY order_id DESC";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                CustomerOrder order =
                        new CustomerOrder();

                order.setOrderId(
                        rs.getLong("order_id"));

                order.setTaluka(
                        rs.getString("taluka"));

                order.setVillage(
                        rs.getString("village"));

                order.setDistrict(
                        rs.getString("district"));

                order.setFullAddress(
                        rs.getString("full_address"));

                order.setStatus(
                        rs.getString("status"));
                
                
                order.setUserId(
                        rs.getLong("user_id"));

                order.setCustomerName(
                        rs.getString("customer_name"));

                order.setMobile(
                        rs.getString("mobile"));

                order.setEmail(
                        rs.getString("email"));

                order.setShopName(
                        rs.getString("shop_name"));

                order.setPincode(
                        rs.getString("pincode"));

                order.setRemarks(
                        rs.getString("remarks"));
                

                if (rs.getTimestamp("created_at") != null) {

                    order.setCreatedAt(
                            rs.getTimestamp("created_at")
                                    .toLocalDateTime());
                }

                orders.add(order);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<CustomerOrder> getPendingOrders() {

        List<CustomerOrder> orders =
                new ArrayList<>();

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM customer_order WHERE status='PENDING_APPROVAL'";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                CustomerOrder order =
                        new CustomerOrder();

                order.setOrderId(
                        rs.getLong("order_id"));

                order.setTaluka(
                        rs.getString("taluka"));

                order.setVillage(
                        rs.getString("village"));

                order.setDistrict(
                        rs.getString("district"));

                order.setFullAddress(
                        rs.getString("full_address"));

                order.setStatus(
                        rs.getString("status"));
                
                
                order.setUserId(
                        rs.getLong("user_id"));

                order.setCustomerName(
                        rs.getString("customer_name"));

                order.setMobile(
                        rs.getString("mobile"));

                order.setEmail(
                        rs.getString("email"));

                order.setShopName(
                        rs.getString("shop_name"));

                order.setPincode(
                        rs.getString("pincode"));

                order.setRemarks(
                        rs.getString("remarks"));
                
                

                orders.add(order);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<CustomerOrder> getApprovedOrders() {

        List<CustomerOrder> orders =
                new ArrayList<>();

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM customer_order WHERE status='APPROVED'";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                CustomerOrder order =
                        new CustomerOrder();

                order.setOrderId(
                        rs.getLong("order_id"));

                order.setTaluka(
                        rs.getString("taluka"));

                order.setVillage(
                        rs.getString("village"));

                order.setDistrict(
                        rs.getString("district"));

                order.setFullAddress(
                        rs.getString("full_address"));

                order.setStatus(
                        rs.getString("status"));
                
                order.setUserId(
                        rs.getLong("user_id"));

                order.setCustomerName(
                        rs.getString("customer_name"));

                order.setMobile(
                        rs.getString("mobile"));

                order.setEmail(
                        rs.getString("email"));

                order.setShopName(
                        rs.getString("shop_name"));

                order.setPincode(
                        rs.getString("pincode"));

                order.setRemarks(
                        rs.getString("remarks"));
                

                orders.add(order);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public void updateStatus(Long orderId,
                             String status) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "UPDATE customer_order SET status=? WHERE order_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setLong(2, orderId);

            ps.executeUpdate();

            ps.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}