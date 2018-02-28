package com.webmarke8.app.gencart.Adapters;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.Objects.Cart;
import com.webmarke8.app.gencart.Objects.ProductStore;
import com.webmarke8.app.gencart.Objects.Products;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;
import com.webmarke8.app.gencart.Utils.AppUtils;
import com.webmarke8.app.gencart.Utils.ServerData;

public class ItemGridviewAdapter extends BaseAdapter {

    private Context context;
    private ProductStore productStore;
    Products[] productsList;

    private ScrollView scrollView;
    private MyApplication myApp;


    public ItemGridviewAdapter(Context context, ProductStore productStore, ScrollView scrollView) {
        this.productStore = productStore;
        this.context = context;
        this.scrollView = scrollView;
        productsList = productStore.getProducts();
        myApp = (MyApplication) context.getApplicationContext();
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
            viewHolder.Price = (TextView) convertView.findViewById(R.id.Price);
            viewHolder.Name = (TextView) convertView.findViewById(R.id.Name);
            viewHolder.Quantity = (TextView) convertView.findViewById(R.id.Quantity);
            viewHolder.Increase = (ImageView) convertView.findViewById(R.id.Increase);
            viewHolder.Decrease = (ImageView) convertView.findViewById(R.id.Decrease);


            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }


        Picasso.with(context)
                .load(ServerData.UrlImage + productsList[position].getImage())
                .error(R.drawable.error_image)
                .transform(AppUtils.GetTransForm())
                .into(viewHolder.image1);
        viewHolder.Price.setText(productsList[position].getPrice() + " SAR");
        viewHolder.Name.setText(productsList[position].getName());


        final ViewHolder finalViewHolder1 = viewHolder;
        viewHolder.Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProductDetail(productsList[position], finalViewHolder1.Quantity.getText().toString());

            }
        });


        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.Increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finalViewHolder.Quantity.setText(String.valueOf(Integer.parseInt(finalViewHolder.Quantity.getText().toString()) + 1));

                if (finalViewHolder.Quantity.getText().equals("1")) {
                    Cart cart = new Cart();
                    cart.setDeparmtmentId(productsList[position].getDepartment_id());
                    cart.setProductDescription(productsList[position].getDescription());
                    cart.setProductiD(productsList[position].getId());
                    cart.setProductName(productsList[position].getName());
                    cart.setStoreId(productsList[position].getStore_id());
                    cart.setQuantity(Integer.valueOf(finalViewHolder.Quantity.getText().toString()));

                    myApp.AddCartItem(cart, productStore.getName());
                } else {
                    myApp.IncreaseQuantity(productsList[position].getId(), productStore.getName(), productsList[position].getDepartment_id());
                }

                ((MainActivity) context).Bandge(myApp.getCartGroupList().size());
            }
        });
        viewHolder.Decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!finalViewHolder.Quantity.getText().equals("0"))
                    finalViewHolder.Quantity.setText(String.valueOf(Integer.parseInt(finalViewHolder.Quantity.getText().toString()) - 1));

                myApp.DecreaseQuantity(productsList[position].getId(), productStore.getName(), productsList[position].getDepartment_id());
                ((MainActivity) context).Bandge(myApp.getCartGroupList().size());


            }
        });


        return convertView;
    }

    private class ViewHolder {
        ImageView image1;
        TextView Price, Name, Quantity;
        ImageView Increase, Decrease;
        LinearLayout Click;
    }


    public void ProductDetail(final Products products, String QuantityNOw) {

        ImageView image1;
        final TextView Price, Name, Quantity, Details;
        ImageView Increase, Decrease;
        final Dialog dialog = new Dialog(context);
        // inflate the layout
        dialog.setContentView(R.layout.dialog_product_details);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        image1 = (ImageView) dialog.findViewById(R.id.Image);
        Price = (TextView) dialog.findViewById(R.id.Price);
        Name = (TextView) dialog.findViewById(R.id.Name);
        Quantity = (TextView) dialog.findViewById(R.id.Quantity);
        Details = (TextView) dialog.findViewById(R.id.Details);
        Increase = (ImageView) dialog.findViewById(R.id.Increase);
        Decrease = (ImageView) dialog.findViewById(R.id.Decrease);

        Picasso.with(context)
                .load(ServerData.UrlImage + products.getImage())
                .error(R.drawable.error_image)
                .into(image1);
        Price.setText(products.getPrice() + " SAR");
        Name.setText(products.getName());
        Quantity.setText(QuantityNOw);
        Details.setText(products.getDescription());



        Increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Quantity.setText(String.valueOf(Integer.parseInt(Quantity.getText().toString()) + 1));

                if (Quantity.getText().equals("1")) {
                    Cart cart = new Cart();
                    cart.setDeparmtmentId(products.getDepartment_id());
                    cart.setProductDescription(products.getDescription());
                    cart.setProductiD(products.getId());
                    cart.setProductName(products.getName());
                    cart.setStoreId(products.getStore_id());
                    cart.setQuantity(Integer.valueOf(Quantity.getText().toString()));

                    myApp.AddCartItem(cart, productStore.getName());
                }


            }
        });

        Decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Quantity.getText().equals("0")) {
                    Quantity.setText(String.valueOf(Integer.parseInt(Quantity.getText().toString()) - 1));

                }

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


