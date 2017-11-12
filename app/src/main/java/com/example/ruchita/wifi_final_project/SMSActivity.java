package com.example.ruchita.wifi_final_project;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ruchita on 21-10-2017.
 */


public class SMSActivity extends AppCompatActivity {

    EditText etPhone, etMessage;
    Button btnSendSMS;

    private final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    private final String SENT = "SMS_SENT";
    private final String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        Intent isms=getIntent();
        String phno=isms.getStringExtra("phn");




        etPhone = (EditText) findViewById(R.id.etPhone);
        etMessage = (EditText) findViewById(R.id.etMessage);
        etPhone.setText(phno);
        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);

        sentPI = PendingIntent.getBroadcast(SMSActivity.this, 0, new Intent(SENT), 0);   //contex ,request code, new intent falg
        deliveredPI = PendingIntent.getBroadcast(SMSActivity.this, 0, new Intent(DELIVERED), 0);




        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = etMessage.getText().toString();
                String telNr = etPhone.getText().toString();

                if (ContextCompat.checkSelfPermission(SMSActivity.this, Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED)   // it is abstract class (conetxtcompat ) method checkselfPermission()
                {
                    ActivityCompat.requestPermissions(SMSActivity.this, new String [] {Manifest.permission.SEND_SMS},
                            MY_PERMISSIONS_REQUEST_SEND_SMS);
                }

                else
                {
                    SmsManager sms = SmsManager.getDefault();

                    sms.sendTextMessage(telNr, null, message, sentPI, deliveredPI);
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(smsSentReceiver);
        unregisterReceiver(smsDeliveredReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();


        smsSentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(context, "SMS sent successfully!", Toast.LENGTH_SHORT).show();
                        break;


                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(context, "Generic failure!", Toast.LENGTH_SHORT).show();
                        break;


                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(context, "No service!", Toast.LENGTH_SHORT).show();
                        break;


                    case SmsManager.RESULT_ERROR_NULL_PDU:  //protocol data unit ..industry format for send mgs
                        Toast.makeText(context, "Null PDU!", Toast.LENGTH_SHORT).show();
                        break;


                    case SmsManager.RESULT_ERROR_RADIO_OFF:      // Failed because radio was explicitly turned off    You switched your device into airplane mode, which tells your device exactly "turn all radios off" (cell, wifi, Bluetooth, NFC, ...).
                        Toast.makeText(context, "Radio off!", Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        };

        smsDeliveredReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch(getResultCode())   //int getResultCode () Retrieve the current result code, as set by the previous receiver.
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(context, "SMS delivered Succesfully !", Toast.LENGTH_SHORT).show();
                        break;

                    case Activity.RESULT_CANCELED:
                        Toast.makeText(context, "SMS not delivered!", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        };


        registerReceiver(smsSentReceiver, new IntentFilter(SENT));
        registerReceiver(smsDeliveredReceiver, new IntentFilter(DELIVERED));
    }

}


