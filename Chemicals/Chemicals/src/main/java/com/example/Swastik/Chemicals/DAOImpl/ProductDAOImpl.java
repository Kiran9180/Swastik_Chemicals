package com.example.Swastik.Chemicals.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Swastik.Chemicals.DAO.ProductDAO;
import com.example.Swastik.Chemicals.Model.Product;
import com.example.Swastik.Chemicals.Util.DBUtil;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Override
    public void save(Product product) {

        try {

            Connection con = DBUtil.getConnection();

            String sql = """
                    INSERT INTO Product
                    (
                        product_name,
                        final_cost,
                        active,
                        created_at
                    )
                    VALUES
                    (
                        ?, ?, ?, NOW()
                    )
                    """;

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, product.getProductName());
            ps.setBigDecimal(2, product.getFinalCost());
            ps.setBoolean(3, product.getActive());

            ps.executeUpdate();

            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

      
    }

    @Override
    public void update(Product product) {

        try {

            Connection con = DBUtil.getConnection();

            String sql = """
                    UPDATE Product
                    SET
                        product_name=?,
                        final_cost=?,
                        active=?
                    WHERE product_id=?
                    """;

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, product.getProductName());
            ps.setBigDecimal(2, product.getFinalCost());
            ps.setBoolean(3, product.getActive());
            ps.setLong(4, product.getProductId());

            ps.executeUpdate();

            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long productId) {

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "DELETE FROM Product WHERE product_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, productId);

            ps.executeUpdate();

            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getById(Long productId) {

        Product product = null;

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM Product WHERE product_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setLong(1, productId);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                product = new Product();

                product.setProductId(
                        rs.getLong("product_id"));

                product.setProductName(
                        rs.getString("product_name"));

                product.setFinalCost(
                        rs.getBigDecimal("final_cost"));

                product.setActive(
                        rs.getBoolean("active"));

                if (rs.getTimestamp("created_at") != null) {

                    product.setCreatedAt(
                            rs.getTimestamp("created_at")
                              .toLocalDateTime());
                }
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<Product> getAll() {

        List<Product> products =
                new ArrayList<>();

        try {

            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT * FROM product ORDER BY product_name";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setProductId(
                        rs.getLong("product_id"));

                product.setProductName(
                        rs.getString("product_name"));

                product.setFinalCost(
                        rs.getBigDecimal("final_cost"));

                product.setActive(
                        rs.getBoolean("active"));

                if (rs.getTimestamp("created_at") != null) {

                    product.setCreatedAt(
                            rs.getTimestamp("created_at")
                                    .toLocalDateTime());
                }

                products.add(product);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getActiveProducts() {

        List<Product> products =
                new ArrayList<>();

        try {

            Connection con = DBUtil.getConnection();

            String sql = """
                    SELECT *
                    FROM Product
                    WHERE active=1
                    ORDER BY product_name
                    """;

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setProductId(
                        rs.getLong("product_id"));

                product.setProductName(
                        rs.getString("product_name"));

                product.setFinalCost(
                        rs.getBigDecimal("final_cost"));

                product.setActive(
                        rs.getBoolean("active"));

                if (rs.getTimestamp("created_at") != null) {

                    product.setCreatedAt(
                            rs.getTimestamp("created_at")
                                    .toLocalDateTime());
                }

                products.add(product);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
}
