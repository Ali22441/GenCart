package com.webmarke8.app.gencart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.Objects.CartGroup;
import com.webmarke8.app.gencart.Objects.Products;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manzoor Hussain on 2/22/2018.
 */

public class CartAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<CartGroup> deptList;

    MyApplication myApplication;
    TextView AllStoreItemPrice;

    public CartAdapter(Context context, List<CartGroup> deptList, TextView AllStoreItemPrice) {
        this.context = context;
        this.deptList = deptList;
        myApplication = (MyApplication) context.getApplicationContext();
        this.AllStoreItemPrice = AllStoreItemPrice;
        AllStoreItemPrice.setText("Sub Total: " + String.valueOf(myApplication.getPriceOfAllStore()) + " SAR");
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Products> productList = deptList.get(groupPosition).getProductList();
        return productList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        final Products products = (Products) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.item_cart, null);
        }

        TextView Description = (TextView) view.findViewById(R.id.Description);
        Description.setText(products.getDescription());
        TextView ProductName = (TextView) view.findViewById(R.id.ProductName);
        ProductName.setText(products.getName());
        final TextView Quantity = (TextView) view.findViewById(R.id.Quantity);
        Quantity.setText(String.valueOf(products.getQuantityInCart()));

        view.findViewById(R.id.Decrease).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Integer.parseInt(Quantity.getText().toString()) > 0) {

                    myApplication.DecreaseQuantity(products.getId(), deptList.get(groupPosition).getName());
                    Quantity.setText(String.valueOf(Integer.parseInt(Quantity.getText().toString()) - 1));
                }

                deptList = myApplication.getCartGroupList();
                notifyDataSetChanged();
                ((MainActivity) context).Bandge(myApplication.getCartQuantity());
                AllStoreItemPrice.setText("Sub Total: " + String.valueOf(myApplication.getPriceOfAllStore()) + " SAR");


            }
        });
        final View finalView = view;
        view.findViewById(R.id.Increase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Quantity.setText(String.valueOf(Integer.parseInt(Quantity.getText().toString()) + 1));
                myApplication.IncreaseQuantity(products.getId(), deptList.get(groupPosition).getName());
                deptList = myApplication.getCartGroupList();
                notifyDataSetChanged();
                ((MainActivity) context).Bandge(myApplication.getCartQuantity());
                AllStoreItemPrice.setText("Sub Total: " + String.valueOf(myApplication.getPriceOfAllStore()) + " SAR");


            }
        });

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<Products> productList = deptList.get(groupPosition).getProductList();
        return productList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return deptList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return deptList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        CartGroup headerInfo = (CartGroup) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.item_cart_group, null);
        }

        TextView Name = (TextView) view.findViewById(R.id.Name);
        Name.setText(headerInfo.getName().trim());

        TextView StoreItemPrice = (TextView) view.findViewById(R.id.StoreItemPrice);
        StoreItemPrice.setText(myApplication.getPriceOfSingleStore(headerInfo.getName()) + " SAR");


        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}