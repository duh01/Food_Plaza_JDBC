package com.foodplaza.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodplaza.dao.CartDao;
import com.foodplaza.pojo.Cart;
import com.foodplaza.utility.DBConnection;


public class CartDaoImpl implements CartDao {
	
	Connection conn;
	PreparedStatement ps;
	Statement st;
	ResultSet rs;
	int row;
	Cart ct = new Cart();
	List<Cart> ctl =  new ArrayList<>();
	
	@Override
	public boolean addToCart(Cart ct) {
		String queryFood = "select foodId , foodPrice from Food where foodName = ?";
		String queryCart = "insert into cart(foodQty,foodId,foodName,CustomerEmailId,foodPrice) values(?,?,?,?,?)";
		
		try {
			conn=DBConnection.establishConnection();
			ps = conn.prepareStatement(queryFood);
			ps.setString(1, ct.getFoodName());
			rs = ps.executeQuery();
			while(rs.next()) {
				int foodId = rs.getInt(1);
				double foodPrice = rs.getDouble(2);
				
				ps = conn.prepareStatement(queryCart);
				ps.setInt(1,ct.getFoodQty());
				ps.setInt(2, foodId);
				ps.setString(3, ct.getFoodName());
				ps.setString(4, ct.getCustomerEmailId());
				ps.setDouble(5, foodPrice);
				
				row = ps.executeUpdate();
				if(row>0) {
					return true;
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCart(int cartId) {
		String query="Delete * from Cart where cartId=?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(query);
			ps.setInt(1, ct.getCartId());
			
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
	public boolean deleteCart(String customerEmailId) {
		String query = "delete from cart where CustomerEmailId =?";
		try {
			conn = DBConnection.establishConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, customerEmailId);
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
	public List<Cart> showCart() {
		String query = "select * from Cart";
		
		try {
			conn=DBConnection.establishConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			ctl.clear();
			while(rs.next()) {
				Cart ct1 = new Cart();
				ct1.setCartId(rs.getInt("cartId"));
				ct1.setCustomerEmailId(rs.getString("CustomerEmailId"));
				ct1.setFoodId(rs.getInt("foodId"));
				ct1.setFoodName(rs.getString("foodName"));
				ct1.setFoodPrice(rs.getDouble("foodPrice"));
				ct1.setFoodQty(rs.getInt("foodQty"));
				
				ctl.add(ct1);
			}
			return ctl;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Cart> showCart(String customerEmailId) {
		String query = "select * from cart where CustomerEmailId = ?";
		try {
			conn = DBConnection.establishConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, customerEmailId);
			rs = ps.executeQuery();
			while(rs.next()) {
				Cart ct2 = new Cart();
				ct2.setCartId(rs.getInt("cartId"));
				ct2.setFoodId(rs.getInt("foodId"));
				ct2.setFoodName(rs.getString("foodName"));
				ct2.setFoodPrice(rs.getDouble("foodPrice"));
				ct2.setFoodQty(rs.getInt("foodQty"));
				
				ctl.add(ct2);
			}
			return ctl;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
}
