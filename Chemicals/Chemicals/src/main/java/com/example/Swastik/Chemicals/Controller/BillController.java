package com.example.Swastik.Chemicals.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Swastik.Chemicals.DAO.BillDAO;
import com.example.Swastik.Chemicals.Model.Bill;

@RestController
@RequestMapping("/bill")
@CrossOrigin("*")
public class BillController {

    @Autowired
    private BillDAO billDAO;

    @PostMapping("/save")
    public String save(@RequestBody Bill bill) {

        billDAO.save(bill);

        return "Bill Saved Successfully";
    }

    @PutMapping("/update")
    public String update(@RequestBody Bill bill) {

        billDAO.update(bill);

        return "Bill Updated Successfully";
    }

    @DeleteMapping("/{billId}")
    public String delete(@PathVariable Long billId) {

        billDAO.delete(billId);

        return "Bill Deleted Successfully";
    }

    @GetMapping("/{billId}")
    public Bill getById(
            @PathVariable Long billId) {

        return billDAO.getById(billId);
    }

    @GetMapping("/order/{orderId}")
    public Bill getByOrderId(
            @PathVariable Long orderId) {

        return billDAO.getByOrderId(orderId);
    }

    @GetMapping("/all")
    public List<Bill> getAll() {

        return billDAO.getAll();
    }
}