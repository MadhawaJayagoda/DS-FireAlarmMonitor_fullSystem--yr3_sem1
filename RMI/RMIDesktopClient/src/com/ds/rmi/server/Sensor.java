
package com.ds.rmi.server;


public class Sensor {
    
    private String sensor_id;
    private String floor_no;
    private String room_no;
    private String smoke_level;
    private String carbondioxide_level;
    private String sensor_status;
    private String alert_status;
    private String created_date;
    private String owner_name;
    private String email;
    private String phone_number;
    
    public Sensor(){
        
    }

    public Sensor(String sensor_id, String floor_no, String room_no, String smoke_level, String carbondioxide_level, String sensor_status, String alert_status, String created_date, String owner_name, String email, String phone_number) {
        this.sensor_id = sensor_id;
        this.floor_no = floor_no;
        this.room_no = room_no;
        this.smoke_level = smoke_level;
        this.carbondioxide_level = carbondioxide_level;
        this.sensor_status = sensor_status;
        this.alert_status = alert_status;
        this.created_date = created_date;
        this.owner_name = owner_name;
        this.email = email;
        this.phone_number = phone_number;
    }

    public String getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(String sensor_id) {
        this.sensor_id = sensor_id;
    }

    public String getFloor_no() {
        return floor_no;
    }

    public void setFloor_no(String floor_no) {
        this.floor_no = floor_no;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getSmoke_level() {
        return smoke_level;
    }

    public void setSmoke_level(String smoke_level) {
        this.smoke_level = smoke_level;
    }

    public String getCarbondioxide_level() {
        return carbondioxide_level;
    }

    public void setCarbondioxide_level(String carbondioxide_level) {
        this.carbondioxide_level = carbondioxide_level;
    }

    public String getSensor_status() {
        return sensor_status;
    }

    public void setSensor_status(String sensor_status) {
        this.sensor_status = sensor_status;
    }

    public String getAlert_status() {
        return alert_status;
    }

    public void setAlert_status(String alert_status) {
        this.alert_status = alert_status;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "Sensor{" + "sensor_id=" + sensor_id + ", floor_no=" + floor_no + ", room_no=" + room_no + ", smoke_level=" + smoke_level + ", carbondioxide_level=" + carbondioxide_level + ", sensor_status=" + sensor_status + ", alert_status=" + alert_status + ", created_date=" + created_date + ", owner_name=" + owner_name + ", email=" + email + ", phone_number=" + phone_number + '}';
    }

  
}
