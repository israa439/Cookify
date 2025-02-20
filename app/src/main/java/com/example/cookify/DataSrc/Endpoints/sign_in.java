package com.example.cookify.DataSrc.Endpoints;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.cookify.DataSrc.connection.Database;
public class sign_in {
    private  SQLiteDatabase db;
    private Database  connection;
    public sign_in(Context context){
        this.connection = new Database(context);
        this.db = connection.getWritableDatabase();

    }
    public String signInUser(Context context,String username, String password) {
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND password = ?", new String[]{username, password});

        if (cursor.moveToFirst()) {
            int fullNameIndex = cursor.getColumnIndex("FullName");
            int userIdIndex = cursor.getColumnIndex("user_id");
            if (fullNameIndex != -1) {
                int userId = cursor.getInt(userIdIndex); // Get user_id
                String fullName = cursor.getString(fullNameIndex); // Use the correct column index
                String initials = getInitials(fullName);
                SharedPreferences prefs = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("userId", userId);


                editor.apply();

                cursor.close();
                return initials;
            }
        } else {
            cursor.close();
            return "Sign-in failed. Incorrect username or password."; // Return message if no data found
        }
        cursor.close(); // Ensure cursor is closed if no result found
        return "Sign-in failed. Incorrect username or password."; // Return in case of no matching user
    }
    private String getInitials(String fullName) {
        String[] nameParts = fullName.trim().split("\\s+"); // Split by spaces
        String initials = "";

        if (nameParts.length > 0) {
            initials += nameParts[0].charAt(0); // First letter of first name
        }
        if (nameParts.length > 1) {
            initials += nameParts[nameParts.length - 1].charAt(0); // First letter of last name
        }

        return initials.toUpperCase(); // Convert to uppercase
    }


}



