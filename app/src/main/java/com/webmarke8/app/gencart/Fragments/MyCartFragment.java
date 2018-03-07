package com.webmarke8.app.gencart.Fragments;


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

import com.google.gson.Gson;
import com.medialablk.easytoast.EasyToast;
import com.webmarke8.app.gencart.Activities.MainActivity;
import com.webmarke8.app.gencart.Adapters.CartAdapter;
import com.webmarke8.app.gencart.Objects.CartGroup;
import com.webmarke8.app.gencart.Objects.SendCart;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


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

                sendCart.setStores(myApplication.getCartGroupList());
                sendCart.setAddress_id("72.213123,87.1213");
                sendCart.setAmount("10000");
                sendCart.setCustomer_id("12");

                Gson gson = new Gson();
                String Json = gson.toJson(sendCart);
                Log.d("JSonData", Json);

                if (myApplication.getCartGroupList().size() != 0) {
                    Fragment fragment = null;
                    Class fragmentClass = null;

                    fragmentClass = CheckOut.class;
                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                        Bundle bundle = new Bundle();
//                    bundle.putSerializable("Product", CProduct);
                        fragment.setArguments(bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.containerForFragments, fragment, "CheckOut").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(null).commit();
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

}
