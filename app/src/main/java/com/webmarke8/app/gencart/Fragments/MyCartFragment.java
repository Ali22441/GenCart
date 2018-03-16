package com.webmarke8.app.gencart.Fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
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
import com.medialablk.easytoast.EasyToast;
import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.Adapters.CartAdapter;
import com.webmarke8.app.gencart.Adapters.ItemGridviewAdapter;
import com.webmarke8.app.gencart.Objects.CartGroup;
import com.webmarke8.app.gencart.Objects.ProductStore;
import com.webmarke8.app.gencart.Objects.SendCart;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;
import com.webmarke8.app.gencart.Utils.AppUtils;
import com.webmarke8.app.gencart.Utils.GPSTracker;
import com.webmarke8.app.gencart.Utils.ServerData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {


    private LinkedHashMap<String, CartGroup> subjects = new LinkedHashMap<String, CartGroup>();
    private List<CartGroup> CartList = new ArrayList<CartGroup>();

    private CartAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;

    MyApplication myApplication;

    TextView AllStoreItemPrice;
    Dialog dialog;


    public MyCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        AllStoreItemPrice = (TextView) view.findViewById(R.id.AllStoreItemPrice);
        // add data for displaying in expandable list view
        dialog = AppUtils.LoadingSpinnerDialog(getActivity());


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


        view.findViewById(R.id.pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.show();
                SendCart sendCart = new SendCart();

                GPSTracker gpsTracker = new GPSTracker(getActivity());
                sendCart.setStores(myApplication.getCartGroupList());
                sendCart.setAddress_id(String.valueOf(gpsTracker.getLatitude()) + "," + String.valueOf(gpsTracker.getLongitude()));
                sendCart.setAmount(String.valueOf(myApplication.getPriceOfAllStore()));
                sendCart.setCustomer_id(String.valueOf(myApplication.getLoginSessionCustomer().getSuccess().getUser().getId()));
                for (CartGroup cartGroup : sendCart.getStores()) {
                    cartGroup.setPrice();
                }
                Gson gson = new Gson();
                String Json = gson.toJson(sendCart);
                Log.d("JSonData", Json);

                if (myApplication.getCartGroupList().size() != 0) {

                    PlaceOrder(Json, sendCart);

                } else {
                    EasyToast.error(getActivity(), "No Item in Cart");
                }


            }
        });


//        loadData();
        myApplication = (MyApplication) getActivity().getApplicationContext();

        CartList = myApplication.getCartGroupList();
        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) view.findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new CartAdapter(getActivity(), CartList, AllStoreItemPrice);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        //expand all the Groups
        expandAll();

        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
//                CartGroup headerInfo = deptList.get(groupPosition);
                //get the child info
//                Cart detailInfo = headerInfo.getProductList().get(childPosition);
                //display it or do something with it
                return false;
            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;

            }
        });


        return view;
    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            simpleExpandableListView.expandGroup(i);
        }
    }

    int previousGroup = -1;


    private void PlaceOrder(final String JsonRequest, final SendCart sendCart) {


        String Url = ServerData.PlaceOrder;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                dialog.dismiss();
                if (response.contains("success")) {
                    Fragment fragment = null;
                    Class fragmentClass = null;

                    fragmentClass = CheckOut.class;
                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Orders", sendCart);
                        fragment.setArguments(bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.containerForFragments, fragment, "CheckOut").setTransition(FragmentTransaction.TRANSIT_UNSET)
                            .addToBackStack(null).commit();

                } else {
                    EasyToast.error(getActivity(), "Something Went Wrong!! Quantity issus");
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        EasyToast.error(getActivity(), "Something Went Wrong!!");

                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//                            Toast.makeText(getActivity(), "Communication Error!", Toast.LENGTH_SHORT).show();

                        } else if (error instanceof AuthFailureError) {
//                            Toast.makeText(getActivity(), "Authentication Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
//                            Toast.makeText(getActivity(), "Server Side Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {
//                            Toast.makeText(getActivity(), "Network Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
//                            Toast.makeText(getActivity(), "Parse Error!", Toast.LENGTH_SHORT).show();
                        }
//                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
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
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + myApplication.getLoginSessionCustomer().getSuccess().getToken());
                return headers;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return JsonRequest == null ? null : JsonRequest.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", JsonRequest, "utf-8");
                    return null;
                }
            }


            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
