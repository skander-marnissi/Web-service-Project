package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import entity.Cart;
import entity.FeedBack;
import entity.Product;
import shared.ICar;
import shared.ICarsManagement;
import shared.IEmploy;
import shared.IEmployManagement;
import shared.IFeedBack;
import shared.IFeedBackManagement;
import webservice.BankWebServiceImpl;
import webservice.BankWebServiceImplServiceLocator;
import webservice.BankWebServiceImplSoapBindingStub;


public class ProductService {

	
	//List<Product> products;
	
	List<Product> products;
	
	List<FeedBack> p_feedBacks;
	
	List<Cart> carts;
	
	ICarsManagement Cars;
	
	IFeedBackManagement feedBacks;
	
	IEmployManagement employees;
	
		
	public ProductService()  {
		super();
		
		
		this.products = new ArrayList<Product>();
		
		this.carts = new ArrayList<Cart>();
		
		this.p_feedBacks= new ArrayList<FeedBack>();
	
	}

	//Convert a given amount from a currency to another one	
	public double convertCurrency(String currencyFrom,String currencyTo,String amount) {
			
		BufferedReader reader;
		String line;
		
		SimpleDateFormat sdf ;
		StringBuffer responseContent = new StringBuffer();
		
	
		try {
			
			 sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			
			String convertedDate = sdf.format(new Date());
	
			
			System.out.println("[convertCurrency ProductController function] Converted date : "+convertedDate);
			
			URL url = new URL("http://currencyconverter.kowabunga.net/converter.asmx/GetConversionAmount?CurrencyFrom="+currencyFrom+"&CurrencyTo="+currencyTo+"&RateDate="+convertedDate+"&Amount="+amount);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			 
			// Get Request Setup
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			
			//Request Status
			int status = connection.getResponseCode();
			
			if(status>299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while((line=reader.readLine())!=null) {
					responseContent.append(line);
				}
				reader.close();
			}else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line=reader.readLine())!=null) {
					responseContent.append(line);
				}
				reader.close();
			}
			
			 	//JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseContent.toString());
		
				System.out.println("[convertCurrency ProductController function] : First Response : XML FILE: " +responseContent.toString());
				 
				
				org.json.JSONObject xmlJSONObj = org.json.XML.toJSONObject(responseContent.toString());
				
				String result = (String) xmlJSONObj.getJSONObject("decimal").get("content").toString();
		
				  if(Double.valueOf(result) ==0.0f) {
				    	
			    	   StringTokenizer st = new StringTokenizer(convertedDate,"/");
			    	   List<String> date = new ArrayList<String>();
			    	   
			    	   String frag ;
			    	   
			    	   responseContent = new StringBuffer();
			    	   
			    	   while (st.hasMoreTokens()) {
			    	         frag= st.nextToken();
			    	         date.add(frag);
			    	     }
			    	     
			    	     if(date.get(1).equals("01")) {
			    	    	 date.set(1, "28");
			    	     }
			    	     
			    	     convertedDate = date.get(0)+"/"+(Integer.valueOf(date.get(1))-1)+"/"+date.get(2);
			    	     
			    	     System.out.println("[convertCurrency BankService function] : Second Response  converted date: "+convertedDate);
						    
			    	     
					URL url2 = new URL("http://currencyconverter.kowabunga.net/converter.asmx/GetConversionAmount?CurrencyFrom="+currencyFrom+"&CurrencyTo="+currencyTo+"&RateDate="+convertedDate+"&Amount="+amount);
					
					connection = (HttpURLConnection) url2.openConnection();
					 
					// Get Request Setup
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
			    	
			    	status = connection.getResponseCode();
					
					if(status>299) {
						reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
						while((line=reader.readLine())!=null) {
							responseContent.append(line);
						}
						reader.close();
					}else {
						reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						while((line=reader.readLine())!=null) {
							responseContent.append(line);
						}
						reader.close();
					}
					

					System.out.println("[convertCurrency BankService function] : Second Response : XML FILE: " +responseContent.toString());
					 
					
					
					org.json.JSONObject xmlJSONObj2 = org.json.XML.toJSONObject(responseContent.toString());
					
					 result = (String) xmlJSONObj2.getJSONObject("decimal").get("content").toString();
				 
				    System.out.println("[convertCurrency BankService function] : Second Response :"+result);
				    
					
					return Double.valueOf(result);
			    	
			    	
			    }
			    
			    System.out.println("[convertCurrency BankService function] : First Response :"+result);
			    
			    return Double.valueOf(result);
		
		
	}
	catch(Exception e) {
		
		System.out.println("[convertCurrency ProductController function] : An Error has occured during the convertCurrencyProcess stacktrace : "+e);
		e.printStackTrace();
		
	}
	
	return 0;
	
	}

	public boolean checkOut(int iban, int price,String ownerFirstName, String ownerLastName){
		
	try {	
		BankWebServiceImpl bankService = new BankWebServiceImplServiceLocator().getBankWebServiceImpl();
		
		((BankWebServiceImplSoapBindingStub) bankService).setMaintainSession(true);
		
		boolean result = bankService.retrieveBalanceToAccount(price, iban,ownerFirstName,ownerLastName);
		
		
		return result;
		
	}
	catch(Exception e) {
		System.out.println("[checkOut fucntion in productService] An Error has occured during checkout process process in product service stacktrace : "+e);
		e.printStackTrace();
	
	}
	
	return false;
	}
	
	public boolean setProductToSold(int product_id) {

	try{
		
		Cars = (ICarsManagement) Naming.lookup("rmi://localhost:1100/CarsManagement");
		
		Cars.searchCarById(product_id).setState("SOLD");
		
		return true;
	}
	catch(Exception e) {
		System.out.println("[setProductToSold in productService function] : An Error has occured during set product to sold process in product service stacktrace : "+e);
		e.printStackTrace();
	}
	return false;
	
	
	}
	
	public int getProductRating(int product_id) {

	try {
			List<FeedBack> feedbacks = 	this.getFeedBacks(product_id);
			
			int average = 0;
			for(FeedBack f: feedbacks) {
				
				average += f.getRating(); 
			}
			
			return average/feedbacks.size();
	}
	catch(Exception e) {
		System.out.println("[getProductRating fucntion in productService] An Error has occured during get product rating process in product service stacktrace : "+e);
		e.printStackTrace();
	}
	return 0;
}
	
	public boolean removeProductFromCart(int cart_id) {
		
		try {
			
			
			for(Cart c : this.carts) {
				if(c.getId()==cart_id) {
					this.carts.remove(c);
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			System.out.println("[removeProductFromCart fucntion in productService] An Error has occured during remove from cart byt cart Id process in product service stacktrace : "+e);
			e.printStackTrace();
		}
		return false;
		
	}
	
	public List<FeedBack> getFeedBacks(int product_id) {
		
		try{
			
			this.p_feedBacks.clear();
			
			feedBacks = (IFeedBackManagement) Naming.lookup("rmi://localhost:1100/FeedBackManagement");
			
			employees = (IEmployManagement) Naming.lookup("rmi://localhost:1099/EmployeeManagement");
			
			
			List<IFeedBack> carFeedBack= feedBacks.searchFeedBackByCarId(product_id);
			
		
			if(!carFeedBack.isEmpty()) {
				for(IFeedBack f : feedBacks.searchFeedBackByCarId(product_id)) {
					
					IEmploy  emp = employees.searchEmployeeById(f.getEmployee_id());
					
					this.p_feedBacks.add(new FeedBack(f.getId(),f.getCar_id(),f.getEmployee_id(),f.getRating(),f.getDescription(),emp.getFirstName(),emp.getLastName()));
				
				}
			}
			
		
				return this.p_feedBacks;
		}
		catch(Exception e) {
			System.out.println("An Error has occured during get feed backs by product id process in product service stacktrace : "+e);
			e.printStackTrace();
		}
		return null;
	
		
	}

	public List<Product> getProducts()  {
		
		try {
			
			this.products.clear();
			
			Cars = (ICarsManagement) Naming.lookup("rmi://localhost:1100/CarsManagement");
			
			//Cars.getAllRentedCarsAtLeastOneTime();
			
			for(ICar c : Cars.getAllRentedCarsAtLeastOneTime()) {		

				this.products.add(new Product(c.getId(),c.getHorsePower(),c.getBrand(),c.getModel(),c.getType()
						,c.getDescription(),c.getImageUrl(),c.getColor(),c.getPrice()));
				System.out.println("[getProducts in productService function] : cars :"+c.getState());
				
				
			}
			
			return this.products;
		}
		catch(Exception e) {
			System.out.println("[getProducts in productService function] : An Error has occured during get products process in product service stacktrace : "+e);
			e.printStackTrace();
		}
		return null;
		
	}

	public List<Cart> getCarts() {
		
		try {
			
			return this.carts;
		}
		catch(Exception e) {
			System.out.println("An Error has occured during get cart process in product service stacktrace : "+e);
			e.printStackTrace();
		}
		return null;
		
	}
	
	public boolean addToCart(int product_id) throws RemoteException{
		
		try {
			
			Product p = searchProductById(product_id);
			
		if(p!=null) {
			
			if(this.carts.isEmpty()){
				
				this.carts.add(new Cart(0,p.getId(),p.getPrice(),1));
				
				return true;
			}
			else{
				
				Cart c = searchCartByProductId(p.getId());
				if(c!=null) {
					//c.setQuantity(c.getQuantity()+1);
					return true;
				}
				this.carts.add(new Cart(this.carts.get(this.carts.size()-1).getId()+1,p.getId(),p.getPrice(),1));
				return true;
			}
		}
			return false;
	
		}catch(Exception e) {
			System.out.println("An Error has occured during add to cart process in product service stacktrace : "+e);
			e.printStackTrace();
		}
		return false;
		
		
	}
	
	public boolean removeFromCartByProductId(int product_id) {
		
		try {
			
			Cart c = searchCartByProductId(product_id);
			if(c!=null) {
				this.carts.remove(c);
				return true;
			}
		
			return false;
		}
		catch(Exception e) {
			System.out.println("[removeFromCartByProductId function] : An Error has occured during remove from cart by product Id process in product service stacktrace : "+e);
			e.printStackTrace();
		}
		return false;
		
	}
	
	public int getTotalCartAmount() {
		
		try {
			int amount = 0;
			
			for(Cart c : this.carts) {
				amount+=c.getPrice()*c.getQuantity();
			}
			
			return amount;
		}
		catch(Exception e) {
			System.out.println("[getTotalCartAmount function in productService] An Error has occured during the get total cart amount function product service stacktrace : "+e);
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public Product searchProductById(int product_id) {
		
		try {
			for(Product p : this.products) {
				if(p.getId()==product_id) {
					return p;
				}
			}
		}
		catch(Exception e) {
			System.out.println("An Error has occured during the research product by id process in product service stacktrace : "+e);
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public Cart searchCartByProductId(int product_id) {
		try {
			for(Cart c : this.carts) {
				if(c.getProduct_id()==product_id) {
					return c;
				}
			}
		}
		catch(Exception e) {
			System.out.println("An Error has occured during the research cart by id process in product service stacktrace : "+e);
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public Cart searchCartById(int cart_id) {
			
		try {
			for(Cart c : this.carts) {
				if(c.getId()==cart_id) {
					return c;
					}
				}
			
			}catch(Exception e) {
				System.out.println("An Error has occured during the research cart by id process in product service stacktrace : "+e);
				e.printStackTrace();
			}
			return null;
		}
		
	public double getCurrencyRate(String currency) {

			
			BufferedReader reader;
			String line;
			
			SimpleDateFormat sdf ;
			StringBuffer responseContent = new StringBuffer();
			

			try {
				
				 sdf = new SimpleDateFormat("MM/dd/yyyy");
				
				
				String convertedDate = sdf.format(new Date());

				
				System.out.println("[getCurrencyRate ProductService function] Converted date : "+convertedDate);
				
				URL url = new URL("http://currencyconverter.kowabunga.net/converter.asmx/GetCurrencyRate?Currency="+currency+"&RateDate="+convertedDate);
			
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				 
				// Get Request Setup
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				
				//Request Status
				int status = connection.getResponseCode();
				
				if(status>299) {
					reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
					while((line=reader.readLine())!=null) {
						responseContent.append(line);
					}
					reader.close();
				}else {
					reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					while((line=reader.readLine())!=null) {
						responseContent.append(line);
					}
					reader.close();
				}
				
				 	//JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseContent.toString());
			
					System.out.println("[getCurrencyRate function in ProductService] : First Response : XML FILE: " +responseContent.toString());
					 
					
					org.json.JSONObject xmlJSONObj = org.json.XML.toJSONObject(responseContent.toString());
					
					String result = (String) xmlJSONObj.getJSONObject("decimal").get("content").toString();
				 
				    System.out.println("[convertCurrency ProductController function] : Response :"+result);
				  
				    return Double.valueOf(result);
				
				
			}
			catch(Exception e) {
				
				System.out.println("[getCurrencyRate function in ProductService] : An Error has occured during the getCurrencyRate stacktrace : "+e);
				e.printStackTrace();
				
			}
			
			return 0;
	
	}


	public void clearCart() {
		
		this.carts.clear();
		
	}
	
	
	
}
