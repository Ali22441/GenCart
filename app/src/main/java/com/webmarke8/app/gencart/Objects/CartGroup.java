package com.webmarke8.app.gencart.Objects;

import java.util.ArrayList;

/**
 * Created by Manzoor Hussain on 2/22/2018.
 */
public class CartGroup {

    private String name;
    private ArrayList<Cart> list = new ArrayList<Cart>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Cart> getProductList() {
        return list;
    }

    public void setProductList(ArrayList<Cart> productList) {
        this.list = productList;
    }

}