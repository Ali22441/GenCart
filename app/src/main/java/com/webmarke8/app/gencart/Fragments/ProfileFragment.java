package com.webmarke8.app.gencart.Fragments;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.medialablk.easytoast.EasyToast;
import com.webmarke8.app.gencart.Activities.Customer_Login;
import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.Objects.Customer;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;
import com.webmarke8.app.gencart.Utils.AppUtils;
import com.webmarke8.app.gencart.Utils.GPSTracker;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    TextView DetectAddress;
    GPSTracker gpsTracker;
    Dialog Progress;
    MyApplication myApplication;

    public ProfileFragment() {
        // Required empty public constructor
    }

    TextView Name, Phone;
    EditText Address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Progress = AppUtils.LoadingSpinner(getActivity());
        Progress.show();
        gpsTracker = new GPSTracker(getActivity());
        DetectAddress = (TextView) view.findViewById(R.id.DetectAddress);
        Name = (TextView) view.findViewById(R.id.Name);
        Phone = (TextView) view.findViewById(R.id.Phone);
        Address = (EditText) view.findViewById(R.id.Address);
        myApplication = (MyApplication) getActivity().getApplicationContext();
        Name.setText(myApplication.getLoginSessionCustomer().getSuccess().getUser().getName());
        Address.setText(myApplication.getLoginSessionCustomer().getSuccess().getUser().getAddress());
        Phone.setText(myApplication.getLoginSessionCustomer().getSuccess().getUser().getPhone());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DetectAddress.setText("Detect Address: " + AppUtils.getCompleteAddressString(gpsTracker.getLatitude(), gpsTracker.getLongitude(), getActivity()));
                gpsTracker.stopUsingGPS();

                Progress.dismiss();
            }
        }, 1000);


        view.findViewById(R.id.navigation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).OpenOpenOrCloseDrawer();
            }
        });


        view.findViewById(R.id.Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ((MainActivity) getActivity()).ShowHome();

            }
        });


        view.findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = new Customer();
                customer = myApplication.getLoginSessionCustomer();
                customer.getSuccess().getUser().setAddress(Address.getText().toString().trim());
                myApplication.createLoginSessionCustomer(customer);
                EasyToast.success(getActivity(), "Success");
            }
        });


        view.findViewById(R.id.ChnagePassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                // inflate the layout
                dialog.setContentView(R.layout.dialog_chnage_password);

                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.findViewById(R.id.Change).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();


                    }
                });

                dialog.findViewById(R.id.Cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();

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
        });
        return view;


    }

}
