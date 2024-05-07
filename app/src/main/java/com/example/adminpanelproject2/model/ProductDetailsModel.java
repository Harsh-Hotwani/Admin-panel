package com.example.adminpanelproject2.model;

public class ProductDetailsModel {
    private long id; // Product ID (auto-generated)
    private String productName;
    private int quantity;
    private double pricing;
    private double mrp;
    private long customerId; // Customer ID (foreign key)

    public ProductDetailsModel(long id, String productName, int quantity, double pricing, double mrp, long customerId) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.pricing = pricing;
        this.mrp = mrp;
        this.customerId = customerId;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricing() {
        return pricing;
    }

    public void setPricing(double pricing) {
        this.pricing = pricing;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
