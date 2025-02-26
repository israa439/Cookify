package com.example.cookify.ui.create_recipe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookify.DataSrc.Adapters.MealAdapter;
import com.example.cookify.DataSrc.Data_structure.userMeal;
import com.example.cookify.DataSrc.connection.Database;
import com.example.cookify.R;
import com.example.cookify.create_recipe;
import com.example.cookify.detailedRecipe;
import com.example.cookify.databinding.FragmentYourRecipeBinding;

import java.util.ArrayList;
import java.util.List;

public class urRecipeFragment extends Fragment {

    private FragmentYourRecipeBinding binding;
    private RecyclerView recyclerView;
    private Database dbHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        urRecipeViewModel urRecipeViewModel =
                new ViewModelProvider(this).get(urRecipeViewModel.class);
        binding = FragmentYourRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbHelper = new Database(requireContext());
        recyclerView = root.findViewById(R.id.ur_recipe_container);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Fetch user meals from the database
        List<userMeal> usermeals = getUserMealsFromDatabase();

        // Set up the MealAdapter with the fetched data
        MealAdapter mealAdapter = new MealAdapter(getContext(), usermeals, (position, meal) -> {
            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("RecipePrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("recipeName", meal.getMealName());
            editor.putString("recipeImage", meal.getMealImage());
            editor.putInt("recipeId", meal.getMealId());
            editor.putInt("recipeDuration", meal.getMealDuration());
            editor.putInt("recipeCals", meal.getMealCalories());
            editor.putString("recipePrepWay", meal.getMealPrepWay());
            editor.putString("recipeIngredients", meal.getMealIngredients());

            editor.apply();

            Intent intent = new Intent(getContext(), detailedRecipe.class);
            startActivity(intent);
        });

        recyclerView.setAdapter(mealAdapter);

        // Handle the "Create Recipe" button click
        CardView createContainer = root.findViewById(R.id.create_container);
        createContainer.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), create_recipe.class);
            startActivity(intent);
        });

        return root;
    }

    // Fetch user meals from the SQLite database
    private List<userMeal> getUserMealsFromDatabase() {
        List<userMeal> userMealsList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Fetch the user ID from SharedPreferences
        SharedPreferences prefs = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        int userId = prefs.getInt("userId", -1);  // Default to -1 if userId is not found

        if (userId == -1) {
            // Handle the case where userId is not found
            return userMealsList;
        }

        // Query the user_meals table and join with Meals_description to get meal details
        String query = "SELECT um.user_id, um.meal_id, um.meal_ingredients, um.meal_prepway, " +
                "um.meal_calories, um.meal_duration, um.meal_image, um.mealName " +
                "FROM user_meals um " +
                "WHERE um.user_id = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        if (cursor != null && cursor.moveToFirst()) {
            // Print column names for debugging purposes
            String[] columnNames = cursor.getColumnNames();
            for (String column : columnNames) {
                Log.d("DB Debug", "Column: " + column);
            }

            do {
                // Check if the column exists before accessing it
                int mealIdIndex = cursor.getColumnIndex("meal_id");
                int mealNameIndex = cursor.getColumnIndex("mealName");
                int mealIngredientsIndex = cursor.getColumnIndex("meal_ingredients");
                int mealPrepWayIndex = cursor.getColumnIndex("meal_prepway");
                int mealCaloriesIndex = cursor.getColumnIndex("meal_calories");
                int mealDurationIndex = cursor.getColumnIndex("meal_duration");
                int mealImageIndex = cursor.getColumnIndex("meal_image");

                if (mealIdIndex != -1 && mealNameIndex != -1 && mealIngredientsIndex != -1 &&
                        mealPrepWayIndex != -1 && mealCaloriesIndex != -1 && mealDurationIndex != -1 &&
                        mealImageIndex != -1) {

                    // Fetch the data as expected
                    int mealId = cursor.getInt(mealIdIndex);  // Use getInt for integer column
                    String mealName = cursor.getString(mealNameIndex);
                    String mealIngredients = cursor.getString(mealIngredientsIndex);
                    String mealPrepWay = cursor.getString(mealPrepWayIndex);
                    int mealCalories = cursor.getInt(mealCaloriesIndex);
                    int mealDuration = cursor.getInt(mealDurationIndex);
                    String mealImage = cursor.getString(mealImageIndex);

                    userMeal meal = new userMeal(mealId,userId, mealIngredients, mealPrepWay,
                            mealCalories, mealDuration, mealImage, mealName);

                    userMealsList.add(meal);
                }
            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return userMealsList;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
