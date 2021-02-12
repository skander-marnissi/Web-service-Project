package test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import shared.IEmploy;
import shared.IEmployManagement;


public class TestEmployeeService {

	public TestEmployeeService() throws RemoteException {}

	public boolean testAddEmployee(IEmployManagement employees,String firstName, String lastName, String email, String address, int age) throws RemoteException {
		
		System.out.println("\n*-Start Method testAddEmployee-*");
		
		boolean result = employees.addEmployee(firstName, lastName, email, address, age);
		
		for(IEmploy emp : employees.getEmployees())
			displayEmployee(emp);
		
		System.out.println("\n*-End Method testAddEmployee-*");
		return result;
	
	}
	
	public boolean testRemoveEmployee(IEmployManagement employees, int id) throws RemoteException {
		
		System.out.println("\n*-Start Method testRemoveEmployee-*");	
		boolean result = false;
		
	try {
		
		result = employees.removeEmployeeById(id);
		
		//Display the returned value 
		for(IEmploy emp : employees.getEmployees())
			displayEmployee(emp);
		
	}
	catch(Exception e) {
		
		System.out.println("An Error has occurend during the test of remove employee from Employeelist : "+e);
		e.printStackTrace();
	}
		
		System.out.println("\n*-End Method testRemoveEmployee-*");		
		return result;
	
	}
	
	public boolean testSearchEmployeesByFirstName(IEmployManagement employees, String firstName) throws RemoteException {
		
		System.out.println("\n*-Start Method testSearchEmployeesByFirstName-*");
		boolean result = false;
		
		try {
			
			List<IEmploy> result_employees = employees.searchEmployeeByFirstName(firstName);
			
			//Display the returned values
			for(IEmploy emp : result_employees) {
				displayEmployee(emp);
				
				if(emp.getFirstName().toLowerCase().equals(firstName.toLowerCase()))
					result = true;
			}
			
		
				
			
		}
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the test of search Employee list by firstname : "+ e);
			e.printStackTrace();
		}
			
		System.out.println("\n*-End Method testSearchEmployeesByFirstName-*");
			return result;
		
		
	
	}
		
	public boolean testSearchEmployeeById(IEmployManagement employees, int id) throws RemoteException {
		
		System.out.println("\n*-Start Method testSearchEmployeeById-*");	
		boolean result = false;
			
			try {
				
				IEmploy result_employee = employees.searchEmployeeById(id);
		
				//Display the returned value 
				displayEmployee(result_employee);
				
					if(result_employee.getId() == id)
						result = true;
				
				
				
					
				
			}
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the test of search Employee by id : "+ e);
				e.printStackTrace();
			}
				
			System.out.println("\n*-End Method testSearchEmployeeById-*");		
			return result;
			
		
	
	}
	
	public boolean testSetEmployee(IEmployManagement employees, String firstName, String changeTo) throws RemoteException {

			System.out.println("\n*-Start Method testSetEmployee-*");	
			boolean result = true;
			
			try {
				
				List<IEmploy> result_employees = employees.searchEmployeeByFirstName(firstName);
		
				//Display the returned values
				System.out.println("Before Change  : -------------");
				for(IEmploy emp : result_employees) {
					
					displayEmployee(emp);
					
					System.out.println("\n Employee id : "+emp.getId()+" FirstName : "+ emp.getFirstName() +" LastName : "+emp.getLastName()+" Email : "+ emp.getEmail()+" Address : "+emp.getAddress()+" Age : "+emp.getAge() );
					
					System.out.println("\n -----------------------------------");
					
					emp.setFirstName(changeTo);
					
				}
				
				//Display the returned values
				System.out.println("\nAfter Change  : -------------");
				for(IEmploy emp : employees.getEmployees()) {
					
					displayEmployee(emp);
					
					System.out.println("\n -----------------------------------");
					
					if(emp.getFirstName().toLowerCase().equals(firstName.toLowerCase())) 
						result = false;
					
					
				}
					
				
					
				
			}
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the test of set Employee  opperation : "+ e);
				e.printStackTrace();
			}
				
			System.out.println("\n*-End Method testSetEmployee-*");		
			return result;
	
	}
	
	
	public void displayEmployee(IEmploy emp) throws RemoteException {
		System.out.println("\n Employee id : "+emp.getId()+" FirstName : "+ emp.getFirstName() +" LastName : "+emp.getLastName()+" Email : "+ emp.getEmail()+" Address : "+emp.getAddress()+" Age : "+emp.getAge() );
		
	}
	

	public static void main(String[] args) throws RemoteException, MalformedURLException  {
		// TODO Auto-generated method stub
		try {
			
			/*App. Employee  - Management Test*/
			
			
			IEmployManagement employees = (IEmployManagement) Naming.lookup("rmi://localhost:1099/EmployeeManagement");
			
			TestEmployeeService tests = new TestEmployeeService();
			
			
			boolean testAddEmployee = false, testRemoveEmployee = false ,testSearchEmployeesByFirstName = false ,testSearchEmployeeById = false ,testSetEmployee = false ;
			
			
			testAddEmployee = tests.testAddEmployee(employees, "Skander", "Marnissi","Skander.marnissi@gmail.com","xxxx",24) && tests.testAddEmployee(employees, "Fairouz", "FarFour","Fairouz.farfour@gmail.com","xxxx",26)  && tests.testAddEmployee(employees, "Hosni", "Ayachi","Hosni.Ayachi@gmail.com","xxxx",22) ;
			
			
			testSearchEmployeesByFirstName = tests.testSearchEmployeesByFirstName(employees, "Fairouz");
			
			testSearchEmployeeById = tests.testSearchEmployeeById(employees,2);
			
			testSetEmployee = tests.testSetEmployee(employees,"Hosni","Mosni");
			
			testRemoveEmployee = tests.testRemoveEmployee(employees, 1);
			
			
			System.out.println("\nResult of Tests of EmployeeService :"
					+"\n testAddEmployee : "+ testAddEmployee
					+"\n testSearchEmployeesByFirstName : "+ testSearchEmployeesByFirstName
					+"\n testSearchEmployeeById : "+ testSearchEmployeeById
					+"\n testSetEmployee : "+ testSetEmployee
					+"\n testRemoveEmployee : "+ testRemoveEmployee);
			
			//Exception
			 }
		
			 catch (Exception e) {
				 
				 e.printStackTrace();
				 System.out.println("An Error Has Occured while running the client for Employeeservice Test stacktrace : " + e);
			 }
		
			 }

	}


