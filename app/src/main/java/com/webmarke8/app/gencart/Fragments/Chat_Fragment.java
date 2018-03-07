package com.webmarke8.app.gencart.Fragments;


import android.Manifest;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
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
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;
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
public class Chat_Fragment extends Fragment implements OnMapReadyCallback {


    EditText edtMessage;
    Button btnSendMessage;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;
    String receiverEmail = "jaani.asif0333@gmail.com";
    FirebaseDatabase database;
    List<Chat_Object> AllMessagesList;
    RecyclerView recycle;
    GoogleMap map;
    FrameLayout MapLayout;
    Marker mk;
    GPSTracker gpsTracker;
    MyApplication myApplication;

    public Chat_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_chat_, container, false);
        myApplication = (MyApplication) getActivity().getApplicationContext();
        recycle = (RecyclerView) view.findViewById(R.id.recycle);
        gpsTracker = new GPSTracker(getActivity());


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
        progressDialog = new ProgressDialog(getActivity());


        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Upload(view);
            }
        });


        receiverEmail = "jaani.asif0333@gmail.com";
        LoadMessages(view);

        return view;


    }


    public void Upload(View view) {

        if (edtMessage.equals("")) {
            edtMessage.setError("Cant Empty");
        } else {


            progressDialog.setMessage("uploading");
            progressDialog.show();

            databaseReference = FirebaseDatabase.getInstance().getReference().child("SessionID123");

            String Message = edtMessage.getText().toString();
            String receiverName = "Jaani";
            receiverName = "jaani.asif0333@gmail.com";
            Chat_Object chat_object = new Chat_Object();
            chat_object.setMessage(Message);
            chat_object.setReciverEmail(receiverEmail);
            chat_object.setReciverName(receiverName);
            chat_object.setSenderEmail(myApplication.getLoginSessionCustomer().getEmail());
            chat_object.setSenderName("Jimi");
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


        database = FirebaseDatabase.getInstance();
        // myRef = database.getReference("Messages");

        Query query = database.getReference("SessionID123");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                AllMessagesList = new ArrayList<Chat_Object>();
                AllMessagesList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                    Chat_Object chat_object = dataSnapshot1.getValue(Chat_Object.class);


                    if (chat_object.getSenderEmail().contains(myApplication.getLoginSessionCustomer().getEmail()) && (chat_object.getReciverEmail().contains(String.valueOf(receiverEmail))) || chat_object.getSenderEmail().contains(String.valueOf(receiverEmail)) && (chat_object.getReciverEmail().contains(String.valueOf(myApplication.getLoginSessionCustomer().getEmail())))) {
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
        progressDialog.dismiss();
        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ChatAdapter chatAdapter = new ChatAdapter(AllMessagesList, getActivity());
        recycle.setLayoutManager(verticalLayoutmanager);
        verticalLayoutmanager.setStackFromEnd(true);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setAdapter(chatAdapter);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(true);
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
        map.setMyLocationEnabled(true);

        LoadLocation();
    }


    public void LoadLocation() {


        database = FirebaseDatabase.getInstance();
        // myRef = database.getReference("Messages");

        Query query = database.getReference("LocationOFDrivers");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Driver driver = dataSnapshot.getValue(Driver.class);
                LatLng currentLatLng = new LatLng(driver.getLat(),
                        driver.getLong());
                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(currentLatLng,
                        15);
                map.moveCamera(update);
                Location location = new Location(LocationManager.GPS_PROVIDER);
                location.setLatitude(driver.getLat());
                location.setLatitude(driver.getLong());
                animateMarker(location, mk);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });


    }

    public void animateMarker(final Location destination, final Marker marker) {
        if (marker != null) {
            final LatLng startPosition = marker.getPosition();
            final LatLng endPosition = new LatLng(destination.getLatitude(), destination.getLongitude());

            final float startRotation = marker.getRotation();

            final LatLngInterpolator latLngInterpolator = new LatLngInterpolator.LinearFixed();
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
            valueAnimator.setDuration(1000); // duration 1 second
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    try {
                        float v = animation.getAnimatedFraction();
                        LatLng newPosition = latLngInterpolator.interpolate(v, startPosition, endPosition);
                        marker.setPosition(newPosition);
                        marker.setRotation(computeRotation(v, startRotation, destination.getBearing()));
                    } catch (Exception ex) {
                        // I don't care atm..
                    }
                }
            });

            valueAnimator.start();
        } else {

            MarkerOptions mkop = new MarkerOptions().position(new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude())).title("Driver");
            mk = map.addMarker(mkop);

        }

    }

    private static float computeRotation(float fraction, float start, float end) {
        float normalizeEnd = end - start; // rotate start to 0
        float normalizedEndAbs = (normalizeEnd + 360) % 360;

        float direction = (normalizedEndAbs > 180) ? -1 : 1; // -1 = anticlockwise, 1 = clockwise
        float rotation;
        if (direction > 0) {
            rotation = normalizedEndAbs;
        } else {
            rotation = normalizedEndAbs - 360;
        }

        float result = fraction * rotation + start;
        return (result + 360) % 360;
    }

    private interface LatLngInterpolator {
        LatLng interpolate(float fraction, LatLng a, LatLng b);

        class LinearFixed implements LatLngInterpolator {
            @Override
            public LatLng interpolate(float fraction, LatLng a, LatLng b) {
                double lat = (b.latitude - a.latitude) * fraction + a.latitude;
                double lngDelta = b.longitude - a.longitude;
                // Take the shortest path across the 180th meridian.
                if (Math.abs(lngDelta) > 180) {
                    lngDelta -= Math.signum(lngDelta) * 360;
                }
                double lng = lngDelta * fraction + a.longitude;
                return new LatLng(lat, lng);
            }
        }
    }

}
