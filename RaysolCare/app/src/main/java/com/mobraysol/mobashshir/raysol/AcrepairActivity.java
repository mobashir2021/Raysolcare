package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AcrepairActivity extends AppCompatActivity {

    String pincodespinnerdata = "";
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acrepair);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.acrepairmainradiogrp);
        Button btn1next = (Button) findViewById(R.id.btnNextAcRepair);
        Button btn1pre = (Button) findViewById(R.id.btnPreviousAcRepair);

        Intent intent = getIntent();
        pincodespinnerdata = intent.getStringExtra("pincodespinner");

        btn1next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedid = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedid);
                if(radioButton.getText() == "Split AC"){
                    Intent intentnxt = new Intent(AcrepairActivity.this, AcrepairNextoneActivity.class);
                    intentnxt.putExtra("acrepairtype", radioButton.getText());
                    intentnxt.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intentnxt);
                }
                else{
                    Intent intenttxt = new Intent(AcrepairActivity.this, AcrepairWindowActivity.class);
                    intenttxt.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intenttxt);
                }

            }
        });
        btn1pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inttxt = new Intent(AcrepairActivity.this, Dashboard.class);
                inttxt.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(inttxt);
            }
        });

    }
}
