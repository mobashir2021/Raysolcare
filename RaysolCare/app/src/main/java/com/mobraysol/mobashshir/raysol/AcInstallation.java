package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AcInstallation extends AppCompatActivity {

    String pincodespinnerdata = "";
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_installation);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogrpAcinstallmain);
        Button btnnext = (Button) findViewById(R.id.btnNextAcinstallmain);
        Button btnpre = (Button) findViewById(R.id.btnPreviousAcinstallmain);
        Intent intent = getIntent();
        pincodespinnerdata = intent.getStringExtra("pincodespinner");

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedid = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedid);
                if(radioButton.getText() == "Split AC"){
                    Intent intentnxt = new Intent(AcInstallation.this, AcinstallationsplitActivity.class);
                    intentnxt.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intentnxt);
                }
                else{
                    Intent intenttxt = new Intent(AcInstallation.this, AcinstallationWindowActivity.class);
                    intenttxt.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intenttxt);
                }

            }
        });
        btnpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inttxt = new Intent(AcInstallation.this, Dashboard.class);
                inttxt.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(inttxt);
            }
        });
    }
}
