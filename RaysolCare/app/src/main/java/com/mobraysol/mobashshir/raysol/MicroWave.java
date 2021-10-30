package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MicroWave extends AppCompatActivity {

    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micro_wave);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogrpmicrowave);
        Button btnNext = (Button) findViewById(R.id.btnMicrowaveNext);
        Button btnPre = (Button) findViewById(R.id.btnMicrowavePrev);

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MicroWave.this, Dashboard.class);
                startActivity(intent1);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedid = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedid);

                Intent intent1 = new Intent(MicroWave.this, GetLocation.class);
                intent1.putExtra("data", "microwave:" + radioButton.getText() + "~");
                startActivity(intent1);
            }
        });
    }
}
