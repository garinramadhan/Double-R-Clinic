/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author GR
 */
public interface InRecipe extends Remote{
    public String getRecipeDetID() throws RemoteException;;
    public void setRecipeDetID(String RecipeDetID) throws RemoteException;;
    public String getRecipeID() throws RemoteException;;
    public void setRecipeID(String RecipeID) throws RemoteException;;
    public String getDrugID() throws RemoteException;;
    public void setDrugID(String DrugID) throws RemoteException;;
    public int getQTY() throws RemoteException;;
    public void setQTY(int QTY) throws RemoteException;;
    public String getDose() throws RemoteException;;
    public void setDose(String Dose) throws RemoteException;;
    public double getSubTotal() throws RemoteException;;
    public void setSubTotal(double SubTotal) throws RemoteException;;
    public int getIsDraft() throws RemoteException;;
    public void setIsDraft(int IsDraft) throws RemoteException;;
    public int doInsert() throws RemoteException;;
//    public int doUpdate() throws RemoteException;;
    public int doDelete() throws RemoteException;;
    public ArrayList tableRecipe() throws RemoteException;;
    public ArrayList tableDrug() throws RemoteException;;
    public ArrayList getRecord(String search) throws RemoteException;;
    public String[] FDRecipe() throws RemoteException;;
    public String autoid() throws RemoteException;;
}
