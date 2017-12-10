package com.android.collegemanagementsystem;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CanteenDetailActivity extends AppCompatActivity {

    TextView tvCNAME,tvBalance;
    EditText amount,cafePass;
    Button pay,debt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen_detail);


        Bundle b=getIntent().getExtras();

        String cName=b.getString("CAFE_NAME");

        tvCNAME=(TextView)findViewById(R.id.tvCNAME);
        tvCNAME.setText(cName);

        tvBalance=(TextView)findViewById(R.id.tvBalance);


        SharedPreferences sp=getApplicationContext().getSharedPreferences("CanteenData",MODE_PRIVATE);
        String bal=sp.getString(tvCNAME.getText().toString() + "_Balance", "0");


        tvBalance.setText(bal);

        amount=(EditText)findViewById(R.id.etAmount);
        cafePass=(EditText)findViewById(R.id.etCafePass);

        pay=(Button)findViewById(R.id.btnPay);
        debt=(Button)findViewById(R.id.btnDebt);


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cafePass.getText().toString().equals(tvCNAME.getText().toString()))
                {
                    int c_bal = Integer.parseInt(tvBalance.getText().toString());
                    int t_amt = Integer.parseInt(amount.getText().toString());


                    c_bal = c_bal - t_amt;
                    SharedPreferences sp = getApplicationContext().getSharedPreferences("CanteenData", 0);
                    SharedPreferences.Editor e = sp.edit();
                    e.putString(tvCNAME.getText().toString() + "_Balance", String.valueOf(c_bal));
                    e.commit();


                    Toast.makeText(CanteenDetailActivity.this, "Account updated!", Toast.LENGTH_SHORT).show();
                    tvBalance.setText(String.valueOf(c_bal));
                    finish();
//                    Intent i=new Intent(CanteenDetailActivity.this,CanteenActivity.class);
//                    startActivity(i);
                }
                else
                {
                    Toast.makeText(CanteenDetailActivity.this, "Wrong Cafe Password", Toast.LENGTH_SHORT).show();
                }


            }
        });

        debt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cafePass.getText().toString().equals(tvCNAME.getText().toString()))
                {

                    int c_bal = Integer.parseInt(tvBalance.getText().toString());
                    int t_amt = Integer.parseInt(amount.getText().toString());


                    c_bal = c_bal + t_amt;
                    SharedPreferences sp = getApplicationContext().getSharedPreferences("CanteenData", 0);
                    SharedPreferences.Editor e = sp.edit();
                    e.putString(tvCNAME.getText().toString()+"_Balance", String.valueOf(c_bal));
                    e.commit();

                    Toast.makeText(CanteenDetailActivity.this, "Account updated!", Toast.LENGTH_SHORT).show();
                    tvBalance.setText(c_bal);
                    finish();
//                    Intent i=new Intent(CanteenDetailActivity.this,CanteenActivity.class);
//                    startActivity(i);
                }
                else
                {
                    Toast.makeText(CanteenDetailActivity.this, "Wrong Cafe Password", Toast.LENGTH_SHORT).show();
                }

            }
        });






    }
}
