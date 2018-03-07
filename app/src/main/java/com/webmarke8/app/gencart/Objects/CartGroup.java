package com.webmarke8.app.gencart.Objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Manzoor Hussain on 2/22/2018.
 */
public class CartGroup implements Serializable {

    private String name;
    private String StoreOrderPrice="10000";
    private ArrayList<Products> ProductList = new ArrayList<Products>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Products> getProductList() {
        return ProductList;
    }

    public void setProductList(ArrayList<Products> productList) {
        ProductList = productList;
    }
}