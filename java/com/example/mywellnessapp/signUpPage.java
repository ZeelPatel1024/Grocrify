package com.example.mywellnessapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Locale;

public class signUpPage extends AppCompatActivity {

    //Allergies
    MaterialCardView selectCard;
    TextView tvAllergies;
    boolean [] selectedAllergies;
    ArrayList<Integer> allergiesList = new ArrayList<>();
    String [] alleriesArray = {"milk","eggs","peanuts","tree nuts","fish","shellfish","Wheat","Soy","Sesame"};
    ArrayList<String> finalAllergies = new ArrayList<>();
    String allergiesChosen = "";

    //Diets
    MaterialCardView selectCard2;
    TextView tvDiet;
    boolean [] selectedDiet;
    ArrayList<Integer> dietList = new ArrayList<>();
    String [] dietArray = {"Atkins","Ketogenic","Vegetarian","Vegan","Raw Food","Mediterranean"};
    ArrayList<String> finalDiets = new ArrayList<>();
    String dietsChosen = "";

    Boolean [] correctInp = {false,false,false,false,false};
    //Inputs
    EditText nameInp,ageInp,weightInp,calGoalInp,idealWeightInp;
    Button clickedOnSignIn;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        getSupportActionBar().hide();

        nameInp = findViewById(R.id.nameInp);
        ageInp = findViewById(R.id.ageInp);
        weightInp = findViewById(R.id.weightInp);
        calGoalInp = findViewById(R.id.calGoalInp);
        idealWeightInp = findViewById(R.id.idealWeightInp);
        clickedOnSignIn = findViewById(R.id.clickedOnSignIn);

        DB = new DBHelper(this);

        clickedOnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInp.getText().toString().trim();
                String age = ageInp.getText().toString().trim();
                String myWeight = weightInp.getText().toString().trim();
                String calGoal = calGoalInp.getText().toString().trim();
                String idealWeight = idealWeightInp.getText().toString().trim();


                if (name.isEmpty()){
                    nameInp.setError("Full name is required");
                    nameInp.requestFocus();

                    return;
                }else {
                    correctInp[0] = true;
                }
                if (age.isEmpty()){
                    ageInp.setError("Age is required");
                    ageInp.requestFocus();
                    return;
                }else {
                    correctInp[1] = true;
                }
                if (myWeight.isEmpty()){
                    weightInp.setError("Weight is required");
                    weightInp.requestFocus();
                    return;
                }else {
                    correctInp[2] = true;
                }
                if (calGoal.isEmpty()){
                    calGoalInp.setError("Calorie goal is required");
                    calGoalInp.requestFocus();
                    return;
                }else {
                    correctInp[3] = true;
                }
                if (idealWeight.isEmpty()){
                    idealWeightInp.setError("Weight goal is required");
                    idealWeightInp.requestFocus();
                    return;
                }else {
                    correctInp[4] = true;
                }


                if (age.length() > 2){
                    ageInp.setError("Invalid Age");
                    ageInp.requestFocus();
                    correctInp[1] = false;
                    return;
                }else {
                    correctInp[1] = true;
                }
                if (myWeight.length() > 3 || myWeight.length()<=2){
                    weightInp.setError("Invalid Weight");
                    weightInp.requestFocus();
                    correctInp[2] = false;
                    return;
                }else {
                    correctInp[2] = true;
                }
                if (idealWeight.length() > 3 || idealWeight.length()<=2){
                    idealWeightInp.setError("Invalid Weight Goal");
                    idealWeightInp.requestFocus();
                    correctInp[4] = false;
                    return;
                }else {
                    correctInp[4] = true;
                }

                int num = 0;
                for (int i = 0; i < correctInp.length; i++) {
                    if (correctInp[i]==true){
                        num++;
                    }
                }
                if (num == 5){//name age myweight calgoal idealweight
                    String nameTXT = nameInp.getText().toString();
                    String ageTXT = ageInp.getText().toString();
                    String myWeightTXT = weightInp.getText().toString();
                    String calGoalTXT = calGoalInp.getText().toString();
                    String idealWeightTXT = idealWeightInp.getText().toString();
                    String allergiesStrTXT = allergiesChosen;
                    String dietsChosenTXT = dietsChosen;

                    Boolean checkinsertdata = DB.insertuserdata(nameTXT,ageTXT,myWeightTXT,calGoalTXT,idealWeightTXT,allergiesStrTXT,dietsChosenTXT);
                    if (checkinsertdata==true){
                        Toast.makeText(signUpPage.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(signUpPage.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(signUpPage.this,MainPage.class));

                }

            }
        });

        selectCard = findViewById(R.id.selectCard);
        tvAllergies = findViewById(R.id.tvAllergies);
        selectedAllergies = new boolean[alleriesArray.length];

        selectCard.setOnClickListener(v ->{
            showCoursesDialog();
        });

        /////////////

        selectCard2 = findViewById(R.id.selectCard2);
        tvDiet = findViewById(R.id.tvDiet);
        selectedDiet = new boolean[dietArray.length];

        selectCard2.setOnClickListener(v ->{
            showCoursesDialog2();
        });


        //diet types. calories consumed per day. dietary restrictions.
    }


    public void showCoursesDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(signUpPage.this);

        builder.setTitle("Select Allergies");
        builder.setCancelable(false);

        builder.setMultiChoiceItems(alleriesArray, selectedAllergies, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked){
                    allergiesList.add(which);
                }
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < allergiesList.size(); i++) {
                    stringBuilder.append(alleriesArray[allergiesList.get(i)]);
                    System.out.println(alleriesArray[allergiesList.get(i)]);
                    finalAllergies.add(alleriesArray[allergiesList.get(i)]);
                    allergiesChosen += alleriesArray[allergiesList.get(i)] + ", ";

                    if (i != allergiesList.size() -1){
                        stringBuilder.append(", ");
                    }

                    tvAllergies.setText(stringBuilder.toString());
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i=0; i <selectedAllergies.length; i++){
                    selectedAllergies[i] = false;

                    allergiesList.clear();
                    finalAllergies.clear();
                    allergiesChosen = "";
                    tvAllergies.setText("Select Allergies");
                }
            }
        });
        builder.show();
    }
    public void showCoursesDialog2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(signUpPage.this);

        builder.setTitle("Select Diets");
        builder.setCancelable(false);

        builder.setMultiChoiceItems(dietArray, selectedDiet, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked){
                    dietList.add(which);
                }
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < dietList.size(); i++) {
                    stringBuilder.append(dietArray[dietList.get(i)]);
                    System.out.println(dietArray[dietList.get(i)]);
                    finalDiets.add(dietArray[dietList.get(i)]);
                    dietsChosen += dietArray[dietList.get(i)] + ", ";
                    if (i != dietList.size() -1){
                        stringBuilder.append(", ");
                    }

                    tvDiet.setText(stringBuilder.toString());
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i=0; i <selectedDiet.length; i++){
                    selectedDiet[i] = false;

                    dietList.clear();
                    finalDiets.clear();
                    dietsChosen = "";
                    tvDiet.setText("Select Diet");
                }
            }
        });
        builder.show();
    }
}