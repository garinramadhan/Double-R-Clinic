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
import object.InRecipe;

/**
 *
 * @author GR
 */
public class MRecipe extends UnicastRemoteObject implements InRecipe {
    private String RecipeDetID, RecipeID, DrugID, Dose;
    private double SubTotal;
    private int QTY , IsDraft;
    private koneksi obj_koneksi = new koneksi();

    public MRecipe()throws Exception
    {
        super();
    }
    
    public String getRecipeDetID() {
        return RecipeDetID;
    }

    public void setRecipeDetID(String RecipeDetID) {
        this.RecipeDetID = RecipeDetID;
    }
    
    public String getRecipeID() {
        return RecipeID;
    }

    public void setRecipeID(String RecipeID) {
        this.RecipeID = RecipeID;
    }

    public double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(double SubTotal) {
        this.SubTotal = SubTotal;
    }

    public koneksi getObj_koneksi() {
        return obj_koneksi;
    }

    public void setObj_koneksi(koneksi obj_koneksi) {
        this.obj_koneksi = obj_koneksi;
    }

    public String getDrugID() {
        return DrugID;
    }

    public void setDrugID(String DrugID) {
        this.DrugID = DrugID;
    }

    public String getDose() {
        return Dose;
    }

    public void setDose(String Dose) {
        this.Dose = Dose;
    }

    public int getIsDraft() {
        return IsDraft;
    }

    public void setIsDraft(int IsDraft) {
        this.IsDraft = IsDraft;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }
    
    
    
    public int doInsert()
    {
        int i = 0 ;
        try
        {
            obj_koneksi.openConnection();
            String str = "exec pcdRecipeDetail ?,?,?,?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, RecipeID);
            pr.setString(2, DrugID);
            pr.setInt(3, QTY);
            pr.setString(4, Dose);
            i = pr.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
    public int doDelete()
    {
        int i = 0 ;
        try
        {
         obj_koneksi.openConnection();
         String str = "delete Recipe.RecipeDetail where Id_RecipeDetail= ?";
         PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
         pr.setString(1, RecipeDetID);
         i = pr.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
    public ArrayList tableRecipe(){
        ArrayList data = new ArrayList();
        String sql = "select a.Id_Recipe, b.Patient_Name, c.Diagnose from Patient.Treatment c join Patient.Patient b on c.Id_Patient = b.Id_Patient join Recipe.Recipe a on c.Id_Recipe = a.Id_Recipe join Patient.Payment x on c.Id_Treatment = x.Id_Treatment where x.isPay = 0";
        try {
            Statement statement = obj_koneksi.con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
             while(rs.next())
             {
                 data.add(rs.getString(1));
                 data.add(rs.getString(2));
                 data.add(rs.getString(3));
             }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return data;
    }
    
    public ArrayList tableDrug(){
        ArrayList data = new ArrayList();
        String sql = "select * from Recipe.Drug";
        try {
            Statement statement = obj_koneksi.con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
             while(rs.next())
             {
                 data.add(rs.getString(1));
                 data.add(rs.getString(2));
                 data.add(rs.getString(3));
                 data.add(rs.getInt(4));
                 data.add(rs.getString(5));
                 data.add(rs.getDouble(6));
             }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return data;
    }
    
    public ArrayList getRecord()
    {
        ArrayList data = new ArrayList();
        try
        {
            obj_koneksi.openConnection();
            String str = "select * from Recipe.Drug where Id_Drug = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, RecipeDetID);
            ResultSet rs = pr.executeQuery();
            while(rs.next())
            {
                 this.setRecipeDetID(rs.getString(1));
                 this.setRecipeID(rs.getString(2));
                 this.setDrugID(rs.getString(3));
//                 this.setQTY(rs.getString(4));
                 this.setDose(rs.getString(5));
//                 this.setSubTotal(rs.getString(6));
//                 this.setIsDraft(rs.getString(7));
                 data.add(this.getRecipeDetID());
                 data.add(this.getRecipeID());
                 data.add(this.getDrugID());
                 data.add(this.getQTY());
                 data.add(this.getDose());
                 data.add(this.getSubTotal());
                 data.add(this.getIsDraft());
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return data;
    }
    
    public String[] FDRecipe()
    {
        try 
        {
//            obj_koneksi.openConnection();
//            String sq = "select a.Id_Recipe, b.Patient_Name, c.Diagnose from Patient.Treatment c join Patient.Patient b on c";
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
    
    public String autoid(){
        String idRecipe = "";
        try {
            obj_koneksi.openConnection();
            String sql1 = "select right (Id_RecipeDetail,5) as 'Id_RecipeDetail' from Recipe.RecipeDetail order by Id_RecipeDetail desc";
            Statement stat = obj_koneksi.con.createStatement();
            ResultSet rs = stat.executeQuery(sql1);
            rs.next();
            int autocode = rs.getInt("Id_RecipeDetail");
            if(autocode < 9){
                idRecipe = "RD0000" + Integer.toString(autocode + 1);
            }else if(autocode < 99){
                idRecipe = "RD000" + Integer.toString(autocode + 1);
            }else if(autocode < 999){
                idRecipe = "RD00" + Integer.toString(autocode + 1);
            }else if(autocode < 9999){
                idRecipe = "RD00" + Integer.toString(autocode + 1);
            }else if(autocode < 99999){
                idRecipe = "RD0" + Integer.toString(autocode + 1);
            }else if(autocode < 99999){
                idRecipe = "RD" + Integer.toString(autocode + 1);
            }else{
                idRecipe = "full";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return idRecipe;
    }
}
