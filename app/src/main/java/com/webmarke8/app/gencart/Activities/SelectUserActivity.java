package com.webmarke8.app.gencart.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;
import com.webmarke8.app.gencart.Utils.AppUtils;
import com.webmarke8.app.gencart.Utils.StaticData;

public class SelectUserActivity extends AppCompatActivity {

    MyApplication myApplication;
    RelativeLayout Progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        myApplication = (MyApplication) getApplicationContext();
        Progress = (RelativeLayout) findViewById(R.id.Progress);


        if (myApplication.isLoggedIn()) {
            if (myApplication.getType().equals("owner")) {

                Progress.setVisibility(View.VISIBLE);
                AppUtils.StartActivity(getApplicationContext(), MainActivity.class);
                finish();

            } else {

                Progress.setVisibility(View.VISIBLE);
                AppUtils.StartActivity(getApplicationContext(), MainActivity.class);
                finish();

            }
        }

        findViewById(R.id.Owner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myApplication.setType(StaticData.Owner);
                AppUtils.StartActivity(getApplicationContext(), Owner_Login.class);


            }
        });
        findViewById(R.id.Customer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myApplication.setType(StaticData.Customer);
                AppUtils.StartActivity(getApplicationContext(), Customer_Login.class);

            }
        });
    }
}
