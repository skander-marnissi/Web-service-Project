package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import entity.Car;
import shared.ICar;
import shared.ICarsManagement;
import shared.IRental;
import shared.IRentalsManagement;

public class CarService extends UnicastRemoteObject implements ICarsManagement {

	
	List<ICar> cars;
	
	IRentalsManagement rs;

	 // private constructor restricted to this class itself 
		private CarService() throws RemoteException
	    {
			cars = new CopyOnWriteArrayList<ICar>();
	    }
	     
	    private static CarService single_instance=null; 
	    
	  
	    // static method to create instance of CarService class 
	    public static CarService GetInstance() throws RemoteException
	    { 
	        // To ensure only one instance is created 
	        if (single_instance == null) 
	        { 
	            single_instance = new CarService(); 
	        } 
	        return single_instance; 
	    }
		
	
	//[For test use] Add car in Car list 
	public boolean addCar(String brand, String model) throws RemoteException {
		
		try {
			
			if(this.cars.isEmpty()) {
				this.cars.add(new Car(0,brand,model));
				return true;
				}
			
			else {
				this.cars.add(new Car(this.cars.get(this.cars.size()-1).getId()+1,brand,model));
				return true;
				}
				
			}
	
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the addition of car process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return false;
		
	}
	
	//[For test use] Add car in Car list 
	public boolean addCar(String brand, String model, int price) throws RemoteException {
		
		try {
			
			if(this.cars.isEmpty()) {
				this.cars.add(new Car(0,price,brand,model));
				return true;
				}
			
			else {
				this.cars.add(new Car(this.cars.get(this.cars.size()-1).getId()+1,price,brand,model));
				return true;
				}
				
			}
	
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the addition of car process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return false;
		
	}
	
	
	//[For test use] Add car in Car list 
	public boolean addCar(int horsePower,String brand, String model, String type,String description,String imageUrl,String color,int price, int registrationNumber) throws RemoteException {
		
		try {
			
			if(this.cars.isEmpty()) {
				this.cars.add(new Car(0, price, registrationNumber, horsePower, brand, model, type, description, imageUrl, color));
				return true;
				}
			
			else if(!this.cars.isEmpty() && this.searchCarByRegistrationNumber(registrationNumber)==null) {
				this.cars.add(new Car(this.cars.get(this.cars.size()-1).getId()+1,price,registrationNumber,horsePower, brand, model, type, description, imageUrl, color));
				return true;
				}
			
			
			else {
				System.out.println("[addCar function in CarService] : The given car registration number already exist ");
				
			}
				
			}
	
		catch(Exception e) {
			
			System.out.println("[addCar function in CarService] : An Error has occurend during the addition of car process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return false;
		
	}
	
	
	//Search car from Car list by it's registration Number
	public ICar searchCarByRegistrationNumber (int registrationNumber) throws RemoteException {
		
		try {
			
			for(ICar c :this.cars) {
				
				if(c.getRegistrationNumber() == registrationNumber) {
					 
					return c ;
					
				}
				
				
			
			}
			
			
			
			return 	null;
				
			}
			
		catch(Exception e) {
			
			System.out.println("[searchCarByModel function in carService] : An Error has occurend during the research of car by registration number process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}

	
	
	//Search car from Car list by it's model
	public List<ICar> searchCarByModel(String model) throws RemoteException {
		
		try {
			
			List result = new ArrayList<Car>(); 
			for(ICar c :this.cars) {
				
				if(c.getModel().toLowerCase().equals(model.toLowerCase())) {
					 
					result.add(c);
					
				}
				
				
			
			}
			
			
			
			return 	result;
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of car by model process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}
	
	//Search car from Car list by it's brand
	public List<ICar> searchCarByBrand(String brand) throws RemoteException {
		try {
			
			List result = new ArrayList<Car>(); 
			for(ICar c :this.cars) {
				
				if(c.getBrand().toLowerCase().equals(brand.toLowerCase())) {
					 
					result.add(c);
					
				}
			
			}
			
			return 	result;
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of car by brand process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}
	
	//Search car from Car list by it's id
	public ICar searchCarById(int id) throws RemoteException {
		
		try {
			
			for(ICar c :this.cars) {
				
				if(c.getId()==id) {
					 
					
					return (ICar) c;
				}
			
			}
			
				
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of car by id process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}
	
	//Remove car from Car list by it's id and remove all other fields containing it from waiting list and rental list
	public boolean removeCarById(int id) throws RemoteException {
	
		try {
			
			rs = RentalService.GetInstance();
			
			for(ICar c :this.cars) {
				
				if(c.getId()==id) {
					 
					rs.updateRentAfterRemovingCar(c.getId());
					this.cars.remove(c);
					
					return true;
				}
			
			}
			
				
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the deletion of car process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return false;
		
	}
	
	//get one time at least rented cars 
	public List<ICar> getAllRentedCarsAtLeastOneTime() throws RemoteException {
			
			try {
				
				rs = RentalService.GetInstance();
				
				List<ICar> rentedCars = new CopyOnWriteArrayList<ICar>();
				
				for(IRental r : rs.getRentals()) {
					
					if(r.getStatus().equals("FINISHED")){
						ICar c = this.searchCarById(r.getCar_id());
						
						if(!c.getState().equals("SOLD"))
							rentedCars.add(this.searchCarById(r.getCar_id()));
					}
			}
				return rentedCars;
				
			}
			catch(Exception e) {
				System.out.println("An Error has occurend during the research of one time at least rented cars stacktrace : "+ e);
				e.printStackTrace();
			}
			
			return null;

		
		}
	
	//Getter of Car list 
	public List<ICar> getCars() throws RemoteException {
		return cars;
	}

	//Setter of  Car list
	public void setCars(List<ICar> cars)  throws RemoteException {
		this.cars = cars;
	}
	
	
	
	
	
	
}
