package com.example.Swastik.Chemicals.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.internet.MimeMessage;

import com.example.Swastik.Chemicals.DAO.BillDAO;
import com.example.Swastik.Chemicals.DAO.CustomerOrderDAO;
import com.example.Swastik.Chemicals.Model.Bill;
import com.example.Swastik.Chemicals.Model.CustomerOrder;

@RestController
@RequestMapping("/email")
@CrossOrigin("*")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BillDAO billDAO;

    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    @PostMapping("/send/{billId}")
    public String sendEmail(
            @PathVariable Long billId,
            @RequestParam("file") MultipartFile file) {

        try {

            Bill bill =
                    billDAO.getById(billId);

            if (bill == null) {

                return "Bill Not Found";

            }

            CustomerOrder order =
                    customerOrderDAO.getById(
                            bill.getOrderId());

            if (order == null) {

                return "Order Not Found";

            }

            MimeMessage message =
                    mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            message,
                            true);

            helper.setFrom(
                    "adminswastikchemicals@gmail.com");

            helper.setTo(
                    order.getEmail());

            helper.setSubject(
                    "Invoice #" +
                    bill.getBillId());

            helper.setText(
                    "Dear " +
                    order.getCustomerName()
                    + ",\n\n" +
                    "Please find attached invoice.\n\n" +
                    "Thank You,\n" +
                    "Swastik Chemicals"
            );

            helper.addAttachment(
                    file.getOriginalFilename(),
                    new ByteArrayResource(
                            file.getBytes()
                    )
            );

            mailSender.send(message);

            return "Email Sent Successfully";

        } catch (Exception e) {

            e.printStackTrace();

            return e.getMessage();

        }
    }
}