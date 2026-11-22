package com.example.Swastik.Chemicals.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Swastik.Chemicals.DAO.BillDAO;
import com.example.Swastik.Chemicals.Model.Bill;
import com.example.Swastik.Chemicals.Util.DBUtil;

@Repository
public class BillDAOImpl implements BillDAO {

    @Override
    public void save(Bill bill) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "INSERT INTO bill(order_id,product_total,additional_total,grand_total,bill_date) VALUES(?,?,?,?,NOW())";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, bill.getOrderId());
            ps.setBigDecimal(2, bill.getProductTotal());
            ps.setBigDecimal(3, bill.getAdditionalTotal());
            ps.setBigDecimal(4, bill.getGrandTotal());

            ps.executeUpdate();

            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void update(Bill bill) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "UPDATE bill SET product_total=?,additional_total=?,grand_total=? WHERE bill_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setBigDecimal(1, bill.getProductTotal());
            ps.setBigDecimal(2, bill.getAdditionalTotal());
            ps.setBigDecimal(3, bill.getGrandTotal());
            ps.setLong(4, bill.getBillId());

            ps.executeUpdate();

            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long billId) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "DELETE FROM bill WHERE bill_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, billId);

            ps.executeUpdate();

            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public Bill getById(Long billId) {

        Bill bill = null;

        try {
        	
        	System.out.println("i am here in bill dao imps");

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM bill WHERE bill_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, billId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                bill = new Bill();

                bill.setBillId(
                        rs.getLong("bill_id"));

                bill.setOrderId(
                        rs.getLong("order_id"));

                bill.setProductTotal(
                        rs.getBigDecimal("product_total"));

                bill.setAdditionalTotal(
                        rs.getBigDecimal("additional_total"));

                bill.setGrandTotal(
                        rs.getBigDecimal("grand_total"));

                if(rs.getTimestamp("bill_date") != null) {

                    bill.setBillDate(
                            rs.getTimestamp("bill_date")
                            .toLocalDateTime());
                }
            }

            rs.close();
            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return bill;
    }

    @Override
    public Bill getByOrderId(Long orderId) {

        Bill bill = null;

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM bill WHERE order_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                bill = new Bill();

                bill.setBillId(
                        rs.getLong("bill_id"));

                bill.setOrderId(
                        rs.getLong("order_id"));

                bill.setProductTotal(
                        rs.getBigDecimal("product_total"));

                bill.setAdditionalTotal(
                        rs.getBigDecimal("additional_total"));

                bill.setGrandTotal(
                        rs.getBigDecimal("grand_total"));
            }

            rs.close();
            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return bill;
    }

    @Override
    public List<Bill> getAll() {

        List<Bill> bills =
                new ArrayList<>();

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM bill ORDER BY bill_id DESC";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Bill bill = new Bill();

                bill.setBillId(
                        rs.getLong("bill_id"));

                bill.setOrderId(
                        rs.getLong("order_id"));

                bill.setProductTotal(
                        rs.getBigDecimal("product_total"));

                bill.setAdditionalTotal(
                        rs.getBigDecimal("additional_total"));

                bill.setGrandTotal(
                        rs.getBigDecimal("grand_total"));

                bills.add(bill);
            }

            rs.close();
            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return bills;
    }
}