package com.example.mywellnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        DB = new DBHelper(this);
    }

    public void clickedOnSignUp(View view){
        startActivity(new Intent(MainActivity.this,signUpPage.class));
    }

    public void clickedOnLogIn(View view){
        Cursor res = DB.getdata();
        if (res.getCount() == 0){
            Toast.makeText(MainActivity.this,"Please Sign In",Toast.LENGTH_SHORT).show();
        }else{
            startActivity(new Intent(MainActivity.this,MainPage.class));
        }

    }

}