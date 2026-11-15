package com.example.Swastik.Chemicals.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Swastik.Chemicals.DAO.AdditionalCostDAO;
import com.example.Swastik.Chemicals.Model.AdditionalCost;
import com.example.Swastik.Chemicals.Util.DBUtil;

@Repository
public class AdditionalCostDAOImpl implements AdditionalCostDAO {

    @Override
    public void save(AdditionalCost additionalCost) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "INSERT INTO additional_cost(order_id,cost_name,amount,remarks) VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, additionalCost.getOrderId());
            ps.setString(2, additionalCost.getCostName());
            ps.setBigDecimal(3, additionalCost.getAmount());
            ps.setString(4, additionalCost.getRemarks());

            ps.executeUpdate();

            ps.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void update(AdditionalCost additionalCost) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "UPDATE additional_cost SET cost_name=?,amount=?,remarks=? WHERE additional_cost_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, additionalCost.getCostName());
            ps.setBigDecimal(2, additionalCost.getAmount());
            ps.setString(3, additionalCost.getRemarks());
            ps.setLong(4, additionalCost.getAdditionalCostId());

            ps.executeUpdate();

            ps.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long additionalCostId) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "DELETE FROM additional_cost WHERE additional_cost_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, additionalCostId);

            ps.executeUpdate();

            ps.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public AdditionalCost getById(Long additionalCostId) {

        AdditionalCost additionalCost = null;

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM additional_cost WHERE additional_cost_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, additionalCostId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                additionalCost = new AdditionalCost();

                additionalCost.setAdditionalCostId(
                        rs.getLong("additional_cost_id"));

                additionalCost.setOrderId(
                        rs.getLong("order_id"));

                additionalCost.setCostName(
                        rs.getString("cost_name"));

                additionalCost.setAmount(
                        rs.getBigDecimal("amount"));

                additionalCost.setRemarks(
                        rs.getString("remarks"));
            }

            rs.close();
            ps.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return additionalCost;
    }

    @Override
    public List<AdditionalCost> getAll() {

        List<AdditionalCost> list =
                new ArrayList<>();

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM additional_cost";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                AdditionalCost additionalCost =
                        new AdditionalCost();

                additionalCost.setAdditionalCostId(
                        rs.getLong("additional_cost_id"));

                additionalCost.setOrderId(
                        rs.getLong("order_id"));

                additionalCost.setCostName(
                        rs.getString("cost_name"));

                additionalCost.setAmount(
                        rs.getBigDecimal("amount"));

                additionalCost.setRemarks(
                        rs.getString("remarks"));

                list.add(additionalCost);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<AdditionalCost> getByOrderId(Long orderId) {

        List<AdditionalCost> list =
                new ArrayList<>();

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM additional_cost WHERE order_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                AdditionalCost additionalCost =
                        new AdditionalCost();

                additionalCost.setAdditionalCostId(
                        rs.getLong("additional_cost_id"));

                additionalCost.setOrderId(
                        rs.getLong("order_id"));

                additionalCost.setCostName(
                        rs.getString("cost_name"));

                additionalCost.setAmount(
                        rs.getBigDecimal("amount"));

                additionalCost.setRemarks(
                        rs.getString("remarks"));

                list.add(additionalCost);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}