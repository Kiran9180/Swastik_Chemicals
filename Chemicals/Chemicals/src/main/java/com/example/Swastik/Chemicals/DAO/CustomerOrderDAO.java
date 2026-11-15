package com.example.Swastik.Chemicals.DAO;

import java.util.List;

import com.example.Swastik.Chemicals.Model.CustomerOrder;

public interface CustomerOrderDAO {

    Long save(CustomerOrder order);

    void update(CustomerOrder order);

    void delete(Long orderId);

    CustomerOrder getById(Long orderId);

    List<CustomerOrder> getAll();

    List<CustomerOrder> getPendingOrders();

    List<CustomerOrder> getApprovedOrders();

    void updateStatus(Long orderId, String status);
}