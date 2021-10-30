package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RefrigeratorCommercial extends AppCompatActivity {

    String pincodespinnerdata = "";
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrigerator_commercial);

        Button btnNext = (Button) findViewById(R.id.btnRefCommercialNext);
        Button btnPre = (Button) findViewById(R.id.btnRefCommercialprev);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogrpRefCommercial);
        Intent intent = getIntent();
        pincodespinnerdata = intent.getStringExtra("pincodespinner");


        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(RefrigeratorCommercial.this, Refrigeratorrepairmain.class);
                intent1.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intent1);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedid = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedid);

                Intent intent1 = new Intent(RefrigeratorCommercial.this, GetLocation.class);
                intent1.putExtra("data", "refcommercial:" + radioButton.getText() + "~");
                intent1.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intent1);
            }
        });
    }
}
