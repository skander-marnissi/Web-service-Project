package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import entity.WaitingList;
import shared.ICarsManagement;
import shared.IRental;
import shared.IRentalsManagement;
import shared.IWaiting;
import shared.IWaitingManagement;

public class WaitingListService extends UnicastRemoteObject implements IWaitingManagement {
	
	List<IWaiting> waitingList;
	
	IRentalsManagement rs ;
	
	ICarsManagement cs;
	
	 // private constructor restricted to this class itself 
	private WaitingListService() throws RemoteException
    {
		waitingList = new CopyOnWriteArrayList<IWaiting>();
    }
     
    private static WaitingListService single_instance=null; 
    
    // static method to create instance of WaitingListService class 
    public static WaitingListService GetInstance() throws RemoteException
    { 
        // To ensure only one instance is created 
        if (single_instance == null) 
        { 
            single_instance = new WaitingListService(); 
        } 
        return single_instance; 
    }
	
    //Add waiting to the waiting list 
	public boolean addWaiting(int car_id, int employee_id, Date date_available, Date end_date) throws RemoteException {
		
		rs = RentalService.GetInstance() ;
		
		
	try {
		
		if(this.waitingList.isEmpty()) {
			this.waitingList.add(new WaitingList(0,car_id,employee_id,date_available, end_date));
			

			
			return true;
			}
		
		else {
			
			this.waitingList.add(new WaitingList(this.waitingList.get(this.waitingList.size()-1).getId()+1,car_id,employee_id,date_available,end_date));
			return true;
			
			}
			
		}

	catch(Exception e) {
		
		System.out.println("An Error has occurend during the addition of waiting process stacktrace : "+ e);
		e.printStackTrace();
		
		}

	return false;
	
}
	
	//[For test use] Add waiting to the waiting list
	public boolean addWaiting(int car_id, int employee_id, Date date_available, String end_date) throws RemoteException {
		
		rs = RentalService.GetInstance() ;
			
		try {
			
			if(this.waitingList.isEmpty()) {
				this.waitingList.add(new WaitingList(0,car_id,employee_id,date_available, end_date));
				
	
				
				return true;
				}
			
			else {
				
				this.waitingList.add(new WaitingList(this.waitingList.get(this.waitingList.size()-1).getId()+1,car_id,employee_id,date_available,end_date));
				return true;
				
				}
				
			}
	
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the addition of waiting process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return false;
		
	}
	
	//[For test use] Add waiting in the waiting list
	public boolean addWaiting(int car_id, int employee_id) throws RemoteException {
		
		rs = RentalService.GetInstance() ;
		
	try {
		
		if(this.waitingList.isEmpty()) {
			
			this.waitingList.add(new WaitingList(0,car_id,employee_id,new Date()));
			
			//rs.searchRentByCarId(car_id).
			//set the available date field !!
			
			return true;
			}
		
		else {
			this.waitingList.add(new WaitingList(this.waitingList.get(this.waitingList.size()-1).getId()+1,car_id,employee_id,new Date()));
			return true;
			}
			
		}

	catch(Exception e) {
		
		System.out.println("An Error has occurend during the addition of waiting process stacktrace : "+ e);
		e.printStackTrace();
		
		}

	return false;
	
}

	//Search the waitings in the waiting list for the given car by it's id
	public List<IWaiting> searchWaitingByCarId(int car_id) throws RemoteException {
		
		try {
			
			List result = new ArrayList<WaitingList>(); 
			
			for(IWaiting w :this.waitingList) {
				
				if(w.getCar_id()==car_id) {
					 
					result.add(w);
					
				}
			
			}
			
			return 	result;
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of waiting by car id process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}
	
	//Search the waitings in the waiting list for the given employee by it's id
	public List<IWaiting> searchWaitingByEmployeeId(int employee_id) throws RemoteException{
		
		try {
			
			List result = new ArrayList<WaitingList>(); 
			
			for(IWaiting w :this.waitingList) {
				
				if(w.getEmployee_id()==employee_id) {
					 
					result.add(w);
					
				}
			
			}
			
			return 	result;
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of waiting by employee id process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}
	
	
	//Search waiting in the waiting list by it's id
	public IWaiting searchWaitingById(int id) throws RemoteException {
		
		try {
			
			for(IWaiting w :this.waitingList) {
				
				if(w.getId()==id) {
					 
					return 	(IWaiting) w;
					
				}
			
			}
			
			
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of waiting by id process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}
	
	//Search waiting by it's request date
	public IWaiting searchWaitingByRequestDate(Date request_date, List<IWaiting> waitingList) throws RemoteException {
		
		try {
			
			if(!waitingList.isEmpty()) {
				
				for(IWaiting w : waitingList) {
					if(w.getRequest_date().equals(request_date)) {
						return w;
					}
					
					return null;
				}
				
	
				 
			}
			
			System.out.println("The waiting list past in parameter is empty please check function");
			return null;
			
		}
		catch(Exception e){
			System.out.println("An Error has occurend during the search  of waiting list in process by request date stacktrace : "+ e);
			e.printStackTrace();
			
		}
		return null ;
		
	}
	
	//Remove the waiting from the waiting list by it's id
	public boolean removeWaiting(int id) throws RemoteException {
	
		try {
			
			for(IWaiting w :this.waitingList) {
				
				if(w.getId()==id) {
					 
					this.waitingList.remove(w);
					return true;
				}
			
			}
			
				
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the deletion of waiting process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return false;
		
	}
	
	//Remove the waitings from the waiting list for the given employee by it's id 
	public boolean removeWaitingByEmployeeId(int employee_id) throws RemoteException {
		
		try {
					
				List<IWaiting> results = this.searchWaitingByEmployeeId(employee_id);
				
				if(!results.isEmpty()) {
					for(IWaiting w :this.waitingList) {
							 this.waitingList.remove(w);							
						}
					return true;
					
					}
				return true;
					
				}
				
					
				catch(Exception e) {
					
					System.out.println("An Error has occurend during the deletion of waiting by employee id process stacktrace : "+ e);
					e.printStackTrace();
					
					}
			
				return false;
		
		}
	
	//Remove the waitings from the waiting list for the given employee by it's id 
	public boolean removeWaitingByCarId(int car_id) throws RemoteException {
		
		try {
					
				List<IWaiting> results = this.searchWaitingByCarId(car_id);
				
				if(!results.isEmpty()) {
					for(IWaiting w :this.waitingList) {
							 this.waitingList.remove(w);
							 //notify employee
						}
					return true;
					
					}
				return true;
					
				}
				
					
				catch(Exception e) {
					
					System.out.println("An Error has occurend during the deletion of waiting by employee id process stacktrace : "+ e);
					e.printStackTrace();
					
					}
			
				return false;
		
		}
	
	//Updates the waiting list with cleaning all the outdated waitings
	public boolean updateWaitingList() throws RemoteException {
		
		//todo : change method name 
		
		rs = RentalService.GetInstance();
		
		cs = CarService.GetInstance();
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			
			for(IWaiting w : waitingList) {
				
			
				

		        
				if((compareDates(w.getDate_available(), new Date())==-1)&& (compareDates(w.getDate_available(), new Date())==-1)) {
					System.out.println("The waiting id : "+ w.getId() +" from Date : "+ formatter.format(w.getDate_available()) +" to : "+formatter.format(w.getEnd_date())+
							" \nPosted : "+ formatter.format(w.getRequest_date()) +"  for car : "+ w.getCar_id()+" was removed from Waiting list [outDated] ");
					
					removeWaiting(w.getId());
				}
				/*
				else if(compareDates(w.getDate_available(), new Date())==0 && !w.getEnd_date().before(new Date())) {
					
					//int check  = compareDates(w.getDate_available(), new Date()) ;
					
				
					System.out.println("The waiting id : "+ w.getId() +" from Date : "+ formatter.format(w.getDate_available()) +" to : "+formatter.format(w.getEnd_date())+
							" \nPosted : "+ formatter.format(w.getRequest_date()) +"  for car : "+ w.getCar_id()+" was added from Waiting list to rentals ");
					
					cs.searchCarById(w.getCar_id()).setAvailability(true);
					
					rs.addRental(w.getCar_id(), w.getEmployee_id(), w.getDate_available(), w.getEnd_date());
					//notify employee
					
					removeWaiting(w.getId());
						}*/
					
				}
				
			
			
			return true;
			
		}
		
		catch(Exception e) {
			System.out.println("An Error has occurend during the Cleaning and/or notifying availablity of cars in the wating list process stacktrace : "+ e);
			e.printStackTrace();
		}
			
		return false ;
		
		
	}

	//Get the maximum end date in the waiting list for the give car by it's id 
	public Date getMaxEndDateWaitingCarId(int car_id) throws RemoteException {
		
		//max waiting in process
		
		try{
			
			Date max_date = new Date() ;
			
			List<IWaiting>waitingList = searchWaitingByCarId(car_id);
			//System.out.println(waitingList.size() );
			
			for(IWaiting w : waitingList) {
				// System.out.println("in loop of check ---" );
				 if(w.getEnd_date().after(max_date)) {
					 
					 
					 max_date = w.getEnd_date();
					 //System.out.println("Max Date :"+ max_date.toString() );
						
					 
				 }
				
			 }
			 return max_date;
			 
			
		}
		catch(Exception e){
			System.out.println("An Error has occurend during the check of waiting list in process by car id stacktrace : "+ e);
			e.printStackTrace();
			
		}
		return null ;
		
	}
	
	//Get minimum resquest date of a waiting in the given waiting list
	public Date getMinDateRequest(List<IWaiting> waitingList) throws RemoteException {
		
		try {
			 
			Date min_date = new Date() ;
			
			if(!waitingList.isEmpty()) {
				
				for(IWaiting w : waitingList) {
					if(w.getRequest_date().before(min_date)) {
						min_date = w.getRequest_date();
					}
					
					return min_date;
				}
				
	
				 
			}
			
			System.out.println("The waiting list past in parameter is empty please check function");
			return null;
			
		}
		catch(Exception e){
			System.out.println("An Error has occurend during the search minimum date of request in waiting list stacktrace : "+ e);
			e.printStackTrace();
			
		}
		return null ;
		
	}
	
	//Get the next rent by Request Date
	public IWaiting getNextRent(int car_id, int rent_id) throws RemoteException {
		try{
			
			List<IWaiting>waitingList = searchWaitingByCarId(car_id);
			
			List<IWaiting> toSortWaiting ;
			
			IRental r = rs.searchRentById(rent_id);
			
			if(!waitingList.isEmpty() && r!=null) {
				
				toSortWaiting = new CopyOnWriteArrayList<IWaiting>();
				
				for(IWaiting w : waitingList) {
					boolean test =(compareDates(r.getStart_date(), w.getRequest_date())==1 
							 || compareDates(r.getStart_date(), w.getRequest_date())==0) 
							 && (compareDates(r.getEnd_date(), w.getRequest_date())==-1 
							 || compareDates(r.getEnd_date(), w.getRequest_date())==0);
							 
					//testing : 
					System.out.println("[getNextRent function in WaintingListService] test is :  "+ test);
					System.out.println("[getNextRent function in WaintingListService] getStart_date after request date :  "+ compareDates(r.getStart_date(), w.getRequest_date())); 
					System.out.println("[getNextRent function in WaintingListService] getEnd_date before request date :  "+ compareDates(r.getEnd_date(), w.getRequest_date())); 
					
					 
					 if(	(compareDates(r.getStart_date(), w.getRequest_date())==-1 
							 || compareDates(r.getStart_date(), w.getRequest_date())==0) 
							 && (compareDates(r.getEnd_date(), w.getRequest_date())==1 
							 || compareDates(r.getEnd_date(), w.getRequest_date())==0)) {
						 
						 
						 
						 toSortWaiting.add(w);
				
			}
				}
				 
			
			Date min_date = this.getMinDateRequest(toSortWaiting);
			
			//System.out.println("Max Date :"+ new SimpleDateFormat("dd/MM/yyyy").format(min_date));
			
			if(min_date!=null) {
				
				IWaiting result = this.searchWaitingByRequestDate(min_date, toSortWaiting);
				
				System.out.println(" [getNextRent function in WaintingListService] : Waiting id : "+ result.getId() +" the car id : "+ result.getCar_id()+" the request date is  : "+ new SimpleDateFormat("dd/MM/yyyy").format(result.getRequest_date()));
				
				return result;
			}
			else {
				System.out.println("[getNextRent function in WaintingListService] :  The minimum request date is null");
				return null;
			
			}
		}
			
			System.out.println("[getNextRent function in WaintingListService] : The waiting list is empty or the rental is null please check function");
			return null;
		
			 
			
		}
		catch(Exception e){
			System.out.println("[getNextRent function in WaintingListService] : An Error has occurend during the check prioprity by request date of waiting list stacktrace : "+ e);
			e.printStackTrace();
			
		}
		return null ;
		
	}
	
	//Get the last Waiting in process for the same car by car id 
	public IWaiting getLastWaitingInProcessByCarId(int car_id) throws RemoteException{
			
			try{
				
				Date check = getMaxEndDateWaitingCarId(car_id);
				
				if(check.after(new Date())) {
					
					List<IWaiting>waitingList = searchWaitingByCarId(car_id);
					
					 for(IWaiting w : waitingList) {
						 if(w.getEnd_date().equals(check)) {
							 
							return w;
						 }
						
					 }
					
				}
				
				 
				
			}
			catch(Exception e){
				
				System.out.println("An Error has occurend during the get of waiting list in process by car id stacktrace : "+ e);
				e.printStackTrace();
				
			}
			return null ;
			
		}
	
	//Getter of the waiting list 
	public List<IWaiting> getWaitingList() throws RemoteException {
		return waitingList;
	}
	
	//Check if waiting exists between today and next end date reservation in the waiting list 
	public boolean checkIfWaitingExist(int car_id) throws RemoteException {
		
		try{
			
			List<IWaiting>waitingList = searchWaitingByCarId(car_id);
			//System.out.println(waitingList.size() );
			
			for(IWaiting w : waitingList) {
				// System.out.println("in loop of check ---" );
				 if(w.getEnd_date().after(new Date())) {
					 
					 
					return true;
					 //System.out.println("Max Date :"+ max_date.toString() );
						
					 
				 }
				
			 }
			 return false;
			 
			
		}
		catch(Exception e){
			System.out.println("[checkIfWaitingExist] : An Error has occurend during the check of current waitings in process by car id stacktrace : "+ e);
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
	
	//Setter of the waiting list 
	public void setWaitingList(List<IWaiting> waitingList) throws RemoteException {
		this.waitingList = waitingList;

		}
	
	
	
	
	

}
