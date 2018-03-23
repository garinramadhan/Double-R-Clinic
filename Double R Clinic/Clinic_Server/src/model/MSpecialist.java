/*
 * To change this template, choose Tools | Templates
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
import object.InSpecialist;
/**
 *
 * @author user
 */
public class MSpecialist extends UnicastRemoteObject implements InSpecialist{
    private String SpcID, SpcName;
    private double SpcFare;
    private koneksi obj_koneksi = new koneksi();
    
    public MSpecialist()throws Exception
    {
        super();
    }

    public String getSpcID() {
        return SpcID;
    }

    public void setSpcID(String SpcID) {
        this.SpcID = SpcID;
    }

    public String getSpcName() {
        return SpcName;
    }

    public void setSpcName(String SpcName) {
        this.SpcName = SpcName;
    }

    public double getSpcFare() {
        return SpcFare;
    }

    public void setSpcFare(double SpcFare) {
        this.SpcFare = SpcFare;
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
            String str = "exec pcdSpecialist ?,?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, SpcName);
//            pr.setString(2, SpcFare);
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
            String str = "UPDATE Doctor.Spc SET Specialist =  ?," +
                    "Fare = ? "+
                    "where Id_Specialist = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, SpcName);
//            pr.setString(2, SpcFare);
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
         String str = "delete Doctor.Specialist where Id_Specialist= ?";
         PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
         pr.setString(1, SpcID);
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
             String str = "select Id_Specialist, Specialist, Fare from Doctor.Specialist";
             ResultSet rs = stmt.executeQuery(str);
             while(rs.next())
             {
                 this.setSpcID(rs.getString(1));
                 this.setSpcName(rs.getString(2));
//                 this.setSpcFare(rs.getString(3));
                 data.add(this.getSpcID());
                 data.add(this.getSpcName());
//                 data.add(this.getSpcFare());
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
            String str = "select * from Doctor.Specialist where Id_Specialist = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, SpcID);
            ResultSet rs = pr.executeQuery();
            while(rs.next())
            {
                 this.setSpcID(rs.getString(1));
                 this.setSpcName(rs.getString(2));
//                 this.setSpcFare(rs.getString(3));
                 data.add(this.getSpcID());
                 data.add(this.getSpcName());
                 data.add(this.getSpcFare());
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return data;
    }
    
    public String[] FDSpc()
    {
        try 
        {
            obj_koneksi.openConnection();
            String sq = "select Id_Specialist from Doctor.Specialist where Specialist=?";
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, SpcName);
            
            ResultSet r = ps.executeQuery();
            String[] data = new String[1];
            if(r.next())
            {
                data[0]= r.getString("Id_Specialist");
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
