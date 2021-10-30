package com.mobraysol.mobashshir.raysol;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class DateTime extends AppCompatActivity {

    Button button;
    String timedata;
    String message = "";
    private static final String TAG = "DateTimeActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        mDisplayDate = (TextView) findViewById(R.id.dateorder);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(DateTime.this, android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener, year,month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "-" + day + "-" + year;
                mDisplayDate.setText(date);
            }
        };
        Intent intentmesssage = getIntent();
        message = intentmesssage.getStringExtra("data");
        Button btnNext = (Button) findViewById(R.id.btnNextDatetime);
        Button btnPre = (Button) findViewById(R.id.btnPreviousDatetime);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDisplayDate.getText() == "") {
                    Toast.makeText(DateTime.this, "Kindly choose Date", Toast.LENGTH_SHORT).show();
                }
                if(timedata == "") {
                    Toast.makeText(DateTime.this, "Kindly choose Time", Toast.LENGTH_SHORT).show();
                }
                if(mDisplayDate.getText() != "" && timedata != "") {
                    Intent intent = new Intent(DateTime.this, Contact.class);
                    intent.putExtra("data", message + "date:" + mDisplayDate.getText() + " " + timedata);
                    startActivity(intent);
                }
            }
        });

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DateTime.this, GetLocation.class);
                startActivity(intent);
            }
        });
    }

    public void onClick(View v)
    {
        Drawable dr = getResources().getDrawable(R.drawable.button_pressed);
        dr.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);

        switch (v.getId()) {
            case R.id.btnninetoeleven:

                if (button == null) {
                    button = (Button) findViewById(v.getId());
                } else {
                    button.setBackgroundResource(R.drawable.button_pressed);
                    button = (Button) findViewById(v.getId());
                }
                button.setBackgroundDrawable(dr);
                timedata = "time:09-11AM~";
                break;

            case R.id.btneleventoonepm:
                if (button == null) {
                    button = (Button) findViewById(v.getId());
                } else {
                    button.setBackgroundResource(R.drawable.button_pressed);
                    button = (Button) findViewById(v.getId());
                }
                button.setBackgroundDrawable(dr);
                timedata = "time:11-01PM~";
                break;

            case R.id.btnonetothreepm:
                if (button == null) {
                    button = (Button) findViewById(v.getId());
                } else {
                    button.setBackgroundResource(R.drawable.button_pressed);
                    button = (Button) findViewById(v.getId());
                }
                button.setBackgroundDrawable(dr);
                timedata = "time:01-03PM~";
                break;

            case R.id.btnthreetofivepm:
                if (button == null) {
                    button = (Button) findViewById(v.getId());
                } else {
                    button.setBackgroundResource(R.drawable.button_pressed);
                    button = (Button) findViewById(v.getId());
                }
                button.setBackgroundDrawable(dr);
                timedata = "time:03-05PM~";
                break;

            case R.id.btnfivetosevenpm:
                if (button == null) {
                    button = (Button) findViewById(v.getId());
                } else {
                    button.setBackgroundResource(R.drawable.button_pressed);
                    button = (Button) findViewById(v.getId());
                }
                button.setBackgroundDrawable(dr);
                timedata = "time:05-07PM~";
                break;

            case R.id.btnseventoninepm:
                if (button == null) {
                    button = (Button) findViewById(v.getId());
                } else {
                    button.setBackgroundResource(R.drawable.button_pressed);
                    button = (Button) findViewById(v.getId());
                }
                button.setBackgroundDrawable(dr);
                timedata = "time:07-09PM~";
                break;

            default:
                break;
        }
    }
}
