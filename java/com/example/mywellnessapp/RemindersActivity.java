package com.example.mywellnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RemindersActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    AlertDialog dialog;
    Button setReminder,butChooseDate,butChooseTime;
    TextView idDateChosen,idTimeChosen;
    EditText nameOfReminder;
    int t1Hour,t1Minute;
    String reminderName = "";
    String reminderDate = "";
    String reminderTime = "";
    DBReminderHelper DB;
    private ArrayList<String> arrayList;
    private RecyclerView recyclerView;
    ItemTouchHelper itemTouchHelper;
    Calendar mycalender;
    SimpleDateFormat simpleDateFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
//        mycalender = Calendar.getInstance();
//        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm aa");
//        System.out.println(simpleDateFormat.format(calendar.getTime()));

        arrayList = new ArrayList<String>();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Settings);
        recyclerView = findViewById(R.id.recyclerView);

        DB = new DBReminderHelper(this);
        Cursor res = DB.getdata();

        while (res.moveToNext()){
//                myName.setText(res.getString(0));
            arrayList.add(res.getString(0));
            System.out.println(res.getString(0));
            MyRemindersAdapter remindersAdapter = new MyRemindersAdapter(arrayList,RemindersActivity.this);
            recyclerView.setAdapter(remindersAdapter);

//            String setDate = res.getString(1) + " " + res.getString(2);
//            System.out.println("setDate " + setDate);

//            if (setDate.equals(simpleDateFormat.format(calendar.getTime()))){
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//                    NotificationChannel channel = new NotificationChannel("My Notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
//                    NotificationManager manager = getSystemService(NotificationManager.class);
//                    manager.createNotificationChannel(channel);
//                }
//
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(RemindersActivity.this,"My Notification");
//                builder.setContentTitle("My Title");
//                builder.setContentText("Hello from app");
//                builder.setSmallIcon(R.drawable.ic_launcher_background);
//                builder.setAutoCancel(true);
//
//                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(RemindersActivity.this);
//                managerCompat.notify(1,builder.build());
//            }

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set Reminder");
        View view = getLayoutInflater().inflate(R.layout.reminderdialog,null);
        setReminder = view.findViewById(R.id.setReminder);
        butChooseDate = view.findViewById(R.id.butChooseDate);
        nameOfReminder = view.findViewById(R.id.nameOfReminder);
        idDateChosen = view.findViewById(R.id.idDateChosen);
        butChooseTime = view.findViewById(R.id.butChooseTime);
        idTimeChosen = view.findViewById(R.id.idTimeChosen);

        butChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(RemindersActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        idDateChosen.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });

        butChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(RemindersActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        t1Hour = hourOfDay;
                        t1Minute = minute;
                        Calendar calender = Calendar.getInstance();
                        calender.set(0,0,0,t1Hour,t1Minute);

                        idTimeChosen.setText(DateFormat.format("hh:mm aa",calender));

                    }
                },12,0,false);
                timePickerDialog.updateTime(t1Hour,t1Minute);
                timePickerDialog.show();
            }
        });


        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reminderName = nameOfReminder.getText().toString();
                reminderDate = idDateChosen.getText().toString();
                reminderTime = idTimeChosen.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(reminderName,reminderDate,reminderTime);
                if (checkinsertdata==true){
                    Toast.makeText(RemindersActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RemindersActivity.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();
                }

                arrayList.add(reminderName.toString());
                MyRemindersAdapter remindersAdapter = new MyRemindersAdapter(arrayList,RemindersActivity.this);
                recyclerView.setAdapter(remindersAdapter);
                dialog.dismiss();
            }
        });

        builder.setView(view);
        dialog = builder.create();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        MyRemindersAdapter remindersAdapter = new MyRemindersAdapter(arrayList,this);
        recyclerView.setAdapter(remindersAdapter);
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
                Toast.makeText(RemindersActivity.this,"Entry Deleted",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(RemindersActivity.this,"Entry Not Deleted",Toast.LENGTH_SHORT).show();
            }

            arrayList.remove(viewHolder.getAdapterPosition());

            MyRemindersAdapter myAdapter = new MyRemindersAdapter(arrayList,RemindersActivity.this);
            recyclerView.setAdapter(myAdapter);
        }
    };

    public void addReminder(View v){
        dialog.show();
    }
}