package com.webmarke8.app.gencart.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GeeksEra on 3/6/2018.
 */

public class SendCart {
    String customer_id;
    String address_id;
    String amount;

    List<CartGroup> Stores=new ArrayList<>();

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
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
