package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IWaiting extends Remote {

	public int getId() throws RemoteException;
	
	public void setId(int id) throws RemoteException;
	
	public int getCar_id() throws RemoteException;
	
	public void setCar_id(int car_id) throws RemoteException;
	
	public int getEmployee_id() throws RemoteException;
	
	public void setEmployee_id(int employee_id) throws RemoteException;
	
	public Date getDate_available() throws RemoteException;
	
	public void setDate_available(Date date_available) throws RemoteException;
	
	public Date getRequest_date() throws RemoteException;
	
	public void setRequest_date(Date request_date) throws RemoteException;

	public Date getEnd_date() throws RemoteException;
	
	public void setEnd_date(Date end_date) throws RemoteException ;
}


