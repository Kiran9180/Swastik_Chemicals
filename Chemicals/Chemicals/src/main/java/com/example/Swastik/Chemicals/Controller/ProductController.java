package com.example.Swastik.Chemicals.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Swastik.Chemicals.DAO.ProductDAO;
import com.example.Swastik.Chemicals.Model.Product;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @PostMapping("/save")
    public String save(@RequestBody Product product) {

        try {

            productDAO.save(product);

            return "Product Saved Successfully";

        } catch(Exception e) {

            e.printStackTrace();

            return "Error While Saving Product";
        }
    }

    @PutMapping("/update")
    public String update(@RequestBody Product product) {

        try {

            productDAO.update(product);

            return "Product Updated Successfully";

        } catch(Exception e) {

            e.printStackTrace();

            return "Error While Updating Product";
        }
    }

    @DeleteMapping("/{productId}")
    public String delete(
            @PathVariable Long productId) {

        try {

            productDAO.delete(productId);

            return "Product Deleted Successfully";

        } catch(Exception e) {

            e.printStackTrace();

            return "Error While Deleting Product";
        }
    }

    @GetMapping("/{productId}")
    public Product getById(
            @PathVariable Long productId) {

        return productDAO.getById(productId);
    }

    @GetMapping("/all")
    public List<Product> getAll() {

        return productDAO.getAll();
    }

    @GetMapping("/active")
    public List<Product> getActiveProducts() {

        return productDAO.getActiveProducts();
    }
}