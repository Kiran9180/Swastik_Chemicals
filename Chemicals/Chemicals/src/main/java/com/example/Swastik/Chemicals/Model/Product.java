package com.example.Swastik.Chemicals.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {

	   private Long productId;
	    private String productName;
	    private BigDecimal finalCost;
	    private Boolean active;
	    private LocalDateTime createdAt;
	    
	    
	    
		public Product(Long productId, String productName, BigDecimal finalCost, Boolean active,
				LocalDateTime createdAt) {
			super();
			this.productId = productId;
			this.productName = productName;
			this.finalCost = finalCost;
			this.active = active;
			this.createdAt = createdAt;
		}
		
		
		public Product() {
			super();
			// TODO Auto-generated constructor stub
		}



		public Long getProductId() {
			return productId;
		}
		public void setProductId(Long productId) {
			this.productId = productId;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public BigDecimal getFinalCost() {
			return finalCost;
		}
		public void setFinalCost(BigDecimal finalCost) {
			this.finalCost = finalCost;
		}
		public Boolean getActive() {
			return active;
		}
		public void setActive(Boolean active) {
			this.active = active;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}


		@Override
		public String toString() {
			return "Product [productId=" + productId + ", productName=" + productName + ", finalCost=" + finalCost
					+ ", active=" + active + ", createdAt=" + createdAt + ", getProductId()=" + getProductId()
					+ ", getProductName()=" + getProductName() + ", getFinalCost()=" + getFinalCost() + ", getActive()="
					+ getActive() + ", getCreatedAt()=" + getCreatedAt() + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}

	    
	    
	    
	    
	    
	    
	    
	    
	    
}
