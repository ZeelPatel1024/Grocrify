package com.example.mywellnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context,"Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key, age TEXT, weight TEXT, calGoal TEXT, idealWeight TEXT, allergiesStr TEXT,dietsChosen TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String name, String age, String weight, String calGoal, String idealWeight, String allergiesStr,String dietsChosen){
       SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("age",age);
        contentValues.put("weight",weight);
        contentValues.put("calGoal",calGoal);
        contentValues.put("idealWeight",idealWeight);
        contentValues.put("allergiesStr",allergiesStr);
        contentValues.put("dietsChosen",dietsChosen);

        long result = DB.insert("Userdetails", null,contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }

    }

    public Boolean updateuserdata(String name, String age, String weight, String calGoal, String idealWeight, String allergiesStr,String dietsChosen){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("age",age);
        contentValues.put("weight",weight);
        contentValues.put("calGoal",calGoal);
        contentValues.put("idealWeight",idealWeight);
        contentValues.put("allergiesStr",allergiesStr);
        contentValues.put("dietsChosen",dietsChosen);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?",new String[]{name});
        if (cursor.getCount()>0){

            long result = DB.update("Userdetails",contentValues,"name=?",new String[] {name});
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

        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?",new String[]{name});
        if (cursor.getCount()>0){

            long result = DB.delete("Userdetails","name=?",new String[] {name});
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

        Cursor cursor = DB.rawQuery("Select * from Userdetails",null);
        return cursor;

    }

}
