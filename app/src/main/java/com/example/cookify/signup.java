package com.example.cookify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cookify.DataSrc.Endpoints.sign_up;

public class signup extends AppCompatActivity {
    private EditText usernameField, passwordField, fullNameField, emailField;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sign_up s=new sign_up(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        signupBtn = findViewById(R.id.signin_button);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usernameField = findViewById(R.id.username_field_signin);
                passwordField = findViewById(R.id.password_field_signin);
                fullNameField = findViewById(R.id.fullname_field);
                emailField = findViewById(R.id.email_field);
                String username = usernameField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();
                String fullName = fullNameField.getText().toString().trim();
                String email = emailField.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty()) {
                    Toast.makeText(signup.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {

                    String response = s.registerUser(username, password, email, fullName);

                    if (!response.equals("Account created successfully.")) {
                        Toast.makeText(signup.this, response, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Toast.makeText(signup.this, response, Toast.LENGTH_SHORT).show();
                    String initials=getInitials(fullName);
                    SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("authenticated", true);
                    editor.putString("userInitials", initials);
                    editor.apply();

                    Intent intent = new Intent(signup.this, MainActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    String errorMessage = e.getMessage() != null ? e.getMessage() : "An unknown error occurred";
                    Toast.makeText(signup.this, errorMessage, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        TextView navTosignin = findViewById(R.id.navtosignup);
        navTosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signup.this, signin.class);
                startActivity(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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