package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface IRentalsManagement extends Remote {

	public boolean addRental(int car_id,int employee_id, String start_date, String end_date) throws RemoteException ;
	
	public boolean addRental(int car_id,int employee_id, Date start_date, Date end_date) throws RemoteException;
	
	public boolean removeRental(int id) throws RemoteException;
	
	public List<IRental> searchRentByEmployeeId(int employee_id) throws RemoteException;
	
	public List<IRental> searchRentByCarId(int car_id) throws RemoteException;
	
	public IRental searchRentById(int id) throws RemoteException;
	
	public void setRentals(List<IRental> rentals) throws RemoteException;
	
	public List<IRental> getRentals() throws RemoteException ;
	
	public IRental getRentInProcessByCarId(int car_id) throws RemoteException ;
	
	void updateRentals() throws RemoteException ;
	
	public IRental getRentInProcessByEmployeeId(int employee_id) throws RemoteException;

	public void updateRentAfterRemovingEmployee(int rent_id) throws RemoteException;
	
	public void updateRentAfterRemovingCar(int car_id) throws RemoteException;
	
	public List<IRental> getFinishedRentByEmployeeId(int employee_id) throws RemoteException;

	public Date getRentMaxEndDateByEmplpoyee(int employee_id) throws RemoteException ;
	
	public boolean checkIfEmployeeFeededBack(int employee_id)  throws RemoteException;
	
	public IRental getLastRentByEmployeeId(int employee_id) throws RemoteException;
}
