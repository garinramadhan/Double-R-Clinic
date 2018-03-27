/*
 * To change this template, choose Tools | Templates
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
            pr.setDouble(2, SpcFare);
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
            String str = "UPDATE Doctor.Specialist SET Specialist =  ?," +
                    "Fare = ? "+
                    "where Id_Specialist = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, SpcName);
            pr.setDouble(2, SpcFare);
            pr.setString(3, SpcID);
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
    
    public ArrayList tableSpecialist(){
        ArrayList data = new ArrayList();
        String sql = "select * from Doctor.Specialist";
        try {
            Statement statement = obj_koneksi.con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
             while(rs.next())
             {
                 this.setSpcID(rs.getString(1));
                 this.setSpcName(rs.getString(2));
                 this.setSpcFare(rs.getDouble(3));
                 data.add(this.getSpcID());
                 data.add(this.getSpcName());
                 data.add(this.getSpcFare());
             }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return data;
    }
    
    public ArrayList getRecord(String search)
    {
        ArrayList data = new ArrayList();
        try
        {
            obj_koneksi.openConnection();
            String str = "select * from Doctor.Specialist where Id_Specialist like ? or Specialist like ?  or Fare like ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, "%"+search+"%");
            pr.setString(2, "%"+search+"%");
            pr.setString(3, "%"+search+"%");
            //pr.setDouble(3, Double.parseDouble("%"+SpcFare+"%"));
            ResultSet rs = pr.executeQuery();
            while(rs.next())
            {
                 this.setSpcID(rs.getString(1));
                 this.setSpcName(rs.getString(2));
                 this.setSpcFare(rs.getDouble(3));
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
    
    public String autoid(){
        String idSpecialist = "";
        try {
            obj_koneksi.openConnection();
            String sql1 = "Select Right(Id_Specialist,3) as 'Id_Specialist' from Doctor.Specialist Order by Id_Specialist DESC";
            Statement stat = obj_koneksi.con.createStatement();
            ResultSet rs = stat.executeQuery(sql1);
            rs.next();
            int autocode = rs.getInt("Id_Specialist");
            if(autocode < 9){
                idSpecialist = "SPC00" + Integer.toString(autocode + 1);
            }else if(autocode < 99){
                idSpecialist = "SPC0" + Integer.toString(autocode + 1);
            }else if(autocode < 999){
                idSpecialist = "SPC" + Integer.toString(autocode + 1);
            }else{
                idSpecialist = "full";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return idSpecialist;
    }
    
    public int specialistAvailable(){
        int jumlah = 0;
        try 
        {
            obj_koneksi.openConnection();
            String sq = "select count(Specialist) as Specialist from Doctor.Specialist where Specialist =?";
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, getSpcName());
            
            ResultSet r = ps.executeQuery();
            if(r.next())
            {
                jumlah = r.getInt("jumlah");
            }
            return jumlah;
        } 
        catch (Exception e) 
        {
        }
        return jumlah;
    }
}
