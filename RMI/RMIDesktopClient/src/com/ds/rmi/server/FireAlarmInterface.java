
package com.ds.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;


public interface FireAlarmInterface extends Remote{
    
    public boolean login(String user,String pass) throws RemoteException;
    
    public String registerSensor(String name,String email,String phone, int floor, int room) throws RemoteException;
    
    public String sensorList() throws RemoteException;
    
    public String getSensorData(String id) throws RemoteException;
    
    public String updateSensor(int id, String name,String email,String phone, int floor, int room) throws RemoteException;
    
    public String deleteSensor(String id) throws RemoteException;
    
    public void sendMail(String receiver, String floor, String room) throws RemoteException, MessagingException;
  
    
}
