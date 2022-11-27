package com.example.mywellnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBReminderHelper extends SQLiteOpenHelper {
    public DBReminderHelper(Context context) {
        super(context,"Reminderdetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Reminderdetails(name TEXT primary key, date TEXT, time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Reminderdetails");
    }

    public Boolean insertuserdata(String name, String date, String time){
       SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("date",date);
        contentValues.put("time",time);


        long result = DB.insert("Reminderdetails", null,contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }

    }

    public Boolean updateuserdata(String name, String date, String time){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date",date);
        contentValues.put("time",time);
        Cursor cursor = DB.rawQuery("Select * from Reminderdetails where name = ?",new String[]{name});
        if (cursor.getCount()>0){

            long result = DB.update("Reminderdetails",contentValues,"name=?",new String[] {name});
            if (result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }

    }

    public Boolean deletedata(String name){

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Reminderdetails where name = ?",new String[]{name});
        if (cursor.getCount()>0){

            long result = DB.delete("Reminderdetails","name=?",new String[] {name});
            if (result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }

    }

    public Cursor getdata (){

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Reminderdetails",null);
        return cursor;

    }

}
