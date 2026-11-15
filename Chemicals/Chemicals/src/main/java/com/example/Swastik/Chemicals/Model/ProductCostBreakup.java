package com.example.Swastik.Chemicals.Model;

import java.math.BigDecimal;

public class ProductCostBreakup {

	
	   private Long breakupId;
	    private Long productId;
	    private String costName;
	    private BigDecimal amount;
		public Long getBreakupId() {
			return breakupId;
		}
		public void setBreakupId(Long breakupId) {
			this.breakupId = breakupId;
		}
		public Long getProductId() {
			return productId;
		}
		public void setProductId(Long productId) {
			this.productId = productId;
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
		public ProductCostBreakup(Long breakupId, Long productId, String costName, BigDecimal amount) {
			super();
			this.breakupId = breakupId;
			this.productId = productId;
			this.costName = costName;
			this.amount = amount;
		}
		public ProductCostBreakup() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "ProductCostBreakup [breakupId=" + breakupId + ", productId=" + productId + ", costName=" + costName
					+ ", amount=" + amount + ", getBreakupId()=" + getBreakupId() + ", getProductId()=" + getProductId()
					+ ", getCostName()=" + getCostName() + ", getAmount()=" + getAmount() + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}
	
	
	    
	    
	    
	    
	    
	    
}
