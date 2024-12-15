package com.example.cookify;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class create_recipe extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private ImageView recipeImg;
    private Button submitButton;
    private EditText nameField, calorieField, durationField, ingredientsField, prepWayField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_recipe);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Button selectImageButton = findViewById(R.id.selectImageButton);
        recipeImg = findViewById(R.id.recipeImg);
        submitButton = findViewById(R.id.Submit_button);
        nameField = findViewById(R.id.name_field);
        calorieField = findViewById(R.id.calorie_field);
        durationField = findViewById(R.id.duration_field);
        ingredientsField = findViewById(R.id.Ingredients_field);
        prepWayField = findViewById(R.id.prepway_field);
        recipeImg = findViewById(R.id.recipeImg);

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageSelector();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areFieldsNotEmpty()) {
                    clearFields();
                    Toast.makeText(create_recipe.this, "Your recipe is created", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(create_recipe.this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void openImageSelector() {
        // Open gallery to pick an image
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Handle the result from image selection
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

            try {
                // Convert the selected image to a Bitmap and display it in the ImageView
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                recipeImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearFields() {
        // Clear the text fields
        nameField.setText("");
        calorieField.setText("");
        durationField.setText("");
        ingredientsField.setText("");
        prepWayField.setText("");

        // Reset the ImageView
        recipeImg.setImageDrawable(null);
    }
    private boolean areFieldsNotEmpty() {
        return !nameField.getText().toString().trim().isEmpty() &&
                !calorieField.getText().toString().trim().isEmpty() &&
                !durationField.getText().toString().trim().isEmpty() &&
                !ingredientsField.getText().toString().trim().isEmpty() &&
                !prepWayField.getText().toString().trim().isEmpty();
    }

}