package test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import shared.ICar;
import shared.ICarsManagement;
import shared.IRental;
import shared.IRentalsManagement;
import shared.IWaiting;
import shared.IWaitingManagement;


public class TestRentalService {

	

		public TestRentalService() throws RemoteException {}

		public boolean testAddRental(IRentalsManagement rentals, int car_id,int employee_id, String start_date, String end_date) throws RemoteException {
			
			System.out.println("\n*-Start Method testAddRental-*");
			
			try {
				
				
				
				boolean result = rentals.addRental(car_id, employee_id, start_date, end_date);
				
				for(IRental r : rentals.getRentals())
						displayRent(r);
				
				
				return result;
				
			}
			catch(Exception e) {
				System.out.println("An Error has occurend during the test of add Rent : "+e);
				e.printStackTrace();
			}
			
			System.out.println("\n*-End Method testAddRental-*");
			return false;
		}
		
		public boolean testRemoveRental(IRentalsManagement rentals, int id) throws RemoteException {
			
			System.out.println("\n*-Start Method testRemoveRental-*");	
			boolean result = false;
			
		try {
			
			result = rentals.removeRental(id);
			
			//Display the returned value 
			for(IRental r : rentals.getRentals())
				displayRent(r);
			
		}
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the test of remove Rent : "+e);
			e.printStackTrace();
		}
			
			System.out.println("\n*-End Method testRemoveRental-*");		
			return result;
		
		}
		
		public boolean testSearchRentalsByCarId(IRentalsManagement rentals, int car_id) throws RemoteException {
			
			System.out.println("\n*-Start Method testSearchRentalsByCarId-*");
			boolean result = false;
			
			try {
				
				List<IRental> result_rents = rentals.searchRentByCarId(car_id);
				
				//Display the returned values
				for(IRental r : result_rents) {
					displayRent(r);
					
					if(r.getCar_id() == car_id)
						result = true;
				}
				
			
					
				
			}
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the test of search Rent by car id : "+ e);
				e.printStackTrace();
			}
				
			System.out.println("\n*-End Method testSearchRentalsByCarId-*");
				return result;
			
			
		
		}
		
		public boolean testSearchRentByEmployeeId(IRentalsManagement rentals, int employee_id) throws RemoteException{
			
			System.out.println("\n*-Start Method testSearchRentByEmployeeId-*");	
			boolean result = false;
			
			try {
				
				List<IRental> result_rentals = rentals.searchRentByEmployeeId(employee_id);
				
				//Display the returned values 
				for(IRental r : result_rentals) {
					displayRent(r);
					
					if(r.getEmployee_id() == employee_id)
						result = true;
				}
				
				
					
				
			}
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the test of search Rend by Employee Id : "+ e);
				e.printStackTrace();
			}
			
				System.out.println("\n*-End Method testSearchRentByEmployeeId-*");	
				return result;
			
		
		}
		
		public boolean testSearchRentById(IRentalsManagement rentals, int id) throws RemoteException {
			
			System.out.println("\n*-Start Method testSearchRentById-*");	
			boolean result = false;
				
				try {
					
					IRental result_rental = rentals.searchRentById(id);
			
					//Display the returned value 
					displayRent(result_rental);
					
						if(result_rental.getId() == id)
							result = true;
					
					
					
						
					
				}
				catch(Exception e) {
					
					System.out.println("An Error has occurend during the test of search Rent by id : "+ e);
					e.printStackTrace();
				}
					
				System.out.println("\n*-End Method testSearchRentById-*");		
				return result;
				
			
		
		}
		
		public boolean testSetRental(IRentalsManagement rentals, int car_id, int changeTo) throws RemoteException {

				System.out.println("\n*-Start Method testSetRental-*");	
				boolean result = true;
				
				try {
					
					List<IRental> result_rentals = rentals.searchRentByCarId(car_id);
			
					//Display the returned values
					System.out.println("Before Change  : -------------");
					for(IRental r : result_rentals) {
						
					
						displayRent(r);
						
						System.out.println("\n -----------------------------------");
						
						r.setCar_id(changeTo);
						
					}
					
					//Display the returned values
					System.out.println("\nAfter Change  : -------------");
					for(IRental r : rentals.getRentals()) {
						
						displayRent(r);
						
						System.out.println("\n -----------------------------------");
						
						if(r.getCar_id()==car_id) 
							result = false;
						
						
					}
						
					
						
					
				}
				catch(Exception e) {
					
					System.out.println("An Error has occurend during the test of set Rent opperation : "+ e);
					e.printStackTrace();
				}
					
				System.out.println("\n*-End Method testSetRental-*");		
				return result;
		
		}
		
		public void displayRent(IRental r) throws RemoteException{
			
			System.out.println("\n Rental id : "+r.getId()+" Car id : "+ r.getCar_id()+" Employee Id : "+r.getEmployee_id()+" Start date : "+r.getStart_date().toString()+" End date : "+r.getEnd_date().toString());
			
		}
		 

		public static void main(String[] args) throws RemoteException, MalformedURLException  {
			// TODO Auto-generated method stub
			try {
				
				/*App. Rental-Management Test*/
				
				
				IRentalsManagement rentals = (IRentalsManagement) Naming.lookup("rmi://localhost:1100/RentalsManagement");
				
				TestRentalService tests = new TestRentalService();
				
				
				boolean testAddRental = false, testSearchRentalsByCarId = false ,testSearchRentByEmployeeId = false ,testSearchRentById = false ,testSetRental = false  ,testRemoveRental = false ;
				
				
				testAddRental = tests.testAddRental(rentals, 0, 0,"14/02/2020", "15/02/2021") && tests.testAddRental(rentals, 2, 1,"16/02/2020", "16/02/2021") && tests.testAddRental(rentals, 1, 2,"17/02/2020", "17/02/2021");
				
				testSearchRentalsByCarId = tests.testSearchRentalsByCarId(rentals, 1);
				
				testSearchRentByEmployeeId = tests.testSearchRentByEmployeeId(rentals, 0);
				
				testSearchRentById = tests.testSearchRentById(rentals,2);
				
				testSetRental = tests.testSetRental(rentals,0,5);
				
				testRemoveRental = tests.testRemoveRental(rentals, 1);
				
				System.out.println("\nResult of Tests of RentService :"
						+"\n testAddRental : "+ testAddRental
						+"\n testSearchRentalsByCarId : "+ testSearchRentalsByCarId
						+"\n testSearchRentByEmployeeId : "+ testSearchRentByEmployeeId
						+"\n testSearchRentById : "+ testSearchRentById
						+"\n testSetRental : "+ testSetRental
						+"\n testRemoveRental : "+ testRemoveRental);
				
				//Exception
				 }
			
				 catch (Exception e) {
					 
					 e.printStackTrace();
					 System.out.println("An Error Has Occured while running the client for RentalService Test stacktrace : " + e);
				 }
			
				 }

		}




