package com.example.ruchita.wifi_final_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;

/**
 * Created by user on 05-10-2017.
 */

public class Add_Student extends AppCompatActivity implements OnClickListener {

    Button save,back;
    EditText name,roll,branch,div,year,add1,mobile,attend;
    Database_Helper mydb_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__student);

        mydb_new = new Database_Helper(this);

        save=(Button) findViewById(R.id.save);
        save.setOnClickListener(this);
        back=(Button) findViewById(R.id.back);

        roll=(EditText)findViewById(R.id.writeroll);
        name=(EditText)findViewById(R.id.writename);
        branch=(EditText)findViewById(R.id.writebranch);
        div=(EditText)findViewById(R.id.writediv);
        year=(EditText)findViewById(R.id.writeyear);
        add1=(EditText)findViewById(R.id.writeaddress);
        mobile=(EditText)findViewById(R.id.writemobile);

        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {



        if(v.getId() == R.id.back)
        {
            Intent i_back=new Intent(getApplicationContext(),Admin.class);
            startActivity(i_back);
        }
        if(v.getId() == R.id.save)
        {

            int r = Integer.parseInt(roll.getText().toString());
           if(roll.getText().toString().length()<0)
            {
                Toast.makeText(Add_Student.this,"Please Enter All The Fields",Toast.LENGTH_LONG).show();
                return;
            }
//logic
            String n = name.getText().toString();
            if(TextUtils.isEmpty(n))
            {
                Toast.makeText(Add_Student.this,"Please Enter All The Fields",Toast.LENGTH_LONG).show();
                return;
            }
            String b = branch.getText().toString();
            if(TextUtils.isEmpty(b))
            {
                Toast.makeText(Add_Student.this,"Please Enter All The Fields",Toast.LENGTH_LONG).show();
                return;
            }
            String d=div.getText().toString();
            if(TextUtils.isEmpty(d))
            {
                Toast.makeText(Add_Student.this,"Please Enter All The Fields",Toast.LENGTH_LONG).show();
                return;
            }
            String y=year.getText().toString();
            if(TextUtils.isEmpty(y))
            {
                Toast.makeText(Add_Student.this,"Please Enter All The Fields",Toast.LENGTH_LONG).show();
                return;
            }
            String a=add1.getText().toString();
            if(TextUtils.isEmpty(a))
            {
                Toast.makeText(Add_Student.this,"Please Enter All The Fields",Toast.LENGTH_LONG).show();
                return;
            }
            String m=mobile.getText().toString();
            if(TextUtils.isEmpty(m))
            {
                Toast.makeText(Add_Student.this,"Please Enter All The Fields",Toast.LENGTH_LONG).show();
                return;
            }

            boolean isInserted = mydb_new.insertRecord(r,n,b,d,y,a,m,n);
            if(isInserted == true) {
                Toast.makeText(Add_Student.this, "Data Inserted", Toast.LENGTH_LONG).show();
                Intent isms=new Intent(Add_Student.this,SMSActivity.class);
                isms.putExtra("phn",m);
                startActivity(isms);


            }
            else
                Toast.makeText(Add_Student.this,"Data not Inserted",Toast.LENGTH_LONG).show();

        }

    }




  /*  public void viewAll() {
        back.setOnClickListener(
                img_new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb_new.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            Toast.makeText(Add_Student.this,"Nothing Found",Toast.LENGTH_LONG).show();
                            return;
                        }

                        StringBuffer buffer = img_new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id : "+ res.getString(0)+"\n");
                            buffer.append("Name : "+ res.getString(1)+"\n");
                            buffer.append("Branch : "+ res.getString(2)+"\n");
                            buffer.append("Division : "+ res.getString(3)+"\n");
                            buffer.append("Year : "+ res.getString(4)+"\n");
                            buffer.append("Address : "+ res.getString(5)+ "\n");
                            buffer.append("Mobile : "+ res.getString(6)+"\n");
                            buffer.append("Attendance : "+ res.getString(7)+ "\n");
                            buffer.append("Password : "+ res.getString(8)+ "\n\n");

                        }

                        // Show all data
                        Toast.makeText(Add_Student.this,buffer.toString(),Toast.LENGTH_LONG).show();

                    }


                }
        );
    }*/


}
