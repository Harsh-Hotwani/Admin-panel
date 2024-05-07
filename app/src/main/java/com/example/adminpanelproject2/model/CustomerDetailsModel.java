package com.example.adminpanelproject2.model;

public class CustomerDetailsModel {
    private long id; // Customer ID (auto-generated)
    private String customerName;
    private String email;
    private String mobileNumber;
    private String city;

    public CustomerDetailsModel(long id, String customerName, String email, String mobileNumber, String city) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.city = city;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}