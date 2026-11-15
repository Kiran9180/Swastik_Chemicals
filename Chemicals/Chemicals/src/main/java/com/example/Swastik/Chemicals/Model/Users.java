package com.example.Swastik.Chemicals.Model;

public class Users {
	
	
	private Long id;

    private String name;

    private String email;

    private String mobile;

    private String shopName;

    private String address;

    private String taluka;

    private String district;

    private String pincode;

    private String access;

    private String passwordHash;

    private Boolean isActive;
    
    private String gstNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTaluka() {
		return taluka;
	}

	public void setTaluka(String taluka) {
		this.taluka = taluka;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public Users(Long id, String name, String email, String mobile, String shopName, String address, String taluka,
			String district, String pincode, String access, String passwordHash, Boolean isActive, String gstNo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.shopName = shopName;
		this.address = address;
		this.taluka = taluka;
		this.district = district;
		this.pincode = pincode;
		this.access = access;
		this.passwordHash = passwordHash;
		this.isActive = isActive;
		this.gstNo = gstNo;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", shopName="
				+ shopName + ", address=" + address + ", taluka=" + taluka + ", district=" + district + ", pincode="
				+ pincode + ", access=" + access + ", passwordHash=" + passwordHash + ", isActive=" + isActive
				+ ", gstNo=" + gstNo + ", getId()=" + getId() + ", getName()=" + getName() + ", getEmail()="
				+ getEmail() + ", getMobile()=" + getMobile() + ", getShopName()=" + getShopName() + ", getAddress()="
				+ getAddress() + ", getTaluka()=" + getTaluka() + ", getDistrict()=" + getDistrict() + ", getPincode()="
				+ getPincode() + ", getAccess()=" + getAccess() + ", getPasswordHash()=" + getPasswordHash()
				+ ", getIsActive()=" + getIsActive() + ", getGstNo()=" + getGstNo() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
    
    

	
    
    

}
