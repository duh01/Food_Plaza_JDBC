package com.foodplaza.pojo;

public class Customer {
	
	private int customerId;
	private String CustomerName,CustomerAddr,CustomerEmailId,CustomerPassword;
	private long contactNo;
	
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getCustomerAddr() {
		return CustomerAddr;
	}
	public void setCustomerAddr(String customerAddr) {
		CustomerAddr = customerAddr;
	}
	public String getCustomerEmailId() {
		return CustomerEmailId;
	}
	public void setCustomerEmailId(String customerEmailId) {
		CustomerEmailId = customerEmailId;
	}
	public String getCustomerPassword() {
		return CustomerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		CustomerPassword = customerPassword;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	
}
