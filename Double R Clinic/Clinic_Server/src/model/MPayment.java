/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import config.koneksi;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import object.InPayment;

/**
 *
 * @author dhadotid
 */
public class MPayment extends UnicastRemoteObject implements InPayment{
    private koneksi obj_koneksi = new koneksi();
    String PayID, TreatID;
    double PayDoctor, PayDrug, PayTotal;
    int IsPay;
    
    public MPayment()throws Exception
    {
        super();
    }

    public String getPayID() {
        return PayID;
    }

    public void setPayID(String PayID) {
        this.PayID = PayID;
    }

    public String getTreatID() {
        return TreatID;
    }

    public void setTreatID(String TreatID) {
        this.TreatID = TreatID;
    }

    public double getPayDoctor() {
        return PayDoctor;
    }

    public void setPayDoctor(double PayDoctor) {
        this.PayDoctor = PayDoctor;
    }

    public double getPayDrug() {
        return PayDrug;
    }

    public void setPayDrug(double PayDrug) {
        this.PayDrug = PayDrug;
    }

    public double getPayTotal() {
        return PayTotal;
    }

    public void setPayTotal(double PayTotal) {
        this.PayTotal = PayTotal;
    }

    public int getIsPay() {
        return IsPay;
    }

    public void setIsPay(int IsPay) {
        this.IsPay = IsPay;
    }

    @Override
    public int doUpdate(){
        int i = 0;
        try
        {
            obj_koneksi.openConnection();
            String str = "update Patient.Payment set isPay = '1' where Id_Payment = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, PayID);
            i = pr.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return i;
    }

    @Override
    public ArrayList tablePayment(){
        ArrayList data = new ArrayList();
        String sql = "select x.Id_Payment, b.Patient_Name, a.Diagnose, x.PaymentDoctor, x.PaymentDrug, x.TotalPayment from Patient.Treatment a join Patient.Patient b on a.Id_Patient = b.Id_Patient join Patient.Payment x on a.Id_Treatment = x.Id_Treatment where x.isPay = '0'";
        try {
            Statement statement = obj_koneksi.con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
             while(rs.next())
             {
                 data.add(rs.getString(1));
                 data.add(rs.getString(2));
                 data.add(rs.getString(3));
                 data.add(rs.getDouble(4));
                 data.add(rs.getDouble(5));
                 data.add(rs.getDouble(6));
             }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return data;
    }

    @Override
    public ArrayList getRecordDetailPayment(String idPayment){
        ArrayList data = new ArrayList();
        String sql = "select x.Id_Payment, b.Patient_Name, c.DoctorName, f.DrugName, f.Price, e.Qty, e.Subtotal from Patient.Treatment a join Patient.Payment x on a.Id_Treatment = x.Id_Treatment join Patient.Patient b on a.Id_Patient = b.Id_Patient join Doctor.Doctor c on a.Id_Doctor = c.Id_Doctor join Recipe.Recipe d on a.Id_Recipe = d.Id_Recipe join Recipe.RecipeDetail e on e.Id_Recipe = d.Id_Recipe join Recipe.Drug f on e.Id_Drug = f.Id_Drug where x.Id_Payment like '" + idPayment + "'";
        try {
            Statement statement = obj_koneksi.con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
             while(rs.next())
             {
                 data.add(rs.getString(1));
                 data.add(rs.getString(2));
                 data.add(rs.getString(3));
                 data.add(rs.getString(4));
                 data.add(rs.getDouble(5));
                 data.add(rs.getInt(6));
                 data.add(rs.getDouble(7));
             }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return data;
    }

    @Override
    public String[] FDPayment(){
        return null;
    }

    @Override
    public String autoid(){
        String idPayment = "";
        try {
            obj_koneksi.openConnection();
            String sql1 = "Select Right(Id_Payment,5) as 'Id_Payment' from Patient.Payment Order by Id_Payment DESC";
            Statement stat = obj_koneksi.con.createStatement();
            ResultSet rs = stat.executeQuery(sql1);
            rs.next();
            int autocode = rs.getInt("Id_Payment");
            if(autocode < 9){
                idPayment = "PAY0000" + Integer.toString(autocode + 1);
            }else if(autocode < 99){
                idPayment = "PAY000" + Integer.toString(autocode + 1);
            }else if(autocode < 999){
                idPayment = "PAY00" + Integer.toString(autocode + 1);
            }else if(autocode < 9999){
                idPayment = "PAY00" + Integer.toString(autocode + 1);
            }else if(autocode < 99999){
                idPayment = "PAY0" + Integer.toString(autocode + 1);
            }else if(autocode < 99999){
                idPayment = "PAY" + Integer.toString(autocode + 1);
            }else{
                idPayment = "full";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return idPayment;
    }
    
}
