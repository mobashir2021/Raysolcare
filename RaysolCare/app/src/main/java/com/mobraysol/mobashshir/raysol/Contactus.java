package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Contactus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        Button buttonhome = (Button) findViewById(R.id.contactushome);
        Button buttonbooking = (Button) findViewById(R.id.contactusbooking);
        Button buttonmyprofile = (Button) findViewById(R.id.contactusmyprofile);

        buttonhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contactus.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contactus.this, BookinghistoryActivity.class);
                startActivity(intent);
            }
        });

        buttonmyprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contactus.this, MyprofileActivity.class);
                startActivity(intent);
            }
        });
    }
}
