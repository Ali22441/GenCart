package com.webmarke8.app.gencart.Objects;

/**
 * Created by GeeksEra on 2/16/2018.
 */

public class Cart {

    String StoreId;
    String DeparmtmentId;
    String ProductiD;
    String ProductName, ProductDescription;
    int Quantity=0;

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getStoreId() {
        return StoreId;
    }

    public void setStoreId(String storeId) {
        StoreId = storeId;
    }

    public String getDeparmtmentId() {
        return DeparmtmentId;
    }

    public void setDeparmtmentId(String deparmtmentId) {
        DeparmtmentId = deparmtmentId;
    }

    public String getProductiD() {
        return ProductiD;
    }

    public void setProductiD(String productiD) {
        ProductiD = productiD;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }
}
