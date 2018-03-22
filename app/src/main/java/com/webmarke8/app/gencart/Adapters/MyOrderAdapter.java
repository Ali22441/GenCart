package com.webmarke8.app.gencart.Adapters;

/**
 * Created by Asus on 2/1/2017.
 */

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.webmarke8.app.gencart.Objects.CartGroup;
import com.webmarke8.app.gencart.Objects.Order;
import com.webmarke8.app.gencart.Objects.OrderGroup;
import com.webmarke8.app.gencart.Objects.Products;
import com.webmarke8.app.gencart.Objects.Store;
import com.webmarke8.app.gencart.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    Context context;
    List<Order> models;
    List<OrderGroup> OrderList = new ArrayList<OrderGroup>();


    MyOrderDetailsAdapter listAdapter;
    ExpandableListView simpleExpandableListView;


    private LinkedHashMap<String, OrderGroup> List = new LinkedHashMap<String, OrderGroup>();
    private ArrayList<Order.ProductsObject> deptList = new ArrayList<Order.ProductsObject>();


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView Open;
        TextView OrderName, TotalPrice, Date;

        public ViewHolder(View v) {
            super(v);
            Open = (ImageView) itemView.findViewById(R.id.Open);
            OrderName = (TextView) itemView.findViewById(R.id.OrderName);
            TotalPrice = (TextView) itemView.findViewById(R.id.TotalPrice);
            Date = (TextView) itemView.findViewById(R.id.Date);


        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyOrderAdapter(Context context, List<Order> models) {
        this.context = context;
        this.models = models;
        ;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_order, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String date_s = models.get(position).getCreated_at();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-mm-dd");
        holder.TotalPrice.setText(models.get(position).getAmount() + " SAR");
        holder.Date.setText(dt1.format(date));
        holder.OrderName.setText("Order No. " + models.get(position).getId());
        holder.Open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenDetails(models.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public void OpenDetails(Order order) {


        final Dialog dialog = new Dialog(context);
        // inflate the layout
        dialog.setContentView(R.layout.dialog_order_details);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        loadData(order);
        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) dialog.findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new MyOrderDetailsAdapter(context, OrderList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
//                CartGroup headerInfo = deptList.get(groupPosition);
                //get the child info
//                Cart detailInfo = headerInfo.getProductList().get(childPosition);
                //display it or do something with it
                return false;
            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
//                CartGroup headerInfo = deptList.get(groupPosition);
                //display it or do something with it
                return false;
            }
        });


        // Set the dialog text -- this is better done in the XML
        final ImageView Cross = (ImageView) dialog.findViewById(R.id.Cross);
        Cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.hide();

            }
        });
        dialog.show();
    }


    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            simpleExpandableListView.expandGroup(i);
        }
    }


    private void loadData(Order storesObjects) {


        OrderGroup orderGroup = new OrderGroup();

        for (Order.StoresObject store : storesObjects.getStores()) {
            orderGroup.setStoreName(store.getName());

            for (Order.ProductsObject productsObject : storesObjects.getProducts()) {

                if (GetStoreName(productsObject.getStore_id(), storesObjects).equals(orderGroup.getStoreName())) {
                    productsObject.setOrderDate(storesObjects.getCreated_at());
                    orderGroup.getProductsObject().add(productsObject);
                }
            }
            OrderList.add(orderGroup);
        }


    }

    public String GetStoreName(int id, Order order) {
        for (Order.StoresObject store : order.getStores()) {
            if (store.getId() == id) {
                return store.getName();
            }
        }
        return "Store(Name?)";
    }

}