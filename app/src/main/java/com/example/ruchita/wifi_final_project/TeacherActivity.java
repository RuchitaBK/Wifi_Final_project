package com.example.ruchita.wifi_final_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.*;
/**
 * Created by user on 05-10-2017.
 */
public class TeacherActivity extends AppCompatActivity implements View.OnClickListener{

    Button attendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        attendance = (Button) findViewById(R.id.button4);
        attendance.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        if(v.getId()  == R.id.button4)
        {
            Intent inew = new Intent(TeacherActivity.this, AccessPointActivity.class);
            startActivity(inew);
        }
    }
}
