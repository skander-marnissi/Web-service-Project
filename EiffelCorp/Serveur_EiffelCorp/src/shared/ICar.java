package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ICar extends Remote{

	public int getId() throws RemoteException;

	public void setId(int id) throws RemoteException; 

	public String getBrand() throws RemoteException;

	public void setBrand(String brand) throws RemoteException;

	public String getModel() throws RemoteException;

	public void setModel(String model) throws RemoteException;
	
	public boolean getAvailability() throws RemoteException ;
	
	public void setAvailability(boolean availability) throws RemoteException;
	
	public int getPrice() throws RemoteException ;

	public void setPrice(int price)  throws RemoteException  ;

	public String getState() throws RemoteException  ;

	public void setState(String state) throws RemoteException ;

	public int getHorsePower() throws RemoteException ;

	public void setHorsePower(int horsePower) throws RemoteException;

	public String getType() throws RemoteException;

	public void setType(String type)  throws RemoteException;

	public String getDescription()  throws RemoteException ;

	public void setDescription(String description) throws RemoteException ;

	public String getImageUrl() throws RemoteException ;

	public void setImageUrl(String imageUrl) throws RemoteException ;

	public String getColor() throws RemoteException ;

	public void setColor(String color) throws RemoteException ;
	
	public int getRegistrationNumber()  throws RemoteException ;
	
	public void setRegistrationNumber(int registrationNumber)  throws RemoteException;
	
}
