/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author GR
 */
public interface InPayment extends Remote{
    public String getPayID() throws RemoteException;;
    public void setPayID(String PayID) throws RemoteException;;
    public String getTreatID() throws RemoteException;;
    public void setTreatID(String TreatID) throws RemoteException;;
    public double getPayDoctor() throws RemoteException;;
    public void setPayDoctor(double PayDoctor) throws RemoteException;;
    public double getPayDrug() throws RemoteException;;
    public void setPayDrug(double PayDrug) throws RemoteException;;
    public double getPayTotal() throws RemoteException;;
    public void setPayTotal(double PayTotal) throws RemoteException;;
    public int getIsPay() throws RemoteException;;
    public void setIsPay(int IsPay) throws RemoteException;;
    public int doUpdate() throws RemoteException;;
    public ArrayList tablePayment() throws RemoteException;;
    public ArrayList getRecordDetailPayment(String idPayment) throws RemoteException;;
    public String[] FDPayment() throws RemoteException;;
    public String autoid() throws RemoteException;;
}
