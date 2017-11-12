package com.example.ruchita.wifi_final_project;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
/**
 * Created by Ruchita on 21-10-2017.
 */

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {

    Button attendance,viewdefaulters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        attendance = (Button) findViewById(R.id.button4);
        attendance.setOnClickListener(this);
        viewdefaulters = (Button) findViewById(R.id.button5);
        viewdefaulters.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        if(v.getId()  == R.id.button4)
        {
            WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            wifi.setWifiEnabled(true);

            WifiConfiguration wificonfig = new WifiConfiguration();
            wificonfig.SSID="TeacherWifi";
            wifi.addNetwork(wificonfig);

        }



        if(v.getId()==R.id.button5)
        {

            Intent intent123 = new Intent(StudentActivity.this,ViewDefaulters.class);
            startActivity(intent123);

        }
    }
}
