package com.webmarke8.app.gencart.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.Adapters.MyCheckoutAdapter;
import com.webmarke8.app.gencart.Adapters.MyOrderAdapter;
import com.webmarke8.app.gencart.Objects.CartGroup;
import com.webmarke8.app.gencart.Objects.Order;
import com.webmarke8.app.gencart.Objects.SendCart;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;
import com.webmarke8.app.gencart.Utils.AppUtils;
import com.webmarke8.app.gencart.Utils.GPSTracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CheckOut extends Fragment implements AdapterView.OnItemClickListener {


    TextView DetectAddress;
    GPSTracker gpsTracker;
    Dialog Progress;

    SendCart sendCart;
    MyApplication myApplication;
    LatLng lat_long;

    RecyclerView rvAllCategories;
    private RecyclerView.Adapter mAdapter;
    List<CartGroup> models;

    public CheckOut() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_out, container, false);


        myApplication = (MyApplication) getActivity().getApplicationContext();
        gpsTracker = new GPSTracker(getActivity());
        DetectAddress = (TextView) view.findViewById(R.id.DetectAddress);
        Progress = AppUtils.LoadingSpinner(getActivity());
        Progress.show();
        sendCart = (SendCart) getArguments().getSerializable("Orders");

        models = new ArrayList<>();
        models = sendCart.getStores();

        rvAllCategories = view.findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(getActivity());
        rvAllCategories.setLayoutManager(mLayoutManager);
        rvAllCategories.setHasFixedSize(true);
        mAdapter = new MyCheckoutAdapter(getActivity(), models);
        rvAllCategories.setAdapter(mAdapter);


        TextView Quantity = (TextView) view.findViewById(R.id.Quantity
        );
        TextView TotalPrice = (TextView) view.findViewById(R.id.TotalPrice);
        TotalPrice.setText(String.valueOf(sendCart.getAmount()) + " SAR");
        Quantity.setText(String.valueOf(myApplication.getCartQuantity()));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                DetectAddress.setText(AppUtils.getCompleteAddressString(gpsTracker.getLatitude(), gpsTracker.getLongitude(), getActivity()));
                lat_long = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
                gpsTracker.stopUsingGPS();
                Progress.dismiss();

            }
        }, 1000);


        view.findViewById(R.id.NewDelivery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateLocation();

            }
        });
        view.findViewById(R.id.navigation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).OpenOpenOrCloseDrawer();
            }
        });


        view.findViewById(R.id.Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().popBackStack();

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
                    bundle.putDouble("lat", lat_long.latitude);
                    bundle.putDouble("long", lat_long.longitude);
                    bundle.putSerializable("Orders", sendCart);
                    bundle.putString("address", edtAddress.getText().toString());
                    fragment.setArguments(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_UNSET).replace(R.id.containerForFragments, fragment, "Location").addToBackStack(null).commit();

            }
        });
        return view;


    }


    private static final String API_KEY = "AIzaSyCWSJNo4sQfVonDPjn0CVhWmK07aypSebA";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    AutoCompleteTextView edtAddress;


    public void UpdateLocation() {

        final Dialog dialog = new Dialog(getActivity());
        // inflate the layout
        dialog.setContentView(R.layout.dialog_update_location);
        edtAddress = (AutoCompleteTextView) dialog.findViewById(R.id.completeTextView);
        edtAddress.setAdapter(new GooglePlacesAutocompleteAdapter(getActivity(), R.layout.item_location_string));
        edtAddress.setOnItemClickListener(this);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.findViewById(R.id.OK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!edtAddress.getText().toString().equals("")) {
                    DetectAddress.setText(edtAddress.getText().toString());
                }
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Progress.show();
        String address = (String) adapterView.getItemAtPosition(position);
        Log.d("address>>>", address);
        Geocoder coder = new Geocoder(getActivity());
        List<Address> addressList = null;
        try {
            // May throw an IOException
            addressList = coder.getFromLocationName(address, 5);
            Address location = addressList.get(0);
            lat_long = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        edtAddress.setText("");
        edtAddress.setText(address);
        edtAddress.setSelection(edtAddress.getText().toString().length());
        Progress.dismiss();
    }

    class GooglePlacesAutocompleteAdapter extends ArrayAdapter<String> implements Filterable {
        private ArrayList<String> resultList;

        public GooglePlacesAutocompleteAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public int getCount() {
            return resultList.size();
        }

        @Override
        public String getItem(int index) {
            return resultList.get(index);
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        // Retrieve the autocomplete results.
                        resultList = autocomplete(constraint.toString());

                        // Assign the data to the FilterResults
                        filterResults.values = resultList;
                        filterResults.count = resultList.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            };
            return filter;
        }
    }

    public static ArrayList<String> autocomplete(String input) {
        ArrayList<String> resultList = null;
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);
            // sb.append("&components=country:US");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));
            URL url = new URL(sb.toString());
            System.out.println("URL: " + url);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            //Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            //Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {

            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList<String>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
                System.out.println("============================================================");
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {
            //Log.e(LOG_TAG, "Cannot process JSON results", e);
            e.printStackTrace();
        }

        return resultList;
    }
}

