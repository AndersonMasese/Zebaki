package com.zpanel.anderson.iat;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anderson on 11/14/2016.
 */

public  class RegisterRegister extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://zebaki.co.ke/iatRegistrationsUpload.php";
    private Map<String, String> params;

    public RegisterRegister(String name,String telephone1,  String emailaddress,String password,  Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("phone",telephone1);
        params.put("email", emailaddress);
        params.put("password",password);


        //params.put("password", email);
        // c 3l.params.put("age", age + "");
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


