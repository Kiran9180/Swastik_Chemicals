package com.example.Swastik.Chemicals.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Swastik.Chemicals.DAO.AdditionalCostDAO;
import com.example.Swastik.Chemicals.Model.AdditionalCost;

@RestController
@RequestMapping("/additionalCost")
@CrossOrigin("*")
public class AdditionalCostController {

    @Autowired
    private AdditionalCostDAO additionalCostDAO;

    @PostMapping("/save")
    public String save(@RequestBody AdditionalCost additionalCost) {

        additionalCostDAO.save(additionalCost);

        return "Saved Successfully";
    }

    @PutMapping("/update")
    public String update(@RequestBody AdditionalCost additionalCost) {

        additionalCostDAO.update(additionalCost);

        return "Updated Successfully";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        additionalCostDAO.delete(id);

        return "Deleted Successfully";
    }

    @GetMapping("/{id}")
    public AdditionalCost getById(@PathVariable Long id) {

        return additionalCostDAO.getById(id);
    }

    @GetMapping("/all")
    public List<AdditionalCost> getAll() {

        return additionalCostDAO.getAll();
    }

    @GetMapping("/order/{orderId}")
    public List<AdditionalCost> getByOrderId(
            @PathVariable Long orderId) {

        return additionalCostDAO.getByOrderId(orderId);
    }
}