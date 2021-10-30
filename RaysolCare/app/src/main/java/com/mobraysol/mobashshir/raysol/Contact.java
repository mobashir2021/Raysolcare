package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crud.entities.Servicesvm;
import com.crud.model.APIUtils;
import com.crud.model.ServicesvmModel;
import com.crud.model.UserService;

import org.json.JSONObject;

import java.security.cert.Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contact extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    String to, subject , message;
    String errorval = "";
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        final String message = intent.getStringExtra("data");
        /*final EditText txtName = (EditText) findViewById(R.id.txtName);
        final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        final EditText txtPhoneno = (EditText) findViewById(R.id.txtPhoneNo);*/

        Button buttonhome = findViewById(R.id.submithome);
        Button buttonprofile = findViewById(R.id.submitprofile);
        Button buttonbooking = findViewById(R.id.submitbooking);
        Button buttonservice = findViewById(R.id.submitservice);

        buttonhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Contact.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        buttonprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Contact.this, MyprofileActivity.class);
                startActivity(intent1);
            }
        });

        buttonbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Contact.this, BookinghistoryActivity.class);
                startActivity(intent1);
            }
        });

        buttonservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Contact.this, Dashboard.class);
                startActivity(intent1);
            }
        });





        //userService = APIUtils.getUserService();


        Button btnSubmit = (Button) findViewById(R.id.btnFinalSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{


                    if(1==1) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        Servicesvm servicesvm = new Servicesvm();
                        servicesvm.setsBookingdate(dateFormat.format(date).toString());
                        /*servicesvm.setsEmailId(txtEmail.getText().toString());
                        servicesvm.setsMobileNo(txtPhoneno.getText().toString());
                        servicesvm.setsUsername(txtName.getText().toString());*/
                        servicesvm.setsTimedata("00:00");
                        String sentMessage = "";
                        String[] inner = message.split(":", 2);

                        if(inner[0].toString().trim().equals("acrepairsplit")){
                            String[] strnew = inner[1].trim().split("~",2);
                            sentMessage = "ServiceType: Split Ac Repair, ";
                            if(strnew[0].toString().trim().equals("Ac Repair/ Services Rs 349")){
                                sentMessage = sentMessage + "Order : Ac Repair/Services, ";
                            }
                            if(strnew[0].toString().trim().equals("Wet Service Rs 599")){
                                sentMessage = sentMessage + "Order : Wet Services, ";
                            }
                            if(strnew[0].toString().trim().equals("Gas Charging Rs 1699")){
                                sentMessage = sentMessage + "Order : Gas Charging, ";
                            }
                        }else if(inner[0].toString().trim().equals("acrepairwindow")){
                            String[] strnew = inner[1].trim().split("~",2);
                            sentMessage = "ServiceType: Window Ac Repair, ";
                            if(strnew[0].toString().trim().equals("Ac Repair/ Services Rs 349")){
                                sentMessage = sentMessage + "Order : Ac Repair/Services, ";
                            }
                            if(strnew[0].toString().trim().equals("Wet Service Rs 599")){
                                sentMessage = sentMessage + "Order : Wet Services, ";
                            }
                            if(strnew[0].toString().trim().equals("Gas Charging Rs 1699")){
                                sentMessage = sentMessage + "Order : Gas Charging, ";
                            }
                        }else if(inner[0].toString().trim().equals("acinstallsplit")){
                            String[] strnew = inner[1].trim().split("~",2);
                            sentMessage = "ServiceType: Split Ac Install, ";
                            if(strnew[0].toString().trim().equals("Installation Rs 1399")){
                                sentMessage = sentMessage + "Order : Installation, ";
                            }
                            if(strnew[0].toString().trim().equals("Uninstallation (Dismantling) Rs 649")){
                                sentMessage = sentMessage + "Order : Uninstallation(Dismantling), ";
                            }
                        }else if(inner[0].toString().trim().equals("acinstallwindow")){
                            String[] strnew = inner[1].trim().split("~",2);
                            sentMessage = "ServiceType: Window Ac Install, ";
                            if(strnew[0].trim().equals("Installation Rs 599")){
                                sentMessage = sentMessage + "Order : Installation, ";
                            }
                            if(strnew[0].trim().equals("Uninstallation(Dismantling)Rs 349")){
                                sentMessage = sentMessage + "Order : Uninstallation(Dismantling), ";
                            }
                        }else if(inner[0].equals("refsingledoor")){
                            String[] strnew = inner[1].trim().split("~",2);
                            sentMessage = "ServiceType: Refrigerator SingleDoor, ";
                            if(strnew[0].trim().equals("Repair Rs 299")){
                                sentMessage = sentMessage + "Order : Repair, ";
                            }
                            if(strnew[0].trim().equals("Gas Charging Rs 899")){
                                sentMessage = sentMessage + "Order : Gas Charging, ";
                            }
                        }else if(inner[0].equals("refdoubledoor")){
                            String[] strnew = inner[1].trim().split("~",2);
                            sentMessage = "ServiceType: Refrigerator DoubleDoor, ";
                            if(strnew[0].trim().equals("Repair Rs 299")){
                                sentMessage = sentMessage + "Order : Repair, ";
                            }
                            if(strnew[0].trim().equals("Gas Charging Rs 999")){
                                sentMessage = sentMessage + "Order : Gas Charging, ";
                            }
                        }else if(inner[0].equals("refcommercial")){
                            String[] strnew = inner[1].trim().split("~",2);
                            sentMessage = "ServiceType: Refrigerator Commercial, ";
                            if(strnew[0].trim().equals("Repair Rs 349")){
                                sentMessage = sentMessage + "Order : Repair, ";
                            }
                            if(strnew[0].trim().equals("Gas Charging Rs 1499")){
                                sentMessage = sentMessage + "Order : Gas Charging, ";
                            }
                        }else if(inner[0].equals("washingmachine")){
                            String[] strnew = inner[1].trim().split("~",2);
                            sentMessage = "ServiceType: Washing Machine, ";
                            if(strnew[0].trim().equals("Repair or Service(Fully Automatic) Rs 349")){
                                sentMessage = sentMessage + "Order : Repair or Service Fully automatic, ";
                            }
                            if(strnew[0].trim().equals("Repair or Service(Semi Automatic) Rs 299")){
                                sentMessage = sentMessage + "Order : Repair or Service Semi automatic, ";
                            }
                            if(strnew[0].trim().equals("Installation/Uninstalltion Rs 399")){
                                sentMessage = sentMessage + "Order : Installation/Uninstallation, ";
                            }
                        }else if(inner[0].equals("microwave")){
                            String[] strnew = inner[1].trim().split("~",2);
                            sentMessage = "ServiceType: Microwave Oven, ";
                            if(strnew[0].trim().equals("Repair or Service Rs 399")){
                                sentMessage = sentMessage + "Order : Repair/Services, ";
                            }

                        }else if(inner[0].equals("aircooler")){
                            String[] strnew = inner[1].trim().split("~",2);
                            sentMessage = "ServiceType: Aircooler, ";
                            if(strnew[0].trim().equals("Repair Rs 299")){
                                sentMessage = sentMessage + "Order : Repair, ";
                            }
                            if(strnew[0].trim().equals("Servicing and Cleaning Rs 299")){
                                sentMessage = sentMessage + "Order : Servicing/Cleaning, ";
                            }
                        }else if(inner[0].equals("watercooler")){
                            String[] strnew = inner[1].trim().split("~",2);
                            sentMessage = "ServiceType: WaterCooler, ";
                            if(strnew[0].trim().equals("Repair Rs 349")){
                                sentMessage = sentMessage + "Order : Repair, ";
                            }
                            if(strnew[0].trim().equals("Gas Charging Dispensor Rs 899")){
                                sentMessage = sentMessage + "Order : Gascharging Dispensor, ";
                            }
                            if(strnew[0].trim().equals("Gas Charging Water Cooler Rs 1449")){
                                sentMessage = sentMessage + "Order : Gascharging Watercooler, ";
                            }
                        }

                        String[] strMessage = inner[1].trim().split("~",2);//strmessage 1 all from location
                        String[] strnewone = strMessage[1].split(":", 2);
                        String[] strloc = strnewone[1].split("~", 2);
                        sentMessage = sentMessage + " Location : " + strloc[0].trim() + ", "; // strloc 1 all from datetime
                        servicesvm.setsUseraddress(strloc[0].trim());
                        String[] strnewtwo = strnewone[1].split(":", 2);
                        String[] strnewdatetime = strnewtwo[1].split("~", 2);
                        sentMessage = sentMessage + "DateTime : " + strnewdatetime[0].trim() + ", "; //strnewdatetime 1 from name


                        Cursor res = databaseHelper.getLoginData();
                        if(res.getCount() > 0) {

                            servicesvm.setLoginId(Integer.parseInt(res.getString(0)));
                            sentMessage = sentMessage + "Name : " + res.getString(1) + ", ";
                            sentMessage = sentMessage + "Email : " + res.getString(3) + ", ";
                            sentMessage = sentMessage + "PhoneNo : " + res.getString(4) + "~";
                            servicesvm.setsUsername("Order Received");
                            servicesvm.setsMobileNo(res.getString(4));
                            servicesvm.setsEmailId(res.getString(3));
                            servicesvm.setsServicesOrdered(sentMessage);
                            //AddService(servicesvm);
                            servicesvm.setsServicesOrdered(sentMessage);
                            boolean result = new HttpRequestAdd().execute(servicesvm).get();


                            if (true) {
                                Intent intent1 = new Intent(Contact.this, Dashboard.class);
                                startActivity(intent1);
                                Toast.makeText(Contact.this, "Your order placed successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                                builder.setMessage("failed");
                                builder.show();
                            }
                        }
                        else{
                            Intent intent1 = new Intent(Contact.this, Login.class);
                            intent1.putExtra("data", message);
                            intent1.putExtra("isOrderSubmitted", "true");
                            startActivity(intent1);
                        }
                    }
                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setMessage("failed");
                    builder.show();
                }





            }
        });
    }



    public void AddService(Servicesvm servicesvm){
        Call call = userService.addservice(servicesvm);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {

                    Intent intent1 = new Intent(Contact.this, MainActivity.class);
                    startActivity(intent1);
                    Toast.makeText(Contact.this, "Your order placed successfully", Toast.LENGTH_SHORT).show();

                }else{
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Toast.makeText(Contact.this, t.getMessage().toString(), Toast.LENGTH_LONG).show();
                Log.e("Error: ", t.getMessage());
            }
        });
    }



    private class HttpRequestAdd extends AsyncTask<Servicesvm, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Servicesvm... servicesvms) {
            try {
                ServicesvmModel servicesvmModel = new ServicesvmModel();
                return servicesvmModel.create(servicesvms[0]);
            }catch (Exception ex){
                throw ex;
            }

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }


}


