package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.crud.entities.BookedhistoryAdapter;
import com.google.android.gms.maps.model.Dash;

import org.w3c.dom.Text;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    private Button button;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    ViewFlipper viewFlipper;
    String citypincode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonprofile = (Button) findViewById(R.id.mainmyprofile);
        Button buttonbookings = (Button) findViewById(R.id.mainbookings);
        Button buttonservices = (Button) findViewById(R.id.mainservices);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationviewmain);
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerpincode);

        TextView txtacrepair = findViewById(R.id.maacrepair);
        TextView txtacinstall = findViewById(R.id.maacinstall);
        TextView txtrefrigerator = findViewById(R.id.marefrigerator);
        TextView txtwatercooler = findViewById(R.id.mawatercooler);
        TextView txtaircooler = findViewById(R.id.maaircooler);
        TextView txtwashingmachine = findViewById(R.id.mawashingmachine);
        TextView txtmicrowave = findViewById(R.id.mamicrowave);
        TextView txtmaintenance = findViewById(R.id.mamaintenance);
        TextView txtwaterheater = findViewById(R.id.mawaterheater);

        txtacrepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentnew = new Intent(MainActivity.this, AcrepairActivity.class);
                intentnew.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                startActivity(intentnew);
            }
        });

        txtacinstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentnew = new Intent(MainActivity.this, AcInstallation.class);
                intentnew.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                startActivity(intentnew);
            }
        });

        txtrefrigerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentnew = new Intent(MainActivity.this, Refrigeratorrepairmain.class);
                intentnew.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                startActivity(intentnew);
            }
        });

        txtwatercooler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentnew = new Intent(MainActivity.this, WaterCooler.class);
                intentnew.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                startActivity(intentnew);
            }
        });

        txtaircooler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentnew = new Intent(MainActivity.this, AirCooler.class);
                intentnew.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                startActivity(intentnew);
            }
        });

        txtwashingmachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentnew = new Intent(MainActivity.this, WashingMachine.class);
                intentnew.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                startActivity(intentnew);
            }
        });

        txtmicrowave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentnew = new Intent(MainActivity.this, MicroWave.class);
                intentnew.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                startActivity(intentnew);
            }
        });

        txtmaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentnew = new Intent(MainActivity.this, MaintenanceActivity.class);
                intentnew.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                startActivity(intentnew);
            }
        });

        txtwaterheater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentnew = new Intent(MainActivity.this, WaterheaterActivity.class);
                intentnew.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                startActivity(intentnew);
            }
        });



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.homemenu:
                        Intent ihome = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(ihome);
                        return true;
                    case R.id.bookingmenu:
                        Intent ibooking = new Intent(MainActivity.this, BookinghistoryActivity.class);
                        ibooking.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                        startActivity(ibooking);
                        return true;
                    case R.id.chooseservicemenu:
                        Intent iservice = new Intent(MainActivity.this, Dashboard.class);
                        if(spinner.getSelectedItem().toString().equals("Select City")){
                            Toast.makeText(getApplicationContext(), "Kindly choose city", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        startActivity(iservice);
                        return true;
                    case R.id.contactusmenu:
                        Intent icontactus = new Intent(MainActivity.this, Contactus.class);
                        icontactus.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                        startActivity(icontactus);
                        return true;
                    case R.id.profilemenu:
                        Intent iprofile = new Intent(MainActivity.this, MyprofileActivity.class);
                        iprofile.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                        startActivity(iprofile);
                        return true;
                    default:
                        return false;
                }
            }
        });


        Spinner myspinner = (Spinner) findViewById(R.id.spinnerpincode);
        ArrayAdapter<String> myAdapterpincode = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinnerdefault));
        myAdapterpincode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(myAdapterpincode);


        viewFlipper = findViewById(R.id.mainviewflipper);
        int images[] = {R.drawable.slideraysolacrepair, R.drawable.slideraysolcarerefrigerator, R.drawable.slideraysolwashingmachineother};
        //for loop
        for(int i = 0; i < images.length; i++){
            flipperImages(images[i]);
        }

        /*new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);*/

        mDrawerLayout = (DrawerLayout) findViewById(R.id.mainactivity);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentprofile = new Intent(MainActivity.this, MyprofileActivity.class);
                intentprofile.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                startActivity(intentprofile);
            }
        });

        buttonbookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbooking = new Intent(MainActivity.this, BookinghistoryActivity.class);
                intentbooking.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                startActivity(intentbooking);
            }
        });

        buttonservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentservice = new Intent(MainActivity.this, Dashboard.class);
                if(spinner.getSelectedItem().toString().equals("Select City")){
                    //Toast.makeText(getApplicationContext(), "Kindly choose city", Toast.LENGTH_SHORT).show();
                }
                else {
                    intentservice.putExtra("pincodespinner", spinner.getSelectedItem().toString());
                    startActivity(intentservice);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            switch (item.getItemId()) {
                case R.id.homemenu:
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    this.startActivity(intent);
                    return true;
                case R.id.chooseservicemenu:
                    Intent intentchooseservices = new Intent(MainActivity.this, Dashboard.class);
                    this.startActivity(intentchooseservices);
                    return true;
                case R.id.contactusmenu:
                    Intent intentcontactus = new Intent(MainActivity.this, Contactus.class);
                    this.startActivity(intentcontactus);
                    return true;
                case R.id.bookingmenu:
                    Intent intentbookinghistory = new Intent(MainActivity.this, BookinghistoryActivity.class);
                    this.startActivity(intentbookinghistory);
                    return true;
                case R.id.profilemenu:
                    Intent intentprofile = new Intent(MainActivity.this, MyprofileActivity.class);
                    this.startActivity(intentprofile);
                    return true;
                default:
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);

        //animation
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}
