package com.example.mywellnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.mywellnessapp.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Calendar;

public class profileActivity extends AppCompatActivity {

    //    ActivityMainPageBinding binding;
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private ArrayList<String> arrayList;
    private EditText editText;
    EditText name,amountBought;
    TextView idDateExpire,idDateBought;
    Button dateBought,expireDate;
    AlertDialog dialog;
    Button collInfo;
    String itemName = "";
    String itemdateBought = "";
    String itemamountBought = "";
    String itemexpireDate = "";
    DBItemHelper DB;

    ItemTouchHelper itemTouchHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();


        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

//        startActivity(new Intent(MainPage.this,homeActivity.class));
        DB = new DBItemHelper(this);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Frigde);
        recyclerView = findViewById(R.id.recycler_id);


        arrayList = new ArrayList<String>();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Item Information");
        View view = getLayoutInflater().inflate(R.layout.custom_dialog,null);

        name = view.findViewById(R.id.nameOfReminder);
        dateBought = view.findViewById(R.id.dateBought);
        amountBought = view.findViewById(R.id.amountBought);
        expireDate = view.findViewById(R.id.expireDate);
        collInfo = view.findViewById(R.id.setReminder);
        idDateBought = view.findViewById(R.id.idDateBought);
        idDateExpire = view.findViewById(R.id.idDateExpire);
        Cursor res = DB.getdata();
        while (res.moveToNext()){
//                myName.setText(res.getString(0));
            arrayList.add(res.getString(0));
            MyAdapter myAdapter = new MyAdapter(arrayList,profileActivity.this);
            recyclerView.setAdapter(myAdapter);
        }

        collInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemName = name.getText().toString();
                itemdateBought = idDateBought.getText().toString();
                itemamountBought = amountBought.getText().toString();
                itemexpireDate = idDateExpire.getText().toString();
                Boolean checkinsertdata = DB.insertuserdata(itemName,itemdateBought,itemamountBought,itemexpireDate);
                if (checkinsertdata==true){
                    Toast.makeText(profileActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(profileActivity.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();
                }
                arrayList.add(itemName.toString());
                MyAdapter myAdapter = new MyAdapter(arrayList,profileActivity.this);
                recyclerView.setAdapter(myAdapter);
                dialog.dismiss();
            }
        });

        dateBought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(profileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        idDateBought.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });

        expireDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(profileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        idDateExpire.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });


        builder.setView(view);
        dialog = builder.create();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        MyAdapter myAdapter = new MyAdapter(arrayList,this);
        recyclerView.setAdapter(myAdapter);
        itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
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
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Boolean checkdeletedata = DB.deletedata(arrayList.get(viewHolder.getAdapterPosition()));
            if (checkdeletedata==true){
                Toast.makeText(profileActivity.this,"Entry Deleted",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(profileActivity.this,"Entry Not Deleted",Toast.LENGTH_SHORT).show();
            }

            arrayList.remove(viewHolder.getAdapterPosition());

            MyAdapter myAdapter = new MyAdapter(arrayList,profileActivity.this);
            recyclerView.setAdapter(myAdapter);
        }
    };

    public void addItem(View v){
        dialog.show();
    }
}