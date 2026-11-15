package com.example.Swastik.Chemicals.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Swastik.Chemicals.DAO.CustomerOrderDAO;
import com.example.Swastik.Chemicals.Model.CustomerOrder;
import com.example.Swastik.Chemicals.DAO.OrderItemDAO;
import com.example.Swastik.Chemicals.DAO.OrderCostBreakupDAO;
import com.example.Swastik.Chemicals.Model.OrderItem;

@RestController
@RequestMapping("/customerOrder")
@CrossOrigin("*")
public class CustomerOrderController {
	
	@Autowired
	private OrderItemDAO orderItemDAO;

	@Autowired
	private OrderCostBreakupDAO orderCostBreakupDAO;

    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    @PostMapping("/save")
    public Long save(
            @RequestBody CustomerOrder customerOrder) {

        return customerOrderDAO.save(customerOrder);

    }

    @PutMapping("/update")
    public String update(
            @RequestBody CustomerOrder customerOrder) {

        try {

            customerOrderDAO.update(customerOrder);

            return "Customer Order Updated Successfully";

        } catch (Exception e) {

            e.printStackTrace();

            return "Error While Updating Customer Order";
        }
    }

    @DeleteMapping("/{orderId}")
    public String delete(
            @PathVariable Long orderId) {

        try {

            customerOrderDAO.delete(orderId);

            return "Customer Order Deleted Successfully";

        } catch (Exception e) {

            e.printStackTrace();

            return "Error While Deleting Customer Order";
        }
    }

    @GetMapping("/{orderId}")
    public CustomerOrder getById(
            @PathVariable Long orderId) {

        return customerOrderDAO.getById(orderId);
    }

    @GetMapping("/all")
    public List<CustomerOrder> getAll() {

        return customerOrderDAO.getAll();
    }

    @GetMapping("/pending")
    public List<CustomerOrder> getPendingOrders() {

        return customerOrderDAO.getPendingOrders();
    }

    @GetMapping("/approved")
    public List<CustomerOrder> getApprovedOrders() {

        return customerOrderDAO.getApprovedOrders();
    }

    @PutMapping("/approve/{orderId}")
    public String approveOrder(
            @PathVariable Long orderId) {

        try {

            List<OrderItem> orderItems =
                    orderItemDAO.getByOrderId(orderId);

            for(OrderItem item : orderItems) {

                orderCostBreakupDAO
                    .copyProductCostBreakup(
                            item.getProductId(),
                            item.getOrderItemId());
            }

            customerOrderDAO.updateStatus(
                    orderId,
                    "APPROVED");

            return "Order Approved Successfully";

        }
        catch(Exception e) {

            e.printStackTrace();

            return "Error While Approving Order";
        }
    }

    @PutMapping("/reject/{orderId}")
    public String rejectOrder(
            @PathVariable Long orderId) {

        try {

            customerOrderDAO.updateStatus(
                    orderId,
                    "REJECTED");

            return "Order Rejected Successfully";

        } catch (Exception e) {

            e.printStackTrace();

            return "Error While Rejecting Order";
        }
    }

    @PutMapping("/complete/{orderId}")
    public String completeOrder(
            @PathVariable Long orderId) {

        try {

            customerOrderDAO.updateStatus(
                    orderId,
                    "COMPLETED");

            return "Order Completed Successfully";

        } catch (Exception e) {

            e.printStackTrace();

            return "Error While Completing Order";
        }
    }
}