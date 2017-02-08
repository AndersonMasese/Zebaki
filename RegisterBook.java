package com.zpanel.anderson.iat;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anderson on 11/14/2016.
 */

public  class RegisterBook extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://zebaki.co.ke/iatBookingsUpload.php";
    private Map<String, String> params;

    public RegisterBook(String name1,  String phone, String campus, String date,String course1, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params = new HashMap<>();
        params.put("name", name1);
        params.put("course",course1);
        params.put("phone", phone);
        params.put("campus", campus);
        params.put("date", date);
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


