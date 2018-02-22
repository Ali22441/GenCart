package com.webmarke8.app.gencart.Adapters;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.webmarke8.app.gencart.Fragments.ItemFragment;
import com.webmarke8.app.gencart.Fragments.ProductDetails;
import com.webmarke8.app.gencart.Objects.Product;
import com.webmarke8.app.gencart.Objects.Store;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;

import java.util.List;

public class StoreGridviewAdapter extends BaseAdapter {

    private Context context;
    private List<Store> Product;

    FrameLayout frameLayout;

    public StoreGridviewAdapter(Context context, List<Store> Product, FrameLayout frameLayout) {
        this.Product = Product;
        this.context = context;
        this.frameLayout = frameLayout;
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
            convertView = layoutInflater.inflate(R.layout.item_store, null);

            viewHolder = new ViewHolder();

            viewHolder.Click = (LinearLayout) convertView.findViewById(R.id.Click);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = null;
                Class fragmentClass = null;

                fragmentClass = ItemFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("Product", CProduct);
                    fragment.setArguments(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container1, fragment, "Items").commit();


            }
        });

        return convertView;
    }

    private class ViewHolder {
        LinearLayout Click;
    }

}


