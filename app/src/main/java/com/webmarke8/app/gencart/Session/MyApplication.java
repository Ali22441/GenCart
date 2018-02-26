package com.webmarke8.app.gencart.Session;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.webmarke8.app.gencart.Objects.Cart;
import com.webmarke8.app.gencart.Objects.CartGroup;
import com.webmarke8.app.gencart.Objects.Customer;
import com.webmarke8.app.gencart.Objects.Owner;

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
        // Clearing all data from Shared Preferences
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

    public void createLoginSessionOwner(Owner owner) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString("Type", "owner");
        new EasySave(getApplicationContext()).saveModel("owner", owner);
        editor.apply();
    }


    public Owner getLoginSessionOwner() {

        return new EasySave(getApplicationContext()).retrieveModel("owner", Owner.class);

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

    public void AddCartItem(Cart Item, String StoreName) {

        boolean Exit = false;
        for (CartGroup cartGroup : CartGroupList
                ) {

            if (cartGroup.getName().equals(StoreName)) {
                cartGroup.getProductList().add(Item);
                Exit = true;
            }
        }
        if (!Exit)

        {
            CartGroup cartGroup = new CartGroup();
            cartGroup.setName(StoreName);
            cartGroup.getProductList().add(Item);
            CartGroupList.add(cartGroup);
        }
    }

    public List<CartGroup> getCartGroupList() {
        return CartGroupList;
    }

    public void setCartGroupList(List<CartGroup> cartGroupList) {
        CartGroupList = cartGroupList;
    }

    public void IncreaseQuantity(String ProdutId, String StoreName, String DepartmentID) {

        for (CartGroup cartGroup : CartGroupList
                ) {

            if (cartGroup.getName().equals(StoreName)) {
                for (Cart cart : cartGroup.getProductList()
                        ) {

                    if (cart.getProductiD().equals(ProdutId) && cart.getDeparmtmentId().equals(DepartmentID)) {
                        cart.setQuantity(cart.getQuantity() + 1);
                    }
                }
            }
        }
    }

    public boolean DecreaseQuantity(String ProdutId, String StoreName, String DepartmentID) {

        int length = 0;
        for (CartGroup cartGroup : CartGroupList
                ) {

            if (cartGroup.getName().equals(StoreName)) {
                for (Cart cart : cartGroup.getProductList()
                        ) {
                    length++;

                    if (cart.getProductiD().equals(ProdutId) && cart.getDeparmtmentId().equals(DepartmentID)) {
                        if (cart.getProductiD().equals(1)) {

                            cartGroup.getProductList().remove(length);
                            return false;

                        } else {
                            cart.setQuantity(cart.getQuantity() - 1);

                        }
                    }
                }
            }
        }
        return true;
    }

}