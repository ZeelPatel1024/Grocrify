package com.example.mywellnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class settingActivity extends AppCompatActivity {

    //    ActivityMainPageBinding binding;
    BottomNavigationView bottomNavigationView;
    DBHelper DB;
    TextView fullNameTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_setting);

//        startActivity(new Intent(MainPage.this,homeActivity.class));
        getSupportActionBar().hide();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Settings);
        DB = new DBHelper(this);
        fullNameTXT = findViewById(R.id.fullNameTXT);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(), MainPage.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Frigde:
                        startActivity(new Intent(getApplicationContext(), profileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.List:
                        startActivity(new Intent(getApplicationContext(), ShoppingList.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Settings:
                        startActivity(new Intent(getApplicationContext(), settingActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                }

                return false;
            }
        });

        Cursor res = DB.getdata();
        if (res.getCount() == 0){
            Toast.makeText(settingActivity.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }

        if (res.moveToNext()){
            fullNameTXT.setText(res.getString(0) + "!");
        }

    }

    public void clickedOnUserInfo(View v){
        startActivity(new Intent(settingActivity.this,setUserInfoActivity.class));
        overridePendingTransition(0, 0);
    }
    public void clickedOnReminders(View v){
        startActivity(new Intent(settingActivity.this,RemindersActivity.class));
        overridePendingTransition(0, 0);
    }
    public void clickedOnDietCard(View v){
        startActivity(new Intent(settingActivity.this,dietCardSettings.class));
        overridePendingTransition(0, 0);
    }
    public void clickedOnCalender(View v){
        startActivity(new Intent(settingActivity.this,calenderSettings.class));
        overridePendingTransition(0, 0);
    }
}