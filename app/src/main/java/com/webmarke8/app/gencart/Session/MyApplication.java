package com.webmarke8.app.gencart.Session;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.Objects.CartGroup;
import com.webmarke8.app.gencart.Objects.Customer;
import com.webmarke8.app.gencart.Objects.Owner;
import com.webmarke8.app.gencart.Objects.Products;

import java.util.ArrayList;
import java.util.List;

import br.vince.easysave.EasySave;

/**
 * Created by u on 20-Dec-17.
 */

public class MyApplication extends Application {


    Context mContext;
    static final String MYPREFERENCES = "MyPrefs";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    private static final String IS_LOGIN = "IsLoggedIn";


    List<CartGroup> CartGroupList = new ArrayList<>();


    private static MyApplication mInstance;

    public void onCreate() {
        super.onCreate();
        mInstance = this;
        editor = getSharedPreferences(MYPREFERENCES, MODE_PRIVATE).edit();
        sharedPreferences = getSharedPreferences(MYPREFERENCES, MODE_PRIVATE);
        CartGroupList = new EasySave(getApplicationContext()).retrieveList("Cart", CartGroup[].class);
        if (CartGroupList == null) {
            CartGroupList = new ArrayList<>();
        }

    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


    public void clearUserPreference(Context mContext) {

        this.mContext = mContext;
        editor.clear();
        editor.commit();
    }

    public void logoutUser() {

        new EasySave(getApplicationContext()).saveModel("customer", null);
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }


    public void createLoginSessionCustomer(Customer customer) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString("Type", "customer");
        new EasySave(getApplicationContext()).saveModel("customer", customer);
        editor.apply();
    }


    public Customer getLoginSessionCustomer() {

        return new EasySave(getApplicationContext()).retrieveModel("customer", Customer.class);

    }

    public String getType() {
        String value = sharedPreferences.getString("Type", null);
        return value;
    }

    public void setType(String Type) {

        editor.putString("Type", Type);
        editor.apply();

    }

    public void AddCartItem(Products products, String StoreName) {

        products.setQuantityInCart(1);
        boolean Exit = false;
        for (CartGroup cartGroup : CartGroupList
                ) {

            if (cartGroup.getName().equals(StoreName)) {
                cartGroup.getProductList().add(products);
                Exit = true;
            }
        }
        if (!Exit) {
            CartGroup cartGroup = new CartGroup();
            cartGroup.setName(StoreName);
            cartGroup.getProductList().add(products);

            List<CartGroup> idea = new ArrayList<>();
            idea.add(cartGroup);
            try {
                CartGroupList.addAll(idea);

            } catch (Exception Ex) {
                CartGroupList.add(cartGroup);
            }

//            CartGroupList.add(cartGroup);
        }

        new EasySave(getApplicationContext()).saveModel("Cart", CartGroupList);
    }

    public List<CartGroup> getCartGroupList() {
        return CartGroupList;
    }

    public void setCartGroupList(List<CartGroup> cartGroupList) {
        CartGroupList = cartGroupList;
    }

    public void IncreaseQuantity(String ProdutId, String StoreName) {

        for (CartGroup cartGroup : CartGroupList
                ) {

            if (cartGroup.getName().equals(StoreName)) {
                for (Products products : cartGroup.getProductList()
                        ) {

                    if (products.getId().equals(ProdutId)) {
                        products.setQuantityInCart(products.getQuantityInCart() + 1);
                    }
                }
            }
        }
        new EasySave(getApplicationContext()).saveModel("Cart", CartGroupList);

    }

    boolean removeparentcheck = false;

    public boolean DecreaseQuantity(String ProdutId, String StoreName) {

        int length = 0;
        int removeparent = 0;

        for (CartGroup cartGroup : CartGroupList
                ) {

            removeparent++;

            if (cartGroup.getName().equals(StoreName)) {
                for (Products products : cartGroup.getProductList()
                        ) {
                    length++;

                    if (products.getId().equals(ProdutId)) {
                        if (products.getQuantityInCart() == 1) {

                            cartGroup.getProductList().remove(length - 1);

                            new EasySave(getApplicationContext()).saveModel("Cart", CartGroupList);

                            return false;

                        } else {
                            products.setQuantityInCart(products.getQuantityInCart() - 1);
                        }
                    }
                }
            }
            if (removeparentcheck) {
                CartGroupList.remove(removeparent - 1);
            }
        }
        new EasySave(getApplicationContext()).saveModel("Cart", CartGroupList);

        return true;
    }

    public int getCartQuantity() {
        int Quantity = 0;
        for (CartGroup cartGroup : CartGroupList
                ) {
            for (Products products : cartGroup.getProductList()) {
                Quantity++;
            }
        }

        return Quantity;
    }

    public int getPriceOfAllStore() {
        int Price = 0;
        for (CartGroup cartGroup : CartGroupList
                ) {

            for (Products products : cartGroup.getProductList()) {

                int withQuantity = Integer.parseInt(products.getPrice()) * products.getQuantityInCart();
                Price = Price + withQuantity;
            }
        }
        return Price;
    }

    public int getPriceOfSingleStore(String StoreName) {
        int Price = 0;
        for (CartGroup cartGroup : CartGroupList
                ) {

            if (StoreName.equals(cartGroup.getName())) {
                for (Products products : cartGroup.getProductList()) {

                    int withQuantity = Integer.parseInt(products.getPrice()) * products.getQuantityInCart();

                    Price = Price + withQuantity;
                }
            }
        }
        return Price;
    }

    public int getInCartQuantity(String ID) {

        for (CartGroup cartGroup : CartGroupList
                ) {


            for (Products products : cartGroup.getProductList()) {

                if (ID.equals(products.getId())) {
                    return products.getQuantityInCart();
                }
            }

        }
        return 0;
    }

    public void CheckingCartGroupItems() {

        for (int i = 0; i < CartGroupList.size(); i++) {

            if (CartGroupList.get(i).getProductList().size() == 0) {
                try {
                    CartGroupList.remove(i);

                } catch (Exception e) {

                }
            }
        }
        new EasySave(getApplicationContext()).saveModel("Cart", CartGroupList);

    }
}