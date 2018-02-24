package com.webmarke8.app.gencart.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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
import com.webmarke8.app.gencart.R;

import java.util.ArrayList;
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
    String receiverEmailid;
    FirebaseDatabase database;
    List<Chat_Object> AllMessagesList;
    RecyclerView recycle;
    String senderid = "hussain@gmail.com";

    GoogleMap map;

    FrameLayout MapLayout;

    public Chat_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_chat_, container, false);

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


        receiverEmailid = "jaani.asif0333@gmail.com";
        LoadMessages(view);

        return view;


    }


    public void Upload(View view) {

        if (edtMessage.equals("")) {
            edtMessage.setError("Cant Empty");
        } else {


            progressDialog.setMessage("uploading");
            progressDialog.show();

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Messages");
            String Message = edtMessage.getText().toString();

            String receiverName = "Jaani";
            receiverEmailid = "jaani.asif0333@gmail.com";
            Chat_Object chat_object = new Chat_Object(senderid, Message, receiverEmailid, receiverName);
            String id = databaseReference.push().getKey();
            databaseReference.child(id).setValue(chat_object);
            LoadMessages(view);

        }
        edtMessage.setText("");
    }

    public void LoadMessages(View view) {


        recycle = (RecyclerView) view.findViewById(R.id.recycle);
        database = FirebaseDatabase.getInstance();
        // myRef = database.getReference("Messages");

        Query query = database.getReference("Messages");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                AllMessagesList = new ArrayList<Chat_Object>();
                AllMessagesList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                    Chat_Object chat_object = dataSnapshot1.getValue(Chat_Object.class);


                    if (chat_object.getSendermailid().contains(senderid) && (chat_object.getRececiveremailid().contains(String.valueOf(receiverEmailid))) || chat_object.getSendermailid().contains(String.valueOf(receiverEmailid)) && (chat_object.getRececiveremailid().contains(String.valueOf(senderid)))) {
                        AllMessagesList.add(chat_object);
                    }


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
//        LatLng UCA = new LatLng(-34, 151);
//        map.addMarker(new MarkerOptions().position(UCA).title("YOUR TITLE")).showInfoWindow();
//
//        map.animateCamera(CameraUpdateFactory.newLatLngZoom(UCA, 17));

    }

}
