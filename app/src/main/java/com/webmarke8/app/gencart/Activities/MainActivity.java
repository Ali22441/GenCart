package com.webmarke8.app.gencart.Activities;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.FrameLayout;
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
import com.webmarke8.app.gencart.Adapters.BottomNavigationViewHelper;
import com.webmarke8.app.gencart.Adapters.ExpandableHeightGridView;
import com.webmarke8.app.gencart.Adapters.StoreGridviewAdapter;
import com.webmarke8.app.gencart.Fragments.Chat_Fragment;
import com.webmarke8.app.gencart.Fragments.MyCartFragment;
import com.webmarke8.app.gencart.Fragments.MyOrders;
import com.webmarke8.app.gencart.Fragments.ProfileFragment;
import com.webmarke8.app.gencart.Fragments.Resturent_Fragemt;
import com.webmarke8.app.gencart.Fragments.StoreFragment;
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

import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {


    FrameLayout frameLayout;


    private ExpandableHeightGridView Gridview;
    private StoreGridviewAdapter GridViewAdapter;
    List<Store> StoreList;
    SwipeRefreshLayout mSwipeRefreshLayout;
    FrameLayout frameLayout1;
    Dialog Progress;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        StoreList = new ArrayList<>();
        Progress = AppUtils.LoadingSpinner(this);

        frameLayout = (FrameLayout) findViewById(R.id.containerForFragments);


        GetStores();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.SwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        Gridview = (ExpandableHeightGridView) findViewById(R.id.gridview);

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
                                frameLayout.setVisibility(View.VISIBLE);
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.containerForFragments, new MyCartFragment(), "MyCartFragment").commit();


                                break;
                            case R.id.Profile:
                                frameLayout.setVisibility(View.VISIBLE);
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.containerForFragments, new ProfileFragment(), "Profile").commit();
                                break;
                            case R.id.Chat:
                                frameLayout.setVisibility(View.VISIBLE);
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.containerForFragments, new Chat_Fragment(), "Chat_Fragment").commit();
                                break;

                        }
                        return true;
                    }
                });


        Bandge(2);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.MOrder) {

            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerForFragments, new MyOrders(), "Orders").commit();

        }
        if (id == R.id.MChat) {

            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerForFragments, new Chat_Fragment(), "Chat_Fragment").commit();

        }
        if (id == R.id.MHome) {

            frameLayout.setVisibility(View.GONE);


        }
        if (id == R.id.MMyCart) {

            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerForFragments, new MyCartFragment(), "MyCartFragment").commit();

        }
        if (id == R.id.MProfile) {

            frameLayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerForFragments, new ProfileFragment(), "ProfileFragment").commit();

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

        frameLayout.setVisibility(View.GONE);
        findViewById(R.id.container1).setVisibility(View.VISIBLE);
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
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "Communication Error!", Toast.LENGTH_SHORT).show();

                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "Authentication Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Server Side Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Parse Error!", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
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

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

    public void Bandge(int Number) {

        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(1); // number of menu from left
        new QBadgeView(this).bindTarget(v).setBadgeNumber(Number).setBadgeBackgroundColor(R.color.transprent).setShowShadow(true).setBadgePadding(7, true);
    }
}
