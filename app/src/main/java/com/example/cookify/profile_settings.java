package com.example.cookify;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cookify.DataSrc.Data_structure.user_info;
import com.example.cookify.DataSrc.connection.Database;

public class profile_settings extends AppCompatActivity {
    private SQLiteDatabase db;
    private Database connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_settings);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        this.connection = new Database(this);
        this.db = connection.getWritableDatabase();
        int userId = getUserIdFromPreferences(this);
        user_info user = getUserData(userId); // Fetch user data

        // Display user info if found
        if (user != null) {
            displayUserInfo(user);
        }
        Button updatePasswordButton = findViewById(R.id.btn_update_password);
        updatePasswordButton.setOnClickListener(view ->{
            LayoutInflater inflater = LayoutInflater.from(this);
            View dialogView = inflater.inflate(R.layout.activity_update_password, null);

            // Create AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(dialogView);
            AlertDialog dialog = builder.create();
            dialog.show();
            EditText oldPassword = dialogView.findViewById(R.id.et_old_password);
            EditText newPassword = dialogView.findViewById(R.id.et_new_password);
            Button submitButton = dialogView.findViewById(R.id.btn_submit_password);
            submitButton.setOnClickListener(v -> {
                String oldPass = oldPassword.getText().toString().trim();
                String newPass = newPassword.getText().toString().trim();

                if (oldPass.isEmpty() || newPass.isEmpty()) {
                    Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }else if(newPassword.length() <= 8){
                    Toast.makeText(this, "Password must be at least 8 characters.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (updateUserPassword(userId, oldPass, newPass)) {
                        Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else {
                        Toast.makeText(this, "Incorrect old password!", Toast.LENGTH_SHORT).show();
                    }
                }


            });



        });
        Button logoutButton = findViewById(R.id.btn_logout);
        logoutButton.setOnClickListener(v -> logoutUser());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private int getUserIdFromPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        return prefs.getInt("userId",-1 ); // Default value is -1 if not found
    }
    public user_info getUserData(int userId) {
        user_info user = null;

        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE user_id = ?", new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            int usernameIndex = cursor.getColumnIndex("username");
            int emailIndex = cursor.getColumnIndex("email");
            int fullNameIndex = cursor.getColumnIndex("FullName");
            int passwordIndex = cursor.getColumnIndex("password"); // Get password column index

            if (usernameIndex != -1 && emailIndex != -1 && fullNameIndex != -1 && passwordIndex != -1) {
                String username = cursor.getString(usernameIndex);
                String email = cursor.getString(emailIndex);
                String fullName = cursor.getString(fullNameIndex);
                String password = cursor.getString(passwordIndex); // Retrieve password

                user = new user_info(userId, username, password, email, fullName);
            }
        }

        cursor.close();
        return user;
    }

    private void displayUserInfo(user_info user) {
        // Find the TextViews by their IDs
        TextView fullNameTextView = findViewById(R.id.fullname_settings);
        TextView usernameTextView = findViewById(R.id.username_settings);
        TextView emailTextView = findViewById(R.id.email_settings);

        // Set the user data in the TextViews
        fullNameTextView.setText(user.getFullName());
        usernameTextView.setText(user.getUsername());
        emailTextView.setText(user.getEmail());
    }

    private boolean updateUserPassword(int userId, String oldPass, String newPass) {
        // Check if the old password matches
        Cursor cursor = db.rawQuery("SELECT password FROM user WHERE user_id = ?", new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            String storedPassword = cursor.getString(0);
            cursor.close();

            if (!storedPassword.equals(oldPass)) {
                // Old password is incorrect
                Toast.makeText(this, "Incorrect old password!", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (storedPassword.equals(newPass)) {
                // New password is the same as the old one
                Toast.makeText(this, "New password cannot be the same as old password!", Toast.LENGTH_SHORT).show();
                return false;
            }

            // Update password
            ContentValues values = new ContentValues();
            values.put("password", newPass);

            int rowsAffected = db.update("user", values, "user_id = ?", new String[]{String.valueOf(userId)});
            return rowsAffected > 0;
        }
        cursor.close();
        return false;
    }

    private void logoutUser() {
        // Clear all stored user data in SharedPreferences
        SharedPreferences prefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear(); // This removes all stored keys
        editor.apply();

        // Navigate to MainActivity
        Intent intent = new Intent(profile_settings.this, MainActivity.class);

        startActivity(intent);

    }



}