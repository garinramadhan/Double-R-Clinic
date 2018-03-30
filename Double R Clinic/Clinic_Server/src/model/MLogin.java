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
import object.InLogin;

/**
 *
 * @author GR
 */
public class MLogin extends UnicastRemoteObject implements InLogin {
    private String UserID, Username, Password, IsAdmin;
    private koneksi obj_koneksi = new koneksi();
    
    public MLogin()throws Exception{
        super();
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(String IsAdmin) {
        this.IsAdmin = IsAdmin;
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
            String str = "insert into AccessLogin(Id_User, Username, UPassword, isAdmin) values(?,?,?,?)";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, UserID);
            pr.setString(2, Username);
            pr.setString(3, Password);
            pr.setString(4, IsAdmin);
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
            String str = "UPDATE AccessLogin SET UPassword =  ?," +
                    "where Id_User = ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, Password);
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
         String str = "delete AccessLogin where Id_User= ?";
         PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
         pr.setString(1, UserID);
         i = pr.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
    public ArrayList getRecord(String search)
    {
        ArrayList data = new ArrayList();
        try
        {
            obj_koneksi.openConnection();
            String str = "select * from AccessLogin where Id_User like ? or Username like ?";
            PreparedStatement pr = obj_koneksi.con.prepareStatement(str);
            pr.setString(1, "%"+search+"%");
            pr.setString(2, "%"+search+"%");
            ResultSet rs = pr.executeQuery();
            while(rs.next())
            {
                 this.setUserID(rs.getString(1));
                 this.setUsername(rs.getString(2));
                 this.setPassword(rs.getString(3));
                 this.setIsAdmin(rs.getString(4));
                 data.add(this.getUserID());
                 data.add(this.getUsername());
                 data.add(this.getPassword());
                 data.add(this.getIsAdmin());
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return data;
    }
    
    public ArrayList display()
    {
         ArrayList data = new ArrayList();
         try
         {
             obj_koneksi.openConnection();
             Statement stmt = obj_koneksi.con.createStatement();
             String str = "select * from AccessLogin where isAdmin = 0";
             ResultSet rs = stmt.executeQuery(str);
             while(rs.next())
             {
                 this.setUserID(rs.getString(1));
                 this.setUsername(rs.getString(2));
                 this.setPassword(rs.getString(3));
                 this.setIsAdmin(rs.getString(4));
                 data.add(this.getUserID());
                 data.add(this.getUsername());
                 data.add(this.getPassword());
                 data.add(this.getIsAdmin());
             }
         }
         catch(SQLException ex)
         {
             System.out.println(ex.getMessage());
         }
         return data;
    }
    
    public String[] FDUser()
    {
        try 
        {
            obj_koneksi.openConnection();
            String sq = "select Id_User from AccessLogin where Username=? AND UPassword=?";
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, Username);
            ps.setString(2, Password);
            
            ResultSet r = ps.executeQuery();
            String[] data = new String[1];
            if(r.next())
            {
                data[0]= r.getString("Id_User");
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
    
    public int doLogin()
    {
        try 
        {
            obj_koneksi.openConnection();
            String sq = "select count(*) as jumlah from AccessLogin where Username=? and UPassword=?";
            //pake prepare statment karena ada parameter
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, getUsername());
            ps.setString(2, getPassword());
            
            ResultSet r = ps.executeQuery();
            int jumlah = 0;
            if(r.next())
            {
                jumlah = r.getInt("jumlah");
            }
            return jumlah;          
        } 
        catch (Exception e) 
        {
        }
        return 0;
    }
    
    public String[] cekAccess()
    {
        try 
        {
            obj_koneksi.openConnection();
            String sq = "select Id_User, Username, isAdmin from AccessLogin where Username=? AND UPassword=?";
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, Username);
            ps.setString(2, Password);
            
            ResultSet r = ps.executeQuery();
            String[] data = new String[3];
            if(r.next())
            {
                data[0]= r.getString("Id_User");
                data[1]= r.getString("Username");
                data[2]= r.getString("isAdmin");
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
    
    public int cekUsername(){
        try 
        {
            obj_koneksi.openConnection();
            String sq = "select count (*) 'hasil' from AccessLogin where Username = ?";
            PreparedStatement ps = obj_koneksi.con.prepareStatement(sq);
            ps.setString(1, getUsername());
            
            ResultSet r = ps.executeQuery();
            int jumlah = 0;
            if(r.next())
            {
                jumlah = r.getInt("hasil");
            }
            return jumlah;          
        } 
        catch (Exception e) 
        {
        }
        return 0;
    }
    
    public String autoid(){
        String idUser = "";
        try {
            obj_koneksi.openConnection();
            String sql1 = "Select Right(Id_User,2) as 'Id_User' from AccessLogin Order by Id_User DESC";
            Statement stat = obj_koneksi.con.createStatement();
            ResultSet rs = stat.executeQuery(sql1);
            rs.next();
            int autocode = rs.getInt("Id_User");
            if(autocode < 9){
                idUser = "USR0" + Integer.toString(autocode + 1);
            }else if(autocode < 99){
                idUser = "USR" + Integer.toString(autocode + 1);
            }else{
                idUser = "full";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return idUser;
    }
}
