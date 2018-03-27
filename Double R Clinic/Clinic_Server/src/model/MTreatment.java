/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import config.koneksi;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import object.InTreatment;

/**
 *
 * @author GR
 */
public class MTreatment extends UnicastRemoteObject implements InTreatment {
    private String TreatID, PatientID, DoctorID, RecipeID, Diagnose, TreatDate;
    private koneksi obj_koneksi = new koneksi();
    
    public MTreatment()throws Exception
    {
        super();
    }

    public String getTreatID() {
        return TreatID;
    }

    public void setTreatID(String TreatID) {
        this.TreatID = TreatID;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String PatientID) {
        this.PatientID = PatientID;
    }

    public String getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(String DoctorID) {
        this.DoctorID = DoctorID;
    }

    public String getRecipeID() {
        return RecipeID;
    }

    public void setRecipeID(String RecipeID) {
        this.RecipeID = RecipeID;
    }

    public String getDiagnose() {
        return Diagnose;
    }

    public void setDiagnose(String Diagnose) {
        this.Diagnose = Diagnose;
    }

    public String getTreatDate() {
        return TreatDate;
    }

    public void setTreatDate(String TreatDate) {
        this.TreatDate = TreatDate;
    }

    public koneksi getObj_koneksi() {
        return obj_koneksi;
    }

    public void setObj_koneksi(koneksi obj_koneksi) {
        this.obj_koneksi = obj_koneksi;
    }
    
    public int doInsert()
    {
        int i = 0 ;
        try
        {
            obj_koneksi.openConnection();
            String str = "exec pcdTreatment ?,?,?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, PatientID);
            pr.setString(2, DoctorID);
            pr.setString(3, Diagnose);
            i = pr.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
    public String autoid(){
        String idTreatment = "";
        try {
            obj_koneksi.openConnection();
            String sql1 = "select right (Id_Treatment,5) as 'Id_Treatment' from Patient.Treatment order by Id_Treatment desc";
            Statement stat = obj_koneksi.con.createStatement();
            ResultSet rs = stat.executeQuery(sql1);
            rs.next();
            int autocode = rs.getInt("Id_Treatment");
            if(autocode < 9){
                idTreatment = "T0000" + Integer.toString(autocode + 1);
            }else if(autocode < 99){
                idTreatment = "T000" + Integer.toString(autocode + 1);
            }else if(autocode < 999){
                idTreatment = "T00" + Integer.toString(autocode + 1);
            }else if(autocode < 9999){
                idTreatment = "T00" + Integer.toString(autocode + 1);
            }else if(autocode < 99999){
                idTreatment = "T0" + Integer.toString(autocode + 1);
            }else if(autocode < 99999){
                idTreatment = "T" + Integer.toString(autocode + 1);
            }else{
                idTreatment = "full";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return idTreatment;
    }
    
    public String autoidRecipe(){
        String idRecipe = "";
        try {
            obj_koneksi.openConnection();
            String sql1 = "select right (Id_Recipe,5) as 'Id_Recipe' from Recipe.Recipe order by Id_Recipe desc";
            Statement stat = obj_koneksi.con.createStatement();
            ResultSet rs = stat.executeQuery(sql1);
            rs.next();
            int autocode = rs.getInt("Id_Recipe");
            if(autocode < 9){
                idRecipe = "R0000" + Integer.toString(autocode + 1);
            }else if(autocode < 99){
                idRecipe = "R000" + Integer.toString(autocode + 1);
            }else if(autocode < 999){
                idRecipe = "R00" + Integer.toString(autocode + 1);
            }else if(autocode < 9999){
                idRecipe = "R00" + Integer.toString(autocode + 1);
            }else if(autocode < 99999){
                idRecipe = "R0" + Integer.toString(autocode + 1);
            }else if(autocode < 99999){
                idRecipe = "R" + Integer.toString(autocode + 1);
            }else{
                idRecipe = "full";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return idRecipe;
    }
    
    public ArrayList getRecordDoctor(String search)
    {
        ArrayList data = new ArrayList();
        try
        {
            obj_koneksi.openConnection();
            String str = "select a.Id_Doctor, a.DoctorName, b.Id_Specialist, a.DoctorGender from Doctor.Doctor a join Doctor.Specialist b on a.Id_Specialist = b.Id_Specialist where a.Id_Doctor like ? or a.DoctorName like ? or b.Id_Specialist like ? or a.DoctorGender like ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, "%"+search+"%");
            pr.setString(2, "%"+search+"%");
            pr.setString(3, "%"+search+"%");
            pr.setString(4, "%"+search+"%");
            ResultSet rs = pr.executeQuery();
            while(rs.next())
            {
                 data.add(rs.getString(1));
                 data.add(rs.getString(2));
                 data.add(rs.getString(3));
                 data.add(rs.getString(4));
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return data;
    }
    
    public ArrayList tablePatient(){
        ArrayList data = new ArrayList();
        String sql = "select * from Patient.Patient";
        try {
            Statement statement = obj_koneksi.con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
             while(rs.next())
             {
                 data.add(rs.getString(1));
                 data.add(rs.getString(2));
                 data.add(rs.getString(3));
                 data.add(rs.getString(4));
                 data.add(rs.getString(5));
             }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return data;
    }
    
    public ArrayList tableDoctor(){
        ArrayList data = new ArrayList();
        String sql = "select a.Id_Doctor, a.DoctorName, b.Id_Specialist, a.DoctorGender from Doctor.Doctor a join Doctor.Specialist b on a.Id_Specialist = b.Id_Specialist";
        try {
            Statement statement = obj_koneksi.con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
             while(rs.next())
             {
                 data.add(rs.getString(1));
                 data.add(rs.getString(2));
                 data.add(rs.getString(3));
                 data.add(rs.getString(4));
             }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return data;
    }
    
    public String[] FDTreatment()
    {
        try 
        {
//            obj_koneksi.openConnection();
//            String sq = "select Id_Treatment from Patient.Treatment where DoctorName=? AND DoctorGender=?";
//            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
//            ps.setString(1, DoctorName);
//            ps.setString(2, DoctorGender);
//            
//            ResultSet r = ps.executeQuery();
//            String[] data = new String[1];
//            if(r.next())
//            {
//                data[0]= r.getString("Id_Doctor");
//            }
//            else
//            {
//               data[0]= "kosong";
//            }
//            return data;
        } 
        catch (Exception e) 
        {
        }
        return null;
    }
    
}
