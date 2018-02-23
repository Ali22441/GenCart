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

import com.webmarke8.app.gencart.Objects.Cart;
import com.webmarke8.app.gencart.Objects.CartGroup;
import com.webmarke8.app.gencart.Objects.Order;
import com.webmarke8.app.gencart.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    Context context;
    List<Order> models;



    MyOrderDetailsAdapter listAdapter;
    ExpandableListView simpleExpandableListView;


    private LinkedHashMap<String, CartGroup> subjects = new LinkedHashMap<String, CartGroup>();
    private ArrayList<CartGroup> deptList = new ArrayList<CartGroup>();



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv;
        ImageView Open;

        public ViewHolder(View v) {
            super(v);
            //    cv = (CardView) itemView.findViewById(R.id.cv);
            Open = (ImageView) itemView.findViewById(R.id.Open);

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
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

//        holder.personName.setText(models.get(position).name);
//
//     /*   final int itemPosition = position;
//        Log.d("zma position", String.valueOf(itemPosition));*/
        holder.Open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenDetails();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public void OpenDetails() {




        final Dialog dialog = new Dialog(context);
        // inflate the layout
        dialog.setContentView(R.layout.dialog_order_details);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        loadData();
        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) dialog.findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new MyOrderDetailsAdapter(context, deptList);
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


    //here we maintain our products in various departments
    private int addProduct(String department, String product) {

        int groupPosition = 0;

        //check the hash map if the group already exists
        CartGroup headerInfo = subjects.get(department);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new CartGroup();
            headerInfo.setName(department);
            subjects.put(department, headerInfo);
            deptList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<Cart> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        Cart detailInfo = new Cart();
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
    }

    private void loadData() {

        addProduct("Mr.Person's Grocery Store", "Spray");
        addProduct("Mr.Person's Grocery Store", "Spray");

        addProduct("Mr.Person's Grocery Store1", "Spray");
        addProduct("Mr.Person's Grocery Store1", "Spray");

        addProduct("Mr.Person's Grocery Store1", "Spray");
        addProduct("Mr.Person's Grocery Store1", "Spray");

    }

}