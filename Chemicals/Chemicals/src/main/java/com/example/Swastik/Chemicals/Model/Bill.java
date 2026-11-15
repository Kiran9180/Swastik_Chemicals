package com.example.Swastik.Chemicals.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bill {

	private Long billId;
    private Long orderId;
    private BigDecimal productTotal;
    private BigDecimal additionalTotal;
    private BigDecimal grandTotal;
    private LocalDateTime billDate;
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getProductTotal() {
		return productTotal;
	}
	public void setProductTotal(BigDecimal productTotal) {
		this.productTotal = productTotal;
	}
	public BigDecimal getAdditionalTotal() {
		return additionalTotal;
	}
	public void setAdditionalTotal(BigDecimal additionalTotal) {
		this.additionalTotal = additionalTotal;
	}
	public BigDecimal getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
	public LocalDateTime getBillDate() {
		return billDate;
	}
	public void setBillDate(LocalDateTime billDate) {
		this.billDate = billDate;
	}
	public Bill(Long billId, Long orderId, BigDecimal productTotal, BigDecimal additionalTotal, BigDecimal grandTotal,
			LocalDateTime billDate) {
		super();
		this.billId = billId;
		this.orderId = orderId;
		this.productTotal = productTotal;
		this.additionalTotal = additionalTotal;
		this.grandTotal = grandTotal;
		this.billDate = billDate;
	}
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", orderId=" + orderId + ", productTotal=" + productTotal
				+ ", additionalTotal=" + additionalTotal + ", grandTotal=" + grandTotal + ", billDate=" + billDate
				+ ", getBillId()=" + getBillId() + ", getOrderId()=" + getOrderId() + ", getProductTotal()="
				+ getProductTotal() + ", getAdditionalTotal()=" + getAdditionalTotal() + ", getGrandTotal()="
				+ getGrandTotal() + ", getBillDate()=" + getBillDate() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
    
    
    
	
	
}
