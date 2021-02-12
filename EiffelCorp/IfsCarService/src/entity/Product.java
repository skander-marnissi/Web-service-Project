package entity;

import java.rmi.RemoteException;
import java.util.List;

import shared.IFeedBack;




public class Product {


	private int id, horsePower;
	private String brand, model,type,description,imageUrl,color;
	
	private double price;
	
	
	

	public Product() throws RemoteException  {
		super();
	}




	public Product(int id, int horsePower, String brand, String model, String type, String description, String imageUrl,
			String color , double price) throws RemoteException {
		super();
		this.id = id;
		this.horsePower = horsePower;
		this.brand = brand;
		this.model = model;
		this.type = type;
		this.description = description;
		this.imageUrl = imageUrl;
		this.color = color;
		

		this.price = price;
	}







	public int getId() throws RemoteException {
		return id;
	}








	public void setId(int id) throws RemoteException {
		this.id = id;
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











	public double getPrice() throws RemoteException  {
		return price;
	}





	public void setPrice(double price)  throws RemoteException  {
		this.price = price;
	}







	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}




	
}
