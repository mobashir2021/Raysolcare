package com.mobraysol.mobashshir.raysol;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crud.entities.Logintabble;
import com.crud.model.LogintabbleModel;

public class MyprofileActivity extends AppCompatActivity {

    int LoginId;
    DatabaseHelper databaseHelper;
    String username;
    String password;
    String preusername;
    String prepassword;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        EditText textViewusername = (EditText) findViewById(R.id.myprofileusername);
        EditText textViewpassword = (EditText) findViewById(R.id.myprofilepassword);

        Button buttonlogout = (Button) findViewById(R.id.myprofileLogout);
        Button buttonhome = (Button) findViewById(R.id.myprofilehome);
        Button buttonsave = (Button) findViewById(R.id.myprofilesave);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationviewmyprofile);

        username = textViewusername.getText().toString();
        password = textViewpassword.getText().toString();

        databaseHelper = new DatabaseHelper(this);
        final Cursor res = databaseHelper.getLoginData();
        while(res.moveToNext()){
            LoginId = Integer.parseInt(res.getString(0));
            preusername = res.getString(1);
            prepassword = res.getString(2);
        }
        if(res.getCount() < 1){
            Intent intent = new Intent(MyprofileActivity.this, Login.class);
            startActivity(intent);
        }
        else{

                textViewusername.setText(preusername);
                textViewpassword.setText(prepassword);

        }
        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteData(String.valueOf(LoginId));
                Intent intent = new Intent(MyprofileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyprofileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(username != preusername || password != prepassword) {
                        //insert login to database
                        Logintabble logintabble = new Logintabble();
                        logintabble.setLoginId(LoginId);
                        logintabble.setName(username);
                        logintabble.setUsername(username);
                        logintabble.setUserEmail(username);
                        logintabble.setPassword(password);

                        boolean result = new HttpRequestAddUpdate().execute(logintabble).get();
                    }

                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setMessage("failed");
                    builder.show();
                }
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.myprofileactivitylayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.procontactus:
                        Intent intentcontactus = new Intent(Intent.ACTION_VIEW);
                        intentcontactus.setData(Uri.parse("http://www.raysolcare.com/ContactUs"));
                        startActivity(intentcontactus);
                        return true;
                    case R.id.proaboutraysolcare:
                        Intent intentaboutus = new Intent(Intent.ACTION_VIEW);
                        intentaboutus.setData(Uri.parse("http://www.raysolcare.com/AboutUs"));
                        startActivity(intentaboutus);
                        return true;
                    case R.id.protermsandcondition:
                        Intent intenttermsandcondition = new Intent(Intent.ACTION_VIEW);
                        intenttermsandcondition.setData(Uri.parse("http://www.raysolcare.com/TermsCondition"));
                        startActivity(intenttermsandcondition);
                        return true;
                    case R.id.propaymentonline:

                        return false;
                    case R.id.proprivacypolicy:
                        Intent intentprivacypolicy = new Intent(Intent.ACTION_VIEW);
                        intentprivacypolicy.setData(Uri.parse("http://www.raysolcare.com/Privacypolicy"));
                        startActivity(intentprivacypolicy);
                        return true;
                    case R.id.prorateus:
                        return false;
                    case R.id.proshare:
                        return false;
                    default:
                        return false;
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class HttpRequestAddUpdate extends AsyncTask<Logintabble, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Logintabble... logintabbles) {
            try {
                LogintabbleModel logintabbleModel = new LogintabbleModel();
                logintabbleModel.isLoginId = true;
                String value = logintabbleModel.create(logintabbles[0]);

                //insert login to sqlite
                databaseHelper.updateData(String.valueOf(LoginId), username, password);
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
}
