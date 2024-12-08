package com.foodplaza.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.foodplaza.daoimpl.CartDaoImpl;
import com.foodplaza.daoimpl.OrderDaoImpl;
import com.foodplaza.pojo.Orders;

public class OrderService {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int orderId,choice;
		String foodName, CustomerEmailId,orderStatus, orderDate;
		double totalBill;
		boolean flag;
		
		Orders o = new Orders();
		OrderDaoImpl odi = new OrderDaoImpl();
		CartDaoImpl ctdi=new CartDaoImpl();
		List<Orders> ol =new ArrayList<>();
		
		do {
			System.out.println("1.Place Order\n2.Show Orders \n3.Show Orders by Customer Email-ID\n4.Cancel Order \n5.Check Order Status \n6.Update Order Status \n7.Exit. \nENETR YOUR CHOICE:");
			choice = sc.nextInt();
			switch(choice) {
			
			case 1:
				System.out.println("Enter customer Email");
				CustomerEmailId = sc.next();
				
				if(ctdi.showCart(CustomerEmailId) != null) {
					flag = odi.placeOrder(CustomerEmailId);
					if(flag) {
						System.out.println("PLACED THE ORDER.");
					}
					else {
						System.out.println("SOMETHING WENT WRONG....");
					}
				}
				break;
				
			case 2:
				ol=odi.showOrders();
				for(Orders o1 : ol) {
					System.out.println(o1.getFoodName()+","+o1.getCustomerEmailId()+","+o1.getOrderStatus()+","+o1.getTotalBill()+","+o1.getOrderDate()+".");
				}
				
				break;
				
			case 3:
				System.out.println("Enter customer Email");
				CustomerEmailId = sc.next();
				
				ol=odi.showOrders();
				for(Orders o1 : ol) {
					System.out.println(o1.getFoodName()+", "+o1.getCustomerEmailId()+", "+o1.getOrderStatus()+", "+o1.getTotalBill()+", "+o1.getOrderDate()+".");
				}
				break;
				
			case 4:
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
				
			case 5:
				System.out.println("Enter customer emailID");
				CustomerEmailId = sc.next();
				o = odi.checkOrderStatus(CustomerEmailId);
				break;
				
			case 6:
				System.out.println("Enter Customer Email ID:");
				CustomerEmailId = sc.next();
				    
				System.out.println("Enter new Order Status:");
				orderStatus = sc.next();
				    
				o = odi.updateOrderStatus(CustomerEmailId,orderStatus);
				break;
				
			case 7:
				System.out.println("Exited successfully plz come again BYE! have a nice day...");
				return;
				
			default:
				System.out.println("INVALID CHOICE CHOOSE AGAIN PLZ.");
				break;	
			}
		}while(choice!=7);
		
	}
}
