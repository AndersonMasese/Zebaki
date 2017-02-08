package com.zpanel.anderson.iat;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        AlertDialog.Builder builder = new AlertDialog.Builder(Success.this);
        builder.setMessage("Operation    Successful")
                .setNegativeButton("OK", null)
                .create()
                .show();
    }
}
