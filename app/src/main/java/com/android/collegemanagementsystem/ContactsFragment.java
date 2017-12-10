package com.android.collegemanagementsystem;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {

    ArrayList<MessageModel> arrayList = new ArrayList<MessageModel>();
    RecyclerView mRecyclerView;
    android.support.v7.widget.RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DBHelper dbHelper;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_contacts, container, false);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        dbHelper = new DBHelper(getActivity());
        FetchMessages();

        mAdapter = new MessageAdapter(getActivity(),dbHelper.readAllMessages());
        mRecyclerView.setAdapter(mAdapter);


        return rootView;
    }

    public void FetchMessages(){

        Log.d("MainActivity","FetchMessages Method");

        String url = "http://kpg.esy.es/contacts/contact.php";
        Log.d("MainActivity", "URL: " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d("Contacts", "Response: " + response);

                try {
                    JSONObject mainObject = new JSONObject(response);
                    boolean SUCCESS_TAG = mainObject.getBoolean("success");

                    if(SUCCESS_TAG){
//                        Toast.makeText(getActivity(),"SUCCESS",Toast.LENGTH_SHORT).show();
                        JSONArray messageArray = mainObject.getJSONArray("messageData");

                        Log.d("MainActivity","Message:"+messageArray.toString());

                        for(int i = 0 ;i<messageArray.length();i++)
                        {
                            JSONObject currentRow = messageArray.getJSONObject(i);

                            int t_id = currentRow.getInt("temp_id");
                            String t_name = currentRow.getString("temp_name");
                            String t_number = currentRow.getString("temp_number");
                            String t_email = currentRow.getString("temp_email");

                            Log.d("MainActivity"," "+t_id+" "+t_name+" "+t_number+" "+t_email);
                            // Toast.makeText(MainActivity.this," "+t_id+" "+t_message+" "+t_person+" "+t_mobile,Toast.LENGTH_SHORT).show();

                            MessageModel temp = new MessageModel(t_id,t_email,t_name,t_number);
                            dbHelper.insertMessages(temp);
                            //arrayList.add(temp);
                        }
                        mAdapter = new MessageAdapter(getActivity(),dbHelper.readAllMessages());
                        mRecyclerView.setAdapter(mAdapter);
                    }
                    else{
                        Toast.makeText(getActivity(),"FAIL",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(getActivity(),"Got The Response",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MainActivity", "ErrorCame");

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest,"req_register");
    }

}
