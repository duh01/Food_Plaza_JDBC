package com.foodplaza.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.CustomerDaoImpl;
import com.foodplaza.pojo.Customer;
import com.foodplaza.pojo.Food;

public class CustomerService {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int customerId,choice,choice1;
		boolean flag;
		String CustomerName,CustomerAddr,CustomerEmailId,CustomerPassword;
		long contactNo;
		Customer c = new Customer();
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		List<Customer> customerlist = new ArrayList<>();
		
		do {
			System.out.println("1.Add Customer\n2.Update Customer\n3.Delete Customer\n4.Display All Customer\n5.Display Customer city\n6.Exit\nEnter the choice: ");
			choice=sc.nextInt();
			
			switch(choice)
		{
			case 1:
				
				sc.nextLine();
				System.out.println("Enter Customer name:");
				CustomerName=sc.nextLine();
				
				System.out.println("Enter Customer address: ---Building name, Landmark, City-----");
				CustomerAddr=sc.nextLine();
				
				System.out.println("Enter Customer EmailID:");
				CustomerEmailId=sc.next();

				System.out.println("Enter Customer EmailID's Password:");
				CustomerPassword=sc.next();
				
				System.out.println("Enter your contact Number: ");
				contactNo=sc.nextLong();
				
				c.setCustomerName(CustomerName);
				c.setCustomerAddr(CustomerAddr);
				c.setCustomerEmailId(CustomerEmailId);
				c.setCustomerPassword(CustomerPassword);
				c.setContactNo(contactNo);

				flag=cdi.addCustomer(c);
				if(flag)
				{
					System.out.println("Customer added successfully.");
				}
				else {
					System.out.println("Something went wrong....");
				}
				
				break;

			case 2 :
			
				System.out.println("Enter the id Which you want to change");
				customerId=sc.nextInt();
				
				sc.nextLine();
				System.out.println("Enter Customer name:");
				CustomerName=sc.nextLine();
				
				System.out.println("Enter Customer address: ---Building name, Landmark, City-----");
				CustomerAddr=sc.nextLine();
				
				System.out.println("Enter Customer EmailID:");
				CustomerEmailId=sc.next();

				System.out.println("Enter Customer EmailID's Password:");
				CustomerPassword=sc.next();
				
				System.out.println("Enter your contact Number: ");
				contactNo=sc.nextLong();
				
				c.setCustomerId(customerId);
				c.setCustomerName(CustomerName);
				c.setCustomerAddr(CustomerAddr);
				c.setCustomerEmailId(CustomerEmailId);
				c.setCustomerPassword(CustomerPassword);
				c.setContactNo(contactNo);

				flag=cdi.updateCustomer(c);
				if(flag)
				{
					System.out.println("Customer updated successfully.");
				}
				else {
					System.out.println("Something went wrong....");
				}		
				break;
				
				
			case 3 :
				System.out.println("Enter the id Which you want to Delete: ");
				customerId=sc.nextInt();
				
				c.setCustomerId(customerId);
				
				flag=cdi.deleteCustomer(c);
				if(flag)
				{
					System.out.println("Customer updated successfully.");
				}
				else {
					System.out.println("Something went wrong....");
				}
				break;
				
				
			case 4 :
				System.out.println("1.Display Customer By Id\t2.Display Customer By Name\n3.Display Customer By ConatctNo\t4.Display Customer By EmailId\n5.Display Customer By Address\t6.Display All Customer \n7.Exit\nEnter Your Choice: ");
				choice1=sc.nextInt();
				
				switch(choice1) {
				case 1:
					System.out.println("Enter the ID you want to check:");
					customerId = sc.nextInt();
					Customer c2 = cdi.displayCustomerById(customerId);

					if (c2 != null) {
			            
					    System.out.println("\nCustomer found and displayed successfully\n");
					} 
					else {
					    System.out.println("\nSomething went wrong or Customer not found.\n");
					}
					break;
				
				case 2:
					sc.nextLine();
					System.out.println("Enter the Customer NAME you want to check: ");
					CustomerName=sc.nextLine();
					 customerlist = cdi.displayCustomeByname(CustomerName);
					
					 for(Customer c1 :customerlist)
						{
					
							System.out.println("Customer Name: "+c1.getCustomerName()+"Customer Address: "+ c1.getCustomerAddr()+"Customer EmailId: "+c1.getCustomerEmailId()+"Customer Password: "+c1.getCustomerPassword()+"Customer Contact Number: "+c1.getContactNo());
					
						}
					 if(customerlist!=null)
					{
						
						System.out.println("\nCUSTOMER NAME FOUND.\n");
					}
					else {
						System.out.println("\nSomething went wrong or Customer not found.\n");
					}
					break;
					
				case 3:
					System.out.println("ENTER YOUR CONTACT NUMBER: ");
					contactNo = sc.nextLong();
					Customer cc = cdi.displayCustomerByConatctNo(contactNo);
					
					break;
					
				case 4:
					System.out.println("Enter the Email-ID you want to check:");
					CustomerEmailId = sc.next();
					Customer c3 = cdi.displayCustomerByEmailId(CustomerEmailId);

					if (c3 != null) {
			            
					    System.out.println("\nCustomer found and displayed successfully\n");
					} 
					else {
					    System.out.println("\nSomething went wrong or Customer not found.\n");
					}
					break;
					
				case 5:
					sc.nextLine();
					System.out.println("Enter the Address you want to check:");
					CustomerAddr= sc.nextLine();
					customerlist = cdi.displayCustomerByAddress(CustomerAddr);
					
					for(Customer c1 :customerlist)
					{
				
						System.out.println("Customer Name: "+c1.getCustomerName()+"Customer Address: "+ c1.getCustomerAddr()+"Customer EmailId: "+c1.getCustomerEmailId()+"Customer Password: "+c1.getCustomerPassword()+"Customer Contact Number: "+c1.getContactNo());
				
					}
					if (customerlist != null) {
			            
					    System.out.println("\nCustomer found and displayed successfully\n");
					} 
					else {
					    System.out.println("\nSomething went wrong or Customer not found.\n");
					}
					break;
					
				case 6:
					customerlist = cdi.displayAllCustomer();
					
					 for(Customer c1 :customerlist)
						{
					
							System.out.println("Customer Name: "+c1.getCustomerName()+"Customer Address: "+ c1.getCustomerAddr()+"Customer EmailId: "+c1.getCustomerEmailId()+"Customer Password: "+c1.getCustomerPassword()+"Customer Contact Number: "+c1.getContactNo());
					
						}
					 if(customerlist!=null)
					{
						
						System.out.println("\nCUSTOMER FOUND.\n");
					}
					else {
						System.out.println("\nSomething went wrong or Food not found.\n");
					}
					break;
					
				case 7:
					System.out.println("EXITED SUCESSFULLY.");
					return;
					
				}
				break;
			case 5 :
				String Town,City,customerAddr;
				String[] addrParts;
				sc.nextLine();
				System.out.println("Enter Customer address: ---Building name, Landmark, City-----");
			    customerAddr = sc.nextLine();
			    
			    addrParts = customerAddr.split(",");
			    
			    Town = addrParts[addrParts.length - 1]; //The last part as city
			    
			    City = "%"+Town+"%";
			    
			    customerlist= cdi.displayByCity(City);
			    
			    if (customerlist!=null&& !customerlist.isEmpty()) {
			    	System.out.println("Customers found in the city: " + City);
			        for (Customer ccc : customerlist) {
			            System.out.println("Name: " + ccc.getCustomerName()+ " Address: " + ccc.getCustomerAddr() +" Email: " + ccc.getCustomerEmailId() +" Contact No: " + ccc.getContactNo());
			        }
			    }
			    else
			    {
			    	System.out.println("No customers found in the city: " + City);
			    }
				break;
				
			case 6 :
				System.out.println("EXITED SUCESSFULLY.");
				return;
				
		}
			
		}while(choice!=6);
	}
}
