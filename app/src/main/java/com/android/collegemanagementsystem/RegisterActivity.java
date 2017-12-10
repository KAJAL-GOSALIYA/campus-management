package com.android.collegemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText etUserName, etFullName, etMobile, etPassword, etID;
    Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etFullName = (EditText) findViewById(R.id.etFullName);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etMobile=(EditText) findViewById(R.id.etMobileNumber);
        etPassword=(EditText) findViewById(R.id.etPassword);
        etID=(EditText) findViewById(R.id.etID);
        bRegister=(Button)findViewById(R.id.bRegister);
        bRegister.setHeight(bRegister.getHeight()+10);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("id", String.valueOf(R.id.bRegister));
                String temp_fullname = etFullName.getText().toString();
                String temp_mobile=etMobile.getText().toString();
                String temp_username=etUserName.getText().toString();
                String temp_password = etPassword.getText().toString();
                String temp_id= etID.getText().toString();
                RegisterMethod(temp_fullname,temp_mobile,temp_username,temp_password, temp_id);
                finish();
            }
        });
    }

    public void RegisterMethod(final String fullname, final String mobile, final String username, final String password, final String id)
    {

        String url="http://summerschoolativ.esy.es/summerschool/register.php";
        Log.d("RegisterActivity","URL: " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RegisterActivity","onresponse");

                        Log.d("RegisterActivity",response);
                        Toast.makeText(RegisterActivity.this,"Registered", Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject mainString = new JSONObject(response);

                            boolean SUCCESS_TAG = mainString.getBoolean("success");
                            Log.d("Success tag", String.valueOf(SUCCESS_TAG));

                            if(SUCCESS_TAG)
                            {
                                Intent i = new Intent(RegisterActivity.this,MainActivity.class);

                                startActivity(i);
                                finish();
                            }

                            else {
                                Toast.makeText(RegisterActivity.this,"Error JSON", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("RegisterActivity", "Error: " + error);

                    }
                })
                    {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("full_name", fullname);
                        params.put("mobile", mobile);
                        params.put("user_name", username);
                        params.put("password", password);
                        params.put("id",id);
                        return params;
                    }
        };

        AppController.getInstance().addToRequestQueue(stringRequest,"req_reg");

    }


}
