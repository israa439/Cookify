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

import com.example.cookify.DataSrc.Endpoints.sign_in;

public class signin extends AppCompatActivity {
    private EditText usernameField, passwordField;
    private Button signinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sign_in s=new sign_in(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        signinBtn=findViewById(R.id.signin_button);
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameField = findViewById(R.id.username_field_signin);
                passwordField = findViewById(R.id.password_field_signin);
                String username = usernameField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty() ) {
                    Toast.makeText(signin.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                    return;
                }
                try{
                    sign_in signInHandler = new sign_in(signin.this);
                    String response = signInHandler.signInUser(signin.this, username, password);
                    if (response.equals("Sign-in failed. Incorrect username or password.")) {
                        Toast.makeText(signin.this, response, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("authenticated", true);
                    editor.putString("userInitials", response);
                    editor.apply();

                    Toast.makeText(signin.this,"Signed in successfully.", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    String errorMessage = e.getMessage() != null ? e.getMessage() : "An unknown error occurred";
                    Toast.makeText(signin.this, errorMessage, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


                Intent intent=new Intent(signin.this, MainActivity.class);
                startActivity(intent);
            }
        });

        TextView navTosignup=findViewById(R.id.navtosignup);
        navTosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signin.this, signup.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}