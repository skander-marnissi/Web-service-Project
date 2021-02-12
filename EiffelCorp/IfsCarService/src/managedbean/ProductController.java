package managedbean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import com.lavasoft.GeoIPServiceLocator;
import com.lavasoft.GeoIPServiceSoap;
import com.neovisionaries.i18n.*;

import entity.*;
import service.ProductService;

@ManagedBean(name ="ProductController") 
@SessionScoped
public class ProductController {

	ProductService productService = new ProductService();
	
	private String firstName, LastName, email, address, country, state , zip, iban, ownerLastName, ownerFirstName, ip, ipCountry, currency, symbol;
	private double rate;
	private Product product;
	private int product_id;

	@PostConstruct
	public void init() {
		
		//ProductController.getRemoteAddress((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
		
		
		
		this.ip = this.getClientIp();
		
		this.ipCountry = this.getClientCountryByIp();
	
		List<String> currencySymbol = this.getCountryCurrencySymbol(ipCountry);
		
		this.currency = currencySymbol.get(0);
		
		this.symbol = currencySymbol.get(1);
		
		this.rate = this.productService.getCurrencyRate(currency);
		
		if(this.rate==0)
			this.rate=1;
		
		System.out.println("[MAIN] : Your IP is : "+ip+"| Your Country is : "+
				ipCountry +"| Your Currency is : "+currency+"| Your Currency Symbol is : "+symbol+"| Currency rate : "+rate);
		
	}
	
	
	
	
	public List<Product> getAllSUVCars(){
		
		System.out.println("[getAllSUVCars  function in ProductController ] : I'm in getAllSUVCars ");
		
		List<Product> carsSUV = new CopyOnWriteArrayList<Product>();
		
		for(Product p : this.productService.getProducts()) {
				if(p.getType().equals("SUV")) {
					carsSUV.add(p);
				}
			
		}
		
		return carsSUV;
		
	}
	
	public List<Product> getAllPICKUPCars(){
		
		System.out.println("[getAllSUVCars  function in ProductController ] : I'm in getAllPICKUPCars ");
		
		List<Product> carsPICKUP = new CopyOnWriteArrayList<Product>();
		
		for(Product p : this.productService.getProducts()) {
				if(p.getType().equals("PICKUP")) {
					carsPICKUP.add(p);
				}
			
		}
		
		return carsPICKUP;
	}
	
	public List<Product> getAllVANCars(){
		
		System.out.println("[getAllVANCars  function in ProductController ] : I'm in getAllVANCars ");
		
		List<Product> carsVAN = new CopyOnWriteArrayList<Product>();
		
		for(Product p : this.productService.getProducts()) {
				if(p.getType().equals("VAN")) {
					carsVAN.add(p);
				}
			
		}
		
		return carsVAN;
	}
	
	public List<Product> getAllTRUCKCars(){
		
		System.out.println("[getAllTRUCKCars  function in ProductController ] : I'm in getAllTRUCKCars ");
		
		List<Product> carsTRUCK = new CopyOnWriteArrayList<Product>();
		
		for(Product p : this.productService.getProducts()) {
				if(p.getType().equals("TRUCK")) {
					carsTRUCK.add(p);
				}
			
		}
		
		return carsTRUCK;
	}
	
	public List<Product> getProducts(){
		
		System.out.println("[getProducts  function in ProductController ] : I'm in getProduct ");
		
		return this.productService.getProducts();
		
		
	}
	
	public List<FeedBack> getProductFeedBack(int product_id){
		return this.productService.getFeedBacks(product_id);
	}
	
	public List<Cart> getCart(){
		return this.productService.getCarts();
	}
	
	public void checkout() {
		try {
			
			boolean result = this.productService.checkOut(Integer.valueOf(iban), getTotalCartAmount(), ownerFirstName, ownerLastName);
			System.out.println("[checkout in product controller function] : Chekout operation result : "+ result);
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			
			if(result) {
				for(Cart c : this.getCart()) 
					this.productService.setProductToSold(c.getProduct_id());
				
				
				this.productService.clearCart();
				ec.redirect(ec.getRequestContextPath() + "/success.xhtml");
				
			}
			else {
				ec.redirect(ec.getRequestContextPath() + "/fail.xhtml");
			}
			
		} 
		catch (Exception e) {
			
			System.out.println("[checkout in product controller function] : An Error has occured during the checkout process stacktrace : "+e);
			e.printStackTrace();
		}
		
		
	}
	
	public void deleteFromCart(Cart c) {
		
		this.productService.removeFromCartByProductId(c.getProduct_id());
	}
	
	public int getTotalCartAmount() {
		return this.productService.getTotalCartAmount();
	}
	
	public Product getProductById(int product_id) {
		
		this.product_id = product_id;
		
		System.out.println("[getProductById function in ProductController] : Selected car id : "+this.product_id);
		
		
		return this.productService.searchProductById(product_id);
	}
	
	public List<Integer> getRatingArray(int product_id) {
		
		int rating = this.productService.getProductRating(product_id);
		
		List<Integer> result =  new ArrayList<Integer>();
		
		int c = rating;
		while(c!=0) {
			result.add(rating);
			c--;
		}
		
		return result;
	}
	
	public int getProductRating(int product_id) {
		
		try {
			int rating = this.productService.getProductRating(product_id);
			System.out.println("[getProductRating in product controller function] : product rating : "+rating);
			return rating;
			
		} 
		catch (Exception e) {
			
			System.out.println("[getProductRating in product controller function] : An Error has occured during the getProductRating process stacktrace : "+e);
			e.printStackTrace();
		}
		return 0;
	}
	
	public void addToCart(int product_id) {
		try {
			this.productService.addToCart(product_id);
			System.out.println("[addToCart in product controller function] : Added to cart : "+this.productService.getCarts().get(this.productService.getCarts().size()-1).getPrice());
			
		} 
		catch (RemoteException e) {
			
			System.out.println("[addToCart in product controller function] : An Error has occured during the addToCart process stacktrace : "+e);
			e.printStackTrace();
		}
	}
	
	public void addToCart(Product p) {
		try {
			this.productService.addToCart(p.getId());
			System.out.println("[addToCart in product controller function] : Added to cart : "+this.productService.getCarts().get(this.productService.getCarts().size()-1).getPrice());
			
		} 
		catch (RemoteException e) {
			
			System.out.println("[addToCart in product controller function] : An Error has occured during the addToCart process stacktrace : "+e);
			e.printStackTrace();
		}
	}
	
	public int getCartSize() {
		try {
			return this.productService.getCarts().size();
		} 
		catch (Exception e) {
			
			System.out.println("[getCartSize in product controller function] : An Error has occured during the getCartSize process stacktrace : "+e);
			e.printStackTrace();
		}
		return 0;
	}
	
	public void onCheckoutLoad() {
		
		System.out.println("[onCheckoutLoad in product controller function] : triggered");
		boolean check = getTotalCartAmount()==0;
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
		
		if(check) {
			
		//	System.out.println("[onCheckoutLoad in product controller function] : i'm in it : "+ check);
			
			
		    
				ec.redirect(ec.getRequestContextPath() + "/productlist.xhtml");
			} 
		
		}catch (IOException e) {
				System.out.println("[onCheckoutLoad in product controller function] : An erro has occured during the onCheckLoad function stacktrace: "+e);
				e.printStackTrace();
			}
				
		}
			
	public List<SelectItem> getAllCountries(){
		
		String[] countryCodes = Locale.getISOCountries();
		List<SelectItem> items = new ArrayList<SelectItem>();
		
		Map<String,String>countryNames = new HashMap<String,String>();
		
		for (String countryCode : countryCodes) {
		
		    Locale obj = new Locale("", countryCode);
		
		    countryNames.put(obj.getISO3Country(),obj.getDisplayCountry().toUpperCase());

			}
		
		Map<String, String> sortedMap = 
				countryNames.entrySet().stream()
			    .sorted(Entry.comparingByValue())
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) -> e1, LinkedHashMap::new));
		
	//	java.util.Collections.sort(countryNames, Collator.getInstance());
	    for (Map.Entry m : sortedMap.entrySet()) {
           // System.out.println("ISO3: "+m.getKey()+", Nom: "+m.getValue());
            
            items.add(new SelectItem(m.getKey() , m.getValue()+""));
        }
			
		  
			

			

		   return items;
	}
		
	public void validateIban(FacesContext context, UIComponent comp, Object value) {

		try {
			
		
		System.out.println("[validateIban function in product controller] inside validate Iban method");

		int mno = Integer.valueOf((String)value);

		}
		catch(NumberFormatException e) {
			
			FacesMessage message = new FacesMessage(
					"The IBAN must be a number");
			context.addMessage(comp.getClientId(context), message);

		}


	}
	
	public void validateEmail(FacesContext context, UIComponent comp, Object value) {

		System.out.println("[validateEmail function in product controller] : inside validate Email method");

		String mno = (String) value;

		if (mno.length() < 4) {
			((UIInput) comp).setValid(false);

			FacesMessage message = new FacesMessage(
					"Minimum length of model number is 4");
			context.addMessage(comp.getClientId(context), message);

		}

	}
	
	public void validateZip(FacesContext context, UIComponent comp, Object value) {

		try {
			
			System.out.println("[validateZip function in product controller] : inside validate Zip method");

			int mno = Integer.valueOf((String)value);

		}
		catch(NumberFormatException e) {
			
			FacesMessage message = new FacesMessage(
					"The zip code must be a number");
			context.addMessage(comp.getClientId(context), message);

		}

		

	}
	
	//Get Country currency and symbole for the given country ISO-4217
	public List<String> getCountryCurrencySymbol(String countryIso) {
		try {
			
			CountryCode cc = CountryCode.getByCode(countryIso);
		
		
			Currency currency = cc.getCurrency();
			String symbol = currency.getSymbol();
			

		
			List<String> result = new ArrayList<String>();
			result.add(currency.toString());
			result.add(symbol);
		
			System.out.println("\n [getCountryCurrencySymbol function] : Country ISO : "+ countryIso + "| Country CODE : "+ cc.getNumeric() +"| Country Currency : "+
					currency+"| Currency symbol : "+ symbol);
			
			return result;
		}
		catch(Exception e) {
			System.out.println("[getCountryCurrencySymbol function] : An Error has occured during the getCountryCurrencySymbol process stacktrace : "+e);
			e.printStackTrace();
			
		}
		return null;
	
		
	}
	
	//[AFTER DEPLOYEMENT] Get the client IP to replace with "getClientIp" method after deployment
	public static String getRemoteIpAddress(HttpServletRequest request) {
	    
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
	  
	    if (ipAddress != null) {
	        // cares only about the first IP if there is a list
	        ipAddress = ipAddress.replaceFirst(",.*", "");
	    } else {
	        ipAddress = request.getRemoteAddr();
	    }
	    
	    System.out.println("ipAddress:" + ipAddress);
	    return ipAddress;
	}

	//[FOR DEMO PUPOSE] Get the current machine IP since the application is not hosted 
	public String getClientIp() {
		
		BufferedReader reader;
		String line;
		
		StringBuffer responseContent = new StringBuffer();
		
		try {
			URL url = new URL("https://api.ipify.org?format=json");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			 
			// Get Request Setup
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			
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
			
			 JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseContent.toString());
			 //JSONParser jsonParser = new JSONParser();
			 //return jsonParser.parse(filename);
			   
			 
			    System.out.println("[getClientIp function] : Response : you ip is : " +jsonObject.get("ip"));
			    return (String) jsonObject.get("ip");
			
			
		}
		catch(Exception e) {
			
			System.out.println("[getClientIp function] : An Error has occured during the getClientIpProcess stacktrace : "+e);
			e.printStackTrace();
			
		}
		return null;
		
	}
   
	//Get client country by ip address
	public String getClientCountryByIp() {
		try {
			
			GeoIPServiceSoap geoIp = new GeoIPServiceLocator().getGeoIPServiceSoap() ;
			
			String result = geoIp.getIpLocation(this.getClientIp());
			
			System.out.println("[getClientCountryByIp function] : First Response : XML FILE: " +result);
			 
			
			org.json.JSONObject xmlJSONObj = org.json.XML.toJSONObject(result);
			result = (String) xmlJSONObj.getJSONObject("GeoIP").get("Country");
			
			System.out.println("[getClientCountryByIp function] : Second  Response : you country ISO-4217 code is : " +result);
			   
			
			return result; 
			
			}
		catch(Exception e) {
			
			 System.out.println("[getClientCountryByIp function] : An Error has occured during the getClientCountryProcess stacktrace : "+e);
			 e.printStackTrace();
			  
		}
		
		return null;
	}
	
	//Convert a given amount from a currency to another one	
	public double convertCurrency(String currencyFrom,String currencyTo,String amount) {
			
			return this.productService.convertCurrency(currencyFrom, currencyTo, amount);
		}
	
	public String goToCheckOut() {
		return "checkout.xhtml";
		}
	
	public String goToCart() {
		return "cart.xhtml";
	}
	
	public String goToProductList() {
		try {
			
			return "productlist.xhtml";
			
		} catch (Exception e) {
			
			System.out.println("[goToProductList function] An Error has Occured during go To ProductList process stracktrace : "+e);
			e.printStackTrace();
		}
		return null;
	}
	
	public String goToDetail(Product p) {
		try {
			return "detail.xhtml?id="+p.getId();
		} catch (RemoteException e) {
			
			System.out.println("[goToDetail function] An Error has Occured during go To Detail process stracktrace : "+e);
			e.printStackTrace();
		}
		return null;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fristName) {
		this.firstName = fristName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIpCountry() {
		return ipCountry;
	}

	public void setIpCountry(String ipCountry) {
		this.ipCountry = ipCountry;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	public int getProduct_id() {
		return product_id;
	}


	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	
	
	
	
}
