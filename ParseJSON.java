package com.zpanel.anderson.iat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJSON extends AppCompatActivity implements View.OnClickListener{

    //public static final String MY_JSON ="MY_JSON";

    private static final String JSON_URL = "http://www.zebaki.co.ke/iatFetchJson.php";


    private String myJSONString="MY_JSON";

    private static final String JSON_ARRAY ="result";
    private static final String MY_ID = "id";
    private static final String LOG= "LogTime";
   // private static final String MYTELEPHONE= "MyTelephone";
    private static final String PENDING= "pendingAccount";
    private static final String APPROVED= "approvedAccount";
    private static final String TALLY="tally";


    private JSONArray users = null;

    private int TRACK = 0;

   private EditText editTextId;
    private EditText editTextLog;
   // private EditText editTextMyTel;//variables to go into xml android  android xml
    private EditText editTextPending;
    private EditText editTextApproved;
    private EditText editTextTally;
    //private TextView textViewJSON;
    public static final String MY_JSON ="MY_JSON";

    Button btnPrev;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json);
        Intent intent = getIntent();
        myJSONString = intent.getStringExtra(Profile.MY_JSON);

        editTextId = (EditText) findViewById(R.id.editTextID);
        editTextLog = (EditText) findViewById(R.id.editTextUsername);
       // editTextMyTel = (EditText) findViewById(R.id.editTextPassword);
        editTextPending = (EditText) findViewById(R.id.editTextID1);
        editTextApproved = (EditText) findViewById(R.id.editTextID2);
        editTextTally=(EditText) findViewById(R.id.editTextID3);



     /*  btnPrev = (Button) findViewById(R.id.buttonPrev);

        btnNext = (Button) findViewById(R.id.buttonNext);

        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        */


        extractJSON();

        showData();




    }





    private void extractJSON(){
        try {
            JSONObject jsonObject = new JSONObject(myJSONString);
            users = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void moveNext(){
        if(TRACK<users.length()){
            TRACK++;
        }
        showData();
    }

    private void movePrev(){
        if(TRACK>0){
            TRACK--;
        }
        showData();
    }

    private void showData(){
        try {
            JSONObject jsonObject = users.getJSONObject(TRACK);

           editTextId.setText(jsonObject.getString(MY_ID));
            editTextLog.setText(jsonObject.getString(LOG));
         //   editTextMyTel.setText(jsonObject.getString(MYTELEPHONE));
            editTextPending.setText(jsonObject.getString(PENDING));
            editTextApproved.setText(jsonObject.getString(APPROVED));
            editTextTally.setText(jsonObject.getString(TALLY));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parse_json, menu);
        return true;
    }

    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v == btnNext){
            moveNext();
        }
        if(v == btnPrev){
            movePrev();
        }


    }
}