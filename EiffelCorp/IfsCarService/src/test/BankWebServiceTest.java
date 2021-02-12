package test;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import service.ProductService;
import webservice.*;

public class BankWebServiceTest {

	public static void main(String[] args) throws ServiceException, RemoteException {
		// TODO Auto-generated method stub
		
		System.out.println("Starting");
		
		BankWebServiceImpl bankService = new BankWebServiceImplServiceLocator().getBankWebServiceImpl();
		
		((BankWebServiceImplSoapBindingStub) bankService).setMaintainSession(true);
		
		
		
	//	boolean result = bankService.retrieveBalanceToAccount(500, 1234,"sander","marnissi");
		
		ProductService ps = new ProductService();
		
		
		double p = ps.getCurrencyRate("TND");
		
		
		//System.out.println("Shopping Opperation : "+result);
		System.out.println("convertion Opperation : "+p);
		
		
	}

}
