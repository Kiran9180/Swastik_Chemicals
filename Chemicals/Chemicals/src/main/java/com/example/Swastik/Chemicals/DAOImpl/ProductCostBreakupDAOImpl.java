package com.example.Swastik.Chemicals.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Swastik.Chemicals.DAO.ProductCostBreakupDAO;
import com.example.Swastik.Chemicals.Model.ProductCostBreakup;
import com.example.Swastik.Chemicals.Util.DBUtil;

@Repository
public class ProductCostBreakupDAOImpl
        implements ProductCostBreakupDAO {

    @Override
    public void save(ProductCostBreakup breakup) {

        try {

            Connection con =
                    DBUtil.getConnection();

            String sql =
                    "INSERT INTO product_cost_breakup(product_id,cost_name,amount) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, breakup.getProductId());
            ps.setString(2, breakup.getCostName());
            ps.setBigDecimal(3, breakup.getAmount());

            ps.executeUpdate();

            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void update(ProductCostBreakup breakup) {

        try {

            Connection con =
                    DBUtil.getConnection();

            String sql =
                "UPDATE product_cost_breakup SET cost_name=?,amount=? WHERE breakup_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, breakup.getCostName());
            ps.setBigDecimal(2, breakup.getAmount());
            ps.setLong(3, breakup.getBreakupId());

            ps.executeUpdate();

            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long breakupId) {

        try {

            Connection con =
                    DBUtil.getConnection();

            String sql =
                "DELETE FROM product_cost_breakup WHERE breakup_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, breakupId);

            ps.executeUpdate();

            ps.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public ProductCostBreakup getById(Long breakupId) {

        ProductCostBreakup breakup = null;

        try {

            Connection con =
                    DBUtil.getConnection();

            String sql =
                    "SELECT * FROM product_cost_breakup WHERE breakup_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, breakupId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                breakup = new ProductCostBreakup();

                breakup.setBreakupId(
                        rs.getLong("breakup_id"));

                breakup.setProductId(
                        rs.getLong("product_id"));

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
    public List<ProductCostBreakup> getByProductId(
            Long productId) {

        List<ProductCostBreakup> list =
                new ArrayList<>();

        try {

            Connection con =
                    DBUtil.getConnection();

            String sql =
                "SELECT * FROM product_cost_breakup WHERE product_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, productId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                ProductCostBreakup breakup =
                        new ProductCostBreakup();

                breakup.setBreakupId(
                        rs.getLong("breakup_id"));

                breakup.setProductId(
                        rs.getLong("product_id"));

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
}