package com.zpanel.anderson.iat;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
public class tab2 extends Fragment {

    public Button bLogin;public EditText telephone1;public EditText telephone2;Spinner spinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView= inflater.inflate(R.layout.fragment_tab2, container, false);

        bLogin=(Button)myView.findViewById(R.id.button);
        telephone1=(EditText)myView.findViewById(R.id.editText);
        telephone2=(EditText)myView.findViewById(R.id.editText2);
        spinner=(Spinner) myView.findViewById(R.id.spinner);

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

    public tab2() {
        // Required empty public constructor
    }

    public void getJSON() {
        class GetJSON extends AsyncTask<String, Void, String> {
            ProgressDialog loading;


            final String telephone11 = telephone1.getText().toString();
            final String telephone22 = telephone2.getText().toString();
            final String spinner2=spinner.getSelectedItem().toString();
           // final String email=email.getText().toString();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Writing data to IAT...",null,true,true);
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
                               // Intent intent = new Intent(getActivity(), Success.class);
                              //  startActivity(intent);


                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Referral operation successful")
                                        .setNegativeButton("OK", null)
                                        .create()
                                        .show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Could not complete request. Please check your data connection")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(telephone11,telephone22,spinner2,responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
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
