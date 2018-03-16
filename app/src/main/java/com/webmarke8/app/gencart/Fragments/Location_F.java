package com.webmarke8.app.gencart.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

//import com.ahmadrosid.lib.drawroutemap.DrawMarker;
//import com.ahmadrosid.lib.drawroutemap.DrawRouteMaps;
import com.arsy.maps_library.MapRipple;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.medialablk.easytoast.EasyToast;
import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.R;
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
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;


/**
 * A simple {@link Fragment} subclass.
 */
public class Location_F extends Fragment implements OnMapReadyCallback, AdapterView.OnItemClickListener {


    GoogleMap mMap;
    FusedLocationProviderClient mFusedLocationProviderClient;
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;

    TextView LocationAddress;
    MapRipple mapRipple;
    GPSTracker gpsTracker;
    LatLng latLng;
    Dialog dialog;


    private static final String API_KEY = "AIzaSyCWSJNo4sQfVonDPjn0CVhWmK07aypSebA";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    AutoCompleteTextView edtAddress;


    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;


    public Location_F() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location_, container, false);

        gpsTracker = new GPSTracker(getActivity());
        dialog = AppUtils.LoadingSpinnerDialog(getActivity());
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

                EasyToast.info(getActivity(), "We are Searching... Your Rider");
//                show();

            }
        });

        try {

            latLng = new LatLng(getArguments().getDouble("lat"), getArguments().getDouble("long"));

        } catch (Exception Ex) {

            latLng = new LatLng(12.12312, 12.12312);
        }

        LocationAddress = (TextView) view.findViewById(R.id.LocationAddress);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

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

//                if (mapRipple.isAnimationRunning()) {
//                    SuperActivityToast.create(getActivity(), new Style(), Style.TYPE_BUTTON)
//                            .setButtonText("Cancel")
//                            .setOnButtonClickListener("good_tag_name", null, new SuperActivityToast.OnButtonClickListener() {
//                                @Override
//                                public void onClick(View view, Parcelable token) {
//                                    getActivity().getSupportFragmentManager().popBackStack();
//                                }
//                            })
//                            .setText("Do you want to cancel booking?")
//                            .setDuration(Style.DURATION_LONG)
//                            .setColor(PaletteUtils.getSolidColor(PaletteUtils.BLACK))
//                            .setAnimations(Style.ANIMATIONS_POP).show();
//                } else {
//                    getActivity().getSupportFragmentManager().popBackStack();
//                }
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        view.findViewById(R.id.navigation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).OpenOpenOrCloseDrawer();
            }
        });
        view.findViewById(R.id.ShareLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();
//                LatLng latLng = new LatLng(gpsTracker.getLatitude(),
//                        gpsTracker.getLongitude());
//
//                if (mapRipple == null)
//                    mapRipple = new MapRipple(mMap, latLng, getActivity());
//                mapRipple.withNumberOfRipples(3);
//                mapRipple.withFillColor(Color.parseColor("#FFA3D2E4"));
//                mapRipple.withStrokeColor(Color.BLACK);
//                mapRipple.withStrokewidth(0);      // 10dp
//                mapRipple.withDistance(2000);      // 2000 metres radius
//                mapRipple.withRippleDuration(12000);    //12000ms
//                mapRipple.withTransparency(0.5f);
//
//                if (!mapRipple.isAnimationRunning()) {
//                    mapRipple.startRippleMapAnimation();
//                }
            }
        });

        return view;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng mDefaultLocation = latLng;


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
        mMap.getUiSettings().setMyLocationButtonEnabled(true);


        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getActivity(), R.raw.style));

            if (!success) {
                Log.e("MapsActivityRaw", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MapsActivityRaw", "Can't find style.", e);
        }

        try {
            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(getArguments().getString("address"))
                    .rotation((float) -15.0)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
            );
        } catch (Exception a) {

        }


        updateLocationUI();
        // Get the current location of the device and set the position of the map.
        getDeviceLocation();

    }

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
                    LocationAddress.setText(edtAddress.getText().toString());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
                    mMap.getUiSettings().setMyLocationButtonEnabled(true);

                    mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title(edtAddress.getText().toString())
                            .rotation((float) -15.0)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                    );
                }

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

    private void getLocationPermission() {

        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getDeviceLocation() {
    /*
     * Get the best and most recent location of the device, which may be null in rare
     * cases when a location is not available.
     */
        try {
            if (mLocationPermissionGranted) {
                Task locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = (Location) task.getResult();
                            if (mLastKnownLocation != null) {
//                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                                        new LatLng(mLastKnownLocation.getLatitude(),
//                                                mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));


                                LatLng origin = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                                LatLng destination = new LatLng(33.5204652, 73.0801742);
                                DrawPath(origin, destination);

                                try {
                                    LocationAddress.setText(getArguments().getString("address"));
                                } catch (Exception a) {
                                    LocationAddress.setText(getCompleteAddressString(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()));

                                }

                            }
                        } else {
                            Log.d("GenCarLOcation", "Current location is null. Using defaults.");
                            Log.e("GenCarLOcation", "Exception: %s", task.getException());
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.d("GetCart", strReturnedAddress.toString());
            } else {
                Log.d("GetCart", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("GetCart", "Canont get Address!");
        }
        return strAdd;
    }

    public void DrawPath(LatLng origin, LatLng destination) {

//        DrawRouteMaps.getInstance(getActivity())
//                .draw(origin, destination, mMap);
//        DrawMarker.getInstance(getActivity()).draw(mMap, origin, R.drawable.personchat, "Driver");
//        DrawMarker.getInstance(getActivity()).draw(mMap, destination, R.drawable.location_map, "UserLocation");
//
//        LatLngBounds bounds = new LatLngBounds.Builder()
//                .include(origin)
//                .include(destination).build();
//        Point displaySize = new Point();
//        getActivity().getWindowManager().getDefaultDisplay().getSize(displaySize);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x, 250, 30));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        try
        {
            String address = (String) adapterView.getItemAtPosition(position);
            Log.d("address>>>", address);
            Geocoder coder = new Geocoder(getActivity());
            List<Address> addressList = null;
            try {
                // May throw an IOException
                addressList = coder.getFromLocationName(address, 5);
                Address location = addressList.get(0);
                latLng = new LatLng(location.getLatitude(), location.getLongitude());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            LocationAddress.setText(address);
            edtAddress.setText("");
            edtAddress.setText(address);
            edtAddress.setSelection(edtAddress.getText().toString().length());
        }catch (Exception Ex)
        {

        }

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

    public void show() {
        dialog.show();
    }

}
