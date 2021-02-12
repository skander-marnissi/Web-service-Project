package test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;



import shared.ICar;
import shared.ICarsManagement;
import shared.IEmploy;
import shared.IEmployManagement;
import shared.IFeedBack;
import shared.IFeedBackManagement;
import shared.IRental;
import shared.IRentalsManagement;
import shared.IWaiting;
import shared.IWaitingManagement;

public class Scenario {
	
	//Scenarios methods :

	//Removes a rentals ; if the rentals is in process another rent will automatically take his place from waiting list if it exists or will be simply removed from rentals
	public boolean removeRentalScenario(IEmployManagement employees,ICarsManagement cars,IRentalsManagement rentals,IWaitingManagement WaitingLists,IFeedBackManagement feedBacks) {
		
		System.out.println("\n*-Start method removeRentalsScenario-*");
		
		try {
			

			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			
			
			
			System.out.println("\n*****************Operation 1: Adding Cars and Employees --------------");
			
			//EMPLOYEES operations :
			System.out.println("\nAdding employees :");
			
			/*
			employees.removeEmployeeById(0);
			employees.removeEmployeeById(1);
			employees.removeEmployeeById(2);
			*/
			
			//Adding employees
			employees.addEmployee("Skander", "Marnissi", "Skander.marnissi@gmail.com", "xxxx", 24);
			employees.addEmployee("Hosni", "Mosni", "Hosni.Mosni@gmail.com", "xxxx", 23);
			employees.addEmployee("Fairouz", "Farfour", "Fairouz.Farfour@gmail.com", "xxxx", 26);
			employees.addEmployee("Ala", "Mokni", "Mokni.ALA@gmail.com", "xxxx", 25);
			
			
			//Display employees
			System.out.println("\nDisplay employees :");
			displayEmployees(employees);
			
			//CARS operations :
			System.out.println("\nAdding Cars :");
			
			/*
			cars.removeCar(0);
			cars.removeCar(1);
			cars.removeCar(2);
			*/
			
			//Adding cars
			cars.addCar("volkswagen", "polo");
			cars.addCar("Mercedes", "E254");
			cars.addCar("BMW", "EGM2");
			
			//Display cars
			System.out.println("\nDisplay cars :");
			displayCars(cars);
			
			//RENTALS operations:
			System.out.println("\nAdding Rentals :");
			
			/*
			rentals.removeRental(0);
			rentals.removeRental(1);
			rentals.removeRental(2);
			*/
			
			System.out.println("\n*****************Operation 2: Adding Rentals --------------");
			//Adding rentals
			rentals.addRental(0, 0, "07/11/2020","07/11/2021");
			rentals.addRental(0, 1, "07/11/2020","07/12/2021");
			rentals.addRental(0, 2, "07/11/2020", "07/01/2022");
			rentals.addRental(1, 3, "07/11/2020", "07/11/2021");
			
			//Display rentals
			System.out.println("\nDisplay rentals :");
			displayRents(rentals);
			
			//Display cars
			System.out.println("\nDisplay cars :");
			displayCars(cars);
			
			//Display Waiting List
			System.out.println("\nDisplay waiting list :");
			displayWaitings(WaitingLists);
			
			
			System.out.println("\n*****************Operation 3: remove Rental --------------");
			
			
			//Setting requested dates: 
			WaitingLists.searchWaitingById(0).setRequest_date(formater.parse("08/11/2020"));
			WaitingLists.searchWaitingById(1).setRequest_date(formater.parse("09/11/2020"));
			
			//Remove rent:
			rentals.removeRental(0);
			
			//Display rentals
			System.out.println("\nDisplay rentals :");
			displayRents(rentals);
			
		
			//Display waitingList
			System.out.println("\nDisplay Waiting List :");
			displayWaitings(WaitingLists);
			
			//Display cars
			System.out.println("\nDisplay cars :");
			displayCars(cars);
			

			System.out.println("\n*-End method removeRentalsScenario-*");
			
			return true;
			
			
			
		}
		catch(Exception e) {
			System.out.println("An Error has occured during the test of removeRentalsScenario scenario stacktrace : "+e);
			e.printStackTrace();
		}
		
		System.out.println("\n*-End method removeRentalsScenario-*");
		return false;
	}
	
	//Removes a car while it's on rent; it will set the rent status to "CANCELED" and automatically remove all waitings with that same car in the waiting list if it exists
	public boolean removeCarWhileRentingScenario(IEmployManagement employees,ICarsManagement cars,IRentalsManagement rentals,IWaitingManagement WaitingLists,IFeedBackManagement feedBacks){
		
		System.out.println("\n*-Start method removeCarWhileRentingScenario-*");
		
		try {
			

			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			
			
			
			System.out.println("\n*****************Operation 1: Adding Cars and Employees --------------");
			
			//EMPLOYEES operations :
			System.out.println("\nAdding employees :");
			
			/*
			employees.removeEmployeeById(0);
			employees.removeEmployeeById(1);
			employees.removeEmployeeById(2);
			*/
			
			//Adding employees
			employees.addEmployee("Skander", "Marnissi", "Skander.marnissi@gmail.com", "xxxx", 24);
			employees.addEmployee("Hosni", "Mosni", "Hosni.Mosni@gmail.com", "xxxx", 23);
			employees.addEmployee("Fairouz", "Farfour", "Fairouz.Farfour@gmail.com", "xxxx", 26);
			employees.addEmployee("Ala", "Mokni", "Mokni.ALA@gmail.com", "xxxx", 25);
			
			
			//Display employees
			System.out.println("\nDisplay employees :");
			displayEmployees(employees);
			
			//CARS operations :
			System.out.println("\nAdding Cars :");
			
			/*
			cars.removeCar(0);
			cars.removeCar(1);
			cars.removeCar(2);
			*/
			
			//Adding cars
			cars.addCar("volkswagen", "polo");
			cars.addCar("Mercedes", "E254");
			cars.addCar("BMW", "EGM2");
			
			//Display cars
			System.out.println("\nDisplay cars :");
			displayCars(cars);
			
			//RENTALS operations:
			System.out.println("\nAdding Rentals :");
			
			/*
			rentals.removeRental(0);
			rentals.removeRental(1);
			rentals.removeRental(2);
			*/
			
			System.out.println("\n*****************Operation 2: Adding Rentals --------------");
			//Adding rentals
			rentals.addRental(0, 0, "07/11/2020","07/11/2021");
			rentals.addRental(0, 1, "07/11/2020","07/12/2021");
			rentals.addRental(0, 2, "07/11/2020", "07/01/2022");
			rentals.addRental(1, 3, "07/11/2020", "07/11/2021");
			
			//Display rentals
			System.out.println("\nDisplay rentals :");
			displayRents(rentals);
			
			//Display cars
			System.out.println("\nDisplay cars :");
			displayCars(cars);
			
			//Display Waiting List
			System.out.println("\nDisplay waiting list :");
			displayWaitings(WaitingLists);
			
			
			System.out.println("\n*****************Operation 3: Remove Car --------------");
			
			//Remove car:
			cars.removeCarById(0);
			
			//Display rentals
			System.out.println("\nDisplay rentals :");
			displayRents(rentals);
			
		
			//Display waitingList
			System.out.println("\nDisplay Waiting List :");
			displayWaitings(WaitingLists);
			
			//Display cars
			System.out.println("\nDisplay cars :");
			displayCars(cars);
			

			System.out.println("\n*-End method removeCarWhileRentingScenario-*");
			
			return true;
			
			
			
		}
		catch(Exception e) {
			System.out.println("An Error has occured during the test of intermediate scenario stacktrace : "+e);
			e.printStackTrace();
		}
		
		System.out.println("\n*-End method removeCarWhileRentingCarScenario-*");
		return false;
	}
	
	//After a rent achieve the end date it's status will be set to "FINISHED" and will automatically search for another pending rent for the same car in the waiting list if it exists 
	public boolean updateRentalsScenario(IEmployManagement employees,ICarsManagement cars,IRentalsManagement rentals,IWaitingManagement WaitingLists,IFeedBackManagement feedBacks) {
		
		System.out.println("\n*-Start method updateRentalsScenario-*");
		
		try {
			

			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			
			
			
			System.out.println("\n*****************Operation 1: Adding Cars and Employees --------------");
			
			//EMPLOYEES operations :
			System.out.println("\nAdding employees :");
			
			/*
			employees.removeEmployeeById(0);
			employees.removeEmployeeById(1);
			employees.removeEmployeeById(2);
			*/
			
			//Adding employees
			employees.addEmployee("Skander", "Marnissi", "Skander.marnissi@gmail.com", "xxxx", 24);
			employees.addEmployee("Hosni", "Mosni", "Hosni.Mosni@gmail.com", "xxxx", 23);
			employees.addEmployee("Fairouz", "Farfour", "Fairouz.Farfour@gmail.com", "xxxx", 26);
			employees.addEmployee("Ala", "Mokni", "Mokni.ALA@gmail.com", "xxxx", 25);
			
			
			//Display employees
			System.out.println("\nDisplay employees :");
			displayEmployees(employees);
			
			//CARS operations :
			System.out.println("\nAdding Cars :");
			
			/*
			cars.removeCar(0);
			cars.removeCar(1);
			cars.removeCar(2);
			*/
			
			//Adding cars

			cars.addCar(5,"WOLKSWAGEN", "polo", "SUV", "Best Car of the year", "bmw.png", "#ffe9", 2550, 12312325);
			cars.addCar(4,"WOLKSWAGEN", "golf", "SUV", "Best Car of the year", "bmw.png", "#ffe9", 2550, 12312359);
			cars.addCar(3,"BMW", "SERIE1", "SUV", "Best Car of the year", "bmw.png", "#ffe9", 3000, 12312354);
			
			/*Test */
			cars.addCar(3,"BMW", "SERIE2", "SUV", "Best Car of the year", "bmw.png", "#ffe9", 5000, 12312356);
			cars.addCar(4,"BMW", "SERIE3", "SUV", "Best Car of the year", "bmw.png", "#ffe9", 6000, 12312351);
			cars.addCar(9,"BMW", "SERIE4", "SUV", "Best Car of the year", "bmw.png", "#ffe9", 7000, 12312389);
			
			
			
			//Display cars
			System.out.println("\nDisplay cars :");
			displayCars(cars);
			
			//RENTALS operations:
			System.out.println("\nAdding Rentals :");
			
			/*
			rentals.removeRental(0);
			rentals.removeRental(1);
			rentals.removeRental(2);
			*/
			
			System.out.println("\n*****************Operation 2: Adding Rentals --------------");
			//Adding rentals
			rentals.addRental(0, 0, "07/11/2020","07/11/2021");
			rentals.addRental(0, 1, "07/11/2020","07/12/2021");
			rentals.addRental(0, 2, "07/11/2020", "07/01/2022");
			rentals.addRental(1, 3, "07/11/2020", "07/11/2021");
			
			/*Test 
			rentals.addRental(3, 2, "07/11/2020", "29/11/2020");
			rentals.addRental(4, 3, "07/11/2020", "29/11/2020");
			rentals.addRental(5, 4, "07/11/2020", "29/11/2020");
			
			rentals.searchRentById(2).setStatus("FINISHED");
			rentals.searchRentById(3).setStatus("FINISHED");;
			rentals.searchRentById(4).setStatus("FINISHED");;
			
			*/
			
			//Display rentals
			System.out.println("\nDisplay rentals :");
			displayRents(rentals);
			
			//Display cars
			System.out.println("\nDisplay cars :");
			displayCars(cars);
			
			//Display Waiting List
			System.out.println("\nDisplay waiting list :");
			displayWaitings(WaitingLists);
			
			
			feedBacks.addFeedBack(0, 0, 5, "Good Car");
			feedBacks.addFeedBack(0, 0, 3, "Huge Car");
			feedBacks.addFeedBack(0, 0, 2, "Bad Car");
			feedBacks.addFeedBack(0, 0, 4, "pretty Car");
			
			
			feedBacks.addFeedBack(1, 0, 5, "Good Car");
			feedBacks.addFeedBack(1, 0, 3, "Huge Car");
			feedBacks.addFeedBack(1, 0, 2, "Bad Car");
			feedBacks.addFeedBack(1, 0, 4, "pretty Car");
			
			
			feedBacks.addFeedBack(2, 0, 5, "Good Car");
			feedBacks.addFeedBack(2, 0, 3, "Huge Car");
			feedBacks.addFeedBack(2, 0, 2, "Bad Car");
			feedBacks.addFeedBack(2, 0, 4, "pretty Car");
			
			
			System.out.println("\n*****************Operation 3: Set Rental --------------");
			
			
			//Setting requested dates: 
			WaitingLists.searchWaitingById(0).setRequest_date(formater.parse("08/11/2020"));
			WaitingLists.searchWaitingById(1).setRequest_date(formater.parse("09/11/2020"));
			
			//Setting end date to out date:
			rentals.searchRentById(0).setEnd_date(formater.parse("10/11/2020"));
			rentals.updateRentals();
			
			//Display rentals
			System.out.println("\nDisplay rentals :");
			displayRents(rentals);
			
		
			//Display waitingList
			System.out.println("\nDisplay Waiting List :");
			displayWaitings(WaitingLists);
			
			//Display cars
			System.out.println("\nDisplay cars :");
			displayCars(cars);
			

	
			
			System.out.println("\n*****************Operation 4: Adding Feedbacks --------------");
			
			feedBacks.addFeedBack(0, 0, 5, "Good Car");
			feedBacks.addFeedBack(0, 0, 3, "Huge Car");
			feedBacks.addFeedBack(0, 0, 2, "Bad Car");
			feedBacks.addFeedBack(0, 0, 4, "pretty Car");
			
			
			feedBacks.addFeedBack(1, 0, 5, "Good Car");
			feedBacks.addFeedBack(1, 0, 3, "Huge Car");
			feedBacks.addFeedBack(1, 0, 2, "Bad Car");
			feedBacks.addFeedBack(1, 0, 4, "pretty Car");
			
			
			feedBacks.addFeedBack(2, 0, 5, "Good Car");
			feedBacks.addFeedBack(2, 0, 3, "Huge Car");
			feedBacks.addFeedBack(2, 0, 2, "Bad Car");
			feedBacks.addFeedBack(2, 0, 4, "pretty Car");
			
			
			
			
			System.out.println("\n*-End method updateRentalsScenario-*");
			
			return true;
			
			
			
		}
		catch(Exception e) {
			System.out.println("An Error has occured during the test of intermediate scenario stacktrace : "+e);
			e.printStackTrace();
		}
		
		System.out.println("\n*-End method updateRentalsScenario-*");
		return false;
	}
	
	//Removes an employee while his on rent; it will set the rent status to "CANCELED" and automatically remove all waitings with that same employee in the waiting list if it exists
	public boolean removeEmployeeWhileRentingCarScenario(IEmployManagement employees,ICarsManagement cars,IRentalsManagement rentals,IWaitingManagement WaitingLists,IFeedBackManagement feedBacks) {
		
		System.out.println("\n*-Start method RemoveEmployeeWhileRentingCar-*");
		
		try{
			
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			
			
			
			System.out.println("\n*****************Operation 1: Adding Cars and Employees --------------");
			
			//EMPLOYEES operations :
			System.out.println("\nAdding employees :");
			
			/*
			employees.removeEmployeeById(0);
			employees.removeEmployeeById(1);
			employees.removeEmployeeById(2);
			*/
			
			//Adding employees
			employees.addEmployee("Skander", "Marnissi", "Skander.marnissi@gmail.com", "xxxx", 24);
			employees.addEmployee("Hosni", "Mosni", "Hosni.Mosni@gmail.com", "xxxx", 23);
			employees.addEmployee("Fairouz", "Farfour", "Fairouz.Farfour@gmail.com", "xxxx", 26);
			
			//Display employees
			System.out.println("\nDisplay employees :");
			displayEmployees(employees);
			
			//CARS operations :
			System.out.println("\nAdding Cars :");
			
			/*
			cars.removeCar(0);
			cars.removeCar(1);
			cars.removeCar(2);
			*/
			
			//Adding cars
			cars.addCar("volkswagen", "polo");
			cars.addCar("Mercedes", "E254");
			cars.addCar("BMW", "EGM2");
			
			//Display cars
			System.out.println("\nDisplay cars :");
			displayCars(cars);
			
			//RENTALS operations:
			System.out.println("\nAdding Rentals :");
			
			/*
			rentals.removeRental(0);
			rentals.removeRental(1);
			rentals.removeRental(2);
			*/
			
			System.out.println("\n*****************Operation 2: Adding Rentals --------------");
			//Adding rentals
			rentals.addRental(0, 0, "07/11/2020","07/11/2021");
			rentals.addRental(0, 1, "07/11/2020","07/12/2021");
			rentals.addRental(0, 2, "07/11/2020", "07/01/2022");
			
			//Display rentals
			System.out.println("\nDisplay rentals :");
			displayRents(rentals);
			
			//Display cars
			System.out.println("\nDisplay cars :");
			displayCars(cars);
			
			//Setting requested Dates: 
			WaitingLists.searchWaitingById(0).setRequest_date(formater.parse("08/11/2020"));
			WaitingLists.searchWaitingById(1).setRequest_date(formater.parse("09/11/2020"));
			
			//Display waitingList
			System.out.println("\nDisplay Waiting List :");
			displayWaitings(WaitingLists);
			
			System.out.println("\n*****************Operation 3: Removing Employee : --------------");
			
			//Employee Operation
			System.out.println("\nRemoving Employee :");
			employees.removeEmployeeById(0);
			
			//Display waitingList
			System.out.println("\nDisplay Waiting List :");
			displayWaitings(WaitingLists);
			
			//Display rentals
			System.out.println("\nDisplay rentals :");
			displayRents(rentals);
			
			//Display employee
			System.out.println("\nDisplay employees :");
			displayEmployees(employees);
			
			//Display cars
			System.out.println("\nDisplay cars :");
			displayCars(cars);
			
			
			System.out.println("\n*-End method RemoveEmployeeWhileRentingCar-*");
			
			return true;
			
			
		}
		catch(Exception e) {
			System.out.println("An Error has occured during the advanced test scenario stacktrace : "+e);
			e.printStackTrace();
		}
		
		System.out.println("\n*-End method RemoveEmployeeWhileRentingCar-*");
		
		return false;
	}
	
	//Display methods : 
	
	public void displayRents(IRentalsManagement rentals) throws RemoteException{
		
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		
		if(!rentals.getRentals().isEmpty()) {
					
					for(IRental r :rentals.getRentals() ) {
						System.out.println("\n Rental id : "+r.getId()+"| Car id : "+ r.getCar_id()+"| Employee Id : "+r.getEmployee_id()+"| Start date : "+formater.format(r.getStart_date())+"| End date : "+formater.format(r.getEnd_date())+"| Status : "+r.getStatus());
						
					}
				}
				else {
					System.out.println("\n rentals is empty");	
					
				}
	
		
		
	}
	
	public void displayWaitings(IWaitingManagement waitingList) throws RemoteException {
		
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		
		if(!waitingList.getWaitingList().isEmpty()) {
			
			for(IWaiting w :waitingList.getWaitingList() ) {
				System.out.println("\n Waiting id : "+w.getId()+"| Car id : "+ w.getCar_id()+"| Employee Id : "+w.getEmployee_id()
				+"| available date : "+ formater.format(w.getDate_available())+"| request date : "+ formater.format(w.getRequest_date())
				+"| end date : "+formater.format(w.getEnd_date()));	
				
			}
		}
		else {
			System.out.println("\n WaitingList is empty");	
			
		}
	
	}
	
	public void displayCars(ICarsManagement cars) throws RemoteException  {
		if(!cars.getCars().isEmpty()) {
						
						for(ICar c :cars.getCars() ) {
							System.out.println("\n Car id : "+c.getId()+"| Model : "+ c.getModel()+"| Brand : "+c.getBrand()+"| Availability : "+c.getAvailability()+"| State : "+c.getState());
							
						}
					}
					else {
						System.out.println("\n Cars is empty");	
						
					}
		
}
	
	public void displayEmployees(IEmployManagement employees) throws RemoteException  {
		if(!employees.getEmployees().isEmpty()) {
						
						for(IEmploy e :employees.getEmployees() ) {
							System.out.println("\n Employee id : "+e.getId()+"| FirstName : "+ e.getFirstName() +"| LastName : "+e.getLastName()+"| age : "+e.getAge()
							+"| Email : "+e.getEmail() +"| Address : "+e.getAddress());
							
						}
					}
					else {
						System.out.println("\n Employees is empty");	
						
					}
		
}
	
	public void displayFeedBacks(IFeedBackManagement feedBacks) throws RemoteException  {
		if(!feedBacks.getFeedBacks().isEmpty()) {
						
						for(IFeedBack f :feedBacks.getFeedBacks() ) {
							System.out.println("\n FeedBack  id : "+f.getId()+" Description : "+ f.getDescription() +" Rating : "+f.getRating() +" Employee id : "+f.getEmployee_id() +" Car Id : "+f.getCar_id());
							
						}
					}
					else {
						System.out.println("\n FeedBacks is empty");	
						
					}
		
}
	
	
	
	public static void main(String[] args) throws RemoteException, MalformedURLException  {
		// TODO Auto-generated method stub

		try {
			
			ICarsManagement cars = (ICarsManagement) Naming.lookup("rmi://localhost:1100/CarsManagement");
			
			IFeedBackManagement feedBacks = (IFeedBackManagement) Naming.lookup("rmi://localhost:1100/FeedBackManagement");
			
			IRentalsManagement rentals = (IRentalsManagement) Naming.lookup("rmi://localhost:1100/RentalsManagement");
			
			IWaitingManagement WaitingLists = (IWaitingManagement) Naming.lookup("rmi://localhost:1100/WaitingManagement");
			
			IEmployManagement employees = (IEmployManagement) Naming.lookup("rmi://localhost:1099/EmployeeManagement");
			
			 
			Scenario sc = new Scenario();
			 
			// boolean removeEmployeeWhileRentingCarScenario = sc.removeEmployeeWhileRentingCarScenario(employees, cars, rentals, WaitingLists, feedBacks);
			 boolean updateRentalsScenario = sc.updateRentalsScenario(employees, cars, rentals, WaitingLists, feedBacks);
			// boolean removeCarWhileRentingScenario = sc.removeCarWhileRentingScenario(employees, cars, rentals, WaitingLists, feedBacks);
			 //boolean removeRentalScenario = sc.removeRentalScenario(employees, cars, rentals, WaitingLists, feedBacks);
				
			 
			 System.out.println("\n*-----------------------------*"
			 		+"\nScenario test result:"
			 		//+"\n removeRentalScenario : "+ removeRentalScenario);
			 		//+"\n removeCarWhileRentingScenario : "+ removeCarWhileRentingScenario);
			 		+"\n updateRentalsScenario : "+ updateRentalsScenario);
			 		//+"\n removeEmployeeWhileRentingCarScenario : "+ removeEmployeeWhileRentingCarScenario);
			 
			 sc.displayCars(cars);
			 }
		
			 catch (Exception e) {
				 
				 
				 System.out.println("An Error Has Occured while running the client for Scenario Tests stacktrace : " + e);
				 e.printStackTrace();
			 }
		

	}

}
