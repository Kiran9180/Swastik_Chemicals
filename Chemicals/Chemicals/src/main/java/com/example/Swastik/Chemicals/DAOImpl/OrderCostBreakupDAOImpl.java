package com.example.Swastik.Chemicals.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Swastik.Chemicals.DAO.OrderCostBreakupDAO;
import com.example.Swastik.Chemicals.Model.OrderCostBreakup;
import com.example.Swastik.Chemicals.Util.DBUtil;

@Repository
public class OrderCostBreakupDAOImpl implements OrderCostBreakupDAO {

    @Override
    public void save(OrderCostBreakup orderCostBreakup) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "INSERT INTO order_cost_breakup(order_item_id,cost_name,amount) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderCostBreakup.getOrderItemId());
            ps.setString(2, orderCostBreakup.getCostName());
            ps.setBigDecimal(3, orderCostBreakup.getAmount());

            ps.executeUpdate();

            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void update(OrderCostBreakup orderCostBreakup) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "UPDATE order_cost_breakup SET cost_name=?, amount=? WHERE order_cost_breakup_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, orderCostBreakup.getCostName());
            ps.setBigDecimal(2, orderCostBreakup.getAmount());
            ps.setLong(3, orderCostBreakup.getOrderCostBreakupId());

            ps.executeUpdate();

            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long orderCostBreakupId) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "DELETE FROM order_cost_breakup WHERE order_breakup_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderCostBreakupId);

            ps.executeUpdate();

            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public OrderCostBreakup getById(Long orderCostBreakupId) {

        OrderCostBreakup breakup = null;

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM order_cost_breakup WHERE order_cost_breakup_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderCostBreakupId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                breakup = new OrderCostBreakup();

                breakup.setOrderCostBreakupId(
                        rs.getLong("order_cost_breakup_id"));

                breakup.setOrderItemId(
                        rs.getLong("order_item_id"));

                breakup.setCostName(
                        rs.getString("cost_name"));

                breakup.setAmount(
                        rs.getBigDecimal("amount"));
            }

            rs.close();
            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return breakup;
    }

    @Override
    public List<OrderCostBreakup> getAll() {

        List<OrderCostBreakup> list =
                new ArrayList<>();

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM order_cost_breakup";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                OrderCostBreakup breakup =
                        new OrderCostBreakup();

                breakup.setOrderCostBreakupId(
                        rs.getLong("order_cost_breakup_id"));

                breakup.setOrderItemId(
                        rs.getLong("order_item_id"));

                breakup.setCostName(
                        rs.getString("cost_name"));

                breakup.setAmount(
                        rs.getBigDecimal("amount"));

                list.add(breakup);
            }

            rs.close();
            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<OrderCostBreakup> getByOrderItemId(
            Long orderItemId) {

        List<OrderCostBreakup> list =
                new ArrayList<>();

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM order_cost_breakup WHERE order_item_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderItemId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                OrderCostBreakup breakup =
                        new OrderCostBreakup();

                breakup.setOrderCostBreakupId(
                        rs.getLong("order_cost_breakup_id"));

                breakup.setOrderItemId(
                        rs.getLong("order_item_id"));

                breakup.setCostName(
                        rs.getString("cost_name"));

                breakup.setAmount(
                        rs.getBigDecimal("amount"));

                list.add(breakup);
            }

            rs.close();
            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    /*
     * APPROVAL WORKFLOW
     *
     * Copy Product Cost Breakup
     * to Order Cost Breakup
     */

    @Override
    public void copyProductCostBreakup(
            Long productId,
            Long orderItemId) {

        try {

            Connection con = DBUtil.getConnection();

            String sql = """
                    INSERT INTO order_cost_breakup
                    (
                        order_item_id,
                        cost_name,
                        amount
                    )
                    SELECT
                        ?,
                        cost_name,
                        amount
                    FROM product_cost_breakup
                    WHERE product_id = ?
                    """;

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderItemId);
            ps.setLong(2, productId);

            ps.executeUpdate();

            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    /*
     * Cost Total For Single Product
     */

    @Override
    public Double getOrderItemTotalCost(
            Long orderItemId) {

        Double total = 0.0;

        try {

            Connection con = DBUtil.getConnection();

            String sql = """
                    SELECT
                        SUM(amount) total
                    FROM order_cost_breakup
                    WHERE order_item_id=?
                    """;

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderItemId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                total =
                        rs.getDouble("total");
            }

            rs.close();
            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return total;
    }

    /*
     * Total Product Cost Of Entire Order
     */

    @Override
    public Double getOrderTotalCost(
            Long orderId) {

        Double total = 0.0;

        try {

            Connection con = DBUtil.getConnection();

            String sql = """
                    SELECT
                        SUM(ocb.amount) total
                    FROM order_cost_breakup ocb
                    INNER JOIN order_item oi
                            ON oi.order_item_id =
                               ocb.order_item_id
                    WHERE oi.order_id = ?
                    """;

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                total =
                        rs.getDouble("total");
            }

            rs.close();
            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return total;
    }
    
    @Override
    public List<OrderCostBreakup> getByOrderId(
            Long orderId) {

        List<OrderCostBreakup> list =
                new ArrayList<>();

        try {

            Connection con =
                    DBUtil.getConnection();

            String sql = """
                    SELECT ocb.*
                    FROM order_cost_breakup ocb
                    INNER JOIN order_item oi
                            ON oi.order_item_id =
                               ocb.order_item_id
                    WHERE oi.order_id = ?
                    ORDER BY ocb.order_item_id
                    """;

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                OrderCostBreakup breakup =
                        new OrderCostBreakup();

                breakup.setOrderCostBreakupId(
                        rs.getLong(
                        "order_breakup_id"));

                breakup.setOrderItemId(
                        rs.getLong(
                        "order_item_id"));

                breakup.setCostName(
                        rs.getString(
                        "cost_name"));

                breakup.setAmount(
                        rs.getBigDecimal(
                        "amount"));

                list.add(breakup);
            }

            rs.close();
            ps.close();

        }
        catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}