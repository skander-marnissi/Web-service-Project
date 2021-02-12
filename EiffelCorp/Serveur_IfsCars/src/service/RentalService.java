package service;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import entity.Car;
import entity.Rental;
import main.ServerIfsCars;
import shared.ICar;
import shared.ICarsManagement;
import shared.IEmployManagement;
import shared.IFeedBack;
import shared.IFeedBackManagement;
import shared.IRental;
import shared.IRentalsManagement;
import shared.IWaiting;
import shared.IWaitingManagement;

public class RentalService extends UnicastRemoteObject implements IRentalsManagement  {

	List<IRental> rentals ;
	
	ICarsManagement cs ;
	
	IFeedBackManagement feedbacks; 
	
	IWaitingManagement waitingList;
	
	IEmployManagement emp;
	
	// private constructor restricted to this class itself 
	private RentalService() throws RemoteException
    {
		rentals = new CopyOnWriteArrayList<IRental>();
    }
     
    private static RentalService single_instance=null; 
    
   // static method to create instance of RentalService class 
    public static RentalService GetInstance() throws RemoteException
    { 
        // To ensure only one instance is created 
        if (single_instance == null) 
        { 
            single_instance = new RentalService(); 
        } 
        return single_instance; 
    }
	
    //[For test use] Add rental to Rental list 
	public boolean addRental(int car_id,int employee_id, String start_date, String end_date) throws RemoteException {
		
		cs = CarService.GetInstance();
			
		waitingList = WaitingListService.GetInstance();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
	
		
		try {
			
			emp = ServerIfsCars.GetInstance();
			
			Date startDateFormated = formatter.parse(start_date);
			Date endDateFormated = formatter.parse(end_date);
			
		if (emp.searchEmployeeById(employee_id) != null) {
			if(!endDateFormated.before(startDateFormated) && !endDateFormated.before(new Date())) {
				
				 if(this.rentals.isEmpty()) {
						
						this.rentals.add(new Rental(0,car_id,employee_id,start_date,end_date));
						
						cs.searchCarById(car_id).setAvailability(false);
						
						return true;
					}

				else if(!cs.searchCarById(car_id).getAvailability()){
						
					System.out.println("search car by id : " +cs.searchCarById(car_id).getId());
					
					IRental r = getRentInProcessByCarId(car_id);
					
					System.out.println("After getRentInProcessByCarId : CarId = "+r.getCar_id()+" employee Id = "+r.getEmployee_id()
					+" start date = "+ formatter.format(r.getStart_date())+" end date = "+ formatter.format(r.getEnd_date())
					);
					
					//updateCarAvailability();
					
					if(r.getEnd_date().before(endDateFormated)) {
						
						
						if(waitingList.getWaitingList().isEmpty() || waitingList.searchWaitingByCarId(car_id).isEmpty() ) {
							
							waitingList.addWaiting(car_id, employee_id, r.getEnd_date(), end_date);
							
							return true;
							
						}
						
						else {
							
							Date check = waitingList.getMaxEndDateWaitingCarId(car_id);
							
								if(check.after(new Date()) && check.before(endDateFormated)) {
									
									waitingList.addWaiting(car_id, employee_id, check, end_date);
									
									return true;
								}
								else {
									
									System.out.println(" [Rent already reserved in waiting list] Sorry but the end date that you have choosen is not available in the waiting list rent check = : "+ formatter.format(check));
									
									return false;
									
								}
							
						}
						
						
						
						
					}
					
					else {
						System.out.println("[Rent already reserved] Sorry but the end date that you have choosen is not available ");
						return false;
						
					}
					
				}
				else {
					
					
					this.rentals.add(new Rental(this.rentals.get(this.rentals.size()-1).getId()+1,car_id,employee_id,start_date,end_date));
					cs.searchCarById(car_id).setAvailability(false);
					
					return true;
					
				}
				
			 
		 }
		 else {
			 
			
			//updateCarAvailability();
			System.out.println("Sorry but the end date that you have choosen is before the current date or it's before the start date that you have given"
					+ ", so please select another date");
			
			return false;
		 }
			
		}

		else {
			//updateCarAvailability();
			System.out.println("the employee does not exist");
			
			return false;
		}
		
	}
		
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the addition of rental process stacktrace : "+ e);
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	//Add rental to Rental list 
	public boolean addRental(int car_id,int employee_id, Date start_date, Date end_date) throws RemoteException {

		cs = CarService.GetInstance();
		
		waitingList= WaitingListService.GetInstance();
		
		try {
			
			emp = ServerIfsCars.GetInstance();
		
			if (emp.searchEmployeeById(employee_id) != null) {
				
				 if(!end_date.before(start_date) && !end_date.before(new Date())) {
						
					 if(this.rentals.isEmpty()) {
							
							this.rentals.add(new Rental(0,car_id,employee_id,start_date,end_date));
							
							cs.searchCarById(car_id).setAvailability(false);
							
							return true;
						}

						else if(!cs.searchCarById(car_id).getAvailability()){
								
							IRental r = getRentInProcessByCarId(car_id);
							
						//	updateCarAvailability();
							
							
							if(r.getEnd_date().before(end_date)) {
								
								
								if(waitingList.getWaitingList().isEmpty() || waitingList.searchWaitingByCarId(car_id).isEmpty() ) {
									
									waitingList.addWaiting(car_id, employee_id, r.getEnd_date(), end_date);
									return true;
									
								}
								
								else {
									
									Date check = waitingList.getMaxEndDateWaitingCarId(car_id);
									
									if(check.after(new Date()) && check.before(end_date)) {
										
										waitingList.addWaiting(car_id, employee_id, check, end_date);
										
										return true;
									}
									else {
										
										System.out.println("[Rent already reserved in waiting list] Sorry but the end date that you have choosen is not available in the waiting list rent check = : "+ new SimpleDateFormat("dd/MM/yyyy").format(check));
										return false;
										
									}
									
								}
								
								
								
								
							}
							
							else {
								System.out.println("[Rent already reserved] Sorry but the end date that you have choosen is not available ");
								return false;
								
							}
							
						}
					 	
						else {
							
							
							this.rentals.add(new Rental(this.rentals.get(this.rentals.size()-1).getId()+1,car_id,employee_id,start_date,end_date));
							cs.searchCarById(car_id).setAvailability(false);
							
							return true;
							
						}
						
					 
				 }
				 else {
					 
					
					//updateCarAvailability();
						System.out.println("Sorry but the end date that you have choosen is before the current date or it's before the start date that you have given"
								+ ", so please select another date ");
					return false;
				 }
			}

			 else {
				 
					
					//updateCarAvailability();
					System.out.println("the Employee does not exist");
					
					return false;
				 }
			
		}
		
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the addition of rental process stacktrace : "+ e);
			e.printStackTrace();
		}
		
		
		return false;
					
					
	}
	
	//Update out dated rents with setting car availablity to true if there is no waitings in waiting list or get next rent from waiting list
	public void updateRentals() throws RemoteException  {
		
		//todo : change method name
		try {
			
			for(IRental r : rentals) {
				
				//System.out.println("i'm in for loop ------");
				
				if((r.getEnd_date().before(new Date()) || compareDates(r.getEnd_date(), new Date())==0) && waitingList.searchWaitingByCarId(r.getCar_id()).isEmpty()) {
					
					cs.searchCarById(r.getCar_id()).setAvailability(true);
					
					r.setStatus("FINISHED");
					
					
				}
				else if((r.getEnd_date().before(new Date()) || compareDates(r.getEnd_date(), new Date())==0) && waitingList.checkIfWaitingExist(r.getCar_id()) ) {
					
					IWaiting w = waitingList.getNextRent(r.getCar_id(), r.getId());
					
					if(w!=null) {
						r.setStatus("FINISHED");
						cs.searchCarById(r.getCar_id()).setAvailability(true);
						this.addRental(w.getCar_id(), w.getEmployee_id(),new Date(), w.getEnd_date());
						waitingList.removeWaiting(w.getId());
					}
					else {
						System.out.println("[updateRentals in RentalService] : The next rent is null please check the updateRentals function ");
						
					}
				
				}
				
			}
			
		}
		catch(Exception e) {
			
			System.out.println("[updateRentals in RentalService] : An Error has Occured in Update Car Availabilty function stacktrace :"+ e);
			e.printStackTrace();
		}
	
	
	}
	
	//Update rent after removing and employee with setting the status to "CANCELED" and get next rent from waiting list
	public void updateRentAfterRemovingEmployee(int rent_id) throws RemoteException  {
			
			try {
				
				IRental r = this.searchRentById(rent_id);
				
				r.setStatus("CANCELED");
				cs.searchCarById(r.getCar_id()).setAvailability(true);
				waitingList.removeWaitingByEmployeeId(r.getEmployee_id());
				
			
				IWaiting w = waitingList.getNextRent(r.getCar_id(), r.getId());
					
					if(w!=null) {
						this.addRental(w.getCar_id(), w.getEmployee_id(), new Date(), w.getEnd_date());
						
					}
					
					
			}
			catch(Exception e) {
				
				System.out.println("An Error has Occured in update Rentals after removing employee function stacktrace :"+ e);
				e.printStackTrace();
			}
		
		
		}

	//Update rent after removing and employee with setting the status to "CANCELED" and get next rent from waiting list
	public void updateRentAfterRemovingCar(int car_id) throws RemoteException  {
			
			try {
				
				List<IRental> rentedCars = this.searchRentByCarId(car_id);
				waitingList.removeWaitingByCarId(car_id);
				
				for(IRental r : rentedCars) {
					
					if(r.getStatus().equals("IN_PROCESS")) {
						
						r.setStatus("CANCELED");
						
						
					}	
				}
		

				
			}
			catch(Exception e) {
				
				System.out.println("An Error has Occured in Update Car Availabilty function stacktrace :"+ e);
				e.printStackTrace();
			}
		
		
		}

	//Search a rent from Rental list with the given employee by it's id
	public List<IRental> searchRentByEmployeeId(int employee_id) throws RemoteException {
			
			try {
				
				List result = new ArrayList<Rental>(); 
				
				for(IRental r :this.rentals) {
					
					if(r.getEmployee_id()==employee_id) {
						 
						result.add(r);
						
					}
				
				}
				
				return 	result;
					
				}
				
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the research of rental by employee id process stacktrace : "+ e);
				e.printStackTrace();
				
				}
		
			return null;
			
		}
	
	//Search a rent from Rental list with the given car by it's id
	public List<IRental> searchRentByCarId(int car_id) throws RemoteException {
			try {
				
				List result = new ArrayList<Rental>(); 
				
				for(IRental r :this.rentals) {
					
					if(r.getCar_id()==car_id) {
						 
						result.add(r);
						
					}
				
				}
				
				return 	result;
					
				}
				
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the research of rental by car id process stacktrace : "+ e);
				e.printStackTrace();
				
				}
		
			return null;
			
		}
		
	//Search a rent from Rental list with the given rent by it's id
	public IRental searchRentById(int id) throws RemoteException {
			
			try {
				
				for(IRental r :this.rentals) {
					
					if(r.getId()==id) {
						 
						
						return (IRental) r;
					}
				
				}
				
					
					
				}
				
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the research of rental by id process stacktrace : "+ e);
				e.printStackTrace();
				
				}
		
			return null;
			
		}
	
	public IRental getLastRentByEmployeeId(int employee_id) throws RemoteException {
		try {
			
			Date latestRent = this.getRentMaxEndDateByEmplpoyee(employee_id) ;
			List<IRental> rentedCars = this.getFinishedRentByEmployeeId(employee_id);
			
			cs = CarService.GetInstance();
			
			if(latestRent!=null && rentedCars!=null) {
				
			
			for(IRental r : rentedCars) {
				if(r.getEnd_date().equals(latestRent)) {
					return r;
				}
			}
			
		}
		
			return null;
			
		}catch(Exception e) {
			
			System.out.println("[getLastCarRentedByEmployeeId function in RentalService] : An Error has occurend during the getLastCarRentedByEmployeeId process stacktrace : "+ e);
			e.printStackTrace();
			
			}
		return null;
	}
	
	//Check if FINISHED state cars where feedbacked
	public boolean checkIfEmployeeFeededBack(int employee_id)  throws RemoteException {
		
		try {
			feedbacks = FeedBackService.GetInstance();
			
			
			Date latestRent = this.getRentMaxEndDateByEmplpoyee(employee_id) ;
			Date lastestFeedback = feedbacks.getFeedbackMaxSendDateByEmplpoyee(employee_id);
			
			if(latestRent!=null) {
				
				if(lastestFeedback!=null) {
					
					if(latestRent.after(lastestFeedback)) {
						return false;
					}
					else {
						return true;
					}
							
			}
				return false;
			
				
			}
		
				
			
			
			return true;
	
		
		}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the check of rental by id process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return true;
		
	}

	//Get a list of finished rentals by employee id
	public List<IRental> getFinishedRentByEmployeeId(int employee_id) throws RemoteException {
		
		try {
			
			List<IRental> rentedCars = this.searchRentByEmployeeId(employee_id);
			List<IRental> result = new CopyOnWriteArrayList<IRental>();
			
			if(rentedCars!=null) {
				for(IRental r : rentedCars ) {
					if(r.getStatus().equals("FINISHED")) {
						result.add(r);
					}
				}
				
				return result;
			}
			
			return null;
			
		}catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of finished rentals by employee id process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
	}


	public Date getRentMaxEndDateByEmplpoyee(int employee_id) throws RemoteException {
			
			//max waiting in process
			
			try{
				
				Date max_date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1999") ;
				
				List<IRental> rentedCars = this.getFinishedRentByEmployeeId(employee_id);
				//System.out.println(waitingList.size() );
				
				if(rentedCars!=null) {
					for(IRental r : rentedCars) {
						// System.out.println("in loop of check ---" );
						 if(r.getEnd_date().after(max_date)) {
							 
							 
							 max_date = r.getEnd_date();
							 //System.out.println("Max Date :"+ max_date.toString() );
								
							 
						 }
						
					 }
					return max_date;
				}
		
				 
				 return null;
				
			}
			catch(Exception e){
				System.out.println("An Error has occurend during the getRentalByMaxEndDateByEmplpoyee  stacktrace : "+ e);
				e.printStackTrace();
				
			}
			return null ;
			
		}
	
	//Get a rent in process for the given car by it's id
	public IRental getRentInProcessByCarId(int car_id) throws RemoteException {
		
		try {
			
			List<IRental> rentedCars = searchRentByCarId(car_id);
			
			for(IRental r : rentedCars) {
				
				if(r.getEnd_date().after(new Date())){
					
					return r;
				}
		}
			
		}
		catch(Exception e) {
			System.out.println("An Error has occurend during the research of Rent In Process Rent By car id process stacktrace : "+ e);
			e.printStackTrace();
		}
		
		return null;

	
	}
	
	//Get a rent in process for the given employee by it's id
	public IRental getRentInProcessByEmployeeId(int employee_id) throws RemoteException {
		
		try {
			
			List<IRental> rentedCars = searchRentByEmployeeId(employee_id);
			
			for(IRental r : rentedCars) {
				
				if(r.getEnd_date().after(new Date())){
					
					return r;
				}
		}
			
		}
		catch(Exception e) {
			System.out.println("An Error has occurend during the research of Rent In Process Rent By employee id process stacktrace : "+ e);
			e.printStackTrace();
		}
		
		return null;

	
	}
	
	//Getter of the Rental list

	public List<IRental> getRentals() throws RemoteException  {
			return rentals;
		}

	//Remove a rent from Rental list by it's id
	public boolean removeRental(int id) throws RemoteException {
			
			try {
				
				for(IRental r :this.rentals) {
					
					if(r.getId()==id) {
						 if(r.getStatus().equals("IN_PROCESS")) {
							 
							 
							 
							 IWaiting w = waitingList.getNextRent(r.getCar_id(), r.getId());
								
								if(w!=null) {
									
									rentals.remove(r);
									cs.searchCarById(r.getCar_id()).setAvailability(true);
									this.addRental(w.getCar_id(), w.getEmployee_id(),new Date(), w.getEnd_date());
									waitingList.removeWaiting(w.getId());
									System.out.println("The rent was removed and a next one took his place from waitinglist");
								}
								else {
									System.out.println("The rent was removed ");
									rentals.remove(r);
								}
								
								
								
						 }
						 else {
							 rentals.remove(r);
							System.out.println("The rent was removed ");
								
						 }
						
						//set car to available !!
						
						return true;
					}
				
				}
						
			}
				
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the deletion of rental process stacktrace : "+ e);
				e.printStackTrace();
				
				}
		
			return false;
		}
	
	//Compare only the dates without timestamps	
	public int compareDates(Date date1, Date date2) throws RemoteException
	{
	    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

	    try
	    {
	        date1 = sdf.parse(sdf.format(date1));
	        date2 = sdf.parse(sdf.format(date2));
	    }
	    catch (ParseException e) {
	        e.printStackTrace();
	        return -2;
	    }

	    Calendar cal1 = new GregorianCalendar();
	    Calendar cal2 = new GregorianCalendar();

	    cal1.setTime(date1);
	    cal2.setTime(date2);

	    if(cal1.equals(cal2))
	    {
	        return 0;
	    }
	    else if(cal1.after(cal2))
	    {
	        return 1;
	    }
	    else if(cal1.before(cal2))
	    {
	        return -1;
	    }

	    return -2;
	}
	
	//Setter of the Rental list
	public void setRentals(List<IRental> rentals) throws RemoteException  {
			this.rentals = rentals;
		}


		
			
	
}
