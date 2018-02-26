package com.webmarke8.app.gencart.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.Adapters.Categories_ResturentAdapter;
import com.webmarke8.app.gencart.Adapters.ResturentAdapter;
import com.webmarke8.app.gencart.Objects.Store;
import com.webmarke8.app.gencart.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Resturent_Fragemt extends Fragment {


    RecyclerView rvAllCategories;
    private RecyclerView.Adapter mAdapter;

    RecyclerView rvHorizental;
    private RecyclerView.Adapter mAdapterHorizental;
    List<Store> models;


    public Resturent_Fragemt() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resturent__fragemt, container, false);

        rvAllCategories = view.findViewById(R.id.rv);
        rvHorizental = view.findViewById(R.id.rv_horizental);


        view.findViewById(R.id.Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ((MainActivity) getActivity()).ShowHome();

            }
        });

        view.findViewById(R.id.navigation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).OpenOpenOrCloseDrawer();
            }
        });


        RecyclerView.LayoutManager recyclerViewLayoutManager;

/*        recyclerViewLayoutManager = new GridLayoutManager(this, 3);

        rvAllCategories.setLayoutManager(recyclerViewLayoutManager);*/
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(getActivity());
        rvAllCategories.setLayoutManager(mLayoutManager);

        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        rvAllCategories.getLayoutManager().scrollToPosition(3);
        rvHorizental.setLayoutManager(linearLayoutManager);
        rvAllCategories.setHasFixedSize(true);
        mAdapter = new ResturentAdapter(getActivity(), models);
        rvAllCategories.setAdapter(mAdapter);

        rvHorizental.setHasFixedSize(true);
        mAdapterHorizental = new Categories_ResturentAdapter(getActivity(), models);
        rvHorizental.setAdapter(mAdapterHorizental);
        return view;


    }

}
