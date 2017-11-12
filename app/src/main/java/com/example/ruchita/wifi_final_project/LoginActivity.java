package com.example.ruchita.wifi_final_project;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/**
 * Created by Ruchita on 21-10-2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button login, button;
    EditText username, password;
    Database_Helper db;
    DatabaseTeacher db_teacher;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new Database_Helper(this);   // database for sudent
        db_teacher = new DatabaseTeacher(this);   //database for teacher

        username = (EditText) findViewById(R.id.editText5);
        password = (EditText) findViewById(R.id.editText6);

        login = (Button) findViewById(R.id.button2);
        login.setOnClickListener(this);




        progressDialog=new ProgressDialog(this);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("Please wait while processing");


    }

    public void onClick(View v) {

        if (v.getId() == R.id.button2) {             //button2 for login button in xml file


            String name = username.getText().toString();
            String pass = password.getText().toString();

            if(name.equals("")) {
                username.setError("Username cannot be empty");
            }
            if(pass.equals("")) {
                password.setError("Password cannot be empty");
            }
          else  if (name.equals("admin") && pass.equals("admin")) {
                performsignIn();


            }

          else  if(name.equals("teacher")&& pass.equals("teacher"))
            {

                Intent itech = new Intent(LoginActivity.this, TeacherActivity.class);
                startActivity(itech);
            }
          else  if(name.equals("student")&& pass.equals("student"))
            {

                Intent istu=new Intent(LoginActivity.this,StudentActivity.class);
                startActivity(istu);
            }
            else
            {
                if((!name.equals("teacher")||!name.equals("student")||!name.equals("admin"))||( !pass.equals("admin"))) {
                    Intent ierror = new Intent(LoginActivity.this, UnknownActivity.class);
                    startActivity(ierror);
                }
            }


        }
    }

    private  void performsignIn()
    {
        new SignIntask().execute(username.getText().toString(),password.getText().toString());

    }

    private  void showProgressDisalog(Boolean mustimp)
    {
        if(mustimp)
        {
            progressDialog.show();
        }
        else
        {
            progressDialog.dismiss();
        }


    }
    private  void showAlert(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this); //Builder class used for Notification Objects
        builder.setTitle(title);
        builder.setNegativeButton("Lets Start", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();



                Intent im = new Intent(LoginActivity.this, Admin.class);
                startActivity(im);

            }
        });
        builder.show();
    }


    class SignIntask extends AsyncTask<String,Void,Boolean> //perform operation in background and show result on ui
    {
        String tempusername ="admin";
        String temppassword="admin";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDisalog(true);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            showProgressDisalog(false);
            if(aBoolean)
            {
                showAlert("Welcome Admin ","You have successfully Login");
            }
            else
            {
                showAlert("Failed","Username /password is Incorrect");
            }
        }



        @Override
        protected Boolean doInBackground(String... strings) {
            String username = strings[0];
            String password =strings[1];
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return username.contentEquals(tempusername) && password.contentEquals(temppassword);

            //  return true;

        }
    }
}
