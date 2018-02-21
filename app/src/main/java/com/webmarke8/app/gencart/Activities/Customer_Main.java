package com.webmarke8.app.gencart.Activities;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.webmarke8.app.gencart.Adapters.BottomNavigationViewHelper;
import com.webmarke8.app.gencart.Fragments.Chat_Fragment;
import com.webmarke8.app.gencart.Fragments.Customer_Main_Fragment;
import com.webmarke8.app.gencart.Fragments.MyCartFragment;
import com.webmarke8.app.gencart.Fragments.ProfileFragment;
import com.webmarke8.app.gencart.R;

public class Customer_Main extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_customer__main);


        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont.replace(R.id.container, new Customer_Main_Fragment());
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
                                        .replace(R.id.container, new Customer_Main_Fragment()).commit();
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
    }

}
