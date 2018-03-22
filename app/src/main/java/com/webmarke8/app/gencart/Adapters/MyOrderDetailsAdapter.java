package com.webmarke8.app.gencart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.webmarke8.app.gencart.Objects.CartGroup;
import com.webmarke8.app.gencart.Objects.Order;
import com.webmarke8.app.gencart.Objects.OrderGroup;
import com.webmarke8.app.gencart.Objects.Products;
import com.webmarke8.app.gencart.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Manzoor Hussain on 2/22/2018.
 */

public class MyOrderDetailsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<OrderGroup> deptList;

    public MyOrderDetailsAdapter(Context context, List<OrderGroup> deptList) {
        this.context = context;
        this.deptList = deptList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<Order.ProductsObject> productList = deptList.get(groupPosition).getProductsObject();
        return productList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        List<Order.ProductsObject> productList = deptList.get(groupPosition).getProductsObject();
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.item_order_dialog, null);
        }
        String date_s = productList.get(1).getOrderDate();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-mm-dd");
        TextView Date = (TextView) view.findViewById(R.id.Date);
        Date.setText(dt1.format(date));
        TextView TotalPrice = (TextView) view.findViewById(R.id.TotalPrice);
        TotalPrice.setText(String.valueOf(getPrice(productList)));


        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        List<Order.ProductsObject> productList = deptList.get(groupPosition).getProductsObject();
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

        OrderGroup orderGroup = (OrderGroup) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.item_order_group, null);
        }

        TextView StoreName = (TextView) view.findViewById(R.id.StoreName);
        StoreName.setText(orderGroup.getStoreName().trim());

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

    public int getPrice(List<Order.ProductsObject> productsObjects) {
        int price = 0;
        for (Order.ProductsObject productsObject : productsObjects) {
            price = price + productsObject.getPrice();
        }
        return price;
    }
}