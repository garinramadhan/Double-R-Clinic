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
public interface InTreatment extends Remote{
    public String getTreatID() throws RemoteException;;
    public void setTreatID(String TreatID) throws RemoteException;;
    public String getPatientID() throws RemoteException;;
    public void setPatientID(String PatientID) throws RemoteException;;
    public String getDoctorID() throws RemoteException;;
    public void setDoctorID(String DoctorID) throws RemoteException;;
    public String getRecipeID() throws RemoteException;;
    public void setRecipeID(String RecipeID) throws RemoteException;;
    public String getDiagnose() throws RemoteException;;
    public void setDiagnose(String Diagnose) throws RemoteException;;
    public String getTreatDate() throws RemoteException;;
    public void setTreatDate(String TreatDate) throws RemoteException;;
    public int doInsert() throws RemoteException;;
    public ResultSet tableTreatment() throws RemoteException;;
    public ArrayList getRecord() throws RemoteException;;
    public String[] FDTreatment() throws RemoteException;;
    public String autoid() throws RemoteException;;
    public String autoidRecipe() throws RemoteException;;
    public ArrayList tablePatient() throws RemoteException;;
    public ArrayList tableDoctor() throws RemoteException;;
}
