package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    String data;
    String isOrderSubmitted = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent intent = getIntent();
        if(intent.hasExtra("isOrderSubmitted")){
            isOrderSubmitted = intent.getStringExtra("isOrderSubmitted");
            data = intent.getStringExtra("data");
        }


        final EditText editTextusername = (EditText) findViewById(R.id.registerusername);
        final EditText editTextPassword = (EditText) findViewById(R.id.registerpassword);
        final EditText editTextPhoneno = (EditText) findViewById(R.id.registerphoneno);
        Button buttonnext = (Button) findViewById(R.id.registernext);

        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextusername.getText().toString().equals("") || editTextPassword.getText().toString().equals("")
                        || editTextPhoneno.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Kindly enter full information", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(Register.this, OTP.class);
                intent.putExtra("username", editTextusername.getText().toString());
                intent.putExtra("password", editTextPassword.getText().toString());
                intent.putExtra("phoneno", editTextPhoneno.getText().toString());
                if(isOrderSubmitted.equals("true")){
                    intent.putExtra("isOrderSubmitted", "true");
                    intent.putExtra("data", data);
                }
                startActivity(intent);
            }
        });
    }
}
