package test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import java.util.List;

import shared.IFeedBack;
import shared.IFeedBackManagement;

public class TestFeedBackService {

	
		public TestFeedBackService() throws RemoteException {}

		public boolean testAddFeedBack(IFeedBackManagement feedBacks, int car_id,int employee_id, int rating, String description) throws RemoteException {
			
			System.out.println("\n*-Start Method TestFeedBackService-*");
			
			boolean result = feedBacks.addFeedBack(car_id, employee_id, rating, description);
			
			for(IFeedBack f : feedBacks.getFeedBacks())
				displayFeedBack(f);
			
			System.out.println("\n*-End Method TestFeedBackService-*");
			return result;
		
		}
		
		public boolean testRemoveFeedBack(IFeedBackManagement feedBacks, int id) throws RemoteException  {
			
			System.out.println("\n*-Start Method testRemoveFeedBack-*");	
			boolean result = false;
			
		try {
			
			result = feedBacks.removeFeedBack(id);
			
			//Display the returned value 
			for(IFeedBack f : feedBacks.getFeedBacks())
				displayFeedBack(f);
			
		}
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the test of remove Feedback : "+e);
			e.printStackTrace();
		}
			
			System.out.println("\n*-End Method testRemoveFeedBack-*");		
			return result;
		
		}
		
		public boolean testSearchFeedBacksByCarId(IFeedBackManagement feedBacks, int car_id) throws RemoteException  {
			
			System.out.println("\n*-Start Method testSearchFeedBacksByCarId-*");
			boolean result = false;
			
			try {
				
				List<IFeedBack> result_feedBacks = feedBacks.searchFeedBackByCarId(car_id);
				
				//Display the returned values
				for(IFeedBack f : result_feedBacks) {
					displayFeedBack(f);
					
					if(f.getCar_id() == car_id)
						result = true;
				}
				
			
					
				
			}
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the test of search feedBack by car id : "+ e);
				e.printStackTrace();
			}
				
			System.out.println("\n*-End Method testSearchFeedBacksByCarId-*");
				return result;
			
			
		
		}
		
		public boolean testSearchFeedBackByEmployeeId(IFeedBackManagement feedBacks, int employee_id) throws RemoteException  {
			
			System.out.println("\n*-Start Method testSearchFeedBackByEmployeeId-*");	
			boolean result = false;
			
			try {
				
				List<IFeedBack> result_feedBacks = feedBacks.searchFeedBackByEmployeeId(employee_id);
				
				//Display the returned values 
				for(IFeedBack f : result_feedBacks) {
					displayFeedBack(f);
					
					if(f.getEmployee_id() == employee_id)
						result = true;
				}
				
				
					
				
			}
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the test of search feed back by Employee Id : "+ e);
				e.printStackTrace();
			}
			
				System.out.println("\n*-End Method testSearchFeedBackByEmployeeId-*");	
				return result;
			
		
		}
		
		public boolean testSearchFeedBackById(IFeedBackManagement feedBacks, int id) throws RemoteException  {
			
			System.out.println("\n*-Start Method testSearchFeedBackById-*");	
			boolean result = false;
				
				try {
					
					IFeedBack result_feedBack = feedBacks.searchFeedBackById(id);
			
					//Display the returned value 
					displayFeedBack(result_feedBack);
					
						if(result_feedBack.getId() == id)
							result = true;
					
					
					
						
					
				}
				catch(Exception e) {
					
					System.out.println("An Error has occurend during the test of search feed back by id : "+ e);
					e.printStackTrace();
				}
					
				System.out.println("\n*-End Method testSearchFeedBackById-*");		
				return result;
				
			
		
		}
		
		public boolean testSetFeedBack(IFeedBackManagement feedBacks, int car_id, int changeTo) throws RemoteException  {

				System.out.println("\n*-Start Method testSetFeedBack-*");	
				boolean result = true;
				
				try {
					
					List<IFeedBack> result_feedBacks = feedBacks.searchFeedBackByCarId(car_id);
			
					//Display the returned values
					System.out.println("Before Change  : -------------");
					for(IFeedBack f : result_feedBacks) {
						
					
						displayFeedBack(f);
						
						System.out.println("\n -----------------------------------");
						
						f.setCar_id(changeTo);
						
					}
					
					//Display the returned values
					System.out.println("\nAfter Change  : -------------");
					for(IFeedBack f : feedBacks.getFeedBacks()) {
						
						displayFeedBack(f);
						
						System.out.println("\n -----------------------------------");
						
						if(f.getCar_id()==car_id) 
							result = false;
						
						
					}
						
					
						
					
				}
				catch(Exception e) {
					
					System.out.println("An Error has occurend during the test of set feed back opperation : "+ e);
					e.printStackTrace();
				}
					
				System.out.println("\n*-End Method testSetFeedBack-*");		
				return result;
		
		}
		
		public void displayFeedBack(IFeedBack f) throws RemoteException {
			System.out.println("\n FeedBack id : "+f.getId()+" Car id : "+ f.getCar_id()+" Employee Id : "+f.getEmployee_id()+" Rating : "+f.getRating()+" Description : "+f.getDescription());
			
		}
		

		public static void main(String[] args) throws RemoteException, MalformedURLException  {
			// TODO Auto-generated method stub
			try {
				
				/*App. FeedBack-Management Test*/
				
				
				IFeedBackManagement feedBacks = (IFeedBackManagement) Naming.lookup("rmi://localhost:1100/FeedBackManagement");
				
				TestFeedBackService tests = new TestFeedBackService();
				
				
				boolean testAddFeedBack = false, testSearchFeedBacksByCarId = false ,testSearchFeedBackByEmployeeId = false ,testSearchFeedBackById = false ,testSetFeedBack = false  ,testRemoveFeedBack = false ;
				
				
				testAddFeedBack = tests.testAddFeedBack(feedBacks, 0, 0, 5,"Very Good car") && tests.testAddFeedBack(feedBacks, 2, 1,4, "Average Car") && tests.testAddFeedBack(feedBacks, 1, 2, 1,"Very Bad Car");
				
				testSearchFeedBacksByCarId = tests.testSearchFeedBacksByCarId(feedBacks, 1);
				
				testSearchFeedBackByEmployeeId = tests.testSearchFeedBackByEmployeeId(feedBacks, 0);
				
				testSearchFeedBackById = tests.testSearchFeedBackById(feedBacks,2);
				
				testSetFeedBack = tests.testSetFeedBack(feedBacks,0,5);
				
				testRemoveFeedBack = tests.testRemoveFeedBack(feedBacks, 1);
				
				System.out.println("\nResult of Tests of FeedBackService :"
						+"\n testAddFeedBack : "+ testAddFeedBack
						+"\n testSearchFeedBacksByCarId : "+ testSearchFeedBacksByCarId
						+"\n testSearchFeedBackByEmployeeId : "+ testSearchFeedBackByEmployeeId
						+"\n testSearchFeedBackById : "+ testSearchFeedBackById
						+"\n testSetFeedBack : "+ testSetFeedBack
						+"\n testRemoveFeedBack : "+ testRemoveFeedBack);
				
				//Exception
				 }
			
				 catch (Exception e) {
					 
					 e.printStackTrace();
					 System.out.println("An Error Has Occured while running the client for FeedBackService Test stacktrace : " + e);
				 }
			
				 }

		}

	

