package com.webmarke8.app.gencart.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
import com.webmarke8.app.gencart.Fragments.Verified_Result;
import com.webmarke8.app.gencart.Objects.Customer;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;
import com.webmarke8.app.gencart.Utils.AppUtils;
import com.webmarke8.app.gencart.Utils.ServerData;
import com.webmarke8.app.gencart.Utils.Validations;

import java.util.HashMap;
import java.util.Map;

public class Code_Verify extends AppCompatActivity {

    EditText Code;
    Dialog dialog;
    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code__verify);
        myApplication = (MyApplication) getApplicationContext();

        dialog = AppUtils.LoadingSpinnerDialog(Code_Verify.this);
        Code = (EditText) findViewById(R.id.Code);

        findViewById(R.id.Resend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendAgain();
            }
        });
        findViewById(R.id.Verify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Validations.isEmpity(Code, "Code is not Valid")) {

                    Verify();
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


    private void Verify() {

        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                ServerData.CodeVerify, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                if (response.contains("success")) {

                    Gson gson = new Gson();
                    Verified_Result customer = new Verified_Result();
                    customer = gson.fromJson(response, Verified_Result.class);
                    Customer customer1 = myApplication.getLoginSessionCustomer();
                    customer1.getSuccess().getUser().setVerified(1);
                    myApplication.createLoginSessionCustomer(customer1);

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    Code.setError("invalid Code");
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            EasyToast.error(getApplicationContext(), "No internet Connection");
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
                map.put("token", Code.getText().toString().trim());
                return map;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + myApplication.getLoginSessionCustomer().getSuccess().getToken());
                headers.put("Accept", "application/json");
                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void SendAgain() {

        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                ServerData.AgainCodeVerify, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                dialog.dismiss();

                EasyToast.success(getApplicationContext(), "Success");
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            EasyToast.error(getApplicationContext(), "No internet Connection");
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer " + myApplication.getLoginSessionCustomer().getSuccess().getToken());
                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
