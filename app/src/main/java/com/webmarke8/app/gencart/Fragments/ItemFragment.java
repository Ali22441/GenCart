package com.webmarke8.app.gencart.Fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
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
import com.webmarke8.app.gencart.Adapters.ItemGridviewAdapter;
import com.webmarke8.app.gencart.Adapters.StoreGridviewAdapter;
import com.webmarke8.app.gencart.Objects.ProductStore;
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
public class ItemFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private ExpandableHeightGridView Gridview;
    private ItemGridviewAdapter GridViewAdapter;
    ProductStore productStore;
    Dialog Progress;
    Store store;
    FrameLayout Detail;
    ScrollView Scroll;
    SwipeRefreshLayout mSwipeRefreshLayout;
    TextView StoreName;


    public ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);


        store = (Store) getArguments().getSerializable("Store");
        StoreName = (TextView) view.findViewById(R.id.StoreName);

        StoreName.setText(store.getName());


        Progress = AppUtils.LoadingSpinner(getActivity());

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


        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        Scroll = (ScrollView) view.findViewById(R.id.Scroll);
        Gridview = (ExpandableHeightGridView) view.findViewById(R.id.gridview);

        GetStores();
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

        String Url = ServerData.GetStoresByID + store.getId();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    Gson gson = new Gson();
                    JSONObject jsonObject = new JSONObject(response);
                    String Object = jsonObject.getString("success");

                    ProductStore product = new ProductStore();
                    product = gson.fromJson(Object, ProductStore.class);

                    productStore = product;
                    GridViewAdapter = new ItemGridviewAdapter(getActivity(), product, Scroll);
                    GridViewAdapter.setMyApp(getActivity().getApplication());
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
