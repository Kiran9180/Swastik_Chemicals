package com.example.Swastik.Chemicals.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Swastik.Chemicals.DAO.OrderCostBreakupDAO;
import com.example.Swastik.Chemicals.Model.OrderCostBreakup;

@RestController
@RequestMapping("/orderCostBreakup")
@CrossOrigin("*")
public class OrderCostBreakupController {

    @Autowired
    private OrderCostBreakupDAO orderCostBreakupDAO;

    @PostMapping("/save")
    public String save(
            @RequestBody OrderCostBreakup orderCostBreakup) {

        orderCostBreakupDAO.save(orderCostBreakup);

        return "Saved Successfully";
    }

    @PutMapping("/update")
    public String update(
            @RequestBody OrderCostBreakup orderCostBreakup) {

        orderCostBreakupDAO.update(orderCostBreakup);

        return "Updated Successfully";
    }

    @DeleteMapping("/{orderCostBreakupId}")
    public String delete(
            @PathVariable Long orderCostBreakupId) {

        orderCostBreakupDAO.delete(orderCostBreakupId);

        return "Deleted Successfully";
    }

    @GetMapping("/{orderCostBreakupId}")
    public OrderCostBreakup getById(
            @PathVariable Long orderCostBreakupId) {

        return orderCostBreakupDAO.getById(
                orderCostBreakupId);
    }

    @GetMapping("/all")
    public List<OrderCostBreakup> getAll() {

        return orderCostBreakupDAO.getAll();
    }

    @GetMapping("/orderItem/{orderItemId}")
    public List<OrderCostBreakup> getByOrderItemId(
            @PathVariable Long orderItemId) {

        return orderCostBreakupDAO.getByOrderItemId(
                orderItemId);
    }

    /*
     * Copy Product Cost Breakup
     * to Order Cost Breakup
     */
    @PostMapping("/copy")
    public String copyProductCostBreakup(

            @RequestParam Long productId,

            @RequestParam Long orderItemId) {

        orderCostBreakupDAO.copyProductCostBreakup(
                productId,
                orderItemId);

        return "Cost Breakup Copied";
    }

    /*
     * Single Product Total Cost
     */
    @GetMapping("/itemTotal/{orderItemId}")
    public Double getOrderItemTotalCost(
            @PathVariable Long orderItemId) {

        return orderCostBreakupDAO.getOrderItemTotalCost(
                orderItemId);
    }

    /*
     * Entire Order Total Cost
     */
    @GetMapping("/orderTotal/{orderId}")
    public Double getOrderTotalCost(
            @PathVariable Long orderId) {

        return orderCostBreakupDAO.getOrderTotalCost(
                orderId);
    }
    
    @GetMapping("/order/{orderId}")
    public List<OrderCostBreakup>
    getByOrderId(
            @PathVariable Long orderId) {

        return orderCostBreakupDAO
                .getByOrderId(orderId);
    }
}