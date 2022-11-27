package com.example.mywellnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBItemHelper extends SQLiteOpenHelper {
    public DBItemHelper(Context context) {
        super(context,"Itemdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Itemdetails(name TEXT primary key, dateBought TEXT, amountBought TEXT, expireDate TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Itemdetails");
    }

    public Boolean insertuserdata(String name, String dateBought, String amountBought, String expireDate){
       SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("dateBought",dateBought);
        contentValues.put("amountBought",amountBought);
        contentValues.put("expireDate",expireDate);


        long result = DB.insert("Itemdetails", null,contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }

    }

    public Boolean updateuserdata(String name, String dateBought, String amountBought, String expireDate){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("dateBought",dateBought);
        contentValues.put("amountBought",amountBought);
        contentValues.put("expireDate",expireDate);
        Cursor cursor = DB.rawQuery("Select * from Itemdetails where name = ?",new String[]{name});
        if (cursor.getCount()>0){

            long result = DB.update("Itemdetails",contentValues,"name=?",new String[] {name});
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

        Cursor cursor = DB.rawQuery("Select * from Itemdetails where name = ?",new String[]{name});
        if (cursor.getCount()>0){

            long result = DB.delete("Itemdetails","name=?",new String[] {name});
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

        Cursor cursor = DB.rawQuery("Select * from Itemdetails",null);
        return cursor;

    }

}
