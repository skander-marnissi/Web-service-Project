package managedBean;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import service.AdminService;
import shared.ICar;
import shared.IEmploy;
import shared.IFeedBack;
import shared.IRental;
import shared.IWaiting;



@ManagedBean(name="EmployeeController")
@SessionScoped
public class EmployeeController {


	
	private int userId; 
	
	private int car_rent_id;
	
	/* Entities id */
	private String car_id, feedback_id,rental_id, waiting_id,employee_id ;

	/* Car inputs */
	private String model,brand,color,car_description,type,price,horsePower,imageUrl;
	
	
	/* Employee inputs */
	private String firstName, lastName, email, address,age,phoneNumber;   
	private char gender;

	
	/* Feedback inputs */
	private String feedback_description,rating;

	
	/* Rental inputs */
	private String status,start_date,end_date;
	

	/* Waiting inputs*/
	private String date_available,request_date;

	AdminService adminService = new AdminService();
	
	
	@PostConstruct
	public void init() {
		
			this.waiting_id="";
			this.rental_id="";
			
			
			
			try {
				
				FacesContext context = FacesContext.getCurrentInstance();
				IEmploy user = (IEmploy) context.getExternalContext().getSessionMap().get("user");
				this.userId = user.getId();
				
				
				
			
			
				
				System.out.println("[ EmployeeController Constructor] : Employee id : "+ userId);
				
			} catch (RemoteException e) {
				System.out.println("[ EmployeeController Constructor] : Remote Exception Problem with Session");
				
				e.printStackTrace();
			}
	}

	/*
	 * <p:outputLabel for="startDate" value="Start date :" />
        <p:datePicker id="startDate" value="#{calendarView.startDate}" />
	 * */
	
	/*
	 * <p:outputLabel for="endDate" value="End date" />
        <p:datePicker id="endDate" value="#{calendarView.endDate}" />
	 * */
	
	
	
	public void redirectToFeedBack() {

		try {
			
			boolean check = this.adminService.checkForFeedBack(this.userId);
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

			System.out.println("[redirectToFeedBack function in EmployeeController] : test if employee Feeded back  : "+ check);
			
			
		if(!check) {
			
		//	System.out.println("[onCheckoutLoad in product controller function] : i'm in it : "+ check);
			
			
		    	IRental r = this.adminService.getLastRentByEmployeeId(this.userId);
		    	
		    	System.out.println("[redirectToFeedBack function in EmployeeController] : miss feedback for car : "+ r.getCar_id());
				
				ec.redirect(ec.getRequestContextPath() + "/employeeFeedback.xhtml?id="+r.getCar_id());
			} 
		
		}catch (IOException e) {
				System.out.println("[redirectToFeedBack function in EmployeeController] : An erro has occured during the onCheckLoad function stacktrace: "+e);
				e.printStackTrace();
			}
	}
	
	public void addEmployee(String employee_id) {
	
		try {
				
				if(age.isEmpty()||Integer.valueOf(age)==null) {
					
					System.out.println("You have to input an age ");
				}if(phoneNumber.isEmpty()||Integer.valueOf(phoneNumber)==null) {
					
					System.out.println("You have to input a phoneNumber ");
				}else if(firstName.isEmpty()) {
					
					System.out.println("You have to input a first name");
				}else if(lastName.isEmpty()) {
					
					System.out.println("You have to input a last name");
				}else if(email.isEmpty()) {
					
					System.out.println("You have to input an email");
				}else if(address.isEmpty()) {
					
					System.out.println("You have to input the address ");
				}
			
			
				System.out.println("[addEmployee function in EmployeeController] : Selected ISO for ownerCountry is esqual to :");
			
			if(employee_id.isEmpty()) {
				
				this.adminService.addEmployee(firstName, lastName, email, address, Integer.valueOf(age), gender, Integer.valueOf(phoneNumber));
			
			}else{
				
				System.out.println("[addEmployee function in EmployeeController]: car id is esqual to :"+car_id);
				
				IEmploy e = this.adminService.searchEmployeeById(Integer.valueOf(employee_id));
				
				if(e!=null) {
					
					e.setFirstName(firstName);
					e.setLastName(lastName);
					e.setEmail(email);
					e.setAddress(address);
					e.setAge(Integer.valueOf(age));
					e.setGender(gender);
					e.setPhoneNumber(Integer.valueOf(phoneNumber));
					
				}
				
			}
				System.out.println("[addEmployee function in EmployeeController]: car id = " +Integer.valueOf(car_id));
				
			
				
				this.employee_id="";
				
		}catch(NumberFormatException i) {
			System.out.println("[addEmployee function in EmployeeController] : You have to input an number for the age field");
		}
		catch(Exception e){
			System.out.println("[addEmployee function in EmployeeController] : An error has occured during the addemployee process from the EmployeeController stacktrace : "+e);
			e.printStackTrace();
		}
			
	}
	
	public String addFeedBack(int feedback_id) {
		try {
			
		
		if(this.rating.isEmpty() || Integer.valueOf(this.rating)==null) {
			System.out.println("You have to input a rating ");
			return null;
		}else if(feedback_description.isEmpty()) {
			System.out.println("You have to input a description");
			return null;
			
		}
		
		this.adminService.addFeedBack(this.car_rent_id, this.userId, Integer.valueOf(this.rating), this.feedback_description);
		
		return "employeeIndex?faces-redirect=true";
		
	}catch(NumberFormatException i) {
		System.out.println("[addFeedBack function in EmployeeController] : You have to input an number for the rating ");
	}
	catch(Exception e){
		System.out.println("[addFeedBack function in EmployeeController] : An error has occured during the addcar process from the employeeController stacktrace : "+e);
		e.printStackTrace();
	}
		return null;
	}
	
	public void addRental(int car_id) {

		try {
				
				
			
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				
				
				System.out.println("[addRental function in EmployeeController] : You have selected date : "+start_date +" to : "+end_date  );
				
				if(this.userId==0) {
					this.adminService.addRental(0, this.userId, formatter.parse(start_date), formatter.parse(end_date));
					
					ec.redirect(ec.getRequestContextPath() + "/employeeHistRent.xhtml");

				}
					else if(this.userId==1) {
						this.adminService.addRental(1, this.userId, formatter.parse(start_date), formatter.parse(end_date));
						ec.redirect(ec.getRequestContextPath() + "/employeeHistRent.xhtml");
						
					}else if(this.userId==2) {
						this.adminService.addRental(0, this.userId, formatter.parse(start_date), formatter.parse(end_date));
						ec.redirect(ec.getRequestContextPath() + "/employeeHistRent.xhtml");
					}
					else if(this.userId==3) {
						this.adminService.addRental(2, this.userId, formatter.parse(start_date), formatter.parse(end_date));
						ec.redirect(ec.getRequestContextPath() + "/employeeHistRent.xhtml");
					}
					else if(this.userId==4) {
						this.adminService.addRental(1, this.userId, formatter.parse(start_date), formatter.parse(end_date));
						ec.redirect(ec.getRequestContextPath() + "/employeeHistRent.xhtml");
					}
				
				
			
		}catch(NumberFormatException i) {
			System.out.println("[addRental function in EmployeeController] : You have to input an number for the age field");
		}
		catch(Exception e){
			System.out.println("[addRental function in EmployeeController] : An error has occured during the addemployee process from the EmployeeController stacktrace : "+e);
			e.printStackTrace();
		}
		
	
	}
	
	public void addWaiting(String waiting_id) {
		
	}
	
	
	public void removeCar(ICar c) {
		
		try {
			this.adminService.removeCar(c.getId());
		} catch (RemoteException e) {
			System.out.println("[removeCar function in EmployeeController] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
	}
	
	public void removeEmployee(IEmploy e) {
	
		try {
			this.adminService.removeEmployee(e.getId());
		} catch (RemoteException ex) {
			System.out.println("[removeEmployee function in EmployeeController] : Remote Exception please check if the interfaces are well implemented.");
			ex.printStackTrace();
		}
	}
		
	public void removeFeedBack(IFeedBack f) {
	
		try {
			this.adminService.removeFeedBack(f.getCar_id());
		} catch (RemoteException ex) {
			System.out.println("[removeFeedBack function in EmployeeController] : Remote Exception please check if the interfaces are well implemented.");
			ex.printStackTrace();
		}
	}

	public void removeRental(IRental r){
	
		try {
			this.adminService.removeFeedBack(r.getId());
		} catch (RemoteException ex) {
			System.out.println("[removeRental function in EmployeeController] : Remote Exception please check if the interfaces are well implemented.");
			ex.printStackTrace();
		}
	}
	
	public void removeWatiting(IWaiting w) {
		
		try {
			this.adminService.removeWaiting(w.getId());
		} catch (RemoteException ex) {
			System.out.println("[removeWatiting function in EmployeeController] : Remote Exception please check if the interfaces are well implemented.");
			ex.printStackTrace();
		}
	}
	

	public List<IRental> getRentalsByEmployeeId(){
	
		return this.adminService.searchRentByEmployeeId(this.userId);
	
	}
	public ICar getCarById(int car_id) {
		
		this.car_rent_id=car_id;
		System.out.println("[getCarById function in EmployeeController] : Selected car id : "+this.car_rent_id);
		
		return this.adminService.searchCarById(car_id);
		
	}
	
	public List<IFeedBack> getFeedBacksByEmployeeId(){
		
		return this.adminService.searchFeedBackByEmployeeId(this.userId);
	}
	
	public void editCar(ICar c) {
		try {
			
			this.car_id = ""+c.getId();
			this.brand = c.getBrand();
			this.model = c.getModel();
			this.type = c.getType();
			this.price = ""+c.getPrice();
			this.color = c.getColor();
			this.horsePower = ""+c.getHorsePower();
			this.car_description = c.getDescription();
			
		} catch (RemoteException e) {
			System.out.println("[editCar function in EmployeeController] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
		
	}
	
	public void editEmployee(IEmploy e) {
	
		try {
				
				this.employee_id = ""+e.getId();
				this.firstName = e.getFirstName();
				this.lastName = e.getLastName();
				this.email = e.getEmail();
				this.address = e.getAddress();
				this.age = ""+e.getAge();
				this.gender = e.getGender();
				this.phoneNumber = ""+e.getPhoneNumber();
				
			} catch (RemoteException ex) {
				System.out.println("[editCar function in EmployeeController] : Remote Exception please check if the interfaces are well implemented.");
				ex.printStackTrace();
			}
	}
	
	public void editFeedback(IFeedBack f) {
		
		try {
			
			this.feedback_id = ""+f.getId();
			this.car_id = ""+f.getCar_id();
			this.employee_id = ""+f.getEmployee_id();
			this.feedback_description = f.getDescription();
			this.rating = ""+f.getRating();
		
		} catch (RemoteException ex) {
			System.out.println("[editFeedback function in EmployeeController] : Remote Exception please check if the interfaces are well implemented.");
			ex.printStackTrace();
		}
	}
	
	public void editRental(IRental r) {
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			this.waiting_id="";
			
			this.rental_id = ""+r.getId();
			this.car_id = ""+r.getCar_id();
			this.employee_id = ""+r.getEmployee_id();
			this.start_date = ""+formatter.format(r.getStart_date()); 
			this.end_date = ""+formatter.format(r.getEnd_date());
			this.status=r.getStatus();
			
		} catch (RemoteException e) {
			System.out.println("[editRental function in EmployeeController] : Remote Exception please check if the interfaces are well implemented.");
			e.printStackTrace();
		}
	}
	
	public void editWaiting(IWaiting w) {
		
		try{
		
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		this.rental_id="";
		
		this.waiting_id=""+w.getId();
		this.car_id = ""+w.getCar_id();
		this.employee_id = ""+w.getEmployee_id();
		this.date_available = ""+formatter.format(w.getDate_available());
		this.end_date = ""+formatter.format(w.getEnd_date());
		this.request_date =""+ formatter.format(w.getRequest_date());
		
	} catch (RemoteException e) {
		System.out.println("[ediWaiting function in EmployeeController] : Remote Exception please check if the interfaces are well implemented.");
		e.printStackTrace();
	}
	}

	
	public String formatDate(Date date) {
	try {
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				return formatter.format(date);
				
			} catch (Exception e) {
				System.out.println("[editRental function in EmployeeController] : Remote Exception please check if the interfaces are well implemented.");
				e.printStackTrace();
			}
			return null;
		}
		
	public List<ICar> getCars(){
		
			try {
				List<ICar> result = new CopyOnWriteArrayList<ICar>() ;
				
				for(ICar c : this.adminService.getCars() ) {
					
				if (!c.getState().equals("SOLD")) {
						result.add(c);
				}
				
			}
				return result;
				
			}catch (RemoteException e) {
				System.out.println("[getCars function in EmployeeController] : Remote Exception please check if the interfaces are well implemented.");
				
				e.printStackTrace();
			}
			return null;
		}
		
		
	
	
	public List<IFeedBack> getFeedBacks(){
		return this.adminService.getFeedBacks()	;
	}
	
	public List<IRental> getRentals(){
		return this.adminService.getRentals()	;
	}
	
	public List<IEmploy> getEmployees() {
		return this.adminService.getEmployees();
	}
	
	public List<IWaiting> getWaitingList(){
		return this.adminService.getWaitingList();
	}

	
	
	
	
	
	
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	public String getCar_id() {
		return car_id;
	}



	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}



	public String getFeedback_id() {
		return feedback_id;
	}



	public void setFeedback_id(String feedback_id) {
		this.feedback_id = feedback_id;
	}



	public String getRental_id() {
		return rental_id;
	}



	public void setRental_id(String rental_id) {
		this.rental_id = rental_id;
	}



	public String getWaiting_id() {
		return waiting_id;
	}



	public void setWaiting_id(String waiting_id) {
		this.waiting_id = waiting_id;
	}



	public String getEmployee_id() {
		return employee_id;
	}



	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}



	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getCar_description() {
		return car_description;
	}


	public void setCar_description(String car_description) {
		this.car_description = car_description;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	
	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getHorsePower() {
		return horsePower;
	}


	public void setHorsePower(String horsePower) {
		this.horsePower = horsePower;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public char getGender() {
		return gender;
	}


	public void setGender(char gender) {
		this.gender = gender;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getFeedback_description() {
		return feedback_description;
	}


	public void setFeedback_description(String feedback_description) {
		this.feedback_description = feedback_description;
	}


	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public String getStart_date() {
		return start_date;
	}


	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}


	public String getEnd_date() {
		return end_date;
	}


	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}


	public AdminService getAdminService() {
		return adminService;
	}


	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate_available() {
		return date_available;
	}

	public void setDate_available(String date_available) {
		this.date_available = date_available;
	}

	public String getRequest_date() {
		return request_date;
	}

	public void setRequest_date(String request_date) {
		this.request_date = request_date;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCar_rent_id() {
		return car_rent_id;
	}

	public void setCar_rent_id(int car_rent_id) {
		this.car_rent_id = car_rent_id;
	}
	
	
	
	
	
}
