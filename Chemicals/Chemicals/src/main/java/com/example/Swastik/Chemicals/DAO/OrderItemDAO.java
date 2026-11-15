package com.example.Swastik.Chemicals.DAO;

import java.util.List;

import com.example.Swastik.Chemicals.Model.OrderItem;

public interface OrderItemDAO {

    void save(OrderItem orderItem);

    void update(OrderItem orderItem);

    void delete(Long orderItemId);

    OrderItem getById(Long orderItemId);

    List<OrderItem> getAll();

    List<OrderItem> getByOrderId(Long orderId);
}