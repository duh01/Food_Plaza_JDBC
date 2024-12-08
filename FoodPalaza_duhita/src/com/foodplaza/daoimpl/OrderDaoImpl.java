package com.foodplaza.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodplaza.dao.OrderDao;
import com.foodplaza.pojo.Orders;
import com.foodplaza.utility.DBConnection;

public class OrderDaoImpl implements OrderDao{
	
	Connection conn;
	PreparedStatement ps;
	Statement st;
	ResultSet rs;
	int row;
	Orders o = new Orders();
	List<Orders> ol =  new ArrayList<>();
	double totalBill;
	
	@Override
	public boolean placeOrder(String CustomerEmailId) {
		
		String foodNameFromCartQuery = "SELECT foodName FROM Cart WHERE CustomerEmailId = ?";
	    String totalBillQuery = "SELECT SUM(foodPrice * foodQty) AS totalBill FROM Cart WHERE CustomerEmailId = ?";
	    String placeOrderQuery = "INSERT INTO Orders(foodName, CustomerEmailId, orderStatus, orderDate, totalBill) VALUES (?, ?, ?, ?, ?)";
	    
	    try {
	    	
			conn= DBConnection.establishConnection();
			 ps = conn.prepareStatement(foodNameFromCartQuery);
		     ps.setString(1, CustomerEmailId);
		     rs = ps.executeQuery();
		     StringBuilder foodNamesBuilder = new StringBuilder();
		        while (rs.next()) {
		            if (foodNamesBuilder.length() > 0) {
		                foodNamesBuilder.append(", ");
		            }
		            foodNamesBuilder.append(rs.getString("foodName"));
		        }
		        
		        String foodNames = foodNamesBuilder.toString();
		       
		        // Calculate the total bill
		        ps = conn.prepareStatement(totalBillQuery);
		        ps.setString(1, CustomerEmailId);
		        rs = ps.executeQuery();
		        if (rs.next()) {
		           totalBill = rs.getDouble("totalBill");
		        }
		     // Place the order with all food names and total bill
		        ps = conn.prepareStatement(placeOrderQuery);
		        ps.setString(1, foodNames);
		        ps.setString(2, CustomerEmailId);
		        ps.setString(3, "pending");
		        ps.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
		        ps.setDouble(5, totalBill);

		        row = ps.executeUpdate();
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
	public List<Orders> showOrders() {
		String query = "Select * from Orders";
		try {
			conn=DBConnection.establishConnection();
			st=conn.createStatement();
			rs=st.executeQuery(query);
			ol.clear();
			while(rs.next())
			{
				Orders o = new Orders();
				o.setFoodName(rs.getString("foodName"));
				o.setCustomerEmailId(rs.getString("CustomerEmailId"));
				o.setOrderStatus(rs.getString("orderStatus"));
				o.setTotalBill(rs.getDouble("totalBill"));
				o.setOrderDate(rs.getString("orderDate"));
				ol.add(o);
			}
			return ol;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Orders> showOrders(String CustomerEmailId) {
		String query="Select * from Orders where CustomerEmailId = ?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(query);
			ps.setString(1, CustomerEmailId);
			rs=ps.executeQuery();
			ol.clear();
			while(rs.next()) {
				Orders o = new Orders();
				o.setFoodName(rs.getString("foodName"));
				o.setCustomerEmailId(rs.getString("CustomerEmailId"));
				o.setOrderStatus(rs.getString("orderStatus"));
				o.setTotalBill(rs.getDouble("totalBill"));
				o.setOrderDate(rs.getString("orderDate"));
				ol.add(o);
			}
			return ol;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean cancelOrder(String CustomerEmailId) {
		String query="Delete * from Orders where CustomerEmailId=?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(query);
			ps.setString(1, CustomerEmailId);
			row = ps.executeUpdate();
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
	public Orders checkOrderStatus(String CustomerEmailId) {
		String query =" select orderStatus FROM Orders where CustomerEmailId = ?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(query);
			ps.setString(1, CustomerEmailId);
			rs=ps.executeQuery();
			if(rs.next())
			{
				System.out.println("YOUR ORDER STATUS IS: "+rs.getString("orderStatus"));
			}
			return o;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Orders updateOrderStatus(String CustomerEmailId, String OrderStatus) {
		String query ="Update Orders set orderStatus = ? where CustomerEmailId = ?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(query);
			ps.setString(1, OrderStatus);
			ps.setString(2, CustomerEmailId);
			row=ps.executeUpdate();
			
			return o;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
