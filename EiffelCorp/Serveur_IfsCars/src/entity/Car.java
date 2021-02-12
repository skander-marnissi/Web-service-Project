package entity;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import shared.ICar;

public class Car extends UnicastRemoteObject implements ICar {

	private int id,price,horsePower,registrationNumber;
	private String brand, model;
	private boolean availability;
	private String state;
	private String type,description,imageUrl,color;
	
	
	
	
	public Car() throws RemoteException  {
		super();
	}





	public Car(int id, String brand, String model) throws RemoteException {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.availability = true ;
		this.state="WORKING";
	}




	

	public Car(int id, int price, String brand, String model) throws RemoteException {
		super();
		this.id = id;
		this.price = price;
		this.brand = brand;
		this.model = model;
		this.availability = true ;
		this.state="WORKING";
	}









	public Car(int id, int price, int registrationNumber,int horsePower, String brand, String model, String type, String description,
			String imageUrl, String color) throws RemoteException {
		super();
		this.id = id;
		this.price = price;
		this.horsePower = horsePower;
		this.brand = brand;
		this.model = model;
		this.type = type;
		this.description = description;
		this.imageUrl = imageUrl;
		this.color = color;
		this.availability = true ;
		this.registrationNumber = registrationNumber;
		this.state="WORKING";
	}



	
	


	public int getRegistrationNumber()  throws RemoteException {
		return registrationNumber;
	}





	public void setRegistrationNumber(int registrationNumber)  throws RemoteException {
		this.registrationNumber = registrationNumber;
	}





	public int getId() throws RemoteException {
		return id;
	}





	public void setId(int id) throws RemoteException {
		this.id = id;
	}





	public int getHorsePower() throws RemoteException  {
		return horsePower;
	}





	public void setHorsePower(int horsePower) throws RemoteException  {
		this.horsePower = horsePower;
	}





	public String getType() throws RemoteException {
		return type;
	}





	public void setType(String type)  throws RemoteException {
		this.type = type;
	}





	public String getDescription()  throws RemoteException {
		return description;
	}





	public void setDescription(String description) throws RemoteException  {
		this.description = description;
	}





	public String getImageUrl() throws RemoteException  {
		return imageUrl;
	}





	public void setImageUrl(String imageUrl) throws RemoteException  {
		this.imageUrl = imageUrl;
	}





	public String getColor() throws RemoteException  {
		return color;
	}





	public void setColor(String color) throws RemoteException  {
		this.color = color;
	}





	public String getBrand() throws RemoteException {
		return brand;
	}





	public void setBrand(String brand) throws RemoteException {
		this.brand = brand;
	}





	public String getModel() throws RemoteException{
		return model;
	}





	public void setModel(String model) throws RemoteException {
		this.model = model;
	}





	public boolean getAvailability() throws RemoteException {
		return availability;
	}





	public void setAvailability(boolean availability) throws RemoteException {
		this.availability = availability;
	}

	public int getPrice() throws RemoteException  {
		return price;
	}





	public void setPrice(int price)  throws RemoteException  {
		this.price = price;
	}





	public String getState() throws RemoteException  {
		return state;
	}





	public void setState(String state) throws RemoteException  {
		this.state = state;
	}




	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", model=" + model + ", availability=" + availability + "]";
	} 
	
	
	
	
	
	
	
	
}
