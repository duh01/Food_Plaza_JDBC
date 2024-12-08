package com.foodplaza.dao;

import java.util.List;

import com.foodplaza.pojo.Customer;

public interface CustomerDao {
	
	boolean addCustomer(Customer c);
	boolean updateCustomer(Customer c);
	boolean deleteCustomer(Customer c);
	
	Customer displayCustomerById(int customerId);
	List<Customer> displayCustomeByname(String CustomerName);	
	Customer displayCustomerByConatctNo(long contactNo);
	Customer displayCustomerByEmailId(String CustomerEmailId);
	List<Customer> displayCustomerByAddress(String CustomerAddr);
	
	List<Customer> displayAllCustomer();
	
	List<Customer>displayByCity(String City);
}
