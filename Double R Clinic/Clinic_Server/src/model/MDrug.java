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
import object.InDrug;

/**
 *
 * @author GR
 */
public class MDrug extends UnicastRemoteObject implements InDrug {
    private String DrugID, DrugName, DrugType, DrugEXP;
    private int DrugStock;
    private double DrugPrice;
    private koneksi obj_koneksi = new koneksi();
    
    public MDrug()throws Exception{
        super();
    }

    public String getDrugID() {
        return DrugID;
    }

    public void setDrugID(String DrugID) {
        this.DrugID = DrugID;
    }

    public String getDrugName() {
        return DrugName;
    }

    public void setDrugName(String DrugName) {
        this.DrugName = DrugName;
    }

    public String getDrugType() {
        return DrugType;
    }

    public void setDrugType(String DrugType) {
        this.DrugType = DrugType;
    }

    public String getDrugEXP() {
        return DrugEXP;
    }

    public void setDrugEXP(String DrugEXP) {
        this.DrugEXP = DrugEXP;
    }

    public int getDrugStock() {
        return DrugStock;
    }

    public void setDrugStock(int DrugStock) {
        this.DrugStock = DrugStock;
    }

    public double getDrugPrice() {
        return DrugPrice;
    }

    public void setDrugPrice(double DrugPrice) {
        this.DrugPrice = DrugPrice;
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
            String str = "exec pcdDrug ?,?,?,?,?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(2, DrugName);
            pr.setString(3, DrugType);
//            pr.setString(4, DrugStock);
            pr.setString(5, DrugEXP);
//            pr.setString(6, DrugPrice);
            i = pr.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
    public int doUpdate()
    {
        int i = 0;
        try
        {
            obj_koneksi.openConnection();
            String str = "UPDATE Recipe.Drug SET DrugName =  ?," +
                    "DrugType = ? "+
                    "Stock = ? "+
                    "ExpDate = ? "+
                    "Price = ? "+
                    "where Id_Drug = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, DrugName);
            pr.setString(2, DrugType);
//            pr.setString(3, DrugStock);
            pr.setString(4, DrugEXP);
//            pr.setString(5, DrugPrice);
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
         String str = "delete Recipe.Drug where Id_rug= ?";
         PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
         pr.setString(1, DrugID);
         i = pr.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
    public ArrayList display()
    {
         ArrayList data = new ArrayList();
         try
         {
             obj_koneksi.openConnection();
             Statement stmt = obj_koneksi.con.createStatement();
             String str = "select Id_Drug, DrugName, DrugType, Stock, ExpDate, Price from Recipe.Drug";
             ResultSet rs = stmt.executeQuery(str);
             while(rs.next())
             {
                 this.setDrugID(rs.getString(1));
                 this.setDrugName(rs.getString(2));
                 this.setDrugType(rs.getString(3));
//                 this.setDrugStock(rs.getString(4));
                 this.setDrugEXP(rs.getString(5));
//                 this.setDrugPrice(rs.getString(6));
                 data.add(this.getDrugID());
                 data.add(this.getDrugName());
                 data.add(this.getDrugType());
                 data.add(this.getDrugStock());
                 data.add(this.getDrugEXP());
                 data.add(this.getDrugPrice());
             }
         }
         catch(SQLException ex)
         {
             System.out.println(ex.getMessage());
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
            pr.setString(1, DrugID);
            ResultSet rs = pr.executeQuery();
            while(rs.next())
            {
                 this.setDrugID(rs.getString(1));
                 this.setDrugName(rs.getString(2));
                 this.setDrugType(rs.getString(3));
//                 this.setDrugStock(rs.getString(4));
                 this.setDrugEXP(rs.getString(5));
//                 this.setDrugPrice(rs.getString(6));
                 data.add(this.getDrugID());
                 data.add(this.getDrugName());
                 data.add(this.getDrugType());
                 data.add(this.getDrugStock());
                 data.add(this.getDrugEXP());
                 data.add(this.getDrugPrice());
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return data;
    }
    
    public String[] FDDrug()
    {
        try 
        {
            obj_koneksi.openConnection();
            String sq = "select Id_Doctor from Doctor.Doctor where DrugName=? AND DrugType=?";
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, DrugName);
            ps.setString(2, DrugType);
            
            ResultSet r = ps.executeQuery();
            String[] data = new String[1];
            if(r.next())
            {
                data[0]= r.getString("Id_Drug");
            }
            else
            {
               data[0]= "Null";
            }
            return data;          
        } 
        catch (Exception e) 
        {
        }
        return null;
    }
}
