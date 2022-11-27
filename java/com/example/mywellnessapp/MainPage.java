package com.example.mywellnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.ScriptGroup;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class MainPage extends AppCompatActivity {

//    ActivityMainPageBinding binding;
    BottomNavigationView bottomNavigationView;
    DBHelper DB;
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String> dataSource;

    MyRvAdapter myRvAdapter;
    TextView typeOfDiet,nameOfPerson;
    ImageView dietImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main_page);
        DB = new DBHelper(this);
//        startActivity(new Intent(MainPage.this,homeActivity.class));
        getSupportActionBar().hide();
        rv = findViewById(R.id.horizontalRv);
        typeOfDiet = findViewById(R.id.typeOfDiet);
        dietImage = findViewById(R.id.dietImage);
        nameOfPerson = findViewById(R.id.nameOfPerson);
        Cursor res = DB.getdata();
        if (res.getCount() == 0){
            Toast.makeText(MainPage.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }

        dataSource = new ArrayList<>();
//        dataSource.add("Hello");
//        dataSource.add("Hello");
//        dataSource.add("Hello");
//        dataSource.add("Hello");
//        dataSource.add("Hello");
//        dataSource.add("Hello");

        if (res.moveToNext()){//name age myweight calgoal idealweight
            nameOfPerson.setText(res.getString(0));
            typeOfDiet.setText(res.getString(6).substring(0,res.getString(6).length()-2));
            if (res.getString(6).substring(0,res.getString(6).length()-2).equals("Mediterranean")){
                dietImage.setImageResource(R.drawable.mediteriandiet);

            }else if (res.getString(6).substring(0,res.getString(6).length()-2).equals("Atkins")){
                dietImage.setImageResource(R.drawable.atkinsdiet);
            }else if (res.getString(6).substring(0,res.getString(6).length()-2).equals("Ketogenic")){
                dietImage.setImageResource(R.drawable.ketogenicdiet);
            }else if (res.getString(6).substring(0,res.getString(6).length()-2).equals("Vegetarian")){
                dietImage.setImageResource(R.drawable.vegetarian);

            }else if (res.getString(6).substring(0,res.getString(6).length()-2).equals("Vegan")){
                dietImage.setImageResource(R.drawable.vegan);

            }else if (res.getString(6).substring(0,res.getString(6).length()-2).equals("Raw Food")){
                dietImage.setImageResource(R.drawable.rawfooddiet);
            }
        }
        dataSource.add("Green salad with avocado");
        dataSource.add("Green Bean & Plum Salad");
        dataSource.add("Thai-Sytle Salad");
        dataSource.add("Salade Cuite");
        dataSource.add("Roasted Carrot & Avacado Salad");
        dataSource.add("Chickpea Salad");

        linearLayoutManager = new LinearLayoutManager(MainPage.this,LinearLayoutManager.HORIZONTAL,false);
        myRvAdapter = new MyRvAdapter(dataSource,MainPage.this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(),MainPage.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Frigde:
                        startActivity(new Intent(getApplicationContext(),profileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.List:
                        startActivity(new Intent(getApplicationContext(), ShoppingList.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Settings:
                        startActivity(new Intent(getApplicationContext(),settingActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                }

                return false;
            }
        });


    }

    public void viewdataclicked(View v){
        Cursor res = DB.getdata();
        if (res.getCount() == 0){
            Toast.makeText(MainPage.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){//name age myweight calgoal idealweight
            buffer.append("Name :" + res.getString(0) + "\n");
            buffer.append("age :" + res.getString(1) + "\n");
            buffer.append("myweight :" + res.getString(2) + "\n");
            buffer.append("calgoal :" + res.getString(3) + "\n");
            buffer.append("idealweight :" + res.getString(4) + "\n");
            buffer.append("allergiesStr :" + res.getString(5) + "\n");
            buffer.append("dietsChosen :" + res.getString(6) + "\n\n");


        }
        AlertDialog.Builder builder = new AlertDialog.Builder(MainPage.this);
        builder.setTitle("User Entries");
        builder.setMessage(buffer.toString());
        builder.show();
    }

    public void clickedOnLearnMore(View v){
        startActivity(new Intent(getApplicationContext(),dietCardSettings.class));
        overridePendingTransition(0, 0);
    }



}