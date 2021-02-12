package entity;

import java.rmi.RemoteException;

public class FeedBack {

	int id, car_id, employee_id, rating;
	
	String description, employeeFirstName, employeeLastName;

	
	
	
	
	public FeedBack() throws RemoteException {
		super();
	}





	public FeedBack(int id, int car_id, int employee_id, int rating, String description) throws RemoteException {
		super();
		this.id = id;
		this.car_id = car_id;
		this.employee_id = employee_id;
		this.rating = rating;
		this.description = description;
	}





	public FeedBack(int id, int car_id, int employee_id, int rating, String description, String employeeFirstName,
			String employeeLastName) {
		super();
		this.id = id;
		this.car_id = car_id;
		this.employee_id = employee_id;
		this.rating = rating;
		this.description = description;
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
	}





	public int getId() throws RemoteException {
		return id;
	}





	public void setId(int id) throws RemoteException {
		this.id = id;
	}





	public int getCar_id() throws RemoteException {
		return car_id;
	}





	public void setCar_id(int car_id) throws RemoteException {
		this.car_id = car_id;
	}





	public int getEmployee_id() throws RemoteException {
		return employee_id;
	}





	public void setEmployee_id(int employee_id) throws RemoteException {
		this.employee_id = employee_id;
	}





	public int getRating() throws RemoteException {
		return rating;
	}





	public void setRating(int rating) throws RemoteException {
		this.rating = rating;
	}





	public String getDescription() throws RemoteException {
		return description;
	}





	public void setDescription(String description) throws RemoteException {
		this.description = description;
	}





	public String getEmployeeFirstName() {
		return employeeFirstName;
	}





	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}





	public String getEmployeeLastName() {
		return employeeLastName;
	}





	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}





	



}
