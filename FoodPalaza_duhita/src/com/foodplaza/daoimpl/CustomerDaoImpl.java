package com.foodplaza.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodplaza.dao.CustomerDao;
import com.foodplaza.pojo.Customer;
import com.foodplaza.utility.DBConnection;

public class CustomerDaoImpl implements CustomerDao{
	
	Connection conn;
	PreparedStatement ps;
	Statement st;
	ResultSet rs;
	int row;
	Customer c = new Customer();
	List<Customer> cl =  new ArrayList<>();
	
	
	@Override
	public boolean addCustomer(Customer c) {
		String query="insert into Customer(CustomerName,CustomerAddr,CustomerEmailId,CustomerPassword,contactNo)values(?,?,?,?,?);";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(query);
			ps.setString(1, c.getCustomerName());
			ps.setString(2, c.getCustomerAddr());
			ps.setString(3, c.getCustomerEmailId());
			ps.setString(4, c.getCustomerPassword());
			ps.setLong(5, c.getContactNo());
			
			row=ps.executeUpdate();
			if(row>0)
			{
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCustomer(Customer c) {
		String query="Update Customer set CustomerName=?, CustomerAddr=?,CustomerEmailId=?,CustomerPassword=?,contactNo=? where customerId=?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(query);
			ps.setString(1, c.getCustomerName());
			ps.setString(2, c.getCustomerAddr());
			ps.setString(3, c.getCustomerEmailId());
			ps.setString(4, c.getCustomerPassword());
			ps.setLong(5, c.getContactNo());
			ps.setInt(6, c.getCustomerId());
			
			row=ps.executeUpdate();
			if(row>0) {
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(Customer c) {
		String query = "Delete from Customer where CustomerId=?";
		
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(query);
			ps.setInt(1, c.getCustomerId());
			
			row=ps.executeUpdate();
			if(row>0) {
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Customer displayCustomerById(int customerId) {
		String query="Select * from Customer where customerId=?";
		
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(query);
			ps.setInt(1, customerId);
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				c.setCustomerId(rs.getInt("customerId"));
				c.setCustomerName(rs.getString("CustomerName"));
				c.setCustomerAddr(rs.getString("CustomerAddr"));
				c.setCustomerEmailId(rs.getString("CustomerEmailId"));
				c.setCustomerPassword(rs.getString("CustomerPassword"));
				c.setContactNo(rs.getLong("contactNo"));
				
				System.out.println("Customer Name: " + c.getCustomerName());
	            System.out.println("Customer ID: " + c.getCustomerId());
	            System.out.println("Customer Address: " + c.getCustomerAddr());
	            System.out.println("Customer E-Mail Id: " + c.getCustomerEmailId());
	            System.out.println("Customer Password" + c.getCustomerPassword());
	            System.out.println("Customer Contact Number: "+ c.getContactNo());
			}
				return c;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Customer> displayCustomeByname(String CustomerName) {
		String query="Select * from Customer where CustomerName = ?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(query);
			ps.setString(1, CustomerName);
			
			rs=ps.executeQuery();
			cl.clear();
			while(rs.next()) {
				Customer c = new Customer();
				c.setCustomerName(rs.getString("CustomerName"));
				c.setCustomerAddr(rs.getString("CustomerAddr"));
				c.setCustomerEmailId(rs.getString("CustomerEmailId"));
				c.setCustomerPassword(rs.getString("CustomerPassword"));
				c.setContactNo(rs.getLong("contactNo"));
				cl.add(c);
			}
			return cl;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer displayCustomerByConatctNo(long contactNo) {
		String query ="Select * from Customer where contactNo = ?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(query);
			ps.setLong(1, contactNo);
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				System.out.println("CUSTOMER IS NAME:  "+rs.getString("CustomerName"));
				System.out.println("CUSTOMER IS ADDRESS: "+rs.getString("CustomerAddr"));
				System.out.println("CUSTOMER IS EMAIL-ID: "+rs.getString("CustomerEmailId"));
				System.out.println("CUSTOMER IS PASSWORD:  "+rs.getString("CustomerPassword"));
			}
		return c;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer displayCustomerByEmailId(String CustomerEmailId) {
		try {
			conn=DBConnection.establishConnection();
			String displayQuery = "select * from Customer where CustomerEmailId=?";
			ps=conn.prepareStatement(displayQuery);
			
			ps.setString(1, CustomerEmailId);
			rs=ps.executeQuery();
			while(rs.next()) {
				
				c.setCustomerName(rs.getString("CustomerName"));
				c.setCustomerAddr(rs.getString("CustomerAddr"));
				c.setCustomerEmailId(rs.getString("CustomerEmailId"));
				c.setContactNo(rs.getLong("contactNo"));
			}
			return c;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Customer> displayCustomerByAddress(String CustomerAddr) {
		try {
			conn=DBConnection.establishConnection();
			String query = "select * from Customer where CustomerAddr= ? ";
			ps=conn.prepareStatement(query);
			
			ps.setString(1, CustomerAddr);
			rs=ps.executeQuery();
			while(rs.next()) {
				
				c.setCustomerName(rs.getString("customerName"));
				c.setCustomerAddr(rs.getString("customerAddress"));
				c.setCustomerEmailId(rs.getString("customerEmail"));
				c.setContactNo(rs.getLong("ContactNo"));
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cl;
	}

	@Override
	public List<Customer> displayAllCustomer() {
		String query = "select * from Customer";
		try {
			conn=DBConnection.establishConnection();
			st=conn.createStatement();
			rs=st.executeQuery(query);
			cl.clear();
			while(rs.next()) {
				Customer c=new Customer();
				c.setCustomerName(rs.getString("CustomerName"));
				c.setCustomerAddr(rs.getString("CustomerAddr"));
				c.setCustomerEmailId(rs.getString("CustomerEmailId"));
				c.setCustomerPassword(rs.getString("CustomerPassword"));
				c.setContactNo(rs.getLong("contactNo"));
				cl.add(c);
			}
			return cl;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Customer> displayByCity(String City) {
		
		String query = "SELECT * FROM Customer WHERE CustomerAddr Like ?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(query);
			ps.setString(1, City);
			ResultSet rs = ps.executeQuery();
			
			cl.clear();
			 while (rs.next()) {
	                Customer ccc = new Customer();
	                
	                ccc.setCustomerName(rs.getString("CustomerName"));
	                ccc.setCustomerAddr(rs.getString("CustomerAddr"));
	                ccc.setCustomerEmailId(rs.getString("CustomerEmailId"));
	                ccc.setCustomerPassword(rs.getString("CustomerPassword"));
	                ccc.setContactNo(rs.getLong("contactNo"));
	                
	                cl.add(ccc);
	            }
			 return cl;
			 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
