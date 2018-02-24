package com.webmarke8.app.gencart.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.Adapters.MyOrderAdapter;
import com.webmarke8.app.gencart.Objects.Order;
import com.webmarke8.app.gencart.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrders extends Fragment {


    RecyclerView rvAllCategories;
    private RecyclerView.Adapter mAdapter;
    List<Order> models;


    public MyOrders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);

        rvAllCategories = view.findViewById(R.id.rv);
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(getActivity());
        rvAllCategories.setLayoutManager(mLayoutManager);
        rvAllCategories.setHasFixedSize(true);
        mAdapter = new MyOrderAdapter(getActivity(), models);
        rvAllCategories.setAdapter(mAdapter);


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


        return view;

    }

}
