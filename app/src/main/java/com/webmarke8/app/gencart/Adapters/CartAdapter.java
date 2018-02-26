package com.webmarke8.app.gencart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.Objects.Cart;
import com.webmarke8.app.gencart.Objects.CartGroup;
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

    public CartAdapter(Context context, List<CartGroup> deptList) {
        this.context = context;
        this.deptList = deptList;
        myApplication = (MyApplication) context.getApplicationContext();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Cart> productList = deptList.get(groupPosition).getProductList();
        return productList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        final Cart detailInfo = (Cart) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.item_cart, null);
        }

        TextView Description = (TextView) view.findViewById(R.id.Description);
        Description.setText(detailInfo.getProductDescription());
        TextView ProductName = (TextView) view.findViewById(R.id.ProductName);
        ProductName.setText(detailInfo.getProductName());
        final TextView Quantity = (TextView) view.findViewById(R.id.Quantity);
        Quantity.setText(String.valueOf(detailInfo.getQuantity()));

        view.findViewById(R.id.Decrease).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myApplication.DecreaseQuantity(detailInfo.getProductiD(), deptList.get(groupPosition).getName(), detailInfo.getDeparmtmentId());
                notifyDataSetChanged();
                ((MainActivity) context).Bandge(myApplication.getCartGroupList().size());


            }
        });
        final View finalView = view;
        view.findViewById(R.id.Increase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Quantity.setText(String.valueOf(Integer.parseInt(Quantity.getText().toString()) + 1));
                myApplication.IncreaseQuantity(detailInfo.getProductiD(), deptList.get(groupPosition).getName(), detailInfo.getDeparmtmentId());
                notifyDataSetChanged();
                ((MainActivity) context).Bandge(myApplication.getCartGroupList().size());

            }
        });

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<Cart> productList = deptList.get(groupPosition).getProductList();
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