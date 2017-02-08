package com.zpanel.anderson.iat;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class tab3 extends Fragment {
    public Button bLogin;public EditText etAge;public EditText etPassword;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView= inflater.inflate(R.layout.fragment_tab3, container, false);

         bLogin=(Button)myView.findViewById(R.id.button3);
        etPassword=(EditText)myView.findViewById(R.id.editText4);
        etAge=(EditText)myView.findViewById(R.id.editText3);

        bLogin.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                if (v == bLogin) {
                    getJSON();
                }


            }
        });




return myView;


    }

    public tab3() {
        // Required empty public constructor
    }

    public void getJSON() {
        class GetJSON extends AsyncTask<String, Void, String> {
            ProgressDialog loading;


            final String telephone = etAge.getText().toString();
            final String password = etPassword.getText().toString();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Copying data from IAT...",null,true,true);
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
                                String name = jsonResponse.getString("name");
                                String telephone=jsonResponse.getString("telephone");
                                //int age = jsonResponse.getInt("age");

                                Intent intent = new Intent(getActivity(), Profile.class);
                               // intent.putExtra("name", name);
                                intent.putExtra("telephone", telephone);
                                // intent.putExtra("username", username);
                                //changed the putExtra from age to telephone to sessionalize the user
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Failed.Use correct login credentials")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(telephone, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(loginRequest);
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
