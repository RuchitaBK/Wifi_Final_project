package com.example.ruchita.wifi_final_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruchita on 23-10-2017.
 */

public class Database_Helper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "NEW_Studentfinal.db";
    public static final String TABLE_NAME = "stud_table";
    public static final String COL_1 = "ROLL_NO";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "BRANCH";
    public static final String COL_4 = "DIVISION";
    public static final String COL_5 = "YEAR";
    public static final String COL_6 = "HARDWARE_ADD";
    public static final String COL_7 = "MOBILE_NO";
    public static final String COL_8 = "ATTENDANCE";
    public static final String COL_9 = "PASSWORD";


    public Database_Helper(Context context) {
        super(context, DATABASE_NAME, null , 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db_new) {
        db_new.execSQL("CREATE TABLE stud_table" + "(ROLL_NO INTEGER PRIMARY KEY,NAME TEXT,BRANCH TEXT,DIVISION TEXT,YEAR TEXT,HARDWARE_ADD TEXT,MOBILE_NO TEXT,ATTENDANCE INTEGER DEFAULT 0,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db_new, int oldVersion, int newVersion) {
        db_new.execSQL("DROP TABLE IF EXISTS stud_table");
        onCreate(db_new);
    }


    public boolean insertRecord(Integer id, String name, String branch,String div,String year,String add1,String mobile,String pass) {
        SQLiteDatabase db_new = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ROLL_NO", id);
        contentValues.put("NAME", name);
        contentValues.put("BRANCH", branch);
        contentValues.put("DIVISION", div);
        contentValues.put("YEAR", year);
        contentValues.put("HARDWARE_ADD",add1);
        contentValues.put("MOBILE_NO", mobile);
        contentValues.put("PASSWORD",pass);

        long result = db_new.insert("stud_table",null,contentValues);   // the framework does not insert a row when
                                                                        // there are no values
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME +" where roll_no="+id+"",null);
        return res;
    }

    public boolean updateData(Integer id,String addr,String mob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_6,addr);
        contentValues.put(COL_7,mob);
        db.update(TABLE_NAME, contentValues, "ROLL_NO = ?", new String[]{ id.toString() });
        return true;
    }
    public void updateContact(int rollno, int att) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_8,att);

        // updating row
        db.update(TABLE_NAME, values, COL_1 + " = ?",
                new String[] { String.valueOf(rollno) });
    }
//=============================================
    // get all the student for view teacher





    Student getStudent(String address) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] {COL_1,
                        COL_2, COL_8}, COL_6 + "=?",
                new String[] { String.valueOf(address) }, null, null, null, null);
        Student s;

        String name = cursor.getColumnName(2);

        // Toast.makeText(AccessPointActivity.this, name, Toast.LENGTH_SHORT).show();

        if (cursor.moveToFirst()){
            s = new Student(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        }
        else
            s=null;


        // return contact
        return s;
    }




    public List<Student> getAllContacts() {
        List<Student> contactList = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student contact = new Student();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setBranch(cursor.getString(2));
                contact.setDiv(cursor.getString(3));
                contact.setYear(cursor.getString(4));
                contact.setAddress(cursor.getString(5));
                contact.setMobile_no(cursor.getString(6));
                contact.setAttendance(Integer.parseInt(cursor.getString(7)));
                contact.setPassword(cursor.getString(8));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }


}
