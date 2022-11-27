package com.example.mywellnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class dietCardSettings extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView typeOfDiet,dietDescription;
    ImageView dietImage;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_card_settings);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Settings);

        dietImage = findViewById(R.id.dietImage);
        dietDescription = findViewById(R.id.dietDescription);
        typeOfDiet = findViewById(R.id.typeOfDiet);

        DB = new DBHelper(this);
        Cursor res = DB.getdata();
        if (res.moveToNext()){
            typeOfDiet.setText(res.getString(6).substring(0,res.getString(6).length()-2));

            if (res.getString(6).substring(0,res.getString(6).length()-2).equals("Mediterranean")){
                dietImage.setImageResource(R.drawable.mediteriandiet);
                dietDescription.setText("A diet of a type traditional in Mediterranean countries, characterized especially by a high consumption of vegetables and olive oil and moderate consumption of protein, and thought to confer health benefits.");

            }else if (res.getString(6).substring(0,res.getString(6).length()-2).equals("Atkins")){
                dietImage.setImageResource(R.drawable.atkinsdiet);
                dietDescription.setText("The Atkins Diet is a popular low-carbohydrate eating plan developed in the 1960s by heart specialist (cardiologist) Robert C. Atkins. The Atkins Diet restricts carbs (carbohydrates) while focusing on protein and fats.");

            }else if (res.getString(6).substring(0,res.getString(6).length()-2).equals("Ketogenic")){
                dietImage.setImageResource(R.drawable.ketogenicdiet);
                dietDescription.setText("Ketogenic is a term for a low-carb diet (like the Atkins diet). The idea is for you to get more calories from protein and fat and less from carbohydrates. You cut back most on the carbs that are easy to digest, like sugar, soda, pastries, and white bread.");

            }else if (res.getString(6).substring(0,res.getString(6).length()-2).equals("Vegetarian")){
                dietImage.setImageResource(R.drawable.vegetarian);
                dietDescription.setText("A vegetarian diet focuses on plants for food. These include fruits, vegetables, dried beans and peas, grains, seeds and nuts. People who follow vegetarian diets can get all the nutrients they need. However, they must be careful to eat a wide variety of foods to meet their nutritional needs. Nutrients vegetarians may need to focus on include protein, iron, calcium, zinc and vitamin B12.");

            }else if (res.getString(6).substring(0,res.getString(6).length()-2).equals("Vegan")){
                dietImage.setImageResource(R.drawable.vegan);
                dietDescription.setText("If you invite a dinner guest who's a vegan, you'll want to check your menu carefully to make sure it follows two basic rules. Foods from plants are OK, but foods from animals are off limits, including common ingredients like eggs, cheese, milk, and honey.");

            }else if (res.getString(6).substring(0,res.getString(6).length()-2).equals("Raw Food")){
                dietImage.setImageResource(R.drawable.rawfooddiet);
                dietDescription.setText("A raw food diet involves eating mainly unprocessed whole, plant-based, and preferably organic foods. Some sources say that when following this diet, raw food should make up three-quarters of the diet.");

            }
        }


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
}