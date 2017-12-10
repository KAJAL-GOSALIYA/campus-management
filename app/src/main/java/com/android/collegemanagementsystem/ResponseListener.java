package com.android.collegemanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ativ on 01-Jul-16.
 */
public class ResponseListener implements Response.Listener<String> {

    Context context;
    String username;
    EditText mPasswordView;
    public boolean SUCCESS_TAG;

    public boolean isSUCCESS_TAG() {
        return SUCCESS_TAG;
    }


    public ResponseListener(Context context, String username,EditText mPasswordView) {
        this.context = context;
        this.username=username;
        this.mPasswordView = mPasswordView;
    }

    @Override
    public void onResponse(String response) {
        {
            Log.d("LoginActivity","onRespons LA");
            Log.d("LoginActivity",response);
            try {
                JSONObject mainString = new JSONObject(response);


                SUCCESS_TAG = mainString.getBoolean("success");
                Log.d("JSON success: ",mainString.getString("success"));
//                            Log.d("JSON message: ",mainString.getString("registered"));

                if(SUCCESS_TAG)
                {
//{"success":true,"message":" Registered","id":"201501040","fullname":"Ativ Joshi","user_name":"ativsc@gmail.com","time_stamp":"2016-07-01 09:07:10"}

                    //Retrieving data from json
                    String fullname = mainString.getString("fullname");
                    String id = mainString.getString("id");

                    Toast.makeText(context,"Registered", Toast.LENGTH_SHORT).show();

                    //Saving data to sharedpreferences
                    SaveSharedPreference.setUserName(context,username);
                    SaveSharedPreference.setFullName(context,fullname);
                    SaveSharedPreference.setUserId(context,id);

                    Log.d("ResponseListener","username: "+SaveSharedPreference.getUserName(context)
                    +" fullname: " + SaveSharedPreference.getFullName(context)
                    +" id: " + SaveSharedPreference.getUserId(context));


                    Intent intent= new Intent(context.getApplicationContext(),MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                    ((Activity)context).finish();

                }

                else {
                    Toast.makeText(context,"Error JSON", Toast.LENGTH_SHORT).show();
/*                    Intent intent = new Intent(context,LoginActivity.class);
                    intent.putExtra("PASSWORD", false);
                    Log.d("ResponseListener", "new activity started");
                    context.startActivity(intent);*/
                    mPasswordView.setError("Wrong Password");
                }

            } catch (JSONException e) {
                Log.d("JSONException", "JSON Error");
                e.printStackTrace();
            }
        }
    }
}
