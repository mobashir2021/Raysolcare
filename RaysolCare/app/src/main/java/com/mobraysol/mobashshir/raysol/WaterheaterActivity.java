package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class WaterheaterActivity extends AppCompatActivity {

    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterheater);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogrpwaterheater);
        Button btnNext = (Button) findViewById(R.id.btnWaterheaternext);
        Button btnPre = (Button) findViewById(R.id.btnWaterheaterprv);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedid = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedid);

                Intent intent1 = new Intent(WaterheaterActivity.this, GetLocation.class);
                intent1.putExtra("data", "waterheater:" + radioButton.getText() + "~");
                startActivity(intent1);
            }
        });

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(WaterheaterActivity.this, Dashboard.class);
                startActivity(intent1);
            }
        });
    }
}
