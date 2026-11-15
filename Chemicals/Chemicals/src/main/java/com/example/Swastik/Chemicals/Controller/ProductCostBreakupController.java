package com.example.Swastik.Chemicals.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Swastik.Chemicals.DAO.ProductCostBreakupDAO;
import com.example.Swastik.Chemicals.Model.ProductCostBreakup;

@RestController
@RequestMapping("/productCostBreakup")
@CrossOrigin("*")
public class ProductCostBreakupController {

    @Autowired
    private ProductCostBreakupDAO productCostBreakupDAO;

    @PostMapping("/save")
    public String save(
            @RequestBody ProductCostBreakup breakup) {

        productCostBreakupDAO.save(breakup);

        return "Saved Successfully";
    }

    @PutMapping("/update")
    public String update(
            @RequestBody ProductCostBreakup breakup) {

        productCostBreakupDAO.update(breakup);

        return "Updated Successfully";
    }

    @DeleteMapping("/{breakupId}")
    public String delete(
            @PathVariable Long breakupId) {

        productCostBreakupDAO.delete(breakupId);

        return "Deleted Successfully";
    }

    @GetMapping("/{breakupId}")
    public ProductCostBreakup getById(
            @PathVariable Long breakupId) {

        return productCostBreakupDAO.getById(breakupId);
    }

    @GetMapping("/product/{productId}")
    public List<ProductCostBreakup> getByProductId(
            @PathVariable Long productId) {

        return productCostBreakupDAO.getByProductId(productId);
    }
}