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
public class tab4 extends Fragment {

    //public Button bLogin;public EditText telephone1;public EditText telephone2;
    public Button bLogin;public EditText name,course;public EditText phone;public Spinner campus;public Spinner date;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView= inflater.inflate(R.layout.fragment_tab1, container, false);

        bLogin=(Button)myView.findViewById(R.id.button);
        name=(EditText)myView.findViewById(R.id.editText);
        phone=(EditText)myView.findViewById(R.id.phone);
        campus=(Spinner) myView.findViewById(R.id.campus);
        date=(Spinner)myView.findViewById(R.id.editText2);
        course=(EditText)myView.findViewById(R.id.course);



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

    public tab4() {
        // Required empty public constructor
    }

    public void getJSON() {
        class GetJSON extends AsyncTask<String, Void, String> {
            ProgressDialog loading;


            final String name1 = name.getText().toString();
            final String phone1 = phone.getText().toString();
            //final String campus1 = campus.getText().toString();
            final String campus1 = campus.getSelectedItem().toString();
            final String date1= date.getSelectedItem().toString();
            final String course1=course.getText().toString();

            // final String email=email.getText().toString();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Writing enquiry data to IAT...",null,true,true);
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
                               /* Intent intent = new Intent(getActivity(), Success.class);
                                startActivity(intent);
                                */
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Success.IAT will get back to you.")
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
                RegisterBook registerRequest = new RegisterBook(name1,phone1,campus1,date1,course1,responseListener);
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
