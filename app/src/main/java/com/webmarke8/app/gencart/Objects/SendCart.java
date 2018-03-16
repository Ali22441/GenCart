package com.webmarke8.app.gencart.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GeeksEra on 3/6/2018.
 */

public class SendCart implements Serializable {
    String customer_id;
    String address_lat_lng;
    String amount;
    String order_id="";

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    List<CartGroup> Stores=new ArrayList<>();

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getAddress_id() {
        return address_lat_lng;
    }

    public void setAddress_id(String address_id) {
        this.address_lat_lng = address_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<CartGroup> getStores() {
        return Stores;
    }

    public void setStores(List<CartGroup> stores) {
        Stores = stores;
    }
}
