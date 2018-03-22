package com.webmarke8.app.gencart.Adapters;

/**
 * Created by Asus on 2/1/2017.
 */

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.webmarke8.app.gencart.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class MyCheckoutAdapter extends RecyclerView.Adapter<MyCheckoutAdapter.ViewHolder> {

    Context context;
    List<CartGroup> OrderList = new ArrayList<CartGroup>();

    List<Products> List = new ArrayList<>();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView Name, Price, Quantity;

        public ViewHolder(View v) {
            super(v);
            Name = (TextView) itemView.findViewById(R.id.ItemName);
            Price = (TextView) itemView.findViewById(R.id.Price);
            Quantity = (TextView) itemView.findViewById(R.id.Quantity);


        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyCheckoutAdapter(Context context, List<CartGroup> list) {
        this.context = context;
        this.OrderList = list;
        for (CartGroup productsObject : OrderList) {
            List.addAll(productsObject.getProductList());
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.Name.setText(List.get(position).getName());
        holder.Quantity.setText("*" + String.valueOf(List.get(position).getQuantityInCart()));
        holder.Price.setText(String.valueOf(Integer.parseInt(List.get(position).getPrice()) * List.get(position).getQuantityInCart()));

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}