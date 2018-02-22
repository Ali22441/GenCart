package com.webmarke8.app.gencart.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.Adapters.ExpandableHeightGridView;
import com.webmarke8.app.gencart.Adapters.ItemGridviewAdapter;
import com.webmarke8.app.gencart.Objects.Product;
import com.webmarke8.app.gencart.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private ExpandableHeightGridView Gridview;
    private ItemGridviewAdapter GridViewAdapter;
    List<Product> ProductList;

    FrameLayout Detail;
    ScrollView Scroll;

    SwipeRefreshLayout mSwipeRefreshLayout;


    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        ProductList = new ArrayList<>();


        view.findViewById(R.id.Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.container1)).commit();

            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        Scroll = (ScrollView) view.findViewById(R.id.Scroll);

        Detail = (FrameLayout) view.findViewById(R.id.Details);
        Detail.setVisibility(View.GONE);
        Gridview = (ExpandableHeightGridView) view.findViewById(R.id.gridview);

        GridViewAdapter = new ItemGridviewAdapter(getActivity(), ProductList, Detail, Scroll);
        GridViewAdapter.setMyApp(getActivity().getApplication());
        Gridview.setExpanded(true);
        Gridview.setAdapter(GridViewAdapter);

        view.findViewById(R.id.navigation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).OpenOpenOrCloseDrawer();
            }
        });

        return view;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setRefreshing(false);

        // Fetching data from server
    }


}
