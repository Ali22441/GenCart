package com.webmarke8.app.gencart.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GeeksEra on 3/19/2018.
 */

public class OrderGroup {

    String StoreName;
   List< Order.ProductsObject> productsObject=new ArrayList<>();

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public List<Order.ProductsObject> getProductsObject() {
        return productsObject;
    }

    public void setProductsObject(List<Order.ProductsObject> productsObject) {
        this.productsObject = productsObject;
    }
}
