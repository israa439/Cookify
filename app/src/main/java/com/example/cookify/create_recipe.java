package com.example.cookify;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cookify.DataSrc.connection.Database;
import com.example.cookify.ui.create_recipe.urRecipeFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class create_recipe extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private ImageView recipeImg;
    private Button submitButton;
    private EditText nameField, calorieField, durationField, ingredientsField, prepWayField;
    private StorageReference storageReference;
    private Database dbHelper; // SQLite Database Helper

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

        // Initialize Firebase Storage
        storageReference = FirebaseStorage.getInstance().getReference("cookifyImages");

        // Initialize SQLite Database Helper
        dbHelper = new Database(this);

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
                    uploadImageToFirebase();
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
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                recipeImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToFirebase() {
        if (imageUri != null) {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + ".jpg");

            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageUrl = uri.toString();
                                    insertRecipeToDatabase(imageUrl);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(create_recipe.this, "Image Upload Failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Please select an image!", Toast.LENGTH_SHORT).show();
        }
    }

    private void insertRecipeToDatabase(String imageUrl) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = prefs.getInt("userId", -1);

        // Format the ingredients into the desired format
        String ingredientsText = ingredientsField.getText().toString().trim();
        String[] ingredientsArray = ingredientsText.split("\n");
        StringBuilder formattedIngredients = new StringBuilder();

        for (String ingredient : ingredientsArray) {
            if (!ingredient.isEmpty()) {
                formattedIngredients.append(ingredient).append("*");
            }
        }
        // Remove the last "*" character
        if (formattedIngredients.length() > 0) {
            formattedIngredients.setLength(formattedIngredients.length() - 1);
        }
        int mealid=generateUniqueUserId();


        // Now insert into user_meals table

        values.put("user_id", userId);
        values.put("meal_id", mealid);  // Use the meal_id returned from Meals_description
        values.put("meal_ingredients", formattedIngredients.toString());
        values.put("meal_prepway", prepWayField.getText().toString().trim());
        values.put("meal_calories", Integer.parseInt(calorieField.getText().toString().trim()));
        values.put("meal_duration", Integer.parseInt(durationField.getText().toString().trim()));
        values.put("meal_image", imageUrl);
        values.put("mealName", nameField.getText().toString().trim());

        long result = db.insert("user_meals", null, values);
        db.close();

        if (result != -1) {
            clearFields();
            Toast.makeText(this, "Recipe Added to user_meals Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(create_recipe.this, urRecipeFragment.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Error Adding Recipe to user_meals!", Toast.LENGTH_SHORT).show();
        }
    }


    private void clearFields() {
        nameField.setText("");
        calorieField.setText("");
        durationField.setText("");
        ingredientsField.setText("");
        prepWayField.setText("");
        recipeImg.setImageDrawable(null);
    }

    private boolean areFieldsNotEmpty() {
        return !nameField.getText().toString().trim().isEmpty() &&
                !calorieField.getText().toString().trim().isEmpty() &&
                !durationField.getText().toString().trim().isEmpty() &&
                !ingredientsField.getText().toString().trim().isEmpty() &&
                !prepWayField.getText().toString().trim().isEmpty();
    }
    private static int generateUniqueUserId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

}
