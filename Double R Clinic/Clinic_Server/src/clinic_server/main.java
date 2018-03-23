/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic_server;

import config.koneksi;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import model.MLogin;

/**
 *
 * @author GR
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        koneksi con = new koneksi();
        con.openConnection();
        try {
            Registry registry = LocateRegistry.createRegistry(1097);          
            MLogin objlogin = new MLogin();
            registry.rebind("objlogin", objlogin);
            System.out.println("Object is registered.");
            System.out.println("Now server is waiting for client request...");
        }
        catch(RemoteException e) {
            System.out.println("FileServer: " + e);
        }
    }
    
}
