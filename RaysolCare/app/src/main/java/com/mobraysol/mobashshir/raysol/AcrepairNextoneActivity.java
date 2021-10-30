package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AcrepairNextoneActivity extends AppCompatActivity {

    String pincodespinnerdata = "";
    RadioButton radioButton;
    String datacol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acrepair_nextone);

        Intent intent = getIntent();
        datacol = intent.getStringExtra("acrepairtype");
        pincodespinnerdata = intent.getStringExtra("pincodespinner");

        Button btnNext = (Button) findViewById(R.id.btnNextAcrepairnextone);
        Button btnPre = (Button) findViewById(R.id.btnPreviousAcrepairnextone);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogrpAcrepairnextone);

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AcrepairNextoneActivity.this, AcrepairActivity.class);
                intent1.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intent1);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedid = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedid);

                Intent intent1 = new Intent(AcrepairNextoneActivity.this, GetLocation.class);
                intent1.putExtra("data", "acrepairsplit:" + radioButton.getText() + "~");
                intent1.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intent1);
            }
        });

    }
}
