package com.example.cookify;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.cookify.DataSrc.Endpoints.fav_recipes;

public class detailedRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fav_recipes favRecipes = new fav_recipes(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed_recipe);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        SharedPreferences sharedPreferences = getSharedPreferences("RecipePrefs", Context.MODE_PRIVATE);
        // Get views
        TextView recipeNameView = findViewById(R.id.Recipe_Name);
        ImageView recipeImageView = findViewById(R.id.Recipe_image);
        TextView calsView = findViewById(R.id.cals);
        TextView durationView = findViewById(R.id.duration);
        GridLayout ingredientsView = findViewById(R.id.Recipe_Ingredients);
        TextView prepWayView = findViewById(R.id.prep_way);
        ImageView heartImage = findViewById(R.id.heart_image);

        // Retrieve data from SharedPreferences

        String recipeName = sharedPreferences.getString("recipeName", "");
        String recipeImage = sharedPreferences.getString("recipeImage", "");
        Integer recipeCals = sharedPreferences.getInt("recipeCals", 0);
        Integer recipeDuration = sharedPreferences.getInt("recipeDuration", 0);
        String recipeIngredients = sharedPreferences.getString("recipeIngredients", "");
        String recipePrepWay = sharedPreferences.getString("recipePrepWay", "");

        SharedPreferences userPrefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = userPrefs.getInt("userId", -1);
        int recipeId = sharedPreferences.getInt("recipeId", -1);

        final boolean isFav = favRecipes.isMealFavorite(recipeId, userId);
        if (isFav) {
            heartImage.setImageResource(R.drawable.filled_heart); // Use your drawable
        } else {
            heartImage.setImageResource(R.drawable.empty_heart);
        }

        heartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = userPrefs.getInt("userId", -1);
                int recipeId = sharedPreferences.getInt("recipeId", -1);

                boolean isFav = favRecipes.isMealFavorite(recipeId, userId);

                if(isFav){
                    //update data base
                    heartImage.setImageResource(R.drawable.empty_heart);
                    favRecipes.removeMealFromFavorites(recipeId, userId);
                    Toast.makeText(detailedRecipe.this, "Recipe removed from the favorites", Toast.LENGTH_SHORT).show();
                }else{
                    heartImage.setImageResource(R.drawable.filled_heart);
                    favRecipes.addMealToFavorites(recipeId, userId);
                    Toast.makeText(detailedRecipe.this, "Recipe added to the favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });
        recipeNameView.setText(recipeName);

        // Load recipe image using Glide
        if (!recipeImage.isEmpty()) {
            Glide.with(this)
                    .load(recipeImage)
                    .into(recipeImageView);
        }


        // Set calories
        calsView.setText(String.valueOf(recipeCals)); // Example: "Calories: 200"

        // Set duration
        int hours = recipeDuration / 60;
        int minutes = recipeDuration % 60;
        durationView.setText(String.format("%02d:%02d", hours, minutes));

        // Set ingredients with bullet points
        if (recipeIngredients != null && !recipeIngredients.isEmpty()) {
            String[] ingredients = recipeIngredients.split("\\*");
            ingredientsView.removeAllViews();

            int totalWidthDp = (int) (getResources().getDisplayMetrics().widthPixels * 0.35);
            int columnWidthDp = (totalWidthDp-16) / 2;

            // Convert dp to pixels (for setting max width)
            float scale = getResources().getDisplayMetrics().density;
            int columnWidthPx = (int) (columnWidthDp * scale + 0.5f);

            for (String ingredient : ingredients) {
                TextView ingredientView = new TextView(this);
                ingredientView.setText("\u2022 " + ingredient.trim());
                ingredientView.setTextSize(14);
                ingredientView.setSingleLine(false);
                ingredientView.setEllipsize(null);
                ingredientView.setMaxWidth(columnWidthPx);
                ingredientView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

                // Set layout parameters
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.setMargins(8, 8, 15, 8);
                params.width = columnWidthPx;
                params.height = GridLayout.LayoutParams.WRAP_CONTENT;
                params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1);
                params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED);

                ingredientView.setLayoutParams(params);
                ingredientsView.addView(ingredientView);
            }
        }

        // Set preparation way
        prepWayView.setText(recipePrepWay);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}