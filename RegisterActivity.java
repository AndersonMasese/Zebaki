package com.zpanel.anderson.iat;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class RegisterActivity extends AppCompatActivity implements  View.OnClickListener{
    public EditText namee,phone, email,password;public Button bRegister;
    //what the fuck!!I wrote namee intentionally and it is not like it is some error which is being correctd.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

         namee = (EditText) findViewById(R.id.editText5);
         phone= (EditText) findViewById(R.id.editText6);
         email = (EditText) findViewById(R.id.editText7);
         password = (EditText) findViewById(R.id.editText8);
         bRegister = (Button) findViewById(R.id.button2);
        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==bRegister){
            getJSON();
        }
    }
///////////////////////////////////////////////////////////////////////////



    public void getJSON() {
        class GetJSON extends AsyncTask<String, Void, String> {
            ProgressDialog loading;


            final String name1 = namee.getText().toString();
            final String phone1 = phone.getText().toString();
            final String email1= email.getText().toString();
            final String password1 = password.getText().toString();







            // final String email=email.getText().toString();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterActivity.this, "Uploading details to IAT...",null,true,true);
                //how to properly show progress in the activity mini activity fragmet without having to destroy the
                //other activities

            }

            @Override
            protected String doInBackground(String... params) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                //Intent intent = new Intent(RegisterActivity.this,Success.class);
                                //startActivity(intent);
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Successfully uploaded  registration data to IAT")
                                        .setNegativeButton("OK", null)
                                        .create()
                                        .show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Could not Upload details to IAT. Please check data connection")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRegister registerRequest = new RegisterRegister(name1,phone1,email1,password1,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
                return null;


            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                long delayInMillis = 5000;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        loading.dismiss();
                    }
                }, delayInMillis);
                //loading.dismiss();
                //lptextViewJSON.setText(s);

            }


        }

        GetJSON gj = new GetJSON();
        gj.execute();




    }






}