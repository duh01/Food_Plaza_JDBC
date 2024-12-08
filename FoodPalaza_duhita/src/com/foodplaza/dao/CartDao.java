package com.foodplaza.dao;

import java.util.List;

import com.foodplaza.pojo.Cart;

public interface CartDao {
	
	boolean addToCart(Cart ct);
	boolean deleteCart(int cartId);
	boolean deleteCart(String customerEmailId);
	List<Cart>showCart();
	List<Cart> showCart(String customerEmailId);
}
