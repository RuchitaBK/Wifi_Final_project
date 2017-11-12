package com.example.ruchita.wifi_final_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/**
 * Created by user on 05-10-2017.
 */

public class Update_Student extends AppCompatActivity implements OnClickListener {

    Button update,back;
    EditText name,roll,branch,div,year,addr,Mob;
    Database_Helper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__student);

        mydb = new Database_Helper(this);
        update=(Button) findViewById(R.id.update);
        update.setOnClickListener(this);

        roll=(EditText)findViewById(R.id.writeroll);
        addr=(EditText)findViewById(R.id.writeaddress);
        Mob=(EditText)findViewById(R.id.writemobile) ;

        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Admin.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.update)
        {

            int r = Integer.parseInt(roll.getText().toString());

            Cursor res = mydb.getAllData(r);
            if(res.getCount() == 0)
            {
                // show message
                Toast.makeText(Update_Student.this,"Roll No Not Found",Toast.LENGTH_LONG).show();
                return;
            }
            else {
                String a=addr.getText().toString();
                if(TextUtils.isEmpty(a))
                {
                    Toast.makeText(Update_Student.this,"Please Enter All The Fields",Toast.LENGTH_LONG).show();
                    return;
                }
                String m=Mob.getText().toString();
                if(TextUtils.isEmpty(a))
                {
                    Toast.makeText(Update_Student.this,"Please Enter All The Fields",Toast.LENGTH_LONG).show();
                    return;
                }

                boolean isUpdate = mydb.updateData(r, a, m);
                if (isUpdate == true)
                    Toast.makeText(Update_Student.this, "Data Updated Successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Update_Student.this, "Data not Updated", Toast.LENGTH_LONG).show();
            }
        }

    }

}

