
package com.ds.rmi.server;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.mail.MessagingException;


public class FireAlarmServer {
    
    public static void main(String[] args){
        try{
            Registry registry = LocateRegistry.createRegistry(1212);
            FireAlarmInterface fai = new FireAlarmImpl();
            registry.bind("FireAlarmServer", fai);
            System.out.println("Server Started.");
            
        }catch(RemoteException e){
            e.getMessage();
        }catch(AlreadyBoundException e){
            e.getMessage();
        }
    }
}
