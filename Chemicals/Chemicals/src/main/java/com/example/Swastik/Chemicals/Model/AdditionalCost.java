package com.example.Swastik.Chemicals.Model;

import java.math.BigDecimal;

public class AdditionalCost {

	
	private Long additionalCostId;
    private Long orderId;
    private String costName;
    private BigDecimal amount;
    private String remarks;
	public Long getAdditionalCostId() {
		return additionalCostId;
	}
	public void setAdditionalCostId(Long additionalCostId) {
		this.additionalCostId = additionalCostId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public AdditionalCost(Long additionalCostId, Long orderId, String costName, BigDecimal amount, String remarks) {
		super();
		this.additionalCostId = additionalCostId;
		this.orderId = orderId;
		this.costName = costName;
		this.amount = amount;
		this.remarks = remarks;
	}
	public AdditionalCost() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AdditionalCost [additionalCostId=" + additionalCostId + ", orderId=" + orderId + ", costName="
				+ costName + ", amount=" + amount + ", remarks=" + remarks + ", getAdditionalCostId()="
				+ getAdditionalCostId() + ", getOrderId()=" + getOrderId() + ", getCostName()=" + getCostName()
				+ ", getAmount()=" + getAmount() + ", getRemarks()=" + getRemarks() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
