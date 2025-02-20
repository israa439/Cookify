package com.example.cookify.DataSrc.Endpoints;
import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.cookify.DataSrc.connection.Database;


public class sign_up {


    private  SQLiteDatabase db;
    private Database  connection;
    private Context context;
    public sign_up(Context context){
        this.connection = new Database(context);
        this.db = connection.getWritableDatabase();
        this.context = context;
    }

    // Method to handle user registration
    public String registerUser(String username, String password, String email, String fullName) {


        if (!isPasswordValid(password)) {
            return "Password must be at least 8 characters long.";
        }

        if (isEmailExist(email)) {
            return "An account with this email already exists.";
        }

        int userId = generateUniqueUserId();

        // Insert the new user data into the database
        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        values.put("username", username);
        values.put("password", password);
        values.put("email", email);
        values.put("FullName", fullName);

        long result = db.insert("user", null, values);
        if (result == -1) {
            return "Error creating the account. Please try again.";
        }
        SharedPreferences prefs = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("userId", userId);
        editor.apply();

        return "Account created successfully.";

    }

    private static boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }

    private  boolean isEmailExist( String email) {
        String query = "SELECT * FROM user WHERE email = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});

        boolean exists = cursor.moveToFirst(); // If the cursor has any data, email exists
        cursor.close(); // Always close the cursor

        return exists;
    }

    private static int generateUniqueUserId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }


}
