package com.webmarke8.app.gencart.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.webmarke8.app.gencart.Adapters.StoreRecyclerViewAdapter;
import com.webmarke8.app.gencart.Objects.Store;
import com.webmarke8.app.gencart.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener {


    RecyclerView rvAllCategories;
    private RecyclerView.Adapter mAdapter;
    List<Store> StoreList;

    SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressBar progressBar;


    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        rvAllCategories = view.findViewById(R.id.rv);
        


        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(getActivity());
        rvAllCategories.setLayoutManager(mLayoutManager);
        rvAllCategories.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        LinearLayoutManager linearLayoutManage  = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvAllCategories.setHasFixedSize(true);
        mAdapter = new StoreRecyclerViewAdapter(getActivity(), StoreList);
        rvAllCategories.setAdapter(mAdapter);
        return view;


    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setRefreshing(false);

        // Fetching data from server
    }


}
