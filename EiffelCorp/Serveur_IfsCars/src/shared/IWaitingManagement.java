package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import entity.WaitingList;

public interface IWaitingManagement extends Remote {
	
	public boolean addWaiting(int car_id, int employee_id,Date date_available, String end_date) throws RemoteException ;
	
	public boolean addWaiting(int car_id, int employee_id,Date date_available, Date end_date) throws RemoteException ;
	
	
	public boolean addWaiting(int car_id, int employee_id) throws RemoteException ;
	
	public List<IWaiting> searchWaitingByCarId(int car_id) throws RemoteException ;
	
	public List<IWaiting> searchWaitingByEmployeeId(int employee_id) throws RemoteException;
	
	public IWaiting searchWaitingById(int id) throws RemoteException;
	
	public boolean removeWaiting(int id) throws RemoteException;	

	public void setWaitingList(List<IWaiting> waitingList) throws RemoteException;
	
	public List<IWaiting> getWaitingList() throws RemoteException;
	
	public Date getMaxEndDateWaitingCarId(int car_id) throws RemoteException;
	
	 IWaiting getLastWaitingInProcessByCarId(int car_id) throws RemoteException;
	 
	 public boolean updateWaitingList() throws RemoteException;
	 
	 public int compareDates(Date date1, Date date2) throws RemoteException;
	 
	 public boolean removeWaitingByEmployeeId(int employee_id) throws RemoteException;
	
	 public IWaiting getNextRent(int car_id, int rent_id) throws RemoteException ;
	 
	 public Date getMinDateRequest(List<IWaiting> waitingList) throws RemoteException ;
	 
	 public IWaiting searchWaitingByRequestDate(Date request_date, List<IWaiting> waitingList) throws RemoteException;
	 
	 public boolean checkIfWaitingExist(int car_id) throws RemoteException;
	 
	 public boolean removeWaitingByCarId(int car_id) throws RemoteException;
}
