package com.webmarke8.app.gencart.Fragments;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.R;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Location_F extends Fragment implements OnMapReadyCallback {


    GoogleMap map;


    public Location_F() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location_, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        view.findViewById(R.id.UpdateLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateLocation();

            }
        });


        view.findViewById(R.id.Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim).remove(getFragmentManager().findFragmentById(R.id.container)).commit();

            }
        });


        view.findViewById(R.id.navigation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).OpenOpenOrCloseDrawer();
            }
        });


        return view;


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
//        LatLng UCA = new LatLng(-34, 151);
//        map.addMarker(new MarkerOptions().position(UCA).title("YOUR TITLE")).showInfoWindow();
//
//        map.animateCamera(CameraUpdateFactory.newLatLngZoom(UCA, 17));

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
