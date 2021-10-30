package com.mobraysol.mobashshir.raysol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class ChooseServicesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String [] SERVICESLIST = {"Ac Repair/Services", "Ac Installation/Uninstalltion", "Refrigerator Repair"
            ,"Water Cooler/Dispensor Repair", "Air Cooler Repair", "Washing Machine Repair", "Microwave Oven Repair"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_services);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this
                , android.R.layout.simple_dropdown_item_1line, SERVICESLIST);
        MaterialBetterSpinner betterSpinner = (MaterialBetterSpinner) findViewById(R.id.chooseservicesid);
        betterSpinner.setOnItemSelectedListener(this);
        betterSpinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
