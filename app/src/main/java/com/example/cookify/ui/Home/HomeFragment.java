package com.example.cookify.ui.Home;

import com.example.cookify.DataSrc.Endpoints.All_recipes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.cookify.R;
import com.example.cookify.databinding.FragmentHomeBinding;
import com.example.cookify.detailedRecipe;
import java.util.ArrayList;
import java.util.List;
import com.example.cookify.DataSrc.Data_structure.Meal;
import com.example.cookify.DataSrc.Adapters.MealAdapter;
import com.example.cookify.DataSrc.connection.Database; // ✅ Added missing import

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private MealAdapter mealAdapter; // ✅ Make adapter a class variable
    private Database connection;
    private List<Meal> meals; // ✅ Store the full meal list for reset

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recipe_container);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // ✅ Initialize database connection
        connection = new Database(requireContext());

        // ✅ Get the full list of meals
        All_recipes allRecipes = new All_recipes(requireContext());
        meals = allRecipes.getAllMealDescriptions();

        // ✅ Initialize adapter with the full list
        mealAdapter = new MealAdapter(getContext(), meals, (position, meal) -> {
            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("RecipePrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("recipeName", meal.getMealName());
            editor.putString("recipeImage", meal.getMealImage());
            editor.putInt("recipeId", meal.getMealId());
            editor.putInt("recipeDuration", meal.getMealDuration());
            editor.putInt("recipeCals", meal.getMealCalories());
            editor.putString("recipePrepWay", meal.getMealPrepWay());
            editor.putString("recipeIngredients", meal.getMealIngredients());
            editor.putInt("recipeCategoryId", meal.getCategoryId());
            editor.apply();

            Intent intent = new Intent(getContext(), detailedRecipe.class);
            startActivity(intent);
        });

        recyclerView.setAdapter(mealAdapter);

        // ✅ Handle search input
        EditText searchBar = root.findViewById(R.id.SearchBar);
        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, android.view.KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        actionId == EditorInfo.IME_ACTION_NEXT ||
                        (event != null && event.getAction() == android.view.KeyEvent.ACTION_DOWN)) {

                    String searchQuery = searchBar.getText().toString().trim();
                    if (!searchQuery.isEmpty()) {
                        updateRecyclerView(searchingByNames(searchQuery)); // ✅ Update RecyclerView
                    } else {
                        updateRecyclerView(meals); // ✅ Reset to full list if search is empty
                    }
                    return true;
                }
                return false;
            }
        });

        return root;
    }

    // ✅ Function to update RecyclerView data
    private void updateRecyclerView(List<Meal> newMealList) {
        mealAdapter.updateData(newMealList); // Update adapter data
        mealAdapter.notifyDataSetChanged(); // Refresh RecyclerView
    }

    // ✅ Modified function to return filtered meal list
    public ArrayList<Meal> searchingByNames(String query) {
        ArrayList<Meal> filteredMeals = new ArrayList<>();
        SQLiteDatabase db = connection.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Meals_description WHERE mealName LIKE ?", new String[]{"%" + query + "%"});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    // ✅ Ensure the column exists before accessing it
                    int mealIdIndex = cursor.getColumnIndex("meal_id");
                    int categoryIdIndex = cursor.getColumnIndex("category_id");
                    int mealIngredientsIndex = cursor.getColumnIndex("meal_ingredients");
                    int mealPrepWayIndex = cursor.getColumnIndex("meal_prepway");
                    int mealCaloriesIndex = cursor.getColumnIndex("meal_calories");
                    int mealDurationIndex = cursor.getColumnIndex("meal_duration");
                    int mealImageIndex = cursor.getColumnIndex("meal_image");
                    int mealNameIndex = cursor.getColumnIndex("mealName");

                    // ✅ If any column is missing, skip this row
                    if (mealIdIndex == -1 || categoryIdIndex == -1 || mealIngredientsIndex == -1 ||
                            mealPrepWayIndex == -1 || mealCaloriesIndex == -1 || mealDurationIndex == -1 ||
                            mealImageIndex == -1 || mealNameIndex == -1) {
                        System.out.println("Error: One or more columns are missing in the database table.");
                        continue;
                    }

                    // ✅ Now safely retrieve the values
                    int mealId = cursor.getInt(mealIdIndex);
                    int categoryId = cursor.getInt(categoryIdIndex);
                    String mealIngredients = cursor.getString(mealIngredientsIndex);
                    String mealPrepWay = cursor.getString(mealPrepWayIndex);
                    int mealCalories = cursor.getInt(mealCaloriesIndex);
                    int mealDuration = cursor.getInt(mealDurationIndex);
                    String mealImage = cursor.getString(mealImageIndex);
                    String mealName = cursor.getString(mealNameIndex);

                    filteredMeals.add(new Meal(mealId, categoryId, mealIngredients, mealPrepWay, mealCalories, mealDuration, mealImage, mealName));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return filteredMeals;
    }

}
