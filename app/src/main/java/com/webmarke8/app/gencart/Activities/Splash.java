package com.webmarke8.app.gencart.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;
import com.webmarke8.app.gencart.Utils.AppUtils;
import com.webmarke8.app.gencart.Utils.StaticData;

import java.util.HashMap;
import java.util.Map;

public class Splash extends AppCompatActivity {

    MyApplication myApplication;
    RelativeLayout Progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        myApplication = (MyApplication) getApplicationContext();
        setPermissionForAPP();


    }

    private void setPermissionForAPP() {
        String[] PERMISSIONS = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        if (!CheckPermisson()) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (myApplication.isLoggedIn()) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(getApplicationContext(), Customer_Login.class));
                        finish();
                    }
                    finish();
                }
            }, 1000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Map<String, Integer> perms = new HashMap<String, Integer>();
        // Initial
        perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
        for (int i = 0; i < permissions.length; i++)
            perms.put(permissions[i], grantResults[i]);
        if (perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && perms.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // All Permissions Granted
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (myApplication.isLoggedIn()) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(getApplicationContext(), Customer_Login.class));
                        finish();
                    }
                    finish();
                }
            }, 1000);

        } else {
            // Permission Denied
            Toast.makeText(Splash.this, "Some Permission is Denied", Toast.LENGTH_SHORT)
                    .show();
            finish();
        }

    }


    public boolean CheckPermisson() {
        if (ActivityCompat.checkSelfPermission(Splash.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Splash.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return false;
        } else {
            // Write you code here if permission already given.
            return true;
        }
    }
}
