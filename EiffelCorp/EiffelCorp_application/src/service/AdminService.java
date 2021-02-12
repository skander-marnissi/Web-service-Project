package service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import shared.ICar;
import shared.ICarsManagement;
import shared.IEmploy;
import shared.IEmployManagement;
import shared.IFeedBack;
import shared.IFeedBackManagement;
import shared.IRental;
import shared.IRentalsManagement;
import shared.IUser;
import shared.IWaiting;
import shared.IWaitingManagement;

public class AdminService {

	private List<ICar> cars;
	
	private List<IFeedBack> feedBacks;
	
	private List<IRental> rentals;
	
	private List<IEmploy> employees;
	
	private List<IWaiting> waitingList;
	
	private List<IUser> users;
	
	private ICarsManagement carsManager;
	
	private IFeedBackManagement feedBacksManager;
	
	private IRentalsManagement  rentalsManager ;
	
	private IEmployManagement  employeeManager;
	
	private IWaitingManagement waitingListManager;
	
	public AdminService() {
	try {
			
			 this.carsManager = (ICarsManagement) Naming.lookup("rmi://localhost:1100/CarsManagement");

			 this.feedBacksManager = (IFeedBackManagement) Naming.lookup("rmi://localhost:1100/FeedBackManagement");
			
			 this.rentalsManager = (IRentalsManagement) Naming.lookup("rmi://localhost:1100/RentalsManagement");
			
			 this.waitingListManager = (IWaitingManagement) Naming.lookup("rmi://localhost:1100/WaitingManagement");
			
			 this.employeeManager = (IEmployManagement) Naming.lookup("rmi://localhost:1099/EmployeeManagement");

			 this.cars = new CopyOnWriteArrayList<ICar>();
			 this.feedBacks = new CopyOnWriteArrayList<IFeedBack>();
			 this.employees = new CopyOnWriteArrayList<IEmploy>();
			 this.rentals = new CopyOnWriteArrayList<IRental>();
			 this.waitingList = new CopyOnWriteArrayList<IWaiting>();
			 this.users =  new CopyOnWriteArrayList<IUser>();
			 
		} catch (MalformedURLException e) {
			
			System.out.println("[AdminService constructor] : Malformed URL please check the url that you gave to connect to the RMI servers");
			e.printStackTrace();
		} catch (RemoteException e) {
			
			System.out.println("[AdminService constructor] : Remote Exception please check if the interfaces are implemented in your project");
			e.printStackTrace();
		} catch (NotBoundException e) {
			
			System.out.println("[AdminService constructor] : Not Bound Exception please check if the RMI servers are running");
			e.printStackTrace();
		}
		

	}
	
	
	public boolean addCar(int horsePower, String brand, String model, String type, String description, String imageUrl, String color, int price, int registrationNumber) {
		try {
			
			return this.carsManager.addCar(horsePower, brand, model, type, description, imageUrl, color, price,registrationNumber);
		
		} catch (RemoteException e) {
			System.out.println("[addCar function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addEmployee(String firstName, String lastName, String email, String address, int age, char gender, int phoneNumber) {
		try {
			
			return this.employeeManager.addEmployee(firstName, lastName, email, address, age, gender, phoneNumber);
		
		} catch (RemoteException e) {
			System.out.println("[addEmployee function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addFeedBack(int car_id, int employee_id, int rating, String description) {
		try {
			
			return this.feedBacksManager.addFeedBack(car_id, employee_id, rating, description);
		
		} catch (RemoteException e) {
			System.out.println("[addFeedBack function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addRental(int car_id,int employee_id, Date start_date, Date end_date) {
		try {
			
			return this.rentalsManager.addRental(car_id, employee_id, start_date, end_date);
		
		} catch (RemoteException e) {
			System.out.println("[addRental function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return false;
	}
	
	
	

	public IUser searchUserByLoginMdp(String login, String password) {
		try {
			
			return this.employeeManager.searchUserLoginMdp(login, password);
			
		} catch (RemoteException e) {
			System.out.println("[searchUserByLoginMdp function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return null;
	}
	
	public ICar searchCarById(int car_id) {
		try {
			
			return this.carsManager.searchCarById(car_id);
			
		} catch (RemoteException e) {
			System.out.println("[searchCarById function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<IFeedBack> searchFeedBackByEmployeeId(int employee_id) {
	try {
				
				return this.feedBacksManager.searchFeedBackByEmployeeId(employee_id);
				
			} catch (RemoteException e) {
				System.out.println("[searchFeedBackByEmployeeId function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
				e.printStackTrace();
			}
			return null;
	}

	public IEmploy searchEmployeeById(int employee_id) {
	
		try {
				
				return this.employeeManager.searchEmployeeById(employee_id);
				
			} catch (RemoteException e) {
				System.out.println("[searchEmployeeById function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
				e.printStackTrace();
			}
			return null;
	}

	public IRental searchRentById(int rent_id) {
		try {
			
			return this.rentalsManager.searchRentById(rent_id);
			
		} catch (RemoteException e) {
			System.out.println("[searchRentById function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return null;
	}
	
	public IFeedBack searchFeedBackById(int feedback_id) {
		try {
			
			return this.feedBacksManager.searchFeedBackById(feedback_id);
			
		} catch (RemoteException e) {
			System.out.println("[searchFeedBackById function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return null;
		}
	
	public IWaiting searchWaitingById(int waiting_id) {
		try {
			
			return this.waitingListManager.searchWaitingById(waiting_id);
			
		} catch (RemoteException e) {
			System.out.println("[searchWaitingById function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<IRental> searchRentByEmployeeId(int employee_id) {
			
			try {
				return this.rentalsManager.searchRentByEmployeeId(employee_id);
			} catch (RemoteException e) {
				System.out.println("[searchRentByEmployeeId function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
				e.printStackTrace();
			}
			return null;
			
		}
	
	public boolean checkForFeedBack(int employee_id) {
		try {
			
			return this.rentalsManager.checkIfEmployeeFeededBack(employee_id);
		} 
		catch (RemoteException e) {
			System.out.println("[searchRentByEmployeeId function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return false;
	}

	
	public boolean removeCar(int id) {
		try {
			
			return this.carsManager.removeCarById(id);
			
		} catch (RemoteException e) {
			System.out.println("[removeCar function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeFeedBack(int id) {
		try {
			
			return this.feedBacksManager.removeFeedBack(id);
			
		} catch (RemoteException e) {
			System.out.println("[removeFeedBack function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeEmployee(int id) {
		try {
			
			return this.employeeManager.removeEmployeeById(id);
			
		} catch (RemoteException e) {
			System.out.println("[removeEmployee function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeRental(int id) {
		try {
			
			return this.rentalsManager.removeRental(id);
			
		} catch (RemoteException e) {
			System.out.println("[removeRental function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeWaiting(int id) {
		try {
			
			return this.waitingListManager.removeWaiting(id);
			
		} catch (RemoteException e) {
			System.out.println("[removeWaitingList function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		return false;
	}
	
	public void updateRentals() {
		try {
			
			this.rentalsManager.updateRentals();
			
		} catch (RemoteException e) {
			System.out.println("[updateRentals function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		
	}

	public List<ICar> getCars() {
		
		try {
			
			this.cars.clear();
			
			for(ICar c : this.carsManager.getCars()){
					
				this.cars.add(c);
			}
		} 
		catch (RemoteException e) {
			System.out.println("[getCars function in AdminService] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		
		return this.cars;
	
	}

	public List<IFeedBack> getFeedBacks() {
		
		try {
			
			this.feedBacks.clear();
			
			for(IFeedBack f : this.feedBacksManager.getFeedBacks()){
				this.feedBacks.add(f);
			}
		} 
		catch (RemoteException e) {
			System.out.println("[getFeedBacks function in AdminService] : Remote Exception please check if the interfaces are well implemented ");
			e.printStackTrace();
		}
		
		return this.feedBacks;
	}

	public List<IRental> getRentals() {
	
		try {
			
			this.rentals.clear();
			
			for(IRental r : this.rentalsManager.getRentals()){
				this.rentals.add(r);
			}
		} 
		catch (RemoteException e) {
			System.out.println("[getRentals function in AdminService] : Remote Exception please check if the interfaces are well implemented ");
			e.printStackTrace();
		}
		
		return this.rentals;
	}

	public List<IEmploy> getEmployees() {
	
		try {
				
				this.employees.clear();
				
				for(IEmploy e : this.employeeManager.getEmployees()){
					this.employees.add(e);
				}
			} 
			catch (RemoteException e) {
				System.out.println("[getEmployees function in AdminService] : Remote Exception please check if the interfaces are well implemented ");
				e.printStackTrace();
			}
			
			return this.employees;
		}

	public List<IWaiting> getWaitingList() {
		
		try {
			
			this.waitingList.clear();
			
			for(IWaiting w : this.waitingListManager.getWaitingList()){
				this.waitingList.add(w);
			}
		} 
		catch (RemoteException e) {
			System.out.println("[getWaitingList function in AdminService] : Remote Exception please check if the interfaces are well implemented ");
			e.printStackTrace();
		}
		
		return this.waitingList;
	}

	public IRental getLastRentByEmployeeId(int userId) {
		try {
			
			 return this.rentalsManager.getLastRentByEmployeeId(userId);
			}
		
		catch (Exception e) {
			System.out.println("[getLastRentByEmployeeId function in AdminService] : Exception please check if the interfaces are well implemented ");
			e.printStackTrace();
		}
		
		return null;
	}


	
	public List<IUser> getUsers() {
		try {
			
			this.users.clear();
			
			for(IUser u : this.employeeManager.getUsers()){
				this.users.add(u);
			}
		} 
		catch (RemoteException e) {
			System.out.println("[getUsers function in AdminService] : Remote Exception please check if the interfaces are well implemented ");
			e.printStackTrace();
		}
		
		return this.users;
	}

	
	
	public void setCars(List<ICar> cars) {
		this.cars = cars;
	}

	public void setRentals(List<IRental> rentals) {
		this.rentals = rentals;
	}

	public void setFeedBacks(List<IFeedBack> feedBacks) {
		this.feedBacks = feedBacks;
	}

	public void setEmployees(List<IEmploy> employess) {
		this.employees = employees;
	}

	public void setWaitingList(List<IWaiting> waitingList) {
		this.waitingList = waitingList;
	}

	

	public ICarsManagement getCarsManager() {
		return carsManager;
	}

	public IRentalsManagement getRentalsManager() {
		return rentalsManager;
	}

	public IFeedBackManagement getFeedBacksManager() {
		return feedBacksManager;
	}

	public IEmployManagement getEmployeeManager() {
		return employeeManager;
	}
	
	public IWaitingManagement getWaitingListManager() {
		return waitingListManager;
	}


	public void setCarsManager(ICarsManagement carsManager) {
		this.carsManager = carsManager;
	}

	public void setFeedBacksManager(IFeedBackManagement feedBacksManager) {
		this.feedBacksManager = feedBacksManager;
	}
	
	public void setRentalsManager(IRentalsManagement rentalsManager) {
		this.rentalsManager = rentalsManager;
	}

	public void setEmployeeManager(IEmployManagement employeeManager) {
		this.employeeManager = employeeManager;
	}

	public void setWaitingListManager(IWaitingManagement waitingListManager) {
		this.waitingListManager = waitingListManager;
	}




	

	


	







}
