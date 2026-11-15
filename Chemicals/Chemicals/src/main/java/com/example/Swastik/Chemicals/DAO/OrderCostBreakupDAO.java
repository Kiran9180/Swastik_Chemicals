package com.example.Swastik.Chemicals.DAO;

import java.util.List;

import com.example.Swastik.Chemicals.Model.OrderCostBreakup;

public interface OrderCostBreakupDAO {

    void save(OrderCostBreakup orderCostBreakup);

    void update(OrderCostBreakup orderCostBreakup);

    void delete(Long orderCostBreakupId);

    OrderCostBreakup getById(Long orderCostBreakupId);

    List<OrderCostBreakup> getAll();

    List<OrderCostBreakup> getByOrderItemId(Long orderItemId);
    
    void copyProductCostBreakup(
            Long productId,
            Long orderItemId);

    Double getOrderItemTotalCost(
            Long orderItemId);

    Double getOrderTotalCost(
            Long orderId);

    List<OrderCostBreakup> getByOrderId(
            Long orderId);
    
}