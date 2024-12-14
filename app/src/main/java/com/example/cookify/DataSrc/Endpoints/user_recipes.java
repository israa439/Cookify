package com.example.cookify.DataSrc.Endpoints;

import com.example.cookify.DataSrc.Data_structure.userMeal;

import java.util.ArrayList;
import java.util.List;

public class user_recipes {
 public  static List<userMeal> getUserMeals(){
     List<userMeal> usermeals = new ArrayList<>();
     usermeals.add(new userMeal(
             1, 123,
             "2 Eggs*2 slices of toast*10g Butter*100ml Milk*Salt*Pepper*1 tbsp Olive Oil*1 slice of Cheese*1 tsp Paprika*1/4 Onion (chopped)*1/4 Tomato (diced)*1 tsp Parsley (chopped)",
             "Beat eggs with milk, salt, and pepper. Heat olive oil in a skillet. Pour egg mixture and stir until scrambled. Toast the bread, butter it, and serve eggs on top. Garnish with parsley.",
             300, 15,
             "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2Fscrambeled_eggs_with_toast.png?alt=media&token=3d0d1516-7802-463d-a6c6-30ba8bbfff7f",
             "Scrambled Eggs with Toast"
     ));
     usermeals.add(new userMeal(
             39, 123,
             "1 Pizza Dough*1/2 cup Tomato Sauce*100g Mozzarella Cheese (shredded)*1/2 cup Toppings (e.g., Pepperoni, Vegetables)*1 tsp Oregano*Salt to taste",
             "Preheat oven to 220°C (425°F). Spread tomato sauce on pizza dough. Add cheese and toppings. Sprinkle oregano and salt. Bake for 15-20 minutes until crust is golden.",
             700, 30,
             "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/pizza.jpg",
             "Pizza"
     ));

     usermeals.add(new userMeal(
             40, 123,
             "200g Lo Mein Noodles*1 cup Mixed Vegetables (e.g., Carrots, Bell Peppers)*200g Protein (e.g., Chicken, Shrimp)*2 tbsp Soy Sauce*1 tbsp Oyster Sauce*1 tsp Sesame Oil*1 Garlic Clove (minced)",
             "Cook noodles according to package instructions. Sauté garlic and protein in sesame oil. Add vegetables, soy sauce, and oyster sauce. Toss noodles with the mixture and serve.",
             450, 25,
             "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/lo%20mein.jpg",
             "Lo Mein"
     ));
     return usermeals;

    }
}
