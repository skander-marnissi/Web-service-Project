package webservice;

public class BankWebServiceImplProxy implements webservice.BankWebServiceImpl {
  private String _endpoint = null;
  private webservice.BankWebServiceImpl bankWebServiceImpl = null;
  
  public BankWebServiceImplProxy() {
    _initBankWebServiceImplProxy();
  }
  
  public BankWebServiceImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initBankWebServiceImplProxy();
  }
  
  private void _initBankWebServiceImplProxy() {
    try {
      bankWebServiceImpl = (new webservice.BankWebServiceImplServiceLocator()).getBankWebServiceImpl();
      if (bankWebServiceImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)bankWebServiceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)bankWebServiceImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (bankWebServiceImpl != null)
      ((javax.xml.rpc.Stub)bankWebServiceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webservice.BankWebServiceImpl getBankWebServiceImpl() {
    if (bankWebServiceImpl == null)
      _initBankWebServiceImplProxy();
    return bankWebServiceImpl;
  }
  
  public boolean retrieveBalanceToAccount(int price, int iban, java.lang.String ownerFirstName, java.lang.String ownerLastName) throws java.rmi.RemoteException{
    if (bankWebServiceImpl == null)
      _initBankWebServiceImplProxy();
    return bankWebServiceImpl.retrieveBalanceToAccount(price, iban, ownerFirstName, ownerLastName);
  }
  
  
}