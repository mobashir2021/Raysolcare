package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crud.entities.Logintabble;
import com.crud.entities.Servicesvm;
import com.crud.model.LogintabbleModel;
import com.crud.model.ServicesvmModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class OTP extends AppCompatActivity {
    FirebaseAuth auth;
    String phoneno;
    String username;
    String password;
    String isOrderSubmitted;
    String verificationcode;
    EditText editTextotp;
    String message;
    boolean result;
    DatabaseHelper databaseHelper;
    int userid = 0;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        databaseHelper = new DatabaseHelper(this);
        //get these values from Intent getextras
        Intent intent = getIntent();
        phoneno = intent.getStringExtra("phoneno");
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        if(intent.hasExtra("isOrderSubmitted")){
            isOrderSubmitted = intent.getStringExtra("isOrderSubmitted");
        }
        if(intent.hasExtra("data")){
            message = intent.getStringExtra("data");
        }

        editTextotp = (EditText)findViewById(R.id.otpvalue);



        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationcode = s;
                Toast.makeText(getApplicationContext(), "Code sent to the mobile number", Toast.LENGTH_SHORT).show();
            }
        };

        send_sms(findViewById(R.id.otpview));


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void send_sms(View v){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneno, 60, TimeUnit.SECONDS,this, mCallback);
    }

    public void SignInWithPhone(PhoneAuthCredential credential){
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            try {
                                //insert login to database
                                Logintabble logintabble = new Logintabble();
                                logintabble.setLoginId(0);
                                logintabble.setName(username);
                                logintabble.setUsername(username);
                                logintabble.setUserEmail(username);
                                logintabble.setPassword(password);
                                logintabble.setUserPhoneno(phoneno);
                                result = new HttpRequestAddLogin().execute(logintabble).get();
                            }catch (Exception e){
                                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                                builder.setMessage("failed");
                                builder.show();
                            }
                            if(isOrderSubmitted.equals("true") && result == true){
                                try {
                                    //call order submit
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    Date date = new Date();
                                    Servicesvm servicesvm = new Servicesvm();
                                    servicesvm.setsBookingdate(dateFormat.format(date).toString());
                                    servicesvm.setsEmailId(username);
                                    servicesvm.setsMobileNo(phoneno);
                                    servicesvm.setsUsername("Order Received");
                                    servicesvm.setsTimedata("00:00");
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
                                    sentMessage = sentMessage + "PhoneNo : " + phoneno + "~";
                                    servicesvm.setsServicesOrdered(sentMessage);
                                    servicesvm.setsUsername("Order Received");
                                    servicesvm.setLoginId(userid);

                                    //AddService(servicesvm);
                                    servicesvm.setsServicesOrdered(sentMessage);
                                    boolean result = new HttpRequestAddServicesvm().execute(servicesvm).get();



                                    if (true) {
                                        Intent intent1 = new Intent(OTP.this, MyprofileActivity.class);
                                        startActivity(intent1);
                                        Toast.makeText(OTP.this, "Your order placed successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                                        builder.setMessage("failed");
                                        builder.show();
                                    }
                                }catch (Exception e){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                                    builder.setMessage("failed");
                                    builder.show();
                                }
                            }

                        }
                    }
                });
    }

    public void verify(View v){
        String inputcode = editTextotp.getText().toString();
        if(inputcode.equals("")){
            Toast.makeText(getApplicationContext(), "Kindly enter OTP", Toast.LENGTH_SHORT).show();
            return;
        }

            verifyphonenumber(verificationcode, inputcode);


    }

    private void verifyphonenumber(String verifycode, String inputcode)
    {
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verifycode, inputcode);
        SignInWithPhone(phoneAuthCredential);
    }

    private class HttpRequestAddLogin extends AsyncTask<Logintabble, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Logintabble... logintabble) {
            try {
                LogintabbleModel logintabbleModel = new LogintabbleModel();
                String value = logintabbleModel.create(logintabble[0]);
                String[] arrval = value.split(",", 2);
                String[] arrvalnew = arrval[0].split(":",2);
                int loginidvalue = Integer.parseInt(arrvalnew[1]);
                //int loginidvalue = Integer.parseInt(value);
                //userid = loginidvalue;
                //insert login to sqlite
                databaseHelper.insertDataLogin(loginidvalue, username, password, phoneno);
                return true;
            }catch (Exception ex){
                throw ex;
            }

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

    private class HttpRequestAddServicesvm extends AsyncTask<Servicesvm, Void, Boolean>{

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
