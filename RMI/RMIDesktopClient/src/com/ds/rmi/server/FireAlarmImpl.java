package com.ds.rmi.server;

import com.ds.rmi.server.FireAlarmInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class FireAlarmImpl extends UnicastRemoteObject implements FireAlarmInterface{
    
    public FireAlarmImpl() throws RemoteException{
        
    }

    @Override
    public boolean login(String user, String pass) throws RemoteException {
        //To change body of generated methods, choose Tools | Templates.
        return (user.equals("admin") && pass.equals("admin"));

    }

    @Override
    public String registerSensor(String name, String email, String phone, int floor, int room) throws RemoteException {
        try {
            //To change body of generated methods, choose Tools | Templates.

            URL url = new URL ("http://localhost:3000/sensorRecord");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            JsonObject newSensor = new JsonObject();
            newSensor.addProperty("floor_no", floor);
            newSensor.addProperty("room_no", room);
            newSensor.addProperty("owner_name", name);
            newSensor.addProperty("email", email);
            newSensor.addProperty("phone_number", phone);
            
            OutputStream os = con.getOutputStream();
            os.write(newSensor.toString().getBytes());  
            os.flush();
            
            if (con.getResponseCode() == 200) { 
                
                return "New Sensor Registered Successfuly.";
                
            }else if(con.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP error code : "
                        + con.getResponseCode());
                
            }

            System.out.println("byte post : "+newSensor);
            
               
            return con.getResponseMessage();
   
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(FireAlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
            return  "Sensor Registering Failed. Error Code: "+ex.getMessage();
            
        } catch (IOException ex) {
            Logger.getLogger(FireAlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
            return  "Sensor Registering Failed. Error Code: "+ex.getMessage();
            
        } 
        
    }
    
    

    @Override
    public String updateSensor(int id, String name, String email, String phone, int floor, int room) throws RemoteException {
        //To change body of generated methods, choose Tools | Templates.
        
        try {
            //To change body of generated methods, choose Tools | Templates.

            URL url = new URL ("http://localhost:3000/sensorData/"+id);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            JsonObject newSensor = new JsonObject();
//            newSensor.addProperty("sensor_id", id);
            newSensor.addProperty("floor_no", floor);
            newSensor.addProperty("room_no", room);
            newSensor.addProperty("owner_name", name);
            newSensor.addProperty("email", email);
            newSensor.addProperty("phone_number", phone);
            
            OutputStream os = con.getOutputStream();
            os.write(newSensor.toString().getBytes());  
            os.flush();
            
            if (con.getResponseCode() == 200) {    
                return "Sensor Updated Successfuly.";
                
            }else if(con.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP error code : "
                        + con.getResponseCode());
                
            }

            System.out.println("byte post : "+newSensor);
            
               
            return con.getResponseMessage();
   
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(FireAlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
            return  "Sensor Updating Failed. Error Code: "+ex.getMessage();
            
        } catch (IOException ex) {
            Logger.getLogger(FireAlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
            return  "Sensor Updating Failed. Error Code: "+ex.getMessage();
            
        } 
        
    }

    @Override
    public String deleteSensor(String id) {
        //To change body of generated methods, choose Tools | Templates.
        
        try {
            //To change body of generated methods, choose Tools | Templates.

            URL url = new URL ("http://localhost:3000/sensorData/"+id);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("DELETE");

            
            
            if (con.getResponseCode() == 200) {    
                return "ensor Updated Successfuly.";
                
            }else if(con.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP error code : "
                        + con.getResponseCode());
            }
            return con.getResponseMessage();
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(FireAlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
            return  "Sensor Deleting Failed. Error Code: "+ex.getMessage();
            
        } catch (IOException ex) {
            Logger.getLogger(FireAlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
            return  "Sensor Deleting Failed. Error Code: "+ex.getMessage();
            
        } 
    }
    
    
    @Override
    public String sensorList() throws RemoteException {
        //To change body of generated methods, choose Tools | Templates.
        
        try {
            URL url = new URL("http://localhost:3000/sensorData");
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            String json = "";
            System.out.println("Output from Server .... \n");
            
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                json += output;
            }
            conn.disconnect();

            return json;
            
                
        } catch (IOException | RuntimeException ex) {
            System.out.println("Error:==="+ex);
            return null;
        } 
 
        
    }

    @Override
    public String getSensorData(String id) throws RemoteException {
        try {
            //To change body of generated methods, choose Tools | Templates.
            
            String sUrl = "http://localhost:3000/sensorData/"+id;
            URL url = new URL(sUrl);
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            String json = "";
            System.out.println("Output from Server .... \n");
            
            while ((output = br.readLine()) != null) {
//                System.out.println("line: "+output);
                json += output;
            }
            conn.disconnect();

            System.out.println("Sensor Data Json: "+json);
            return json;
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(FireAlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage().toString();
        } catch (IOException ex) {
            Logger.getLogger(FireAlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage().toString();
        }
        
    }

    @Override
    public void sendMail(String receiver, String floor, String room) throws RemoteException, MessagingException {
        //To change body of generated methods, choose Tools | Templates.
        
       try {
            //To change body of generated methods, choose Tools | Templates.

            URL url = new URL ("http://localhost:25000/sendEmail");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            JsonObject newSensor = new JsonObject();
            newSensor.addProperty("room_no", receiver);
            newSensor.addProperty("floor", floor);
            newSensor.addProperty("room", room);
            
            OutputStream os = con.getOutputStream();
            os.write(newSensor.toString().getBytes());  
            os.flush();
            
            if (con.getResponseCode() == 200) { 
                
                System.out.println("New Sensor Registered Successfuly.");
                
            }else if(con.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP error code : "
                        + con.getResponseCode());
                
            }

            System.out.println("byte post : "+newSensor);
            
 
        } catch (MalformedURLException ex) {
            Logger.getLogger(FireAlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
 
            
        } catch (IOException ex) {
            Logger.getLogger(FireAlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        } 
        
    }
        
    }

    
}
