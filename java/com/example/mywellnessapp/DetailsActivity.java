package com.example.mywellnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DetailsActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView textView,dateBought,itemAmount,itemExpireDate;
    Bundle bundle;
    String message;
    DBItemHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Frigde);
        DB = new DBItemHelper(this);
        dateBought = findViewById(R.id.dateBought);
        itemAmount = findViewById(R.id.itemAmount);
        itemExpireDate = findViewById(R.id.itemExpireDate);

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
        textView = findViewById(R.id.textView);
        bundle = getIntent().getExtras();
        message = bundle.getString("key");

        textView.setText(message);
        Cursor res = DB.getdata();
        while (res.moveToNext()){

            if (res.getString(0).equals(message)){
                dateBought.setText(res.getString(1));
                itemAmount.setText(res.getString(2));
                itemExpireDate.setText(res.getString(3));
            }

        }

    }



}