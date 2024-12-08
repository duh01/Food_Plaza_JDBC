package com.foodplaza.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodplaza.dao.FoodDao;
import com.foodplaza.pojo.Food;
import com.foodplaza.utility.DBConnection;

public class FoodDaoImpl implements FoodDao{
	Connection conn;
	PreparedStatement ps;
	Statement st;
	ResultSet rs;
	int row;
	Food f1 = new Food();
	List<Food> fl =  new ArrayList<>();

	@Override
	public boolean addFood(Food f) {
		
		String insertQuery = "insert into Food(foodName,foodPrice,foodCategory,foodType)values(?,?,?,?)";

		try {
			conn=DBConnection.establishConnection(); //con kuthe return krtoy te samjayla ith ek variable lagel store karyla
			 ps = conn.prepareStatement(insertQuery);
			 ps.setString(1, f.getFoodName());
			 ps.setDouble(2, f.getFoodPrice());
			 ps.setString(3, f.getFoodCategory());
			 ps.setString(4, f.getFoodType());
			 
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
	public boolean updateFood(Food f) {
		String insertQuery = "UPDATE Food SET foodName = ?, foodPrice = ?, foodCategory = ?,foodType = ? WHERE foodId=?;";

		try {
			//con kuthe return krtoy te samjayla ith ek variable lagel store karyla
			conn=DBConnection.establishConnection(); 
			 ps = conn.prepareStatement(insertQuery);
			 
			 ps.setString(1, f.getFoodName());
			 ps.setDouble(2, f.getFoodPrice());
			 ps.setString(3, f.getFoodCategory());
			 ps.setString(4, f.getFoodType());
			 ps.setInt(5,f.getFoodId());
			 
			//insert krtana User i/p ie SC CLASS var SET----> GET from POJO ----> DB madhe ps.SET krata hoto

			 row = ps.executeUpdate();
			 
			 if(row>0)
			 {
				 return true;
			 }


		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				return false;
	}

	@Override
	public boolean deleteFood(int foodId) {
		String insertQuery = "Delete from Food where foodId=?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(insertQuery);
			ps.setInt(1, foodId);
			
			row = ps.executeUpdate();
		 
		 	if(row>0)
		 		{
		 			return true;
		 		}
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		 
		return false;
	}

	@Override
	public Food display(int foodId) {
		String selectQuery = "SELECT * FROM Food WHERE foodId = ?";
	    try {
	        conn = DBConnection.establishConnection();
	        ps = conn.prepareStatement(selectQuery);
	        ps.setInt(1, foodId);
	        
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            Food f1 = new Food();
	            f1.setFoodId(rs.getInt("foodId"));//food class madhe jo set karaycha ID aahe tho DB madhun aalay ;
	            f1.setFoodName(rs.getString("foodName"));
	            f1.setFoodPrice(rs.getDouble("foodPrice"));
	            f1.setFoodCategory(rs.getString("foodCategory"));
	            f1.setFoodType(rs.getString("foodType"));
	            
	            // Print details to the console
	            System.out.println("Food ID: " + f1.getFoodId());
	            System.out.println("Food Name: " + f1.getFoodName());
	            System.out.println("Food Price: " + f1.getFoodPrice());
	            System.out.println("Food Category: " + f1.getFoodCategory());
	            System.out.println("Food Type: " + f1.getFoodType());
	            
	            return f1;  // Return the food object if needed elsewhere
	        } else {
	            System.out.println("No food found with the provided ID.");
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	    return null;  
	}

	@Override
	public Food display(String foodName) {
		String insertQuery="Select * from Food where foodName = ?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(insertQuery);
			
			ps.setString(1, foodName);
			rs=ps.executeQuery();
			if(rs.next())
			{
				Food f6 = new Food();
				//food class madhe jo set karaycha ID aahe tho DB madhun aalay ;
	            f6.setFoodName(rs.getString("foodName"));
	            f6.setFoodPrice(rs.getDouble("foodPrice"));
	            f6.setFoodCategory(rs.getString("foodCategory"));
	            f6.setFoodType(rs.getString("foodType"));
	            
				System.out.println("Food Name: "+f6.getFoodName());
				System.out.println("Food Price: "+f6.getFoodPrice());
				System.out.println("Food Category: "+f6.getFoodCategory());
				System.out.println("Food type: "+f6.getFoodType());
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Food> display(double foodprice) {
		String insertQuery="Select * from Food where foodPrice=?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(insertQuery);
			ps.setDouble(1, foodprice);
			
			rs=ps.executeQuery();
			//jasa varti Food f hota tr food cha f. ne tya pojo method madhun call karayla pn jasa ki ithe direct aapan variable vapartoy tr tya sathi aapan ithe RS use kelay;
			fl.clear();
			while(rs.next())
			{
				Food f = new Food();//yela aat ghetla ahe ki tho baher ghetla tr current value DB madhli fetch krto
				
				f.setFoodName(rs.getString("foodName"));//ithe DB madhun value gheychi hoti mhnun RS vaparla pn ithe nakki kay hotay ki 
				f.setFoodPrice(rs.getDouble("foodPrice"));
				f.setFoodCategory(rs.getString("foodCategory"));
				f.setFoodType(rs.getString("foodType"));
				
				fl.add(f);
				//aaplya kade 2 type e call karayche te mhnje java vrun pn call kru shakto value get karayla aani DB madhun pn kru shakto JAVA cha jhala tr POJO class ani DB ch jhala tr RS interface;
				
				//Select krtana rs.GET from DB ----> setPOJOmethod() variable by f.set here they call the method from POJO ----> SYSO all get method using FOR-EACH Loop in main(); 
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fl; //ithe return null nhi tr fl krtoy aapan.
	}

	@Override
	public List<Food> displayAllFood() {
		fl.clear();
		String displayAllQuery = "select * from Food";
		
		 try {
			conn = DBConnection.establishConnection();
			
			st = conn.createStatement();
			rs = st.executeQuery(displayAllQuery);
			
			while(rs.next())
			{
				Food f = new Food();//yela aat ghetla ahe ki tho baher ghetla tr current value DB madhli fetch krto
				
				f.setFoodName(rs.getString("foodName"));//ithe DB madhun value gheychi hoti mhnun RS vaparla pn ithe nakki kay hotay ki 
				f.setFoodPrice(rs.getDouble("foodPrice"));
				f.setFoodCategory(rs.getString("foodCategory"));
				f.setFoodType(rs.getString("foodType"));
				
				fl.add(f);
				
			}			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  fl; //ha fl nakki kuthe return hotoy??
	}

	@Override
	public List<Food> displayFoodByRange(double intialRange, double FinalRange) {
		String query= "Select * from Food where foodPrice between ? and ?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(query);
			ps.setDouble(1, intialRange);
			ps.setDouble(2, FinalRange);
			rs=ps.executeQuery();
			
			fl.clear();
			while(rs.next())
			{
				Food f = new Food();
				f.setFoodName(rs.getString("foodName"));
				f.setFoodPrice(rs.getDouble("foodPrice"));
				f.setFoodCategory(rs.getString("foodCategory"));
				f.setFoodType(rs.getString("foodType"));
				fl.add(f);
			}
			
			
			return fl;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Food> displayFoodByCategory(String foodCategory){
		String insertQuery="Select * from Food where foodCategory=?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(insertQuery);
			ps.setString(1, foodCategory);
			rs=ps.executeQuery();
			
			fl.clear();
			while(rs.next())
			{
				Food f = new Food();
				f.setFoodName(rs.getString("foodName"));
				f.setFoodPrice(rs.getDouble("foodPrice"));
				f.setFoodCategory(rs.getString("foodCategory"));
				f.setFoodType(rs.getString("foodType"));
				fl.add(f);
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fl;
	}

	@Override
	public List<Food> displayFoodByType(String foodType) {
		String insertQuery="Select * from Food where foodType=?";
		try {
			conn=DBConnection.establishConnection();
			ps=conn.prepareStatement(insertQuery);
			ps.setString(1, foodType);
			rs=ps.executeQuery();
			
			fl.clear();
			while(rs.next())
			{
				Food f = new Food();
				f.setFoodName(rs.getString("foodName"));
				f.setFoodPrice(rs.getDouble("foodPrice"));
				f.setFoodCategory(rs.getString("foodCategory"));
				f.setFoodType(rs.getString("foodType"));
				fl.add(f);
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fl;
	}
	
	

}
