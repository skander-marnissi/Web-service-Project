/**
 * BankWebServiceImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public interface BankWebServiceImpl extends java.rmi.Remote {
    public boolean retrieveBalanceToAccount(int price, int iban, java.lang.String ownerFirstName, java.lang.String ownerLastName) throws java.rmi.RemoteException;
}
