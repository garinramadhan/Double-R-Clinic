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
public interface InPatient extends Remote{
    public String getPatientID() throws RemoteException;;
    public void setPatientID(String PatientID) throws RemoteException;;
    public String getPatientName() throws RemoteException;;
    public void setPatientName(String PatientName) throws RemoteException;;
    public String getPatientGender() throws RemoteException;;
    public void setPatientGender(String PatientGender) throws RemoteException;;
    public String getPatientDOB() throws RemoteException;;
    public void setPatientDOB(String PatientDOB) throws RemoteException;;
    public String getPatientAddress() throws RemoteException;;
    public void setPatientAddress(String PatientAddress) throws RemoteException;;
    public int doInsert() throws RemoteException;;
    public int doUpdate() throws RemoteException;;
    public int doDelete() throws RemoteException;;
    public ArrayList display() throws RemoteException;;
    public ArrayList getRecord() throws RemoteException;;
    public String[] FDPatient() throws RemoteException;;
}
