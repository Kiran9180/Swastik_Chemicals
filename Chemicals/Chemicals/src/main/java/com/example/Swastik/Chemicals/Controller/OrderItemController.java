package com.example.Swastik.Chemicals.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Swastik.Chemicals.DAO.OrderItemDAO;
import com.example.Swastik.Chemicals.Model.OrderItem;

@RestController
@RequestMapping("/orderItem")
@CrossOrigin("*")
public class OrderItemController {

    @Autowired
    private OrderItemDAO orderItemDAO;

    @PostMapping("/save")
    public String save(
            @RequestBody OrderItem orderItem) {

        orderItemDAO.save(orderItem);

        return "Saved Successfully";
    }

    @PutMapping("/update")
    public String update(
            @RequestBody OrderItem orderItem) {

        orderItemDAO.update(orderItem);

        return "Updated Successfully";
    }

    @DeleteMapping("/{orderItemId}")
    public String delete(
            @PathVariable Long orderItemId) {

        orderItemDAO.delete(orderItemId);

        return "Deleted Successfully";
    }

    @GetMapping("/{orderItemId}")
    public OrderItem getById(
            @PathVariable Long orderItemId) {

        return orderItemDAO.getById(orderItemId);
    }

    @GetMapping("/all")
    public List<OrderItem> getAll() {

        return orderItemDAO.getAll();
    }

    @GetMapping("/order/{orderId}")
    public List<OrderItem> getByOrderId(
            @PathVariable Long orderId) {

        return orderItemDAO.getByOrderId(orderId);
    }
}