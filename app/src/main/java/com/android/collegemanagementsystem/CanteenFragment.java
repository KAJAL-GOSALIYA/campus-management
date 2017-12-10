package com.android.collegemanagementsystem;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class CanteenFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<String> alName;
    ArrayList<Integer> alImage;
    public CanteenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_canteen, container, false);
        // Inflate the layout for this fragment

        alName = new ArrayList<>(Arrays.asList("Cafe1", "Cafe2", "Cafe3", "Cafe4", "Cafe5"));
        alImage = new ArrayList<>(Arrays.asList(R.drawable.cafe1, R.drawable.cafe2, R.drawable.cafe3, R.drawable.cafe4, R.drawable.cafe5));

        // Calling the RecyclerView
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GridAdapter(getActivity(), alName, alImage);
        mRecyclerView.setAdapter(mAdapter);


        SharedPreferences sp=getActivity().getApplicationContext().getSharedPreferences("MyData",getActivity().MODE_PRIVATE);

        return rootView;
    }

}
