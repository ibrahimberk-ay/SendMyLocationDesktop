/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deneme.MapLabelApplication;

/**
 *
 * @author Berk
 */
public class Users {
    
    //Parametreler
    private String userId;
    private String userName;
    private double latitude;//Enlem
    private double longitude;//Boylam
    private String dateTime;
    private int priority;
    private String condition;
    
    //Parametresiz Consturctor
    public Users(){}
    //Parametreli Consturctor
    public Users(String userId, String userName, double latitude, double longitude,String dateTime,int priority,String condition){
        this.userId = userId;
        this.userName = userName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
        this.priority = priority;
        this.condition = condition;
    }
    
    
    //Getter ve Setter'lar
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
