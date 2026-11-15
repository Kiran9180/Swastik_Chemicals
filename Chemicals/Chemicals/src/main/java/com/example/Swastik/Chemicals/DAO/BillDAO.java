package com.example.Swastik.Chemicals.DAO;

import java.util.List;

import com.example.Swastik.Chemicals.Model.Bill;

public interface BillDAO {

    void save(Bill bill);

    void update(Bill bill);

    void delete(Long billId);

    Bill getById(Long billId);

    Bill getByOrderId(Long orderId);

    List<Bill> getAll();
}