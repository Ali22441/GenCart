package com.webmarke8.app.gencart.Fragments;


import android.Manifest;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.Adapters.ChatAdapter;
import com.webmarke8.app.gencart.Objects.Chat_Object;
import com.webmarke8.app.gencart.Objects.Driver;
import com.webmarke8.app.gencart.Objects.RideResponse;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;
import com.webmarke8.app.gencart.Utils.AppUtils;
import com.webmarke8.app.gencart.Utils.DecimalUtils;
import com.webmarke8.app.gencart.Utils.DistanceCalculator;
import com.webmarke8.app.gencart.Utils.GPSTracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Chat_Fragment extends Fragment implements OnMapReadyCallback, LocationListener {


    TextView Order, LocationStatus;
    EditText edtMessage;
    Button btnSendMessage;
    Dialog Progress;
    DatabaseReference databaseReference;
    String receiverEmail = "";
    FirebaseDatabase database;
    List<Chat_Object> AllMessagesList;
    RecyclerView recycle;
    GoogleMap googleMap;
    FrameLayout MapLayout;
    Marker mk;
    GPSTracker gpsTracker;
    MyApplication myApplication;
    Driver driver;
    RideResponse rideResponse;
    Location Currentlocation;
    LinearLayout NoMessageLayout, ChatLayout;

    public Chat_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_chat_, container, false);
        myApplication = (MyApplication) getActivity().getApplicationContext();
        rideResponse = myApplication.GetWorkingOrder();
        Order = (TextView) view.findViewById(R.id.Order);
        LocationStatus = (TextView) view.findViewById(R.id.LocationStatus);
        NoMessageLayout = (LinearLayout) view.findViewById(R.id.NoMessageLayout);
        ChatLayout = (LinearLayout) view.findViewById(R.id.ChatLayout);
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

        if (rideResponse == null) {
            ChatLayout.setVisibility(View.GONE);
            LocationStatus.setText("Messages");
            NoMessageLayout.setVisibility(View.VISIBLE);
        } else {
            LocationStatus.setText("Driver Status..");
            ChatLayout.setVisibility(View.VISIBLE);
            NoMessageLayout.setVisibility(View.GONE);
            Order.setText("Order No. " + rideResponse.getOrderID());
            recycle = (RecyclerView) view.findViewById(R.id.recycle);
            gpsTracker = new GPSTracker(getActivity());
            Currentlocation = new Location("");
            Currentlocation.setLatitude(gpsTracker.getLatitude());
            Currentlocation.setLongitude(gpsTracker.getLongitude());
            gpsTracker.stopUsingGPS();
            driver = new Driver();
            try {

                driver.setEmail(rideResponse.getDriver().getEmail());
                driver.setUsername(rideResponse.getDriver().getName());
            } catch (Exception Ex) {
                driver.setEmail("kamiclient1@gmail.com");
                driver.setUsername("Kami");
            }


            MapLayout = (FrameLayout) view.findViewById(R.id.MapLayout);

            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);


            view.findViewById(R.id.ViewDriverOnMap).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (MapLayout.getVisibility() == View.GONE) {
                        view.findViewById(R.id.ViewDriverOnMap).setVisibility(View.GONE);
                        MapLayout.setVisibility(View.VISIBLE);
                    }


                }
            });

            view.findViewById(R.id.Cross).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MapLayout.getVisibility() == View.VISIBLE) {
                        MapLayout.setVisibility(View.GONE);
                        view.findViewById(R.id.ViewDriverOnMap).setVisibility(View.VISIBLE);
                    }
                }
            });


            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

            edtMessage = (EditText) view.findViewById(R.id.edtMessage);
            btnSendMessage = (Button) view.findViewById(R.id.btnSendMessage);
            Progress = AppUtils.LoadingSpinner(getActivity());
            Progress.show();


            databaseReference = FirebaseDatabase.getInstance().getReference();

            btnSendMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Upload(view);
                }
            });


            receiverEmail = driver.getEmail();
            LoadMessages(view);

        }

        return view;


    }


    public void Upload(View view) {

        if (edtMessage.getText().equals("")) {
        } else {


            Progress.show();

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Chat").child(rideResponse.getOrderID());

            String Message = edtMessage.getText().toString().trim();
            String receiverName = driver.getUsername();
            Chat_Object chat_object = new Chat_Object();
            chat_object.setMessage(Message);
            chat_object.setReciverEmail(receiverEmail);
            chat_object.setReciverName(receiverName);
            chat_object.setSenderEmail(myApplication.getLoginSessionCustomer().getSuccess().getUser().getEmail());
            chat_object.setSenderName(myApplication.getLoginSessionCustomer().getSuccess().getUser().getName());
            DateFormat df = new SimpleDateFormat("HH:mm");
            String Time = df.format(Calendar.getInstance().getTime());
            chat_object.setSendTime(Time);


            String id = databaseReference.push().getKey();
            databaseReference.child(id).setValue(chat_object);
            LoadMessages(view);

        }
        edtMessage.setText("");
    }

    public void LoadMessages(View view) {


        Progress.show();
        database = FirebaseDatabase.getInstance();
        // myRef = database.getReference("Messages");

        Query query = database.getReference().child("Chat").child(rideResponse.getOrderID());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                AllMessagesList = new ArrayList<Chat_Object>();
                AllMessagesList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                    Chat_Object chat_object = dataSnapshot1.getValue(Chat_Object.class);


                    if (chat_object.getSenderEmail().contains(myApplication.getLoginSessionCustomer().getSuccess().getUser().getEmail()) && (chat_object.getReciverEmail().contains(String.valueOf(receiverEmail))) || chat_object.getSenderEmail().contains(String.valueOf(receiverEmail)) && (chat_object.getReciverEmail().contains(String.valueOf(myApplication.getLoginSessionCustomer().getSuccess().getUser().getEmail())))) {
                    }
                    AllMessagesList.add(chat_object);


                }


                LoadChattingHistory();


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });


    }


    public void LoadChattingHistory() {
        Progress.dismiss();
        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ChatAdapter chatAdapter = new ChatAdapter(AllMessagesList, getActivity());
        recycle.setLayoutManager(verticalLayoutmanager);
        verticalLayoutmanager.setStackFromEnd(true);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setAdapter(chatAdapter);
    }


    @Override
    public void onMapReady(GoogleMap googleMa) {
        googleMap = googleMa;
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getActivity(), R.raw.style));

            if (!success) {
                Log.e("MapsActivityRaw", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MapsActivityRaw", "Can't find style.", e);
        }
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);

        LoadLocation();
    }


    public void LoadLocation() {


        database = FirebaseDatabase.getInstance();
        // myRef = database.getReference("Messages");

        Query query = database.getReference().child("DriversLocation").child(driver.getEmail().replaceAll("[^A-Za-z]", ""));

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Driver driver = dataSnapshot.getValue(Driver.class);

                if (driver != null) {
                    startLatitude = driver.getLatitude();
                    startLongitude = driver.getLongitude();


                    if (Currentlocation != null) {

                        LatLng Driver = new LatLng(startLatitude, startLongitude);
                        LatLng User = new LatLng(Currentlocation.getLatitude(), Currentlocation.getLongitude());
                        Location location = new Location("");
                        location.setLatitude(startLatitude);
                        location.setLongitude(startLongitude);
                        LocationStatus.setText(String.valueOf(DecimalUtils.round(GetDistance(location), 2)) + " Miles Away");

                    }

                    if (isFirstPosition) {
                        startPosition = new LatLng(startLatitude, startLongitude);

                        carMarker = googleMap.addMarker(new MarkerOptions().position(startPosition).
                                flat(true).icon(BitmapDescriptorFactory.fromResource(R.mipmap.new_car_small)));
                        carMarker.setAnchor(0.5f, 0.5f);

                        googleMap.moveCamera(CameraUpdateFactory
                                .newCameraPosition
                                        (new CameraPosition.Builder()
                                                .target(startPosition)
                                                .zoom(15.5f)
                                                .build()));

                        isFirstPosition = false;

                    } else {
                        endPosition = new LatLng(startLatitude, startLongitude);

                        Log.d(TAG, startPosition.latitude + "--" + endPosition.latitude + "--Check --" + startPosition.longitude + "--" + endPosition.longitude);

                        if ((startPosition.latitude != endPosition.latitude) || (startPosition.longitude != endPosition.longitude)) {

                            Log.e(TAG, "NOT SAME");
                            startBikeAnimation(startPosition, endPosition);

                        } else {

                            Log.e(TAG, "SAMME");
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });


    }

    private static final long ANIMATION_TIME_PER_ROUTE = 3000;
    String polyLine = "q`epCakwfP_@EMvBEv@iSmBq@GeGg@}C]mBS{@KTiDRyCiBS";
    private PolylineOptions polylineOptions;
    private Polyline greyPolyLine;
    private SupportMapFragment mapFragment;
    private Handler handler;
    private Marker carMarker;
    private int index;
    private int next;
    private LatLng startPosition;
    private LatLng endPosition;
    private float v;
    Button button2;
    List<LatLng> polyLineList;
    private double lat, lng;
    // banani
    double latitude = 23.7877649;
    double longitude = 90.4007049;
    private String TAG = "HomeActivity";

    // Give your Server URL here >> where you get car location update
    public static final String DRIVER_LOCATION_ON_RIDE = "*******";
    private boolean isFirstPosition = true;
    private Double startLatitude;
    private Double startLongitude;

    private void startBikeAnimation(final LatLng start, final LatLng end) {

        Log.i("", "startBikeAnimation called...");

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(ANIMATION_TIME_PER_ROUTE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                //LogMe.i(TAG, "Car Animation Started...");
                v = valueAnimator.getAnimatedFraction();
                lng = v * end.longitude + (1 - v)
                        * start.longitude;
                lat = v * end.latitude + (1 - v)
                        * start.latitude;

                LatLng newPos = new LatLng(lat, lng);
                carMarker.setPosition(newPos);
                carMarker.setAnchor(0.5f, 0.5f);
                CameraPosition cameraPosition = CameraPosition.builder()
                        .target(new LatLng(lat, lng))
                        .zoom(15f)
                        .bearing(90)
                        .build();

                // Animate the change in camera view over 2 seconds
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                        2000, null);

                carMarker.setRotation(getBearing(start, end));

//                // todo : Shihab > i can delay here
//                googleMap.moveCamera(CameraUpdateFactory
//                        .newCameraPosition
//                                (new CameraPosition.Builder()
//                                        .target(newPos)
//                                        .zoom(15.5f)
//                                        .build()));

                startPosition = carMarker.getPosition();

            }

        });
        valueAnimator.start();
    }


    public static float getBearing(LatLng begin, LatLng end) {
        double lat = Math.abs(begin.latitude - end.latitude);
        double lng = Math.abs(begin.longitude - end.longitude);

        if (begin.latitude < end.latitude && begin.longitude < end.longitude)
            return (float) (Math.toDegrees(Math.atan(lng / lat)));
        else if (begin.latitude >= end.latitude && begin.longitude < end.longitude)
            return (float) ((90 - Math.toDegrees(Math.atan(lng / lat))) + 90);
        else if (begin.latitude >= end.latitude && begin.longitude >= end.longitude)
            return (float) (Math.toDegrees(Math.atan(lng / lat)) + 180);
        else if (begin.latitude < end.latitude && begin.longitude >= end.longitude)
            return (float) ((90 - Math.toDegrees(Math.atan(lng / lat))) + 270);
        return -1;
    }


    @Override
    public void onLocationChanged(Location location) {

        Currentlocation = location;

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override

    public void onProviderDisabled(String provider) {

    }

    public Double GetDistance(Location location) {
        return getMiles(Double.parseDouble(String.valueOf(Currentlocation.distanceTo(location))));
    }

    public Double getMiles(Double i) {
        return i * 0.000621371192;
    }
}
