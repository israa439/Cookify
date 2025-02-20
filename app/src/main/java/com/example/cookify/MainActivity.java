package com.example.cookify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookify.DataSrc.Data_structure.Meal;
import com.example.cookify.DataSrc.connection.Database;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.cookify.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SQLiteDatabase db;
    private Database connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().hide();
        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        MenuItem menuItem = bottomNavigationView.getMenu().findItem(R.id.navigation_dashboard);

        Drawable icon = menuItem.getIcon();
        icon.setBounds(0, 0, 100, 100);
        menuItem.setIcon(icon);

        // Profile icon click listener (navigate to sign-in)
        ImageView navToAccounts = findViewById(R.id.profileicon);
        navToAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signin.class);
                startActivity(intent);
            }
        });

        // Check if user is logged in and update profile icon
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isAuthenticated = prefs.getBoolean("authenticated", false);
        String userInitials = prefs.getString("userInitials", "");
        ImageView profileIcon = findViewById(R.id.profileicon);

        if (isAuthenticated && !userInitials.isEmpty()) {
            updateProfileIcon(userInitials); // Show initials
            navToAccounts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, profile_settings.class);
                    startActivity(intent);
                }
            });

        } else {
            profileIcon.setImageResource(R.drawable.profile); // Show default icon
            navToAccounts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, signin.class);
                    startActivity(intent);
                }
            });
        }


    }

    private void updateProfileIcon(String initials) {
        int size = 100; // Match ImageView size
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        // Draw circular background
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#E63946")); // Background color
        paint.setAntiAlias(true);
        canvas.drawCircle(size / 2, size / 2, size / 2, paint);

        // Draw initials
        paint.setColor(Color.WHITE); // Text color
        paint.setTextSize(size / 2.5f); // Make text larger
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.DEFAULT_BOLD);

        // Adjust text positioning
        Rect textBounds = new Rect();
        paint.getTextBounds(initials, 0, initials.length(), textBounds);
        float x = size / 2;
        float y = size / 2 - textBounds.exactCenterY(); // Perfect centering

        canvas.drawText(initials, x, y, paint);

        // Set bitmap to profile icon
        ImageView profileIcon = findViewById(R.id.profileicon);
        profileIcon.setImageBitmap(bitmap);
    }






}