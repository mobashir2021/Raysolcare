package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.Dash;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent intent = getIntent();
        final String pincodespinnerdata = intent.getStringExtra("pincodespinner");

        Button buttonhome = (Button) findViewById(R.id.dashboardhome);
        Button buttonprofile = (Button) findViewById(R.id.dashboardmyprofile);
        Button buttonbooking = (Button) findViewById(R.id.dashboardbooking);

        buttonhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenthome = new Intent(Dashboard.this, MainActivity.class);
                intenthome.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intenthome);
            }
        });

        buttonprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentprofile = new Intent(Dashboard.this, MyprofileActivity.class);
                intentprofile.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intentprofile);
            }
        });

        buttonbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbooking = new Intent(Dashboard.this, BookinghistoryActivity.class);
                intentbooking.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intentbooking);
            }
        });

        TextView buttonacinstall = (TextView) findViewById(R.id.imgacinstall);
        TextView buttonacrepair = (TextView) findViewById(R.id.imgaircondition);
        TextView buttonwashingmachine = (TextView) findViewById(R.id.imgwashingmachine);
        TextView buttonaircooler = (TextView) findViewById(R.id.imgaircooler);
        TextView buttonwatercooler = (TextView) findViewById(R.id.imgwatercooler);
        TextView buttonref = (TextView) findViewById(R.id.imgref);
        TextView buttonmicrooven = (TextView) findViewById(R.id.microoven);
        TextView buttonannualmaintenance = (TextView) findViewById(R.id.imgmaintenance);
        TextView buttonwaterheater = (TextView) findViewById(R.id.imgwaterheater);

        buttonannualmaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Dashboard.this, MaintenanceActivity.class);
                intent1.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intent1);
            }
        });

        buttonacinstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, AcInstallation.class);
                intent.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intent);
            }
        });

        buttonacrepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, AcrepairActivity.class);
                intent.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intent);
            }
        });

        buttonaircooler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, AirCooler.class);
                intent.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intent);
            }
        });

        buttonref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Refrigeratorrepairmain.class);
                intent.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intent);
            }
        });

        buttonwashingmachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, WashingMachine.class);
                intent.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intent);
            }
        });

        buttonwatercooler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, WaterCooler.class);
                intent.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intent);
            }
        });

        buttonmicrooven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, MicroWave.class);
                intent.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intent);
            }
        });


        buttonwaterheater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, WaterheaterActivity.class);
                intent.putExtra("pincodespinner", pincodespinnerdata);
                startActivity(intent);
            }
        });
    }


}
