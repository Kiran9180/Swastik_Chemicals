package com.example.Swastik.Chemicals.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Swastik.Chemicals.DAO.OrderItemDAO;
import com.example.Swastik.Chemicals.Model.OrderItem;
import com.example.Swastik.Chemicals.Util.DBUtil;

@Repository
public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public void save(OrderItem orderItem) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "INSERT INTO order_item(order_id,product_id,quantity) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderItem.getOrderId());
            ps.setLong(2, orderItem.getProductId());
            ps.setBigDecimal(3, orderItem.getQuantity());

            ps.executeUpdate();

            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void update(OrderItem orderItem) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "UPDATE order_item SET product_id=?, quantity=? WHERE order_item_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderItem.getProductId());
            ps.setBigDecimal(2, orderItem.getQuantity());
            ps.setLong(3, orderItem.getOrderItemId());

            ps.executeUpdate();

            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long orderItemId) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "DELETE FROM order_item WHERE order_item_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderItemId);

            ps.executeUpdate();

            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public OrderItem getById(Long orderItemId) {

        OrderItem orderItem = null;

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM order_item WHERE order_item_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderItemId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                orderItem = new OrderItem();

                orderItem.setOrderItemId(
                        rs.getLong("order_item_id"));

                orderItem.setOrderId(
                        rs.getLong("order_id"));

                orderItem.setProductId(
                        rs.getLong("product_id"));

                orderItem.setQuantity(
                        rs.getBigDecimal("quantity"));
            }

            rs.close();
            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return orderItem;
    }

    @Override
    public List<OrderItem> getAll() {

        List<OrderItem> list =
                new ArrayList<>();

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM order_item ORDER BY order_item_id DESC";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                OrderItem orderItem =
                        new OrderItem();

                orderItem.setOrderItemId(
                        rs.getLong("order_item_id"));

                orderItem.setOrderId(
                        rs.getLong("order_id"));

                orderItem.setProductId(
                        rs.getLong("product_id"));

                orderItem.setQuantity(
                        rs.getBigDecimal("quantity"));

                list.add(orderItem);
            }

            rs.close();
            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<OrderItem> getByOrderId(Long orderId) {

        List<OrderItem> list =
                new ArrayList<>();

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM order_item WHERE order_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                OrderItem orderItem =
                        new OrderItem();

                orderItem.setOrderItemId(
                        rs.getLong("order_item_id"));

                orderItem.setOrderId(
                        rs.getLong("order_id"));

                orderItem.setProductId(
                        rs.getLong("product_id"));

                orderItem.setQuantity(
                        rs.getBigDecimal("quantity"));

                list.add(orderItem);
            }

            rs.close();
            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}