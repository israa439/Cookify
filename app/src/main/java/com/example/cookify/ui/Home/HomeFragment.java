package com.example.cookify.ui.Home;
import com.example.cookify.DataSrc.Endpoints.All_recipes;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookify.R;
import com.example.cookify.databinding.FragmentHomeBinding;
import com.example.cookify.detailedRecipe;

import java.util.List;

import com.example.cookify.DataSrc.Data_structure.Meal;
import com.example.cookify.DataSrc.Adapters.MealAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recipe_container);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Instantiate All_recipes and get the meal descriptions
        All_recipes allRecipes = new All_recipes(requireContext());
        List<Meal> meals = allRecipes.getAllMealDescriptions();

        // Create and set the adapter for the RecyclerView
        MealAdapter mealAdapter = new MealAdapter(getContext(), meals, (position, meal) -> {
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

            System.out.println("Navigating to detailedRecipe with meal: " + meal.getMealName());
            Intent intent = new Intent(getContext(), detailedRecipe.class);
            startActivity(intent);
        });

        recyclerView.setAdapter(mealAdapter);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}