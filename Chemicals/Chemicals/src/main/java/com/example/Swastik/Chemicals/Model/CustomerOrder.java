package com.example.Swastik.Chemicals.Model;

import java.time.LocalDateTime;

public class CustomerOrder {
	
	
	 private Long orderId;
	    private String taluka;
	    private String village;
	    private String district;
	    private String fullAddress;
	    private String status;
	    private LocalDateTime createdAt;
	    private Long userId;

	    private String customerName;

	    private String mobile;

	    private String email;

	    private String shopName;

	    private String pincode;

	    private String remarks;

		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

		public String getTaluka() {
			return taluka;
		}

		public void setTaluka(String taluka) {
			this.taluka = taluka;
		}

		public String getVillage() {
			return village;
		}

		public void setVillage(String village) {
			this.village = village;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getFullAddress() {
			return fullAddress;
		}

		public void setFullAddress(String fullAddress) {
			this.fullAddress = fullAddress;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getShopName() {
			return shopName;
		}

		public void setShopName(String shopName) {
			this.shopName = shopName;
		}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

		public CustomerOrder(Long orderId, String taluka, String village, String district, String fullAddress,
				String status, LocalDateTime createdAt, Long userId, String customerName, String mobile, String email,
				String shopName, String pincode, String remarks) {
			super();
			this.orderId = orderId;
			this.taluka = taluka;
			this.village = village;
			this.district = district;
			this.fullAddress = fullAddress;
			this.status = status;
			this.createdAt = createdAt;
			this.userId = userId;
			this.customerName = customerName;
			this.mobile = mobile;
			this.email = email;
			this.shopName = shopName;
			this.pincode = pincode;
			this.remarks = remarks;
		}

		public CustomerOrder() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "CustomerOrder [orderId=" + orderId + ", taluka=" + taluka + ", village=" + village + ", district="
					+ district + ", fullAddress=" + fullAddress + ", status=" + status + ", createdAt=" + createdAt
					+ ", userId=" + userId + ", customerName=" + customerName + ", mobile=" + mobile + ", email="
					+ email + ", shopName=" + shopName + ", pincode=" + pincode + ", remarks=" + remarks
					+ ", getOrderId()=" + getOrderId() + ", getTaluka()=" + getTaluka() + ", getVillage()="
					+ getVillage() + ", getDistrict()=" + getDistrict() + ", getFullAddress()=" + getFullAddress()
					+ ", getStatus()=" + getStatus() + ", getCreatedAt()=" + getCreatedAt() + ", getUserId()="
					+ getUserId() + ", getCustomerName()=" + getCustomerName() + ", getMobile()=" + getMobile()
					+ ", getEmail()=" + getEmail() + ", getShopName()=" + getShopName() + ", getPincode()="
					+ getPincode() + ", getRemarks()=" + getRemarks() + ", getClass()=" + getClass() + ", hashCode()="
					+ hashCode() + ", toString()=" + super.toString() + "]";
		}

	    
	  

}
