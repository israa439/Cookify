package com.example.cookify.DataSrc.Endpoints;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import com.example.cookify.DataSrc.Data_structure.Meal;
import com.example.cookify.DataSrc.connection.Database;

import java.sql.Connection;
import java.util.ArrayList;

public class All_recipes {

    private SQLiteDatabase db;
    private Database  connection;

    public All_recipes(Context context) {
        this.connection = new Database(context);
        this.db = connection.getWritableDatabase();
    }

    // Method to retrieve all meals descriptions
    public ArrayList<Meal> getAllMealDescriptions() {
        ArrayList<Meal> meals = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Meals_description", null);


        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Extract column indices to avoid errors
                int mealIdIndex = cursor.getColumnIndex("meal_id");
                int categoryIdIndex = cursor.getColumnIndex("category_id");
                int mealIngredientsIndex = cursor.getColumnIndex("meal_ingredients");
                int mealPrepWayIndex = cursor.getColumnIndex("meal_prepway");
                int mealCaloriesIndex = cursor.getColumnIndex("meal_calories");
                int mealDurationIndex = cursor.getColumnIndex("meal_duration");
                int mealImageIndex = cursor.getColumnIndex("meal_image");
                int mealNameIndex=cursor.getColumnIndex("mealName");


                // Check if column indices are valid
                if (mealIdIndex >= 0 && categoryIdIndex >= 0) {
                    int mealId = cursor.getInt(mealIdIndex);
                    int categoryId = cursor.getInt(categoryIdIndex);
                    String mealIngredients = cursor.getString(mealIngredientsIndex);
                    String mealPrepWay = cursor.getString(mealPrepWayIndex);
                    int mealCalories = cursor.getInt(mealCaloriesIndex);
                    int mealDuration = cursor.getInt(mealDurationIndex);
                    String mealImage = cursor.getString(mealImageIndex);
                    String mealName=cursor.getString(mealNameIndex);

                    // Create a new Meal object and add to list
                    Meal meal = new Meal(mealId, categoryId, mealIngredients, mealPrepWay, mealCalories, mealDuration, mealImage, mealName);
                    meals.add(meal);
                }

            } while (cursor.moveToNext());

            cursor.close();
        }

        return meals;
    }
    // Method to retrieve a meal by its ID
    public Meal getMealById(int mealId) {
        Meal meal = null;
        Cursor cursor = db.rawQuery("SELECT * FROM Meals_description WHERE meal_id = ?", new String[]{String.valueOf(mealId)});

        if (cursor != null && cursor.moveToFirst()) {
            int mealIdIndex = cursor.getColumnIndex("meal_id");
            int categoryIdIndex = cursor.getColumnIndex("category_id");
            int mealIngredientsIndex = cursor.getColumnIndex("meal_ingredients");
            int mealPrepWayIndex = cursor.getColumnIndex("meal_prepway");
            int mealCaloriesIndex = cursor.getColumnIndex("meal_calories");
            int mealDurationIndex = cursor.getColumnIndex("meal_duration");
            int mealImageIndex = cursor.getColumnIndex("meal_image");

            if (mealIdIndex >= 0 && categoryIdIndex >= 0) {
                int categoryId = cursor.getInt(categoryIdIndex);
                String mealIngredients = cursor.getString(mealIngredientsIndex);
                String mealPrepWay = cursor.getString(mealPrepWayIndex);
                int mealCalories = cursor.getInt(mealCaloriesIndex);
                int mealDuration = cursor.getInt(mealDurationIndex);
                String mealImage = cursor.getString(mealImageIndex);

                meal = new Meal(mealId, categoryId, mealIngredients, mealPrepWay, mealCalories, mealDuration, mealImage, "");
            }

            cursor.close();
        }

        return meal;
    }
}
