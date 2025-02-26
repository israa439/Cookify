package com.example.cookify.DataSrc.Endpoints;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cookify.DataSrc.Data_structure.Meal;
import com.example.cookify.DataSrc.Data_structure.userMeal;
import com.example.cookify.DataSrc.connection.Database;

import java.util.ArrayList;
import java.util.List;

public class fav_recipes {

    private SQLiteDatabase db;
    private Database connection;

    public fav_recipes(Context context) {
        this.connection = new Database(context);
        this.db = connection.getWritableDatabase();
    }

    public boolean isMealFavorite(int mealId, int userId) {
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM Favorite WHERE meal_id = ? AND user_id = ?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(mealId), String.valueOf(userId)});

            boolean exists = cursor != null && cursor.getCount() > 0; // Check for null cursor

            return exists;

        } catch (SQLException e) {
            Log.e("Database Error", "Error checking favorite meal: " + e.getMessage());
            // Handle the error appropriately, e.g., return false or throw an exception
            return false; // Or throw a custom exception
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    // Add a meal to favorites
    public boolean addMealToFavorites(int mealId, int userId) {
        if (isMealFavorite(mealId, userId)) {
            return false;
        }

        ContentValues values = new ContentValues();
        values.put("meal_id", mealId);
        values.put("user_id", userId);

        long result = db.insert("Favorite", null, values);

        return result != -1; // Returns true if insertion is successful
    }

    // Remove a meal from favorites
    public boolean removeMealFromFavorites(int mealId, int userId) {
        int result = db.delete("Favorite", "meal_id = ? AND user_id = ?", new String[]{String.valueOf(mealId), String.valueOf(userId)});

        return result > 0; // Returns true if deletion is successful
    }

    public List<userMeal> getAllFavoriteMeals(int userId) {
        List<userMeal> favRecipes = new ArrayList<>();
        Cursor cursor = null;

        try {
            String query = "SELECT m.meal_id, m.category_id, m.meal_ingredients, m.meal_prepway, " +
                    "m.meal_calories, m.meal_duration, m.meal_image, m.mealName " +
                    "FROM Favorite f " +
                    "JOIN Meals_description m ON f.meal_id = m.meal_id " +
                    "WHERE f.user_id = ?";

            cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

            if (cursor != null && cursor.moveToFirst()) { // Check for null cursor
                do {
                    userMeal meal = new userMeal(
                            cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getInt(4),
                            cursor.getInt(5),
                            cursor.getString(6),
                            cursor.getString(7)
                    );
                    favRecipes.add(meal);
                } while (cursor.moveToNext());
            }

        } catch (SQLException e) {
            Log.e("Database Error", "Error getting favorite meals: " + e.getMessage());
            // Handle the error appropriately, e.g., return an empty list or throw an exception
            return new ArrayList<>(); // Return empty list on error
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return favRecipes;
    }}