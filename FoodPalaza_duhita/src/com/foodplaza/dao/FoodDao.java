package com.foodplaza.dao;

import java.util.List;

import com.foodplaza.pojo.Food;

public interface FoodDao {
	
	boolean addFood(Food f);
	
	boolean updateFood(Food f);
	
	boolean deleteFood(int foodId);
	
//	Food displayFoodById(int foodId);
//	
//	Food diplayFoodByName(String foodName);
//	
//	Food displayFoodByPrice(double foodPrice);
//	
//This all are having same method type but different method name so we use POLYMORPHISM which look like:
	
	Food display(int foodId);
	
	Food display(String foodName);
	
	List<Food> display(double foodprice);
	
	List<Food>displayAllFood();
	
	List<Food>displayFoodByRange(double intialRange,double FinalRange);
	
	List<Food>displayFoodByCategory(String foodCategory);
	
	List<Food>displayFoodByType(String foodType);



	
}
