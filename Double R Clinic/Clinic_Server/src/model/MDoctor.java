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
import object.InDoctor;
/**
 *
 * @author user
 */
public class MDoctor extends UnicastRemoteObject implements InDoctor{
    private String DoctorID, DoctorSPC, DoctorName, DoctorGender, DoctorDOB, DoctorPhone;
    private koneksi obj_koneksi = new koneksi();
    
    public MDoctor()throws Exception
    {
        super();
    }

    public String getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(String DoctorID) {
        this.DoctorID = DoctorID;
    }

    public String getDoctorSPC() {
        return DoctorSPC;
    }

    public void setDoctorSPC(String DoctorSPC) {
        this.DoctorSPC = DoctorSPC;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String DoctorName) {
        this.DoctorName = DoctorName;
    }

    public String getDoctorGender() {
        return DoctorGender;
    }

    public void setDoctorGender(String DoctorGender) {
        this.DoctorGender = DoctorGender;
    }

    public String getDoctorDOB() {
        return DoctorDOB;
    }

    public void setDoctorDOB(String DoctorDOB) {
        this.DoctorDOB = DoctorDOB;
    }

    public String getDoctorPhone() {
        return DoctorPhone;
    }

    public void setDoctorPhone(String DoctorPhone) {
        this.DoctorPhone = DoctorPhone;
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
            String str = "insert into Doctor.Doctor(Id_Doctor, Id_Specialist, DoctorName, DoctorGender, DateOfBirth, Phone) values(?,?,?,?,?,?)";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, DoctorID);
            pr.setString(2, DoctorSPC);
            pr.setString(3, DoctorName);
            pr.setString(4, DoctorGender);
            pr.setString(5, DoctorDOB);
            pr.setString(6, DoctorPhone);
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
            String str = "UPDATE Doctor.Doctor SET Id_Specialist =  ?," +
                    "DoctorName = ? "+
                    "DoctorGender = ? "+
                    "DateOfBirth = ? "+
                    "Phone = ? "+
                    "where Id_Doctor = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, DoctorSPC);
            pr.setString(2, DoctorName);
            pr.setString(3, DoctorGender);
            pr.setString(4, DoctorDOB);
            pr.setString(5, DoctorPhone);
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
         String str = "delete Doctor.Doctor where Id_Doctor= ?";
         PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
         pr.setString(1, DoctorID);
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
             String str = "select Id_Doctor, Id_Specialist, DoctorName, DoctorGender, DateOfBirth, Phone from Doctor.Doctor";
             ResultSet rs = stmt.executeQuery(str);
             while(rs.next())
             {
                 this.setDoctorID(rs.getString(1));
                 this.setDoctorSPC(rs.getString(2));
                 this.setDoctorName(rs.getString(3));
                 this.setDoctorGender(rs.getString(4));
                 this.setDoctorDOB(rs.getString(5));
                 this.setDoctorPhone(rs.getString(6));
                 data.add(this.getDoctorID());
                 data.add(this.getDoctorSPC());
                 data.add(this.getDoctorName());
                 data.add(this.getDoctorGender());
                 data.add(this.getDoctorDOB());
                 data.add(this.getDoctorPhone());
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
            String str = "select * from Doctor.Doctor where Id_Doctor = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, DoctorID);
            ResultSet rs = pr.executeQuery();
            while(rs.next())
            {
                 this.setDoctorID(rs.getString(1));
                 this.setDoctorSPC(rs.getString(2));
                 this.setDoctorName(rs.getString(3));
                 this.setDoctorGender(rs.getString(4));
                 this.setDoctorDOB(rs.getString(5));
                 this.setDoctorPhone(rs.getString(6));
                 data.add(this.getDoctorID());
                 data.add(this.getDoctorSPC());
                 data.add(this.getDoctorName());
                 data.add(this.getDoctorGender());
                 data.add(this.getDoctorDOB());
                 data.add(this.getDoctorPhone());
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return data;
    }
    
    public String[] FDDoctor()
    {
        try 
        {
            obj_koneksi.openConnection();
            String sq = "select Id_Doctor from Doctor.Doctor where DoctorName=? AND DoctorGender=?";
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, DoctorName);
            ps.setString(2, DoctorGender);
            
            ResultSet r = ps.executeQuery();
            String[] data = new String[1];
            if(r.next())
            {
                data[0]= r.getString("Id_Doctor");
            }
            else
            {
               data[0]= "kosong";
            }
            return data;          
        } 
        catch (Exception e) 
        {
        }
        return null;
    }
    
    
    
    
}
