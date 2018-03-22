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
        dialog.show();


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


                SendCart sendCart = new SendCart();

                GPSTracker gpsTracker = new GPSTracker(getActivity());
                sendCart.setStores(myApplication.getCartGroupList());
                sendCart.setAddress_id(String.valueOf(gpsTracker.getLatitude()) + "," + String.valueOf(gpsTracker.getLongitude()));
                sendCart.setAmount(String.valueOf(myApplication.getPriceOfAllStore()));
                sendCart.setCustomer_id(String.valueOf(myApplication.getLoginSessionCustomer().getSuccess().getUser().getId()));
                for (CartGroup cartGroup : sendCart.getStores()) {
                    cartGroup.setPrice();
                }


                if (myApplication.getCartGroupList().size() != 0) {

                    try {
                        Fragment fragment = null;
                        Class fragmentClass = null;

                        fragmentClass = CheckOut.class;
                        try {
                            sendCart.setOrder_id("");
                            fragment = (Fragment) fragmentClass.newInstance();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("Orders", sendCart);
                            myApplication.SaveOrder(sendCart);
                            fragment.setArguments(bundle);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.containerForFragments, fragment, "CheckOut").setTransition(FragmentTransaction.TRANSIT_UNSET)
                                .addToBackStack(null).commit();
                    } catch (Exception Ex) {
                        EasyToast.error(getActivity(), "Something Went Wrong!!");

                    }

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
        dialog.dismiss();
    }

}
