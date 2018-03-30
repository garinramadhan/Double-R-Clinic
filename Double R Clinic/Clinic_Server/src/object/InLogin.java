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
public interface InLogin extends Remote {
    public String getUserID() throws RemoteException;;
    public void setUserID(String UserID) throws RemoteException;;
    public String getUsername() throws RemoteException;;
    public void setUsername(String Username) throws RemoteException;;
    public String getPassword() throws RemoteException;;
    public void setPassword(String Password) throws RemoteException;;
    public String getIsAdmin() throws RemoteException;;
    public void setIsAdmin(String IsAdmin) throws RemoteException;;
    public int doInsert() throws RemoteException;;
    public int doUpdate() throws RemoteException;;
    public int doDelete() throws RemoteException;;
    public ArrayList display() throws RemoteException;;
    public ArrayList getRecord(String search) throws RemoteException;;
    public int doLogin() throws RemoteException;;
    public String[] FDUser() throws RemoteException;;
    public String[] cekAccess() throws RemoteException;;
    public int cekUsername() throws RemoteException;;
    public String autoid() throws RemoteException;;
}
