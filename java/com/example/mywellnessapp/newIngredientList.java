package com.example.mywellnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class newIngredientList extends AppCompatActivity {
    Bundle bundle;
    String message;
    TextView itemName;
    RecyclerView rv;
    MyIngerdientAdapter MyIngerdientAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ingredient_list);
        itemName = findViewById(R.id.itemName);
        bundle = getIntent().getExtras();
        message = bundle.getString("key");

        rv = findViewById(R.id.rv);
        itemName.setText(message);

        ingredients = new ArrayList<>();
        addIngredients(message,ingredients);


        linearLayoutManager = new LinearLayoutManager(newIngredientList.this,LinearLayoutManager.VERTICAL,false);
        MyIngerdientAdapter = new MyIngerdientAdapter(ingredients,newIngredientList.this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(MyIngerdientAdapter);

    }

    public void addIngredients(String message, ArrayList ingredients){
        if (message.equals("Green salad with avocado")){
            ingredients.add("1 tbsp lemon juice");
            ingredients.add("Pinch of salt");
            ingredients.add("4 tbsp olive oil");
            ingredients.add("Small bunch finely chopped chives");
            ingredients.add("200g bag mixed salad leaves");
            ingredients.add("2 sliced, ripe avocados");

        }else if (message.equals("Green Bean & Plum Salad")){
            ingredients.add("1/2 pound green beans");
            ingredients.add("10 small plums");
            ingredients.add("1/4 cup rice wine vinegar");
            ingredients.add("2 tablespoons water");
            ingredients.add("1 teaspoon sugar");
            ingredients.add("1/2 teaspoon salt");
            ingredients.add("1 pinch red pepper flakes");
            ingredients.add("1 tablespoon fresh chopped chives");

        }else if (message.equals("Thai-Sytle Salad")){
            ingredients.add("1 (10 ounce) package kale");
            ingredients.add("Brussels sprout");
            ingredients.add("Broccoli");
            ingredients.add("1 package frozen shelled edamame");
            ingredients.add("2 packages Sriracha-flavored baked tofu");
            ingredients.add("1/2 cup spicy peanut vinaigrette");

        }else if (message.equals("Salade Cuite")){
            ingredients.add("3 cubanelle peppers");
            ingredients.add("1 red bell pepper");
            ingredients.add("1 colorful bell pepper");
            ingredients.add("1/4 cup olive oil");
            ingredients.add("3 cloves garlic");
            ingredients.add("1 14.5-ounce can petite diced tomatoes");
            ingredients.add("1 teaspoon harissa");
            ingredients.add("1 baguette");
            ingredients.add("Olive oil");
            ingredients.add("Salt");

        }else if (message.equals("Roasted Carrot & Avacado Salad")){
            ingredients.add("1 pound carrots");
            ingredients.add("3 tablespoons olive oil");
            ingredients.add("1/4 teaspoon ground cumin");
            ingredients.add("Coarse salt and freshly ground black pepper");
            ingredients.add("1/2 an avocado");
            ingredients.add("Juice of half a lemon");

        }else if (message.equals("Chickpea Salad")){
            ingredients.add("½ cup green chickpeas");
            ingredients.add("½ cup brown chickpeas");
            ingredients.add("1 bunch fresh scallions");
            ingredients.add("1 large tomato");
            ingredients.add("1 teaspoon red chili flakes");
            ingredients.add("2 limes");
        }
    }

}