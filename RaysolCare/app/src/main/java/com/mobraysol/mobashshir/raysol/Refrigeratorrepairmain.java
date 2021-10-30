package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Refrigeratorrepairmain extends AppCompatActivity {

    String pincodespinnerdata = "";
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrigeratorrepairmain);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogrpRefMain);
        Button btnnext = (Button) findViewById(R.id.btnRefNext);
        Button btnpre = (Button) findViewById(R.id.btnRefPrev);
        Intent intent = getIntent();
        pincodespinnerdata = intent.getStringExtra("pincodespinner");

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedid = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedid);
                if(radioButton.getText() == "Single Door"){
                    Intent intentnxt = new Intent(Refrigeratorrepairmain.this, RefrigeratorSingledoor.class);
                    intentnxt.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intentnxt);
                }
                else if(radioButton.getText() == "Double Door"){
                    Intent intenttxt = new Intent(Refrigeratorrepairmain.this, RefrigeratorDoubledoor.class);
                    intenttxt.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intenttxt);
                }
                else{
                    Intent intenttxt = new Intent(Refrigeratorrepairmain.this, RefrigeratorCommercial.class);
                    intenttxt.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intenttxt);
                }

            }
        });
        btnpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inttxt = new Intent(Refrigeratorrepairmain.this, Dashboard.class);
                inttxt.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(inttxt);
            }
        });
    }
}
