package com.foodplaza.dao;

import java.util.List;

import com.foodplaza.pojo.Orders;

public interface OrderDao {
	
	boolean placeOrder(String CustomerEmailId);
	
	List<Orders>showOrders();
	
	List<Orders>showOrders(String CustomerEmailId);
	
	boolean cancelOrder(String CustomerEmailId);
	
	Orders checkOrderStatus(String CustomerEmailId);
	
	Orders updateOrderStatus(String CustomerEmailId, String OrderStatus);
}