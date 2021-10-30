package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class GetLocation extends AppCompatActivity {
    String pincodespinnerdata = "";
    String getPincodespinnerdata = "";
    private FusedLocationProviderClient client;
    boolean isPincodematch = false;
    Geocoder geocoder;
    List<Address> addresses;
    String message = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        Intent intent = getIntent();
        message = intent.getStringExtra("data");
        getPincodespinnerdata = intent.getStringExtra("pincodespinner");
        Button btnNext = (Button) findViewById(R.id.btnnextaddressactivity);
        Button btnPre = (Button) findViewById(R.id.btnpreviousaddresactivity);
        final TextView txtLocality = findViewById(R.id.CurrentAddressLocality);

        if(getPincodespinnerdata.equals("Choose Pincode")){
            pincodespinnerdata = "00000";
        }else{
            pincodespinnerdata = getPincodespinnerdata.split("-", 2)[1];

        }


        /*GPSTracker gpsTracker = new GPSTracker(this, GetLocation.this);
        if(gpsTracker.isGPSTrackingEnabled()){

        }*/

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView) findViewById(R.id.CurrentAddress);
                TextView textView1 = (TextView) findViewById(R.id.CurrentAddressLocality);
                String strtextloc = textView.getText().toString().trim() + textView1.getText().toString().trim();
                isPincodematch = txtLocality.getText().toString().equals(pincodespinnerdata);
                if(strtextloc.equals("")){
                    Toast.makeText(GetLocation.this, "Kindly enter your address", Toast.LENGTH_SHORT).show();
                }
                else if(!isPincodematch){
                    Toast.makeText(GetLocation.this, "Currently we don't provide service in this location or Select city", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent1 = new Intent(GetLocation.this, DateTime.class);
                    intent1.putExtra("data", message + "locationaddress:" + textView.getText() + "," + textView1.getText() + "~");
                    startActivity(intent1);
                }
            }
        });

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] str = message.split(":", 2);
                if(str[0].equals("acinstallsplit")){
                    Intent intent1 = new Intent(GetLocation.this, AcinstallationsplitActivity.class);
                    intent1.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intent1);
                }else if(str[0].equals("acinstallwindow")){
                    Intent intent1 = new Intent(GetLocation.this, AcinstallationWindowActivity.class);
                    intent1.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intent1);
                }else if(str[0].equals("acrepairsplit")){
                    Intent intent1 = new Intent(GetLocation.this, AcrepairNextoneActivity.class);
                    intent1.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intent1);
                }else if(str[0].equals("acrepairwindow")){
                    Intent intent1 = new Intent(GetLocation.this, AcrepairWindowActivity.class);
                    intent1.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intent1);
                }else if(str[0].equals("aircooler")){
                    Intent intent1 = new Intent(GetLocation.this, AirCooler.class);
                    intent1.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intent1);
                }else if(str[0].equals("microwave")){
                    Intent intent1 = new Intent(GetLocation.this, MicroWave.class);
                    intent1.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intent1);
                }else if(str[0].equals("microwave")){
                    Intent intent1 = new Intent(GetLocation.this, WashingMachine.class);
                    intent1.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intent1);
                }else if(str[0].equals("watercooler")){
                    Intent intent1 = new Intent(GetLocation.this, WaterCooler.class);
                    intent1.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intent1);
                }else if(str[0].equals("refsingledoor")){
                    Intent intent1 = new Intent(GetLocation.this, RefrigeratorSingledoor.class);
                    intent1.putExtra("pincodespinner", pincodespinnerdata);

                    startActivity(intent1);
                }else if(str[0].equals("refdoubledoor")){
                    Intent intent1 = new Intent(GetLocation.this, RefrigeratorDoubledoor.class);
                    intent1.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intent1);
                }else if(str[0].equals("refcommercial")){
                    Intent intent1 = new Intent(GetLocation.this, RefrigeratorCommercial.class);
                    intent1.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intent1);
                }else{
                    Intent intent1 = new Intent(GetLocation.this, Dashboard.class);
                    intent1.putExtra("pincodespinner", pincodespinnerdata);
                    startActivity(intent1);
                }


            }
        });
        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);
        if(ActivityCompat.checkSelfPermission(GetLocation.this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            client.getLastLocation().addOnSuccessListener(GetLocation.this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null){

                        geocoder = new Geocoder(GetLocation.this, Locale.getDefault());
                        try {
                            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                        if(addresses.size() > 0) {
                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            String city = addresses.get(0).getLocality();
                            String state = addresses.get(0).getAdminArea();
                            String country = addresses.get(0).getCountryName();
                            String postalCode = addresses.get(0).getPostalCode();
                            isPincodematch = postalCode.equals(pincodespinnerdata);

                            TextView txtLocationView = findViewById(R.id.CurrentAddress);
                            txtLocationView.setText(address);

                            txtLocality.setText(postalCode);
                        }
                    }
                }
            });
        }





    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
}
