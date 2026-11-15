package com.example.Swastik.Chemicals.DAO;

import java.util.List;

import com.example.Swastik.Chemicals.Model.AdditionalCost;

public interface AdditionalCostDAO {

    void save(AdditionalCost additionalCost);

    void update(AdditionalCost additionalCost);

    void delete(Long additionalCostId);

    AdditionalCost getById(Long additionalCostId);

    List<AdditionalCost> getAll();

    List<AdditionalCost> getByOrderId(Long orderId);
}