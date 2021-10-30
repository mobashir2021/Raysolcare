package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.crud.entities.BookedhistoryAdapter;
import com.crud.entities.Servicesvm;
import com.crud.model.RetrofitClient;
import com.crud.model.UserService;
import com.google.android.gms.maps.model.Dash;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class BookinghistoryActivity extends AppCompatActivity {

    int userid = 0;
    String url;
    private ListView listViewBookedhistory;
    private List<Servicesvm> servicesvmList;


    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinghistory);
        Button buttonhome = (Button) findViewById(R.id.bookinghome);
        Button buttonprofile = (Button) findViewById(R.id.bookingmyprofile);
        Button buttonservices = (Button) findViewById(R.id.bookingservices);
        final TextView txtbookingnorecord = findViewById(R.id.norecordbooking);

        buttonhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenthome = new Intent(BookinghistoryActivity.this, MainActivity.class);
                startActivity(intenthome);
            }
        });

        buttonprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentprofile = new Intent(BookinghistoryActivity.this, MyprofileActivity.class);
                startActivity(intentprofile);
            }
        });

        buttonservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentservices = new Intent(BookinghistoryActivity.this, Dashboard.class);
                startActivity(intentservices);
            }
        });

        databaseHelper = new DatabaseHelper(this);
        Cursor res = databaseHelper.getLoginData();
        if(res.getCount() < 1){
            //txtbookingnorecord.setText("No Booking History found");
        }
        else{
            while (res.moveToNext()){
                userid = Integer.parseInt(res.getString(0));
                url = "http://www.raysolcare.com/api/Servicesapi/";
                listViewBookedhistory = (ListView) findViewById(R.id.bookinghistorylistview);
                UserService userService = RetrofitClient.getClient(url).create(UserService.class);

                retrofit2.Call<List<Servicesvm>> call = userService.findBooking(userid);
                call.enqueue(new Callback<List<Servicesvm>>() {
                    @Override
                    public void onResponse(retrofit2.Call<List<Servicesvm>> call, Response<List<Servicesvm>> response) {
                        servicesvmList = (List<Servicesvm>) response.body();
                        listViewBookedhistory.setAdapter(new BookedhistoryAdapter(getApplicationContext(), servicesvmList));
                        txtbookingnorecord.setText("");
                    }

                    @Override
                    public void onFailure(retrofit2.Call<List<Servicesvm>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Failed to retrive bookings", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }




    }
}
