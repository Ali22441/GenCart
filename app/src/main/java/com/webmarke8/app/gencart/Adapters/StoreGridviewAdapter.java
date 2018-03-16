package com.webmarke8.app.gencart.Adapters;

import android.app.Activity;
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
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.webmarke8.app.gencart.Fragments.ItemFragment;
import com.webmarke8.app.gencart.Fragments.Resturent_Fragemt;
import com.webmarke8.app.gencart.Objects.Store;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Utils.AppUtils;
import com.webmarke8.app.gencart.Utils.ServerData;

import java.util.List;

public class StoreGridviewAdapter extends BaseAdapter {

    private Context context;
    private List<Store> StoreList;

    FrameLayout frameLayout;

    public StoreGridviewAdapter(Context context, List<Store> StoreList, FrameLayout frameLayout) {
        this.StoreList = StoreList;
        this.context = context;
        this.frameLayout = frameLayout;
    }


    @Override
    public int getCount() {
        return this.StoreList.size();
    }

    @Override
    public Object getItem(int position) {
        return StoreList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder Holder = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_store, null);

            Holder = new ViewHolder();

            Holder.Click = (LinearLayout) convertView.findViewById(R.id.Click);
            Holder.StoreImage = (ImageView) convertView.findViewById(R.id.StoreImage);
            Holder.StoreName = (TextView) convertView.findViewById(R.id.StoreName);
            Holder.StoreDistance = (TextView) convertView.findViewById(R.id.Distance);
            Holder.StoreStatus = (TextView) convertView.findViewById(R.id.Status);
            Holder.StoreRatting = (TextView) convertView.findViewById(R.id.Ratting);

            convertView.setTag(Holder);

        } else {

            Holder = (ViewHolder) convertView.getTag();
        }


        Picasso.with(context)
                .load(ServerData.UrlImage + StoreList.get(position).getLogo())
                .transform(AppUtils.GetTransForm())
                .error(R.drawable.picturestore)
                .into(Holder.StoreImage);

        Holder.StoreName.setText(StoreList.get(position).getName());
        String[] Split = StoreList.get(position).getDistance().split("\\.");

        Holder.StoreDistance.setText(Split[0] + " miles");

        if (StoreList.get(position).getStatus().equals(true)) {

            Holder.StoreStatus.setText("Open");

        } else {

            Holder.StoreStatus.setText("Close");
        }

        Holder.Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (StoreList.get(position).getType().toLowerCase().contains("store")) {
                    Fragment fragment = null;
                    Class fragmentClass = null;

                    fragmentClass = ItemFragment.class;
                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Store", StoreList.get(position));
                        fragment.setArguments(bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.containerForFragments, fragment, "ItemFragment").addToBackStack(null).commit();

                    frameLayout.setVisibility(View.VISIBLE);
                } else {

                    Fragment fragment = null;
                    Class fragmentClass = null;

                    fragmentClass = Resturent_Fragemt.class;
                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Store", StoreList.get(position));
                        fragment.setArguments(bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.containerForFragments, fragment, "ItemFragment").addToBackStack(null).commit();

                    frameLayout.setVisibility(View.VISIBLE);

                }


            }
        });

        return convertView;
    }

    private class ViewHolder {
        LinearLayout Click;
        ImageView StoreImage;
        TextView StoreName, StoreStatus, StoreDistance, StoreRatting;
    }

}


