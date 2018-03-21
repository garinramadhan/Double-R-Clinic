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
public interface InDrug extends Remote {
    public String getDrugID() throws RemoteException;;
    public void setDrugID(String DrugID) throws RemoteException;;
    public String getDrugName() throws RemoteException;;
    public void setDrugName(String DrugName) throws RemoteException;;
    public String getDrugType() throws RemoteException;;
    public void setDrugType(String DrugType) throws RemoteException;;
    public int getDrugStock() throws RemoteException;;
    public void setDrugStock(int DrugStock) throws RemoteException;;
    public String getDrugEXP() throws RemoteException;;
    public void setDrugEXP(String DrugEXP) throws RemoteException;;
    public double getDrugPrice() throws RemoteException;;
    public void setDrugPrice(double DrugPrice) throws RemoteException;;
    public int doInsert() throws RemoteException;;
    public int doUpdate() throws RemoteException;;
    public int doDelete() throws RemoteException;;
    public ArrayList display() throws RemoteException;;
    public ArrayList getRecord() throws RemoteException;;
    public String[] FDDrug() throws RemoteException;;
}
