package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.List;





public interface ICarsManagement extends Remote {
	

	public boolean addCar(String brand, String model) throws RemoteException ;
	
	
	public List<ICar> searchCarByModel(String model) throws RemoteException ;
	
	public List<ICar> searchCarByBrand(String brand) throws RemoteException ;
	
	public ICar searchCarById(int id) throws RemoteException ;
	
	public boolean removeCarById(int id) throws RemoteException ;
	
	public List<ICar> getCars() throws RemoteException ;
	
	public void setCars(List<ICar> cars)  throws RemoteException;
	
	public boolean addCar(String brand, String model, int price) throws RemoteException;
	
	public List<ICar> getAllRentedCarsAtLeastOneTime() throws RemoteException;
	
	
	public boolean addCar(int horsePower,String brand, String model, String type,String description,String imageUrl,String color,int price,int registrationNumber) throws RemoteException;
		
}
