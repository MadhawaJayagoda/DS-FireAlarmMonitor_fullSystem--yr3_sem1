
package com.ds.rmi.server;


public class User {
    
    private String name;
    private String email;
    private String phone;
    private String floor;
    private String room;

    public User(String name, String email, String phone, String floor, String room) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.floor = floor;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    
    
    
}
