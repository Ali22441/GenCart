package com.webmarke8.app.gencart.Adapters;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.webmarke8.app.gencart.Fragments.ProfileFragment;
import com.webmarke8.app.gencart.Objects.Product;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ItemGridviewAdapter extends BaseAdapter {

    private Context context;
    private List<com.webmarke8.app.gencart.Objects.Product> Product;

    private FrameLayout frameLayout;
    private ScrollView scrollView;
    private MyApplication myApp;
    private int StockQty;

    public ItemGridviewAdapter(Context context, List<Product> Product, FrameLayout FrameLayout, ScrollView scrollView) {
        this.Product = Product;
        this.context = context;
        this.frameLayout = FrameLayout;
        this.scrollView = scrollView;
    }

    public void setMyApp(Application myApp) {
        this.myApp = (MyApplication) myApp;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return Product.get(position);
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
//            viewHolder.Price = (TextView) convertView.findViewById(R.id.Price);
//            viewHolder.addToCart = convertView.findViewById(R.id.AddToCatrd);
//            viewHolder.COndition = convertView.findViewById(R.id.Condition);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

//        final Product CProduct = (Product) getItem(position);



        viewHolder.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.fullScroll(ScrollView.FOCUS_UP);


                Fragment fragment = null;
                Class fragmentClass = null;

                fragmentClass = ProfileFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("Product", CProduct);
                    fragment.setArguments(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.Details, fragment).commit();

                frameLayout.setVisibility(View.VISIBLE);

            }
        });
//        viewHolder.addToCart.setTag(CProduct);
//        viewHolder.addToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        return convertView;
    }

    private class ViewHolder {
        ImageView image1;
        TextView Price;
        public TextView addToCart;
    }



//    public void OutOFStock() {
//        final Dialog dialog = new Dialog(context);
//        // inflate the layout
//        dialog.setContentView(R.layout.item_product);
//        // Set the dialog text -- this is better done in the XML
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


