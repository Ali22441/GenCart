package com.webmarke8.app.gencart.Fragments;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.webmarke8.app.gencart.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CheckOut extends Fragment {


    public CheckOut() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_out, container, false);


        view.findViewById(R.id.NewDelivery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateLocation();

            }
        });

        view.findViewById(R.id.Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim)
                        .replace(R.id.container, new MyCartFragment(), "MyCartFragment").commit();

            }
        });

        view.findViewById(R.id.Checkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                Class fragmentClass = null;

                fragmentClass = Location_F.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("Product", CProduct);
                    fragment.setArguments(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim).replace(R.id.container, fragment, "Location").commit();

            }
        });
        return view;


    }

    public void UpdateLocation() {
        final Dialog dialog = new Dialog(getActivity());
        // inflate the layout
        dialog.setContentView(R.layout.dialog_update_location);
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

}
