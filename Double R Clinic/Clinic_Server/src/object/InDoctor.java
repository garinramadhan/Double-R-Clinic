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
public interface InDoctor extends Remote{
    public String getDoctorID() throws RemoteException;;
    public void setDoctorID(String DoctorID) throws RemoteException;;
    public String getDoctorSPC() throws RemoteException;;
    public void setDoctorSPC(String DoctorSPC) throws RemoteException;;
    public String getDoctorName() throws RemoteException;;
    public void setDoctorName(String DoctorName) throws RemoteException;;
    public String getDoctorGender() throws RemoteException;;
    public void setDoctorGender(String DoctorGender) throws RemoteException;;
    public String getDoctorDOB() throws RemoteException;;
    public void setDoctorDOB(String DoctorDOB) throws RemoteException;;
    public String getDoctorPhone() throws RemoteException;;
    public void setDoctorPhone(String DoctorPhone) throws RemoteException;;
    public int doInsert() throws RemoteException;;
    public int doUpdate() throws RemoteException;;
    public int doDelete() throws RemoteException;;
    public ArrayList display() throws RemoteException;;
    public ArrayList getRecord() throws RemoteException;;
    public String[] FDDoctor() throws RemoteException;;
    public String autoid() throws RemoteException;;
}
