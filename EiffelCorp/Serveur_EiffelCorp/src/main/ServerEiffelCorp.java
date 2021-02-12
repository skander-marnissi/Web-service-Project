package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import service.EmployeeService;
import shared.ICarsManagement;
import shared.IEmployManagement;
import shared.IRentalsManagement;
import shared.IWaitingManagement;


public class ServerEiffelCorp {

	
	public static IWaitingManagement Waitings = null;
	
	public static IRentalsManagement Rentals = null;
	
	public static ICarsManagement Cars = null;
	
	 private ServerEiffelCorp() {
		try {
			
			
			Waitings = (IWaitingManagement) Naming.lookup("rmi://localhost:1100/WaitingManagement");
		
			Rentals = (IRentalsManagement) Naming.lookup("rmi://localhost:1100/RentalsManagement");
			
			Cars = (ICarsManagement) Naming.lookup("rmi://localhost:1100/CarsManagement");
			
		
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("An Error Has Occured when calling Waiting list and Rentlas Manager for IfsCars server stacktrace : " + e);
			 
		}
		
		
		
	}
	
	 public static IWaitingManagement GetInstanceWaitings() throws MalformedURLException, RemoteException, NotBoundException 
	    {
		 
		 // To ensure only one instance is created 
		        if (Waitings == null) 
		        { 
		        	Waitings = (IWaitingManagement) Naming.lookup("rmi://localhost:1100/WaitingManagement");
		    		
		        } 
		        return Waitings; 
		 
		 
	      
	    }
	 
	 public static ICarsManagement GetInstanceCars() throws MalformedURLException, RemoteException, NotBoundException 
	    {
		 
		 // To ensure only one instance is created 
		        if (Cars == null) 
		        { 
		        	Cars = (ICarsManagement) Naming.lookup("rmi://localhost:1100/CarsManagement");
					
		        } 
		        return Cars; 
		 
		 
	      
	    }
	 
	 public static IRentalsManagement GetInstanceRentals() throws MalformedURLException, RemoteException, NotBoundException 
	    {
		 
		 // To ensure only one instance is created 
		        if (Rentals == null) 
		        { 
		        	Rentals = (IRentalsManagement) Naming.lookup("rmi://localhost:1100/RentalsManagement");
					
		        } 
		        return Rentals; 
		 
		 
	      
	    }
	
	
	
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		// TODO Auto-generated method stub

		try {
			
			LocateRegistry.createRegistry(1099);
			
			IEmployManagement emp = EmployeeService.GetInstance();
			
			
			Naming.rebind("rmi://localhost:1099/EmployeeManagement",emp);
			
			}
		
		catch (Exception e) {
			
			 System.out.println("An Error Has Occured while running the server for EiffelCorp stacktrace : " + e.getMessage());
			 
			 }
	
	}

}
