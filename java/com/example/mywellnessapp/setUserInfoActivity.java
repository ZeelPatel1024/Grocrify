package com.example.mywellnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class setUserInfoActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView myName,myAge,myWeight,myCal,myIdealWeight;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_info);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Settings);
        myName = findViewById(R.id.myName);
        myAge = findViewById(R.id.myAge);
        myWeight = findViewById(R.id.myWeight);
        myCal = findViewById(R.id.myCal);
        myIdealWeight = findViewById(R.id.myIdealWeight);
//        reNam = findViewById(R.id.reName);
//        reAge = findViewById(R.id.reAge);
//        reMyWeight = findViewById(R.id.reMyWeight);

        DB = new DBHelper(this);

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
            Toast.makeText(setUserInfoActivity.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }

//        while (res.moveToNext()){//name age myweight calgoal idealweight
//            buffer.append("Name :" + res.getString(0) + "\n");
//            buffer.append("age :" + res.getString(1) + "\n");
//            buffer.append("myweight :" + res.getString(2) + "\n");
//            buffer.append("calgoal :" + res.getString(3) + "\n");
//            buffer.append("idealweight :" + res.getString(4) + "\n");
//            buffer.append("allergiesStr :" + res.getString(5) + "\n");
//            buffer.append("dietsChosen :" + res.getString(6) + "\n\n");
//
//
//        }
        if (res.moveToNext()){
            myName.setText(res.getString(0));
            myAge.setText(res.getString(1));
            myWeight.setText(res.getString(2));
            myCal.setText(res.getString(3));
            myIdealWeight.setText(res.getString(4));
        }


    }
    public void clickedOnDeleteData(View v){
        String nameTXT = myName.getText().toString();

        Boolean checkdeletedata = DB.deletedata(nameTXT);


        if (checkdeletedata==true){
            Toast.makeText(setUserInfoActivity.this,"Entry Deleted",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(setUserInfoActivity.this,"Entry Not Deleted",Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(setUserInfoActivity.this,signUpPage.class));

    }


}