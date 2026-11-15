package com.example.Swastik.Chemicals.Model;

import java.math.BigDecimal;

public class OrderItem {

	
	private Long orderItemId;
    private Long orderId;
    private Long productId;
    private BigDecimal quantity;
    
    
    
    
    
    
    
	public OrderItem(Long orderItemId, Long orderId, Long productId, BigDecimal quantity) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		
		
		
		
		
		
		
		
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", orderId=" + orderId + ", productId=" + productId
				+ ", quantity=" + quantity + ", getOrderItemId()=" + getOrderItemId() + ", getOrderId()=" + getOrderId()
				+ ", getProductId()=" + getProductId() + ", getQuantity()=" + getQuantity() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
}
