package com.webmarke8.app.gencart.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
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

import com.webmarke8.app.gencart.Adapters.BottomNavigationViewHelper;
import com.webmarke8.app.gencart.Fragments.Chat_Fragment;
import com.webmarke8.app.gencart.Fragments.MyCartFragment;
import com.webmarke8.app.gencart.Fragments.MyOrders;
import com.webmarke8.app.gencart.Fragments.ProfileFragment;
import com.webmarke8.app.gencart.Fragments.Resturent_Fragemt;
import com.webmarke8.app.gencart.Fragments.StoreFragment;
import com.webmarke8.app.gencart.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont.replace(R.id.container, new StoreFragment());
        mTransactiont.commit();


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);


        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.Home:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.container, new StoreFragment()).commit();
                                break;
                            case R.id.MyCart:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.container, new MyCartFragment()).commit();
                                break;
                            case R.id.Profile:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.container, new ProfileFragment()).commit();
                                break;
                            case R.id.Chat:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.container, new Chat_Fragment()).commit();
                                break;

                        }
                        return true;
                    }
                });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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


        if (id == R.id.Order) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new MyOrders()).commit();

        }
        if (id == R.id.Rest) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Resturent_Fragemt()).commit();
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
}
