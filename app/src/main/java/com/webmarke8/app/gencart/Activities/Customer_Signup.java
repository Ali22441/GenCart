package com.webmarke8.app.gencart.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;
import com.medialablk.easytoast.EasyToast;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.webmarke8.app.gencart.Objects.Customer;
import com.webmarke8.app.gencart.Objects.Customer_New;
import com.webmarke8.app.gencart.R;
import com.webmarke8.app.gencart.Session.MyApplication;
import com.webmarke8.app.gencart.Utils.AppUtils;
import com.webmarke8.app.gencart.Utils.GPSTracker;
import com.webmarke8.app.gencart.Utils.ServerData;
import com.webmarke8.app.gencart.Utils.Validations;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Customer_Signup extends AppCompatActivity {

    CallbackManager callbackManager;
    TwitterAuthClient mTwitterAuthClient;
    Dialog dialog;
    EditText Name, Phone, Password, Email;
    GPSTracker gpsTracker;
    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        Twitter.initialize(this);
        setContentView(R.layout.activity_customer__signup);
        myApplication = (MyApplication) getApplicationContext();
        gpsTracker = new GPSTracker(this);


        Name = (EditText) findViewById(R.id.name);
        Phone = (EditText) findViewById(R.id.phone);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);


        callbackManager = CallbackManager.Factory.create();
        mTwitterAuthClient = new TwitterAuthClient();
        dialog = AppUtils.LoadingSpinnerDialog(Customer_Signup.this);


        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppUtils.StartActivity(getApplicationContext(), Customer_Login.class);
                finish();
            }
        });

        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Validations.isValidEmail(Email, "Email is not Valid") && Validations.isEmpity(Password, "Password is not Valid") && Validations.isEmpity(Phone, "Phone  is not Valid") && Validations.isEmpity(Name, "Name is not Valid")) {
                    dialog.show();
                    registerApiCall();
                } else {
                    return;
                }


            }
        });

        findViewById(R.id.facebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LoginManager.getInstance().logInWithReadPermissions(Customer_Signup.this, Arrays.asList("email"));
                LoginManager.getInstance().registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                Log.d("Success", "Login");

                            }

                            @Override
                            public void onCancel() {
                                EasyToast.custom(getApplicationContext(), "Login Cancel");
                            }

                            @Override
                            public void onError(FacebookException exception) {
                                Toast.makeText(Customer_Signup.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });

        findViewById(R.id.twitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mTwitterAuthClient.authorize(Customer_Signup.this, new com.twitter.sdk.android.core.Callback<TwitterSession>() {

                    @Override
                    public void success(Result<TwitterSession> twitterSessionResult) {

                        String username = twitterSessionResult.data.getUserName();

                    }

                    @Override
                    public void failure(TwitterException e) {
                        e.printStackTrace();
                    }
                });

            }
        });
    }

    private void registerApiCall() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                ServerData.CustomerSignup, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if (response.contains("success")) {

                    Gson gson = new Gson();
                    Customer_New customer = new Customer_New();
                    customer = gson.fromJson(response, Customer_New.class);
                    Customer customer1 = new Customer();
                    Customer.Success success = new Customer.Success();

                    success.setToken(customer.getSuccess().getToken());
                    Customer.User user = new Customer.User();
                    user.setAuthyId(customer.getSuccess().getData().getAuthy_id());
                    user.setEmail(customer.getSuccess().getData().getEmail());
                    user.setPhone(customer.getSuccess().getData().getPhone());
                    user.setAddress(customer.getSuccess().getData().getAddress());
                    user.setName(customer.getSuccess().getData().getName());
                    user.setVerified(0);

                    success.setUser(user);
                    customer1.setSuccess(success);
                    myApplication.createLoginSessionCustomer(customer1);
                    startActivity(new Intent(getApplicationContext(), Code_Verify.class));
                    finish();
                } else {
                    Email.setError("invalid info");
                }

                dialog.dismiss();


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
                map.put("email", Email.getText().toString().trim());
                map.put("phone", Phone.getText().toString().trim());
                map.put("name", Name.getText().toString().trim());
                map.put("role", "customer");
                map.put("country_code", "92");
                map.put("place_id", "");
                map.put("address", AppUtils.getCompleteAddressString(gpsTracker.getLatitude(), gpsTracker.getLongitude(), Customer_Signup.this));
                map.put("lat_lng", String.valueOf(gpsTracker.getLongitude() + "," + String.valueOf(gpsTracker.getLongitude())));
                map.put("password", Password.getText().toString().trim());
                map.put("zipcode", "42000");
                map.put("password_confirmation", Password.getText().toString().trim());
                map.put("fcm_token", AppUtils.getFirebaseInstanceId(getApplicationContext()));
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
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


    protected void getUserDetails(LoginResult loginResult) {
        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {


//                        try {
//                            JSONObject jsonObject = new JSONObject(json_object.toString());
//                            user_email.setText(jsonObject.get("email").toString());
//                            user_name.setText(jsonObject.get("name").toString());
//                            profile_pic_data = new JSONObject(jsonObject.get("picture").toString());
//                            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
//                            Picasso.with(getApplicationContext()).load(profile_pic_url.getString("url"))
//                                    .into(user_picture);
//
//                        } catch(Exception e){
//                            e.printStackTrace();
//                        }

                    }
                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();

    }

    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
    }

}
