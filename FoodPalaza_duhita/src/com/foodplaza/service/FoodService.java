package com.foodplaza.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.FoodDaoImpl;
import com.foodplaza.pojo.Food;

public class FoodService {
	public static void main(String[] args) {
		
		List<Food> foodList = new ArrayList<>(); 
		
		Scanner sc = new Scanner(System.in);
		int choice,c1,FoodId;
		boolean flag;
		String foodName, foodCategory,foodType;
		double foodPrice,intialRange,FinalRange;
		
		FoodDaoImpl fdi = new FoodDaoImpl();
		
		Food f =  new Food();
		do
		{
			
			System.out.println("1.Add Food\n2.Update Food\n3.Delete Food\n4.Display All Food\n5.To check list of food between ur range\n6.Exit\nEnter your choice\n");
			
			choice =  sc.nextInt();
			
			switch(choice)
			{
			
				case 1:
				
					System.out.println("Enter food name:");
					foodName = sc.next();
				
					System.out.println("Enter food price:");
					foodPrice = sc.nextDouble();
				
					System.out.println("Enter food category:");
					foodCategory = sc.next();
				
					System.out.println("Enter food type:");
					foodType =  sc.next();
				
					f.setFoodName(foodName);
					f.setFoodPrice(foodPrice);
					f.setFoodCategory(foodCategory);
					f.setFoodType(foodType);
				
				
					flag = fdi.addFood(f);
					if(flag)
					{
						System.out.println("\n Food added successfully\n");
					
					}
					else
					{
						System.out.println("\n Something went wrong....\n");
					}
				
					break;
					
				case 2:
					
					System.out.println("Enter the id Which you want to change");
					FoodId = sc.nextInt();
					
					System.out.println("Enter food name:");
					foodName = sc.next();
				
					System.out.println("Enter food price:");
					foodPrice = sc.nextDouble();
				
					System.out.println("Enter food category:");
					foodCategory = sc.next();
				
					System.out.println("Enter food type:");
					foodType =  sc.next();
					
					f.setFoodId(FoodId);
					f.setFoodName(foodName);
					f.setFoodPrice(foodPrice);
					f.setFoodCategory(foodCategory);
					f.setFoodType(foodType);
				
				
					flag = fdi.updateFood(f);
					if(flag)
					{
						System.out.println("\nFood updated successfully\n");
					
					}
					else
					{
						System.out.println("\n Something went wrong....\n");
					}
					break;
					
				case 3:
					System.out.println("Enter the id Which you want to Delete");
					FoodId = sc.nextInt();
					
					f.setFoodId(FoodId);				
				
					flag = fdi.deleteFood(FoodId);
					if(flag)
					{
						System.out.println("\nFood Deleted successfully\n");
					
					}
					else
					{
						System.out.println("\n Something went wrong....\n");
					}
					break;
				case 4:
					System.out.println("Enter to Display \n1.ID\n2.Food NAME\n3.Food Price\n4.All Food \n5.Food Category\n6.Food Type");
					c1=sc.nextInt();
					switch(c1)
					{
					case 1: 
						System.out.println("Enter the ID you want to check:");
						FoodId = sc.nextInt();
						Food f2 = fdi.display(FoodId);

						if (f2 != null) {
						    System.out.println("\nFood found and displayed successfully\n");
						} 
						else {
						    System.out.println("\nSomething went wrong or Food not found.\n");
						}
						break;
					case 2:
						System.out.println("Enter the FODD NAME you want to check: ");
						foodName=sc.next();
						Food f5 = fdi.display(foodName); //hey food nava cha ekda datatype madhe store e jyacha var f5 ahe ani tyanne he sadha hotay ki food nava cha pojo class e tho ithe DaoImplement sobat connect hotoy tithun foodName access krtoy n store krtoy.
						if(f5!=null)
						{
							System.out.println("\nFOOD NAME FOUND.");
						}
						else {
							System.out.println("\nSomething went wrong or Food not found.\n");
						}
						break;
					case 3:
						System.out.println("Enter food price:");
						foodPrice = sc.nextDouble();
						foodList = fdi.display(foodPrice);
						
						for(Food f1 :foodList)
						{
					
							System.out.println("Food Type: "+f1.getFoodType()+"Food Name: " +f1.getFoodName() +"Food Price: "+ f1.getFoodPrice()+"Food Category: "+f1.getFoodCategory());
					
						}
						
						break;
					case 4:
						foodList = fdi.displayAllFood();
				
						for(Food f1 :foodList)
						{
					
							System.out.println("Food Type: "+f1.getFoodType()+"Food Name: " +f1.getFoodName() +"Food Price: "+ f1.getFoodPrice()+"Food Category: "+f1.getFoodCategory());
					
						}
						break;
					case 5:
						System.out.println("Enter the FODD Catagory you want to check: ");
						foodCategory=sc.next();
						
						foodList = fdi.displayFoodByCategory(foodCategory);
						//Food f9 = new Food();
						for(Food f9 : foodList) //ithe food cha navin obj ka banvayla lagto nhi tr to local variable change krayla sangto
						{
							System.out.println("Food Type: "+f9.getFoodType()+"Food Name: " +f9.getFoodName() +"Food Price: "+ f9.getFoodPrice()+"Food Category: "+f9.getFoodCategory());
						}
						break;
					case 6:
						System.out.println("Enter the FODD TYPE you want to check: ");
						foodType=sc.next();
						
						foodList = fdi.displayFoodByType(foodType);
						
						for(Food f9 : foodList) //ithe food cha navin obj ka banvayla lagto nhi tr to local variable change krayla sangto
						{
							System.out.println("Food Type: "+f9.getFoodType()+"Food Name: " +f9.getFoodName() +"Food Price: "+ f9.getFoodPrice()+"Food Category: "+f9.getFoodCategory());
						}
						break;
					}
				case 5:
					System.out.println("Enter the FODD Price you want to check from: ");
					intialRange=sc.nextDouble();
					
					System.out.println("Enter the FODD Price till you want: ");
					FinalRange=sc.nextDouble();
					
					foodList=fdi.displayFoodByRange(intialRange, FinalRange);
					for(Food f9 : foodList)
					{
						System.out.println("Food Type: "+f9.getFoodType()+"Food Name: " +f9.getFoodName()+"Food Price: "+ f9.getFoodPrice()+"Food Category: "+f9.getFoodCategory());
					}
				
				break;	
				
				case 6:
					System.out.println("EXITED SUCCESSFULLY.");
					return;
			}
		}while(choice!=6);
	}
}