package com.example.cookify.ui.create_recipe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cookify.DataSrc.Adapters.MealAdapter;
import com.example.cookify.DataSrc.Data_structure.userMeal;
import com.example.cookify.DataSrc.Endpoints.user_recipes;
import com.example.cookify.MainActivity;
import com.example.cookify.R;
import com.example.cookify.create_recipe;
import com.example.cookify.databinding.FragmentYourRecipeBinding;
import com.example.cookify.detailedRecipe;
import com.example.cookify.signin;


import java.util.List;

public class urRecipeFragment extends Fragment {

    private FragmentYourRecipeBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        urRecipeViewModel urRecipeViewModel =
                new ViewModelProvider(this).get(urRecipeViewModel.class);
        binding = FragmentYourRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.ur_recipe_container);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        List<userMeal> usermeals= user_recipes.getUserMeals();

        MealAdapter mealAdapter = new MealAdapter(getContext(), usermeals, (position, meal) -> {
            System.out.println("entered the meal adapter");
            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("RecipePrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("recipeName", meal.getMealName());
            editor.putString("recipeImage", meal.getMealImage());
            editor.putInt("recipeId", meal.getMealId());
            editor.putInt("recipeDuration", meal.getMealDuration());
            editor.putInt("recipeCals", meal.getMealCalories());
            editor.putString("recipePrepWay", meal.getMealPrepWay() );
            editor.putString("recipeIngredients", meal.getMealIngredients());

            editor.apply();

            System.out.println("Navigating to detailedRecipe with meal: " + meal.getMealName());
            Intent intent=new Intent(getContext(), detailedRecipe.class);
            startActivity(intent);

        });
        recyclerView.setAdapter(mealAdapter);
        CardView createContainer=root.findViewById(R.id.create_container);
        createContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireContext(), create_recipe.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}