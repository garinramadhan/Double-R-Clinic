/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package object;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
/**
 *
 * @author user
 */
public interface InSpecialist extends Remote{
    public String getSpcID() throws RemoteException;;
    public void setSpcID(String SpcID) throws RemoteException;;
    public String getSpcName() throws RemoteException;;
    public void setSpcName(String SpcName) throws RemoteException;;
    public double getSpcFare() throws RemoteException;;
    public void setSpcFare(double SpcFare) throws RemoteException;;
    public int doInsert() throws RemoteException;;
    public int doUpdate() throws RemoteException;;
    public int doDelete() throws RemoteException;;
    public ArrayList tableSpecialist() throws RemoteException;;
    public ArrayList getRecord(String search) throws RemoteException;;
    public String[] FDSpc() throws RemoteException;;
    public String autoid() throws RemoteException;;
    public int specialistAvailable() throws RemoteException;;
}
