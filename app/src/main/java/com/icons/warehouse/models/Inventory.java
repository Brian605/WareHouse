package com.icons.warehouse.models;

public class Inventory {
    private int id,quantity;
    private String productName,units;

    public Inventory(int id, int quantity, String productName, String units) {
        this.id = id;
        this.quantity = quantity;
        this.productName = productName;
        this.units = units;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getUnits() {
        return units;
    }
}
