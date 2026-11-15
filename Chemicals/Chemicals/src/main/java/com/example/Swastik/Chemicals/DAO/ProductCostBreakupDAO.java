package com.example.Swastik.Chemicals.DAO;

import java.util.List;

import com.example.Swastik.Chemicals.Model.ProductCostBreakup;

public interface ProductCostBreakupDAO {

    void save(ProductCostBreakup productCostBreakup);

    void update(ProductCostBreakup productCostBreakup);

    void delete(Long breakupId);

    ProductCostBreakup getById(Long breakupId);

    List<ProductCostBreakup> getByProductId(Long productId);

}