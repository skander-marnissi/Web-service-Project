package test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import shared.ICarsManagement;
import shared.IFeedBackManagement;



public class WebServiceTest {

	
	public WebServiceTest() {
		
	}
	
	
	public static void main(String[] args) throws ParseException, MalformedURLException, RemoteException, NotBoundException {
		// TODO Auto-generated method stub
	
		ICarsManagement cars = (ICarsManagement) Naming.lookup("rmi://localhost:1100/CarsManagement");
		IFeedBackManagement feedBacks = (IFeedBackManagement) Naming.lookup("rmi://localhost:1100/FeedBackManagement");
	

		
		//Adding cars

		cars.addCar(5,"WOLKSWAGEN", "polo", "SUV", "Best Car of the year", "bmw.png", "#ffe9", 2550, 12312325);
		cars.addCar(4,"WOLKSWAGEN", "golf", "SUV", "Best Car of the year", "bmw.png", "#ffe9", 2550, 12312359);
		cars.addCar(3,"BMW", "SERIE1", "SUV", "Best Car of the year", "bmw.png", "#ffe9", 3000, 12312354);
		
	
		feedBacks.addFeedBack(0, 0, 5, "Good Car");
		feedBacks.addFeedBack(0, 0, 3, "Huge Car");
		feedBacks.addFeedBack(0, 0, 2, "Bad Car");
		feedBacks.addFeedBack(0, 0, 4, "pretty Car");
		
		
		feedBacks.addFeedBack(1, 0, 5, "Good Car");
		feedBacks.addFeedBack(1, 0, 3, "Huge Car");
		feedBacks.addFeedBack(1, 0, 2, "Bad Car");
		feedBacks.addFeedBack(1, 0, 4, "pretty Car");
		
		
		feedBacks.addFeedBack(2, 0, 5, "Good Car");
		feedBacks.addFeedBack(2, 0, 3, "Huge Car");
		feedBacks.addFeedBack(2, 0, 2, "Bad Car");
		feedBacks.addFeedBack(2, 0, 4, "pretty Car");
		
		
		
		
		
		
	
	}

}
