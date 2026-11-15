package com.example.Swastik.Chemicals.Model;

import java.math.BigDecimal;

public class OrderCostBreakup {

	
	private Long orderCostBreakupId;
    private Long orderItemId;
    private String costName;
    private BigDecimal amount;
	public Long getOrderCostBreakupId() {
		return orderCostBreakupId;
	}
	public void setOrderCostBreakupId(Long orderCostBreakupId) {
		this.orderCostBreakupId = orderCostBreakupId;
	}
	public Long getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}
	public String getCostName() {
		return costName;
	}
	public void setCostName(String costName) {
		this.costName = costName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public OrderCostBreakup(Long orderCostBreakupId, Long orderItemId, String costName, BigDecimal amount) {
		super();
		this.orderCostBreakupId = orderCostBreakupId;
		this.orderItemId = orderItemId;
		this.costName = costName;
		this.amount = amount;
	}
	public OrderCostBreakup() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderCostBreakup [orderCostBreakupId=" + orderCostBreakupId + ", orderItemId=" + orderItemId
				+ ", costName=" + costName + ", amount=" + amount + ", getOrderCostBreakupId()="
				+ getOrderCostBreakupId() + ", getOrderItemId()=" + getOrderItemId() + ", getCostName()="
				+ getCostName() + ", getAmount()=" + getAmount() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
