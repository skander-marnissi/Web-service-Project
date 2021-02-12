package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import service.CarService;
import service.FeedBackService;
import service.RentalService;
import service.WaitingListService;
import shared.ICarsManagement;
import shared.IEmployManagement;
import shared.IFeedBackManagement;
import shared.IRentalsManagement;
import shared.IWaitingManagement;

public class ServerIfsCars {
	
	public static IEmployManagement Employees = null;
	
	 private ServerIfsCars() {
		try {
			
			Employees = (IEmployManagement) Naming.lookup("rmi://localhost:1099/EmployeeManagement");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("An Error Has Occured when calling Employee Manager for Eiffel Corp server stacktrace : " + e);
			 
		}
		
		
		
	}
	
	 public static IEmployManagement GetInstance() throws MalformedURLException, RemoteException, NotBoundException 
	    {
		 
		 // To ensure only one instance is created 
		        if (Employees == null) 
		        { 
		        	Employees = (IEmployManagement) Naming.lookup("rmi://localhost:1099/EmployeeManagement");
		        } 
		        return Employees; 
		 
		 
	      
	    }






	public static void main(String[] args) {
		// TODO Auto-generated method stub
	try {
				
				LocateRegistry.createRegistry(1100);
				
				ICarsManagement cars =  CarService.GetInstance();
				
				IRentalsManagement rentals = RentalService.GetInstance();
				
				IWaitingManagement waitings = WaitingListService.GetInstance();
				
				IFeedBackManagement feedBacks = FeedBackService.GetInstance();
				
			
				
				
				
				Naming.rebind("rmi://localhost:1100/CarsManagement",cars);
				Naming.rebind("rmi://localhost:1100/RentalsManagement",rentals);
				Naming.rebind("rmi://localhost:1100/WaitingManagement",waitings);
				Naming.rebind("rmi://localhost:1100/FeedBackManagement",feedBacks);
				
				
				}
			
			catch (Exception e) {
				
				 System.out.println("An Error Has Occured while running the server  stacktrace : " + e.getMessage());
				 
				 }
		
		}

	}


