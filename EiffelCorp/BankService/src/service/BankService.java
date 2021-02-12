package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;

import com.neovisionaries.i18n.CountryCode;


import webservice.Account;
import webservice.Bank;

public class BankService {

	List<Account> accountList ; 
	
	List<Bank> bankList;
	
	 // private constructor restricted to this class itself 
	private BankService() throws RemoteException
    {
		bankList = new CopyOnWriteArrayList<Bank>();
		accountList = new CopyOnWriteArrayList<Account>();
		
		addBank("BIAT");
		addBank("S.T.B");
		addBank("S.G");
		addBank("CIC");
		addBank("LCL");
		addBank("BNP");
		addBank("CA");
		addBank("JPMorgane");
		
		//Initialisation
		addAccount(1234, 0, 32000.0f , "Skander", "Marnissi", "skander.marnissi@gmail.com", "TUN");
		
    }
     
    private static BankService single_instance=null; 
    
  
    // static method to create instance of CarService class 
    public static BankService GetInstance() throws RemoteException
    { 
        // To ensure only one instance is created 
        if (single_instance == null) 
        { 
            single_instance = new BankService(); 
        } 
        return single_instance; 
    }
	
	public boolean addBank(String name) {
		
		try {
					
					if(this.bankList.isEmpty()) {
						
						System.out.println("[addBank function] : new bank added with id 0");
						this.bankList.add(new Bank(0,name));
						//this.bankAccounts.put(new Bank(0,name), null);
					
						return true;
						}
					
					else {
						System.out.println("[addBank function] : bank was added with id : "+this.bankList.get(this.bankList.size()-1).getId()+1);
						this.bankList.add(new Bank(this.bankList.get(this.bankList.size()-1).getId()+1,name));
						
						//this.bankAccounts.put(new Bank(this.bankAccounts.get(this.bankAccounts.keySet().forEach(key);,name), null));
						return true;
						}
						
					}
			
				catch(Exception e) {
					
					System.out.println("[addBank function] : An Error has occurend during the addition of bank process stacktrace : "+ e);
					e.printStackTrace();
					
					}
			
				return false;
			
		}
		

	public boolean addAccount(int iban, int bank_id,float balance, String ownerFirstName, String ownerLastName, String ownerEmail,String ownerCountry) {
			try {
				
				if(!this.bankList.isEmpty() && searchBankById(bank_id)!=null && searchAccountByIban(iban)==null&& this.accountList.isEmpty()) {
					

					CountryCode cc = CountryCode.getByCode(ownerCountry);
				
					System.out.println("[addBank function] : Added currency for country : "+ownerCountry+" is :"+ cc.getCurrency().toString());
					
					
					this.accountList.add(new Account(0,iban, bank_id, balance, ownerFirstName, ownerLastName, ownerEmail, ownerCountry,cc.getCurrency().toString()));
					

						
					System.out.println("[addAccount function] : new Account with id 0 added with success");
					
				
					return true;
					}
				else if(!this.bankList.isEmpty() && searchBankById(bank_id)!=null && searchAccountByIban(iban)==null && !this.accountList.isEmpty())
				{
				
					CountryCode cc = CountryCode.getByCode(ownerCountry);
					
					System.out.println("[addBank function] : Added currency for country : "+ownerCountry+" is :"+ cc.getCurrency().toString());
					
					System.out.println("[addBank function] : account was added with id : "+this.accountList.get(this.accountList.size()-1).getId()+1);
					
					this.accountList.add(new Account(this.accountList.get(this.accountList.size()-1).getId()+1,iban, bank_id, balance, ownerFirstName, ownerLastName, ownerEmail, ownerCountry,cc.getCurrency().toString()));
				
			
				return true;
					
				
				}
				System.out.println("[addAccount function] : The bank declared do not exist and/or the iban is already used by another account");
				
				return false;
			}
		
			catch(Exception e) {
				
				System.out.println("[addAccount function] : An Error has occurend during the addition of account process stacktrace : "+ e);
				e.printStackTrace();
				
				}
		
			return false;
		}

		
	public boolean removeBank(int id) {

			try {
					boolean check = false;
				
				/*	for(Account a: this.accountList) {
						if(a.getBank_id()==id)
							 this.accountList.remove(a);
					}*/
					
					for(Bank b: this.bankList) {
						if(b.getId()==id) 
							this.bankList.remove(b);
							return true;
					}
					
					System.out.println("[removeBank function] : This bank do not exist");
					
					return false;
					
					
			}
			catch(Exception e) {
				
				System.out.println("[removeBank function] : An Error has occurend during the remove of bank process stacktrace : "+ e);
				e.printStackTrace();
				
				}
		
			return false;
		}
		
		
	public boolean removeAccount(int iban) {
			
		try {
					
					for(Account a: this.accountList) {
						if(a.getIban()==iban)
							this.accountList.remove(a);
							return true;
					}
		
					
					System.out.println("[removeAccount function] : was not removed because this account do not exist ");
					
					return false;
						
					}
			
				catch(Exception e) {
					
					System.out.println("[removeAccount function] : An Error has occurend during the addition of account process stacktrace : "+ e);
					e.printStackTrace();
					
					}
			
			return false;
		}
		
		
	public Bank searchBankById(int id) {
			try {
				
					for(Bank b : this.bankList) {
						if(b.getId()==id)
							return(b);
					}
				
					System.out.println("[searchBankById function] : This bank do not exist");
					return null;
					}
				
				
		
			catch(Exception e) {
				
				System.out.println("[searchBankById function] : An Error has occurend during the research of bank by id process stacktrace : "+ e);
				e.printStackTrace();
				
				}
		
			return null;
			
		}
		
		
	public Account searchAccountByIban(int iban) {
		
			try {
					
						for(Account a: this.accountList) {
							if(a.getIban()==iban)
								return(a);
						}
					
					System.out.println("[searchAccountByIban function] : This account do not exist and/or the iban is already used by another account");
					
					return null;
						
					}
			
				catch(Exception e) {
					
					System.out.println("[searchAccountByIban function] : An Error has occurend during the research of account process stacktrace : "+ e);
					e.printStackTrace();
					
					}
			
				return null;
			}

	public Account searchAccountById(int account_id) {
			
			try {
					
						for(Account a: this.accountList) {
							if(a.getId()==account_id)
								return(a);
						}
					
					System.out.println("[searchAccountById function] : This account do not exist and/or the iban is already used by another account");
					
					return null;
						
					}
			
				catch(Exception e) {
					
					System.out.println("[searchAccountById function] : An Error has occurend during the research of account process stacktrace : "+ e);
					e.printStackTrace();
					
					}
			
				return null;
			}


		
	public boolean checkBalance(double price,int iban) {
				
			try {
				
				Account a = searchAccountByIban(iban);
				if(a!=null) {
					
					
					double convertedPrice = convertCurrency("EUR",a.getCurrency(),price+"");
					
					System.out.println("[CheckBalance function] : converted amount is : "+convertedPrice+" Currency is : "+a.getCurrency()+" Account Balance is : "+a.getBalance());
					
					
					return a.getBalance()>=convertedPrice;
					
				}
				
				System.out.println("[CheckBalance function] : this account do not exist");
				
				return false;
					
				}
		
			catch(Exception e) {
				
				System.out.println("[CheckBalance function] : An Error has occurend during the check balance of an account process stacktrace : "+ e);
				e.printStackTrace();
				
				}

			return false;
			}
			
		
	public boolean addBalanceToAccount(float balance, int iban) {
			try {
				
				Account a = searchAccountByIban(iban);
				
				if(a!=null) {
					
					a.setBalance(a.getBalance()+balance);;
					return true;
				}
				
				System.out.println("[addBalanceToAccount function] : this account do not exist");
				
				return false;
					
				}
		
			catch(Exception e) {
				
				System.out.println("[addBalanceToAccount function] : An Error has occurend during the add balance of an account process stacktrace : "+ e);
				e.printStackTrace();
				
				}

			return false;
			}
			
		
	public boolean retrieveBalanceToAccount(double price, int iban, String ownerFirstName, String ownerLastName) {
			try {
				
				Account a = searchAccountByIban(iban);
				
				System.out.println("[retrieveBalanceToAccount function] Object found : "+a.toString());
				
				//test
			//	boolean resutl= convertCurrency("EUR",a.getCurrency(),a.getBalance()+"")>=price;
				
			//	System.out.println("[retrieveBalanceToAccount function] Boolean function : "+resutl );
				
				if(a!=null&&checkBalance(price, iban)) {
					
					if(a.getOwnerFirstName().toLowerCase().equals(ownerFirstName.toLowerCase())&&a.getOwnerLastName().toLowerCase().equals(ownerLastName.toLowerCase())) {
						
						double priceToRetrieve = convertCurrency("EUR",a.getCurrency(),price+"");
						
						a.setBalance(a.getBalance()-priceToRetrieve);
						
						return true;
					}
					else {
						System.out.println("[retrieveBalanceToAccount function] : the account first and last name don't match with the sended data  ");
						
						return false;
					}
					
				
				}
				
				
				System.out.println("[retrieveBalanceToAccount function] : this account do not exist or/and balance is not enougth balance : "+this.accountList.get(0).getBalance());
				
				return false;
					
				}
		
			catch(Exception e) {
				
				System.out.println("[retrieveBalanceToAccount function] : An Error has occurend during the add balance of an account process stacktrace : "+ e);
				e.printStackTrace();
				
				}

			return false;
			}
	
		
		
	public double convertCurrency(String currencyFrom,String currencyTo,String amount) {
			
			BufferedReader reader;
			String line;
			
			SimpleDateFormat sdf ;
			StringBuffer responseContent = new StringBuffer();
			

			try {
				
				 sdf = new SimpleDateFormat("MM/dd/yyyy");
				
				
				String convertedDate = sdf.format(new Date());
	
				
				System.out.println("[convertCurrency BankService function] Converted date : "+convertedDate);
				
				
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
			
					System.out.println("[convertCurrency BankService function] : First Response : XML FILE: " +responseContent.toString());
					 
					
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
				
				System.out.println("[convertCurrency BankService function] : An Error has occured during the convertCurrencyProcess stacktrace : "+e);
				e.printStackTrace();
				
			}
			
			return 0;
			
		}

		
		
	public List<Account> getAccountList() {
			return accountList;
		}

	public void setAccountList(List<Account> accountList) {
			this.accountList = accountList;
		}

		
	public List<Bank> getBankList() {
			return bankList;
		}

		
	public void setBankList(List<Bank> bankList) {
			this.bankList = bankList;
		}
	
	
}
