package test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import shared.ICar;
import shared.ICarsManagement;
import shared.IEmploy;
import shared.IEmployManagement;


public class TestCarsService {
	
	
	public TestCarsService() throws RemoteException {}

	public boolean testAddCar(ICarsManagement cars, String model, String brand) throws RemoteException {
		
		System.out.println("\n*-Start Method addCar-*");
		
		boolean result = cars.addCar(brand, model);
		
		for(ICar c : cars.getCars())
			displayCar(c);
		
		System.out.println("\n*-End Method addCar-*");
		return result;
	
	}
	
	public boolean testRemoveCar(ICarsManagement cars, int id) throws RemoteException  {
		
		System.out.println("\n*-Start Method testRemoveCar-*");	
		boolean result = false;
		
	try {
		
		result = cars.removeCarById(id);
		
		//Display the returned value 
		for(ICar c : cars.getCars())
			displayCar(c);
		
	}
	catch(Exception e) {
		
		System.out.println("An Error has occurend during the test of remove car : "+e);
		e.printStackTrace();
	}
		
		System.out.println("\n*-End Method testRemoveCar-*");		
		return result;
	
	}
	
	public boolean testSearchCarByModel(ICarsManagement cars, String model) throws RemoteException  {
		
		System.out.println("\n*-Start Method searchCarByModel-*");
		boolean result = false;
		
		try {
			
			List<ICar> result_cars = cars.searchCarByModel(model);
			
			//Display the returned values
			for(ICar c : result_cars) {
				displayCar(c);
				
				if(c.getModel().equals(model))
					result = true;
			}
			
		
				
			
		}
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the test of search car by model : "+ e);
			e.printStackTrace();
		}
			
		System.out.println("\n*-End Method searchCarByModel-*");
			return result;
		
		
	
	}
	
	public boolean testSearchCarByBrand(ICarsManagement cars, String brand) throws RemoteException  {
		
		System.out.println("\n*-Start Method searchCarByBrand-*");	
		boolean result = false;
		
		try {
			
			List<ICar> result_cars = cars.searchCarByBrand(brand);
			
			//Display the returned values 
			for(ICar c : result_cars) {
				displayCar(c);
				
				if(c.getBrand().equals(brand))
					result = true;
			}
			
			
				
			
		}
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the test of search car by brand : "+ e);
			e.printStackTrace();
		}
		
			System.out.println("\n*-End Method searchCarByBrand-*");	
			return result;
		
	
	}
	
	public boolean testSearchCarById(ICarsManagement cars, int id)  throws RemoteException {
		
		System.out.println("\n*-Start Method searchCarById-*");	
		boolean result = false;
			
			try {
				
				ICar result_car = cars.searchCarById(id);
		
				//Display the returned value 
				displayCar(result_car);
					
					if(result_car.getId() == id)
						result = true;
				
				
				
					
				
			}
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the test of search car by id : "+ e);
				e.printStackTrace();
			}
				
			System.out.println("\n*-End Method searchCarById-*");		
			return result;
			
		
	
	}
	
	public boolean testSetCar(ICarsManagement cars, String model, String changeTo, boolean availability) throws RemoteException  {

			System.out.println("\n*-Start Method testSetCar-*");	
			boolean result = true;
			
			try {
				
				List<ICar> result_cars = cars.searchCarByModel(model);
		
				//Display the returned values
				System.out.println("Before Change  : -------------");
				for(ICar c : result_cars) {
					
				
					displayCar(c);
					
					System.out.println("\n -----------------------------------");
					
					c.setModel(changeTo);
					c.setAvailability(availability);
				}
				
				//Display the returned values
				System.out.println("\nAfter Change  : -------------");
				for(ICar c : cars.getCars()) {
					
					displayCar(c);
					
					System.out.println("\n -----------------------------------");
					
					if(c.getModel().equals(model)) 
						result = false;
					
					
				}
					
				
					
				
			}
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the test of set car opperation : "+ e);
				e.printStackTrace();
			}
				
				System.out.println("\n*-End Method testSetCar-*");	
				return result;
	
	}
	
	public void displayCar(ICar c) throws RemoteException  {
		System.out.println("\n Car id : "+c.getId()+" Model : "+ c.getModel()+" Brand : "+c.getBrand()+" Availability : "+c.getAvailability());
	}
	

	public static void main(String[] args) throws RemoteException, MalformedURLException  {
		// TODO Auto-generated method stub
		try {
			
			/*App. Car-Management Test*/
			
			
			ICarsManagement cars = (ICarsManagement) Naming.lookup("rmi://localhost:1100/CarsManagement");
			
			TestCarsService tests = new TestCarsService();
			
			
			boolean testAddCar = false, testSearchCarByModel = false ,testSearchCarByBrand = false ,testSearchCarById = false ,testSetCar = false  ,testRemoveCar = false ;
			
			
			testAddCar = tests.testAddCar(cars, "polo", "Volkswagen") && tests.testAddCar(cars, "polo", "Volkswagen") && tests.testAddCar(cars, "E230", "Mercedes");
			
			testSearchCarByModel = tests.testSearchCarByModel(cars, "polo");
			
			testSearchCarByBrand = tests.testSearchCarByBrand(cars, "Volkswagen");
			
			testSearchCarById = tests.testSearchCarById(cars,0);
			
			testSetCar = tests.testSetCar(cars, "polo", "golf",false);
			
			testRemoveCar = tests.testRemoveCar(cars, 1);
			
			System.out.println("\nResult of Tests of CarService :"
					+"\n testAddCar : "+ testAddCar
					+"\n testSearchCarByModel : "+ testSearchCarByModel
					+"\n testSearchCarByBrand : "+ testSearchCarByBrand
					+"\n testSearchCarById : "+ testSearchCarById
					+"\n testSetCar : "+ testSetCar
					+"\n testRemoveCar : "+ testRemoveCar);
			
			//Exception
			 }
		
			 catch (Exception e) {
				 
				 e.printStackTrace();
				 System.out.println("An Error Has Occured while running the client for CarService Test stacktrace : " + e);
			 }
		
			 }

	}


