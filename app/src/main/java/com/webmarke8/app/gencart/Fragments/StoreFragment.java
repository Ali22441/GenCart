package com.webmarke8.app.gencart.Fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.NetworkError;
import com.android.volley.error.NoConnectionError;
import com.android.volley.error.ParseError;
import com.android.volley.error.ServerError;
import com.android.volley.error.TimeoutError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.Adapters.ExpandableHeightGridView;
import com.webmarke8.app.gencart.Adapters.StoreGridviewAdapter;
import com.webmarke8.app.gencart.Objects.Store;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Utils.AppUtils;
import com.webmarke8.app.gencart.Utils.ServerData;
import com.webmarke8.app.gencart.Utils.StaticData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private ExpandableHeightGridView Gridview;
    private StoreGridviewAdapter GridViewAdapter;
    List<Store> StoreList;
    SwipeRefreshLayout mSwipeRefreshLayout;
    FrameLayout frameLayout;
    Dialog Progress;


    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);


        StoreList = new ArrayList<>();
        Progress = AppUtils.LoadingSpinner(getActivity());

        frameLayout = (FrameLayout) view.findViewById(R.id.container1);


        GetStores();

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        Gridview = (ExpandableHeightGridView) view.findViewById(R.id.gridview);

        GridViewAdapter = new StoreGridviewAdapter(getActivity(), StoreList, frameLayout);
        Gridview.setExpanded(true);
        Gridview.setAdapter(GridViewAdapter);


        view.findViewById(R.id.navigation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).OpenOpenOrCloseDrawer();
            }
        });
        return view;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setRefreshing(false);
        GetStores();
    }


    private void GetStores() {

        Progress.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                ServerData.GetStores, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                StoreList.clear();
                try {
                    Gson gson = new Gson();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("success");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        String Object = jsonArray.getString(i);
                        Store store = new Store();
                        store = gson.fromJson(Object, Store.class);
                        StoreList.add(store);
                    }

                    GridViewAdapter = new StoreGridviewAdapter(getActivity(), StoreList, frameLayout);
                    Gridview.setExpanded(true);
                    Gridview.setAdapter(GridViewAdapter);

                } catch (JSONException e) {

                    Log.d("GetCartError", e.getMessage());
                }


                Progress.dismiss();


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Progress.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(getActivity(), "Communication Error!", Toast.LENGTH_SHORT).show();

                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getActivity(), "Authentication Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getActivity(), "Server Side Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getActivity(), "Network Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getActivity(), "Parse Error!", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("origin", "33.525550,73.112831");
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", StaticData.DummyAuthentication);

                return headers;
            }


            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


}
