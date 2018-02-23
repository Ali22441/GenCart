package com.webmarke8.app.gencart.Adapters;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.webmarke8.app.gencart.Objects.ProductStore;
import com.webmarke8.app.gencart.Objects.Products;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ItemGridviewAdapter extends BaseAdapter {

    private Context context;
    private ProductStore productStore;
    Products[] productsList;

    private FrameLayout frameLayout;
    private ScrollView scrollView;
    private MyApplication myApp;


    public ItemGridviewAdapter(Context context, ProductStore productStore, FrameLayout FrameLayout, ScrollView scrollView) {
        this.productStore = productStore;
        this.context = context;
        this.frameLayout = FrameLayout;
        this.scrollView = scrollView;
        productsList = productStore.getProducts();
    }

    public void setMyApp(Application myApp) {
        this.myApp = (MyApplication) myApp;
    }

    @Override
    public int getCount() {
        return productsList.length;
    }

    @Override
    public Object getItem(int position) {
        return productsList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_product_item, null);

            viewHolder = new ViewHolder();

            viewHolder.image1 = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.Click = (LinearLayout) convertView.findViewById(R.id.Click);


            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProductDetail();

            }
        });


        return convertView;
    }

    private class ViewHolder {
        ImageView image1;
        TextView Price;
        public TextView addToCart;
        LinearLayout Click;
    }


    public void ProductDetail() {
        final Dialog dialog = new Dialog(context);
        // inflate the layout
        dialog.setContentView(R.layout.dialog_product_details);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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
//
//    public void LowQuantity(String Availablle) {
//        final Dialog dialog = new Dialog(context);
//        // inflate the layout
//        dialog.setContentView(R.layout.item_short_dialog_low);
//        // Set the dialog text -- this is better done in the XML
//
//        TextView Message = (TextView) dialog.findViewById(R.id.Message);
//        Message.setText("We have only " + Availablle);
//        final LinearLayout Click = (LinearLayout) dialog.findViewById(R.id.Click);
//        Click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                dialog.hide();
//
//            }
//        });
//        dialog.show();
//    }
}


