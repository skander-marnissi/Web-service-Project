package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IRental extends Remote {


	public int getId()  throws RemoteException;

	public void setId(int id)  throws RemoteException;
	
	public int getCar_id()  throws RemoteException; 

	public void setCar_id(int car_id)  throws RemoteException;

	public int getEmployee_id()  throws RemoteException;
	
	public void setEmployee_id(int employee_id) throws RemoteException;


	public Date getStart_date()  throws RemoteException;

	public void setStart_date(Date start_date)   throws RemoteException;
	
	public Date getEnd_date()  throws RemoteException ;

	public void setEnd_date(Date end_date)  throws RemoteException;
	
	public String getStatus() throws RemoteException ;
	
	public void setStatus(String status)  throws RemoteException;
}
