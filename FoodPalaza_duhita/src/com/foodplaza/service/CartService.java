package com.foodplaza.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.CartDaoImpl;
import com.foodplaza.daoimpl.CustomerDaoImpl;
import com.foodplaza.pojo.Cart;

public class CartService {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean flag=true;
		int choice;
		
		int cartId,foodQty;
		String foodName,CustomerEmailId;
		
		List<Cart> cartList = new ArrayList<>();
		Cart ct = new Cart();
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		CartDaoImpl ctdi = new CartDaoImpl();
		
		do {
			
			System.out.println("1.Add to cart\n2.delete from cart\n3.delete from cart by customerEmailID\n4.show cart\n5.show cart by customer E-MailId");
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			
			switch(choice) {
			
			case 1:
				
				System.out.println("Enter customer Email");
				CustomerEmailId = sc.next();
				
				if(cdi.displayCustomerByEmailId(CustomerEmailId) != null) {
					System.out.println("Enter food name");
					foodName = sc.next();
					System.out.println("Enter food quantity");
					foodQty = sc.nextInt();
					
					ct.setCustomerEmailId(CustomerEmailId);
					ct.setFoodName(foodName);
					ct.setFoodQty(foodQty);
					
					flag = ctdi.addToCart(ct);
				}
				else {
					System.out.println("email is not valid");
				}
				
				if(flag) {
					System.out.println("Cart added successfully");
				}
				else {
					System.out.println("something went wrong");
				}
				
				break;
				
			case 2:
				System.out.println("Enter cartID to delete: ");
				cartId = sc.nextInt();
				ct.setCartId(cartId);
				
				flag=ctdi.deleteCart(cartId);
				if(flag)
				{
					System.out.println("Customer updated successfully.");
				}
				else {
					System.out.println("Something went wrong....");
				}
				break;
			case 3:
				System.out.println("Enter customer emailID");
				CustomerEmailId = sc.next();
				
				flag = ctdi.deleteCart(CustomerEmailId);
				
				if(flag) {
					System.out.println("cart deleted successfully");
				}
				else {
					System.out.println("something went wrong");
				}
				
				break;

				
			case 4:
				cartList = ctdi.showCart();
				
				for(Cart c1 : cartList) {
					System.out.println(c1.getCartId()+","+c1.getCustomerEmailId()+","+c1.getFoodId()+","+c1.getFoodName()+","+c1.getFoodPrice()+","+c1.getFoodQty());
					
				}
				break;

				
			case 5:
				System.out.println("Enter customer email id");
				CustomerEmailId = sc.next();
				
				cartList = ctdi.showCart(CustomerEmailId);
				
				for(Cart c1 : cartList) {
					System.out.println(c1.getCartId()+","+c1.getFoodId()+","+c1.getFoodName()+","+c1.getFoodPrice()+","+c1.getFoodQty());
					
				}

				break;
				
			default:
				System.out.println("INVALID CHOICE");
				break;
			}
			
		}
		while(choice!=5);
	}

}

