package com.webmarke8.app.gencart.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.BaseKeyListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
import com.medialablk.easytoast.EasyToast;
import com.webmarke8.app.gencart.Adapters.BottomNavigationViewHelper;
import com.webmarke8.app.gencart.Adapters.ExpandableHeightGridView;
import com.webmarke8.app.gencart.Adapters.StoreGridviewAdapter;
import com.webmarke8.app.gencart.Fragments.Chat_Fragment;
import com.webmarke8.app.gencart.Fragments.MyCartFragment;
import com.webmarke8.app.gencart.Fragments.MyOrders;
import com.webmarke8.app.gencart.Fragments.ProfileFragment;
import com.webmarke8.app.gencart.Fragments.Resturent_Fragemt;
import com.webmarke8.app.gencart.Fragments.StoreFragment;
import com.webmarke8.app.gencart.Manifest;
import com.webmarke8.app.gencart.Objects.Store;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;
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

import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {


    FrameLayout frameLayout;
    TextView TextSearch;


    private int star = 0;
    private int end = 10;
    private int now = 1;
    private ExpandableHeightGridView Gridview;
    private StoreGridviewAdapter GridViewAdapter;
    List<Store> StoreList;
    SwipeRefreshLayout mSwipeRefreshLayout;
    Dialog Progress;
    TextView NumberBandage;
    BottomNavigationView bottomNavigationView;
    MyApplication myApplication;
    List<Store> Backup = new ArrayList<>();
    LayoutAnimationController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        findViewById(R.id.RetryConection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (GetLocationPermission()) {
                    if (TextSearch.getText().toString().length() != 0)
                        GetStores(true);
                    else GetStores(false);
                } else {

                    ShowNoLocation();
                }
            }
        });

        findViewById(R.id.RetryLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (GetLocationPermission()) {
                    if (AppUtils.isLocationEnabled(getApplicationContext())) {
                        GetStores(false);
                        HideNoLocation();
                    } else {
                        ShowNoLocation();
                    }

                } else {
                    ShowNoLocation();
                }

            }
        });

        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(50);
        set.addAnimation(animation);
        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(100);
        set.addAnimation(animation);
        controller = new LayoutAnimationController(
                set, 0.5f);


        myApplication = (MyApplication) getApplicationContext();

        TextSearch = (TextView) findViewById(R.id.TextSearch);

        TextSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    GetStores(true);
                } else {
                    GetStores(false);

                }

            }
        });


        StoreList = new ArrayList<>();
        Progress = AppUtils.LoadingSpinner(this);

        frameLayout = (FrameLayout) findViewById(R.id.containerForFragments);


        if (GetLocationPermission()) {
            if (AppUtils.isLocationEnabled(getApplicationContext())) {
                GetStores(false);
                HideNoLocation();
            } else {
                ShowNoLocation();
            }

        } else {
            ShowNoLocation();
        }


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.SwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        Gridview = (ExpandableHeightGridView) findViewById(R.id.gridview);
        Gridview.setLayoutAnimation(controller);
        Gridview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScroll(AbsListView view,
                                 int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                //Algorithm to check if the last item is visible or not
                final int lastItem = firstVisibleItem + visibleItemCount;
                if (lastItem == end) {
                    end = end + 10;
                    if (TextSearch.getText().toString().length() != 0)
                        GetStores(true);
                    else GetStores(false);
                }
                if (totalItemCount < end && totalItemCount > 10) {
                    findViewById(R.id.Progress).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.Progress).setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //blank, not required in your case
            }
        });

        GridViewAdapter = new StoreGridviewAdapter(MainActivity.this, StoreList, frameLayout);
        Gridview.setExpanded(true);
        Gridview.setAdapter(GridViewAdapter);


        findViewById(R.id.navigationDrawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOpenOrCloseDrawer();
            }
        });


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);


        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.Home:
                                ShowHome();
//                                getSupportFragmentManager().beginTransaction()
//                                        .replace(R.id.container, new StoreFragment(),"Home").commit();
                                break;
                            case R.id.MyCart:
                                HideNoConnection();

                                if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("MyCartFragment")) {
                                    break;
                                } else {
                                    frameLayout.setVisibility(View.VISIBLE);
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.containerForFragments, new MyCartFragment(), "MyCartFragment").setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).addToBackStack(null).commit();
                                }
                                break;
                            case R.id.Profile:


                                if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("Profile")) {
                                    break;
                                } else {
                                    HideNoConnection();

                                    frameLayout.setVisibility(View.VISIBLE);
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.containerForFragments, new ProfileFragment(), "Profile").setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).addToBackStack(null).commit();
                                }
                                break;
                            case R.id.Chat:

                                HideNoConnection();

                                if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("Chat_Fragment")) {
                                    break;
                                } else {
                                    HideNoConnection();

                                    frameLayout.setVisibility(View.VISIBLE);
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.containerForFragments, new Chat_Fragment(), "Chat_Fragment").setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).addToBackStack(null).commit();
                                }
                                break;

                        }
                        return true;
                    }
                });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(1);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;
        View badge = LayoutInflater.from(this)
                .inflate(R.layout.cart_bandage, bottomNavigationMenuView, false);
        NumberBandage = (TextView) badge.findViewById(R.id.Number);
        NumberBandage.setVisibility(View.GONE);

        itemView.addView(badge);


        Bandge(myApplication.getCartQuantity());

    }

    private Fragment getCurrentFragment() {
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.getUserVisibleHint())
                return fragment;
        }
        return null;
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int Count2 = getSupportFragmentManager().getBackStackEntryCount();

            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    return;
                }

                this.doubleBackToExitPressedOnce = true;
                EasyToast.info(getApplicationContext(), "Please click BACK again to exit");

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            } else {

                if (getSupportFragmentManager().getBackStackEntryCount() == 1)
                    ShowHome();
                else {
                    getSupportFragmentManager().popBackStack();

                }


            }
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.MOrder) {
            HideNoConnection();


            if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("Orders")) {
            } else {
                frameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerForFragments, new MyOrders(), "Orders").setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).addToBackStack(null).commit();
            }


        }
        if (id == R.id.MChat) {
            HideNoConnection();

            if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("Chat_Fragment")) {
            } else {
                frameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerForFragments, new Chat_Fragment(), "Chat_Fragment").setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).addToBackStack(null).commit();
            }

        }
        if (id == R.id.MHome) {

            frameLayout.setVisibility(View.GONE);


        }
        if (id == R.id.MMyCart) {

            HideNoConnection();

            if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("MyCartFragment")) {
            } else {
                frameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerForFragments, new MyCartFragment(), "MyCartFragment").setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).addToBackStack(null).commit();
            }


        }
        if (id == R.id.MProfile) {

            HideNoConnection();

            if (getCurrentFragment() != null && getCurrentFragment().getTag().equals("ProfileFragment")) {
            } else {
                frameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerForFragments, new ProfileFragment(), "ProfileFragment").setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).addToBackStack(null).commit();
            }


        }
        if (id == R.id.MLogout) {


            myApplication.logoutUser();
            startActivity(new Intent(getApplicationContext(), Customer_Login.class));
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void OpenOpenOrCloseDrawer() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    public void ShowHome() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        frameLayout.setVisibility(View.GONE);
        findViewById(R.id.container1).setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {

        mSwipeRefreshLayout.setRefreshing(true);
        star = 0;
        end = 10;
        if (GetLocationPermission()) {
            if (TextSearch.getText().toString().length() != 0)
                GetStores(true);
            else GetStores(false);
        } else {

            ShowNoLocation();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Progress != null && Progress.isShowing()) {
            Progress.cancel();
        }
    }

    private void GetStores(Boolean Check) {

        String URL = "";
        Progress.show();

        if (Check) {

            URL = ServerData.SearchStore + TextSearch.getText().toString() + "/" + String.valueOf(star) + "/" + String.valueOf(end);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL
                    , new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    HideNoConnection();

                    mSwipeRefreshLayout.setRefreshing(false);
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

                        findViewById(R.id.Progress).setVisibility(View.GONE);
                        GridViewAdapter = new StoreGridviewAdapter(MainActivity.this, StoreList, frameLayout);
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
                            mSwipeRefreshLayout.setRefreshing(false);
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                EasyToast.error(getApplicationContext(), "No internet Connection");
                                ShowNoConnection();
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
                    map.put("origin", "33.525550,73.112831");
                    return map;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Accept", "application/json");
                    headers.put("Authorization", "Bearer " + myApplication.getLoginSessionCustomer().getSuccess().getToken());

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

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest);

        } else {


            URL = ServerData.GetStores + "/" + String.valueOf(star) + "/" + String.valueOf(end);
            ;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL
                    , new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    HideNoConnection();
                    mSwipeRefreshLayout.setRefreshing(false);
                    StoreList.clear();
                    Progress.dismiss();

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
                        Backup = StoreList;
                        GridViewAdapter.notifyDataSetChanged();
                        findViewById(R.id.Progress).setVisibility(View.GONE);


                    } catch (JSONException e) {
                        Progress.dismiss();

                        Log.d("GetCartError", e.getMessage());
                    }

                    mSwipeRefreshLayout.setRefreshing(false);

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mSwipeRefreshLayout.setRefreshing(false);
                            Progress.dismiss();

                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                EasyToast.error(getApplicationContext(), "No internet Connection");
                                ShowNoConnection();
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
                    map.put("origin", "33.525550,73.112831");
                    return map;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Accept", "application/json");
                    headers.put("Authorization", "Bearer " + myApplication.getLoginSessionCustomer().getSuccess().getToken());

                    return headers;
                }


                @Override
                public String getBodyContentType() {

                    return "application/x-www-form-urlencoded; charset=UTF-8";
                }
            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(1000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest);

        }
    }


    public void Bandge(int Number) {

        if (Number == 0) {
            NumberBandage.setVisibility(View.GONE);

        } else {
            if (String.valueOf(Number).length() == 1) {
                NumberBandage.setText("  " + String.valueOf(Number) + "  ");
                NumberBandage.setVisibility(View.VISIBLE);
            } else {
                NumberBandage.setText(" " + String.valueOf(Number) + " ");
                NumberBandage.setVisibility(View.VISIBLE);
            }


        }


    }


    public boolean GetLocationPermission() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return false;
        } else {
            // Write you code here if permission already given.
            return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public void ShowNoLocation() {
        findViewById(R.id.NoLocation).setVisibility(View.VISIBLE);
    }

    public void HideNoLocation() {
        findViewById(R.id.NoLocation).setVisibility(View.GONE);
    }

    public void ShowNoConnection() {
        findViewById(R.id.NoInterent).setVisibility(View.VISIBLE);
    }

    public void HideNoConnection() {
        findViewById(R.id.NoInterent).setVisibility(View.GONE);
    }
}
