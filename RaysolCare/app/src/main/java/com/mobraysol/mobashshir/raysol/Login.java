package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crud.entities.Logintabble;
import com.crud.entities.Servicesvm;
import com.crud.model.LoginService;
import com.crud.model.RetrofitClient;
import com.crud.model.ServicesvmModel;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    String url = "http://www.raysolcare.com/api/Loginapi/";
    private Logintabble logintabble;
    String username = "";
    String password = "";
    DatabaseHelper databaseHelper;
    String isOrderSubmitted = "";
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DatabaseHelper(this);

        final EditText editTextusername = (EditText) findViewById(R.id.loginusername);
        final EditText editTextpasword = (EditText) findViewById(R.id.loginpassword);
        Button buttonLogin = (Button) findViewById(R.id.loginbutton);
        TextView textViewRegister = (TextView) findViewById(R.id.loginlinkregister);
        Intent intentpassed = getIntent();
        if(intentpassed.hasExtra("isOrderSubmitted")){
            isOrderSubmitted = intentpassed.getStringExtra("isOrderSubmitted");
            message = intentpassed.getStringExtra("data");
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editTextusername.getText().toString();
                password = editTextpasword.getText().toString();
                if(username.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(), "Kindly enter informations", Toast.LENGTH_SHORT).show();
                    return;
                }
                LoginService loginService = RetrofitClient.getClient(url).create(LoginService.class);
                Call<Logintabble> call = loginService.findLoginbyPassword(username, password);
                call.enqueue(new Callback<Logintabble>() {
                    @Override
                    public void onResponse(Call<Logintabble> call, Response<Logintabble> response) {
                        logintabble = (Logintabble) response.body();
                        if(logintabble.getLoginId() > 0) {
                            if (!logintabble.getPassword().equals("")) {
                                if (isOrderSubmitted.equals("true")) {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    Date date = new Date();
                                    Servicesvm servicesvm = new Servicesvm();
                                    servicesvm.setsBookingdate(dateFormat.format(date).toString());
                                    servicesvm.setsEmailId(username);
                                    servicesvm.setsMobileNo(logintabble.getUserPhoneno());
                                    servicesvm.setsUsername("Order Received");
                                    servicesvm.setsTimedata("00:00");
                                    servicesvm.setLoginId(logintabble.getLoginId());
                                    String sentMessage = "";
                                    String[] inner = message.split(":", 2);

                                    if (inner[0].toString().trim().equals("acrepairsplit")) {
                                        String[] strnew = inner[1].trim().split("~", 2);
                                        sentMessage = "ServiceType: Split Ac Repair, ";
                                        if (strnew[0].toString().trim().equals("Ac Repair/ Services Rs 349")) {
                                            sentMessage = sentMessage + "Order : Ac Repair/Services, ";
                                        }
                                        if (strnew[0].toString().trim().equals("Wet Service Rs 599")) {
                                            sentMessage = sentMessage + "Order : Wet Services, ";
                                        }
                                        if (strnew[0].toString().trim().equals("Gas Charging Rs 1699")) {
                                            sentMessage = sentMessage + "Order : Gas Charging, ";
                                        }
                                    } else if (inner[0].toString().trim().equals("acrepairwindow")) {
                                        String[] strnew = inner[1].trim().split("~", 2);
                                        sentMessage = "ServiceType: Window Ac Repair, ";
                                        if (strnew[0].toString().trim().equals("Ac Repair/ Services Rs 349")) {
                                            sentMessage = sentMessage + "Order : Ac Repair/Services, ";
                                        }
                                        if (strnew[0].toString().trim().equals("Wet Service Rs 599")) {
                                            sentMessage = sentMessage + "Order : Wet Services, ";
                                        }
                                        if (strnew[0].toString().trim().equals("Gas Charging Rs 1699")) {
                                            sentMessage = sentMessage + "Order : Gas Charging, ";
                                        }
                                    } else if (inner[0].toString().trim().equals("acinstallsplit")) {
                                        String[] strnew = inner[1].trim().split("~", 2);
                                        sentMessage = "ServiceType: Split Ac Install, ";
                                        if (strnew[0].toString().trim().equals("Installation Rs 1399")) {
                                            sentMessage = sentMessage + "Order : Installation, ";
                                        }
                                        if (strnew[0].toString().trim().equals("Uninstallation (Dismantling) Rs 649")) {
                                            sentMessage = sentMessage + "Order : Uninstallation(Dismantling), ";
                                        }
                                    } else if (inner[0].toString().trim().equals("acinstallwindow")) {
                                        String[] strnew = inner[1].trim().split("~", 2);
                                        sentMessage = "ServiceType: Window Ac Install, ";
                                        if (strnew[0].trim().equals("Installation Rs 599")) {
                                            sentMessage = sentMessage + "Order : Installation, ";
                                        }
                                        if (strnew[0].trim().equals("Uninstallation(Dismantling)Rs 349")) {
                                            sentMessage = sentMessage + "Order : Uninstallation(Dismantling), ";
                                        }
                                    } else if (inner[0].equals("refsingledoor")) {
                                        String[] strnew = inner[1].trim().split("~", 2);
                                        sentMessage = "ServiceType: Refrigerator SingleDoor, ";
                                        if (strnew[0].trim().equals("Repair Rs 299")) {
                                            sentMessage = sentMessage + "Order : Repair, ";
                                        }
                                        if (strnew[0].trim().equals("Gas Charging Rs 899")) {
                                            sentMessage = sentMessage + "Order : Gas Charging, ";
                                        }
                                    } else if (inner[0].equals("refdoubledoor")) {
                                        String[] strnew = inner[1].trim().split("~", 2);
                                        sentMessage = "ServiceType: Refrigerator DoubleDoor, ";
                                        if (strnew[0].trim().equals("Repair Rs 299")) {
                                            sentMessage = sentMessage + "Order : Repair, ";
                                        }
                                        if (strnew[0].trim().equals("Gas Charging Rs 999")) {
                                            sentMessage = sentMessage + "Order : Gas Charging, ";
                                        }
                                    } else if (inner[0].equals("refcommercial")) {
                                        String[] strnew = inner[1].trim().split("~", 2);
                                        sentMessage = "ServiceType: Refrigerator Commercial, ";
                                        if (strnew[0].trim().equals("Repair Rs 349")) {
                                            sentMessage = sentMessage + "Order : Repair, ";
                                        }
                                        if (strnew[0].trim().equals("Gas Charging Rs 1499")) {
                                            sentMessage = sentMessage + "Order : Gas Charging, ";
                                        }
                                    } else if (inner[0].equals("washingmachine")) {
                                        String[] strnew = inner[1].trim().split("~", 2);
                                        sentMessage = "ServiceType: Washing Machine, ";
                                        if (strnew[0].trim().equals("Repair or Service(Fully Automatic) Rs 349")) {
                                            sentMessage = sentMessage + "Order : Repair or Service Fully automatic, ";
                                        }
                                        if (strnew[0].trim().equals("Repair or Service(Semi Automatic) Rs 299")) {
                                            sentMessage = sentMessage + "Order : Repair or Service Semi automatic, ";
                                        }
                                        if (strnew[0].trim().equals("Installation/Uninstalltion Rs 399")) {
                                            sentMessage = sentMessage + "Order : Installation/Uninstallation, ";
                                        }
                                    } else if (inner[0].equals("microwave")) {
                                        String[] strnew = inner[1].trim().split("~", 2);
                                        sentMessage = "ServiceType: Microwave Oven, ";
                                        if (strnew[0].trim().equals("Repair or Service Rs 399")) {
                                            sentMessage = sentMessage + "Order : Repair/Services, ";
                                        }

                                    } else if (inner[0].equals("aircooler")) {
                                        String[] strnew = inner[1].trim().split("~", 2);
                                        sentMessage = "ServiceType: Aircooler, ";
                                        if (strnew[0].trim().equals("Repair Rs 299")) {
                                            sentMessage = sentMessage + "Order : Repair, ";
                                        }
                                        if (strnew[0].trim().equals("Servicing and Cleaning Rs 299")) {
                                            sentMessage = sentMessage + "Order : Servicing/Cleaning, ";
                                        }
                                    } else if (inner[0].equals("watercooler")) {
                                        String[] strnew = inner[1].trim().split("~", 2);
                                        sentMessage = "ServiceType: WaterCooler, ";
                                        if (strnew[0].trim().equals("Repair Rs 349")) {
                                            sentMessage = sentMessage + "Order : Repair, ";
                                        }
                                        if (strnew[0].trim().equals("Gas Charging Dispensor Rs 899")) {
                                            sentMessage = sentMessage + "Order : Gascharging Dispensor, ";
                                        }
                                        if (strnew[0].trim().equals("Gas Charging Water Cooler Rs 1449")) {
                                            sentMessage = sentMessage + "Order : Gascharging Watercooler, ";
                                        }
                                    }

                                    String[] strMessage = inner[1].trim().split("~", 2);//strmessage 1 all from location
                                    String[] strnewone = strMessage[1].split(":", 2);
                                    String[] strloc = strnewone[1].split("~", 2);
                                    sentMessage = sentMessage + " Location : " + strloc[0].trim() + ", "; // strloc 1 all from datetime
                                    servicesvm.setsUseraddress(strloc[0].trim());
                                    String[] strnewtwo = strnewone[1].split(":", 2);
                                    String[] strnewdatetime = strnewtwo[1].split("~", 2);
                                    sentMessage = sentMessage + "DateTime : " + strnewdatetime[0].trim() + ", "; //strnewdatetime 1 from name
                                    sentMessage = sentMessage + "Name : " + username + ", ";
                                    sentMessage = sentMessage + "Email : " + username + ", ";
                                    sentMessage = sentMessage + "PhoneNo : " + logintabble.getUserPhoneno() + "~";
                                    servicesvm.setsServicesOrdered(sentMessage);
                                    servicesvm.setLoginId(logintabble.getLoginId());
                                    servicesvm.setsUsername("Order Received");

                                    //AddService(servicesvm);
                                    servicesvm.setsServicesOrdered(sentMessage);
                                    try {
                                        boolean resultnew = new HttpRequestAddServicesvmLogin().execute(servicesvm).get();
                                    } catch (Exception e) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                                        builder.setMessage("failed");
                                        builder.show();
                                    }
                                }

                                //insert into sqllite
                                databaseHelper.insertDataLogin(logintabble.getLoginId(), logintabble.getUsername(), logintabble.getPassword(), logintabble.getUserPhoneno());
                                Intent intent = new Intent(Login.this, MyprofileActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Username and password is wrong", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Logintabble> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Failed to Login", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                if(isOrderSubmitted.equals("true")) {
                    intent.putExtra("isOrderSubmitted", "true");
                    intent.putExtra("data", message);
                }
                startActivity(intent);
            }
        });


    }

    private class HttpRequestAddServicesvmLogin extends AsyncTask<Servicesvm, Void, Boolean> {

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
