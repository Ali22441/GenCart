package com.webmarke8.app.gencart.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.webmarke8.app.gencart.Objects.Customer;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;
import com.webmarke8.app.gencart.Utils.AppUtils;
import com.webmarke8.app.gencart.Utils.ServerData;
import com.webmarke8.app.gencart.Utils.Validations;

import java.util.HashMap;
import java.util.Map;

public class Customer_Login extends AppCompatActivity {

    EditText Email, Password;
    MyApplication myApplication;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__login);


        myApplication = (MyApplication) getApplicationContext();
        dialog = AppUtils.LoadingSpinnerDialog(Customer_Login.this);

        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);


        findViewById(R.id.forgot_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Validations.isValidEmail(Email, "Email is not Valid") && Validations.isEmpity(Password, "Password is not Valid")) {

                    dialog.show();
                    userLogin();

                }
            }
        });

        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppUtils.StartActivity(getApplicationContext(), Customer_Signup.class);

            }
        });
    }

    private void userLogin() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                ServerData.CustomerLogin, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                if (response.contains("success")) {

                    Gson gson = new Gson();
                    Customer customer = new Customer();
                    customer = gson.fromJson(response, Customer.class);
                    myApplication.createLoginSessionCustomer(customer);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    Email.setError("invalid info");
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            EasyToast.error(getApplicationContext(), "Please check your internet Connection");
                        } else if (error instanceof AuthFailureError) {
                            EasyToast.error(getApplicationContext(), "Authentication Error!");
                        } else if (error instanceof ServerError) {
                            EasyToast.error(getApplicationContext(), "Server Side Error!");
                        } else if (error instanceof NetworkError) {
                            EasyToast.error(getApplicationContext(), "Network Error!");
                        } else if (error instanceof ParseError) {
                            EasyToast.error(getApplicationContext(), "Parse Error!");
                        }
                    }
                }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("email", Email.getText().toString().trim());
                map.put("password", Password.getText().toString().trim());
                map.put("fcm_token", AppUtils.getFirebaseInstanceId(getApplicationContext()));

                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");

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

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
