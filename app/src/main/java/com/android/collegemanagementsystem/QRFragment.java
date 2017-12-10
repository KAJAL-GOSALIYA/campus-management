package com.android.collegemanagementsystem;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
// * Use the {@link QRFragment#} factory method to
 * create an instance of this fragment.
 */
public class QRFragment extends Fragment {

    public QRFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //getting rootView
        final View rootView = inflater.inflate(R.layout.fragment_qr, container, false);
        RadioButton radioButton_out= (RadioButton) rootView.findViewById(R.id.radioButton_out);
        RadioButton radioButton_in= (RadioButton) rootView.findViewById(R.id.radioButton_in);
        final RadioButton radioButton_atten= (RadioButton) rootView.findViewById(R.id.radioButton_atten);
        final RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radiogroup);
        final Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView_qr);
        //initializing radiogroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d("QRF", "radiogrp oncheckchanged");
                if(checkedId!=-1) {
                    Log.d("Radiogroup", ((RadioButton) rootView.findViewById(checkedId)).getText().toString());
                    if (spinner.getSelectedItemPosition()==0)
                    {
                        if (checkedId==R.id.radioButton_in)
                        {
                           String json= getJSONString(String.valueOf(spinner.getSelectedItemPosition()),
                                    SaveSharedPreference.getUserId(getActivity()),
                                    String.valueOf(1));

                            try {
                                imageView.setImageBitmap(encodeAsBitmap(json));
                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                        }

                       else if (checkedId==R.id.radioButton_out)
                        {
                            String json= getJSONString(String.valueOf(spinner.getSelectedItemPosition()),
                                    SaveSharedPreference.getUserId(getActivity()),
                                    String.valueOf(0));

                            try {
                                imageView.setImageBitmap(encodeAsBitmap(json));
                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    else if (spinner.getSelectedItemPosition()==1)
                    {
                        if (checkedId==R.id.radioButton_in)
                        {
                            String json= getJSONString(String.valueOf(spinner.getSelectedItemPosition()),
                                    SaveSharedPreference.getUserId(getActivity()),
                                    String.valueOf(1));

                            try {
                                imageView.setImageBitmap(encodeAsBitmap(json));
                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                        }

                        else if (checkedId==R.id.radioButton_out)
                        {
                            String json= getJSONString(String.valueOf(spinner.getSelectedItemPosition()),
                                    SaveSharedPreference.getUserId(getActivity()),
                                    String.valueOf(0));

                            try {
                                imageView.setImageBitmap(encodeAsBitmap(json));
                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                        }

                        else if (checkedId==R.id.radioButton_atten)
                        {
                            String json= getJSONString(String.valueOf(spinner.getSelectedItemPosition()),
                                    SaveSharedPreference.getUserId(getActivity()),
                                    String.valueOf(2));

                            try {
                                imageView.setImageBitmap(encodeAsBitmap(json));
                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                        }
                    }



                }
            }
        });

        //initializing spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("QRF", "spinner onItemSelected");
                if(position==0)
                {
                    radioButton_atten.setVisibility(View.GONE);
                    radioGroup.clearCheck();
                    imageView.setImageResource(0);
                }

                else if (position==1)
                {

                    radioButton_atten.setVisibility(View.VISIBLE);
                    radioGroup.clearCheck();
                    imageView.setImageResource(0);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return rootView;
    }

    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, 200, 200, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? Color.BLACK : Color.WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, 200, 0, 0, w, h);
        return bitmap;
    }

    String getJSONString(String spinnerItemNo, String id, String radioButtonNo)
    {
        JSONObject jsonObject;
        try {
             jsonObject = new JSONObject()
                    .put("A", spinnerItemNo)
                    .put("B", radioButtonNo)
                    .put("I", id);
            Log.d("getJSONString", jsonObject.toString(4));
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
