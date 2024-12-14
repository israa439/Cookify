//package DataSrc;
//import java.sql.*;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class All_recipes {
//    public static class Meal {
//        private int mealId, categoryId, mealCalories, mealDuration;
//        private String mealIngredients, mealPrep, mealImage, mealName;
//
//        // Constructor
//        public Meal(int mealId, int categoryId, String mealIngredients, String mealPrep,
//                    int mealCalories, int mealDuration, String mealImage, String mealName) {
//            this.mealId = mealId;
//            this.categoryId = categoryId;
//            this.mealIngredients = mealIngredients;
//            this.mealPrep = mealPrep;
//            this.mealCalories = mealCalories;
//            this.mealDuration = mealDuration;
//            this.mealImage = mealImage;
//            this.mealName = mealName;
//        }
//
//        @Override
//        public String toString() {
//            return "Meal ID: " + mealId + ", Name: " + mealName + ", Calories: " + mealCalories +
//                    ", Ingredients: " + mealIngredients + ", Duration: " + mealDuration + " mins";
//        }
//    }
//
//    public static List<Meal> getMeals() {
//        String query = "SELECT * FROM Meals_description";
//        return Database.executeQuery(query, resultSet -> {
//            List<Meal> meals = new ArrayList<>();
//            while (resultSet.next()) {
//                meals.add(new Meal(
//                        resultSet.getInt("meal_id"),
//                        resultSet.getInt("category_id"),
//                        resultSet.getString("meal_ingredients"),
//                        resultSet.getString("meal_prepway"),
//                        resultSet.getInt("meal_calories"),
//                        resultSet.getInt("meal_duration"),
//                        resultSet.getString("meal_image"),
//                        resultSet.getString("meal_name")
//                ));
//            }
//            return meals;
//        });
//    }
//
//
//}
package com.example.cookify.DataSrc.Endpoints;

import com.example.cookify.DataSrc.Data_structure.Meal;

import java.util.ArrayList;
import java.util.List;


public class All_recipes {
    public static List<Meal> getMeals() {
        List<Meal> meals = new ArrayList<>();

        meals.add(new Meal(
                1, 1,
                "2 Eggs*2 slices of toast*10g Butter*100ml Milk*Salt*Pepper*1 tbsp Olive Oil*1 slice of Cheese*1 tsp Paprika*1/4 Onion (chopped)*1/4 Tomato (diced)*1 tsp Parsley (chopped)",
                "Beat eggs with milk, salt, and pepper. Heat olive oil in a skillet. Pour egg mixture and stir until scrambled. Toast the bread, butter it, and serve eggs on top. Garnish with parsley.",
                300, 15,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2Fscrambeled_eggs_with_toast.png?alt=media&token=3d0d1516-7802-463d-a6c6-30ba8bbfff7f",
                "Scrambled Eggs with Toast"
        ));
        meals.add(new Meal(
                2, 1,
                "1 cup old-fashioned rolled oats*1 cup milk*2 large eggs*1 tbsp unsalted butter*1 tbsp granulated sugar*2/3 cup all-purpose flour*2 tsp baking powder*1/4 tsp kosher salt*1/4 tsp ground cinnamon (optional)*1 tsp Vanilla Extract*1 tsp Maple Syrup*1/2 tsp Baking Soda",
                "Whisk oats and milk in a bowl. Let stand for 10 minutes. Melt butter and cool it. Combine butter, eggs, and sugar with the oats. Add flour, baking powder, salt, cinnamon, and whisk until just mixed. Heat a skillet, add butter, and drop batter into portions. Cook 3 minutes until bubbles appear, flip, and cook 2-3 minutes more. Repeat with remaining batter.",
                350, 25,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2FFluffy%20Oatmeal%20Pancakes.png?alt=media&token=c31677f6-7651-4866-a97c-e35322d4d52b",
                "Oatmeal Pancakes"
        ));
        meals.add(new Meal(
                3, 1,
                "200g Chorizo (crumbled)*4 large Flour Tortillas*4 Eggs*1/4 cup Milk*1/2 cup Cheddar Cheese (shredded)*1 tbsp Olive Oil*1/4 Onion (chopped)*1/4 Bell Pepper (diced)*Salt*Pepper*1/2 Avocado (sliced)*2 tbsp Salsa",
                "Cook chorizo in a skillet until browned. In a bowl, whisk eggs, milk, salt, and pepper. Add the egg mixture to the skillet and scramble with chorizo. Warm tortillas, fill with scrambled egg mixture, cheese, avocado, and salsa. Fold and serve warm.",
                450, 20,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2FSavory%20Mexican%20Breakfast%20Casserole.png?alt=media&token=cd47c2da-5a6a-414a-96d0-d0a3af80bf88",
                "Chorizo Breakfast Burritos"
        ));

        meals.add(new Meal(
                4, 1,
                "6 Breakfast Sausages (cooked and crumbled)*4 slices Bread (cubed)*6 Eggs*1/4 cup Milk*1 cup Cheddar Cheese (shredded)*1/4 cup Green Onion (chopped)*1 tsp Garlic Powder*Salt*Pepper*1/4 cup Red Bell Pepper (diced)*1 tbsp Butter (for greasing pan)*1/4 tsp Paprika",
                "Preheat oven to 180°C (350°F). Grease a casserole dish with butter. Layer bread cubes, cooked sausage, and cheese. In a bowl, whisk eggs, milk, salt, pepper, garlic powder, and paprika. Pour over the bread mixture. Sprinkle green onions and bell peppers on top. Bake for 25-30 minutes until golden brown.",
                600, 35,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2FItalian%20Sausage%20Breakfast%20Casserole.png?alt=media&token=6e68457b-a08b-4151-8c0a-28e79c24b7d2",
                "Sausage Breakfast Casserole"
        ));


        meals.add(new Meal(
                5, 1,
                "1 loaf Sourdough Bread*4 tbsp Butter (melted)*2 Garlic Cloves (minced)*1/4 cup Parsley (chopped)*1/4 cup Parmesan Cheese (grated)*1/2 tsp Salt*1/2 tsp Black Pepper*1/2 tsp Chili Flakes (optional)*1 tbsp Olive Oil*1/4 cup Mozzarella Cheese (shredded)*1 tbsp Oregano (optional)",
                "Preheat oven to 200°C (400°F). Cut the bread diagonally in a crisscross pattern without slicing through the bottom. Mix melted butter, garlic, parsley, olive oil, salt, and pepper. Drizzle mixture into the cuts. Stuff with mozzarella and parmesan. Wrap in foil and bake for 15 minutes. Uncover and bake for another 5 minutes until golden.",
                350, 20,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2FCheesy%20Pull-Apart%20Garlic%20Bread%20-%20Kalejunkie.png?alt=media&token=3d21cf51-27e7-4828-87c0-8fd7010f6194",
                "Pull Apart Garlic Bread"
        ));

        meals.add(new Meal(
                6, 1,
                "1 cup Greek Yogurt*1/2 cup Granola*1/4 cup Honey*1/4 cup Mixed Berries (blueberries, strawberries)*1 tbsp Chia Seeds*1 tbsp Flaxseeds*1 tbsp Almond Slices*1/4 Banana (sliced)*1/2 tsp Vanilla Extract*1 pinch Cinnamon",
                "In a bowl, layer Greek yogurt, granola, and honey. Top with mixed berries, banana slices, chia seeds, flaxseeds, and almond slices. Drizzle with vanilla extract and sprinkle cinnamon for flavor. Serve immediately.",
                250, 5,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2FGreek%20Yogurt%20and%20Berry%20Smoothie%20Bowl.png?alt=media&token=c240bd81-b6a5-4b67-99d3-e410b85e0481",
                "Granola Yogurt"
        ));
        meals.add(new Meal(
                7, 1,
                "1 Whole Grain Bagel (sliced)*2 tbsp Cream Cheese*4 Cucumber Slices*2 Tomato Slices*4 slices of Avocado*1 tsp Olive Oil*1/2 tsp Salt*1/2 tsp Black Pepper*1 tbsp Microgreens (optional)*1 tbsp Red Onion (thinly sliced)*1/4 tsp Chili Flakes",
                "Toast the bagel halves. Spread cream cheese evenly on each half. Top with cucumber, tomato, avocado slices, and red onion. Drizzle with olive oil, sprinkle with salt, pepper, and chili flakes. Add microgreens if desired. Serve fresh.",
                300, 10,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2FClassic%20New%20York-Style%20Bagel%20with%20Cream%20Cheese%20Recipe.png?alt=media&token=1df5f7c1-4166-4e6d-b65d-3690d5316de9",
                "Bagel and Cream Cheese with Veggies"
        ));
        meals.add(new Meal(
                8, 1,
                "2 slices Whole Grain Bread*1 Avocado (mashed)*2 Eggs (fried or poached)*1/4 tsp Salt*1/4 tsp Black Pepper*1 tsp Lemon Juice*1 tsp Olive Oil*1 pinch Chili Flakes*1 tbsp Feta Cheese (crumbled)*1 tbsp Chives (chopped)*1/2 tsp Garlic Powder",
                "Toast the bread slices. Mash the avocado with lemon juice, salt, pepper, and garlic powder. Spread the avocado mixture on the bread. Top each slice with a fried or poached egg. Sprinkle with chili flakes, feta cheese, and chives. Serve warm.",
                400, 15,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2Favocado%20bread%20with%20eggs.png?alt=media&token=ca48b335-ee56-4e96-aa5b-5c51089f5c35",
                "Avocado Bread with Eggs"
        ));
        meals.add(new Meal(
                9, 1,
                "4 cups Hashbrowns (shredded)*1 cup Cheddar Cheese (shredded)*1/2 cup Sour Cream*1/2 cup Milk*1/4 cup Butter (melted)*1 tsp Salt*1/2 tsp Black Pepper*1/2 cup Onion (diced)*1/2 cup Bell Pepper (diced)*1/4 tsp Paprika*1 tbsp Parsley (chopped)",
                "Preheat oven to 180°C (350°F). Mix hashbrowns, cheddar cheese, sour cream, milk, butter, salt, pepper, onion, and bell pepper in a large bowl. Pour the mixture into a greased casserole dish. Sprinkle paprika on top. Bake for 40 minutes until golden brown. Garnish with parsley and serve warm.",
                550, 40,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2Fhashbrown%20casserole.png?alt=media&token=b94a0a2f-a3f9-4aaf-a10d-2271e7289673",
                "Hashbrown Casserole"
        ));

        meals.add(new Meal(
                10, 1,
                "6 slices Bread (cubed)*4 Eggs*1/2 cup Milk*1/4 cup Heavy Cream*1/4 cup Sugar*1/2 tsp Vanilla Extract*1/4 tsp Cinnamon*2 tbsp Butter (melted)*1/4 cup Maple Syrup*1/4 cup Raisins (optional)*1 tbsp Powdered Sugar (for garnish)",
                "Preheat oven to 180°C (350°F). Grease a casserole dish. Layer bread cubes in the dish. In a bowl, whisk eggs, milk, heavy cream, sugar, vanilla, and cinnamon. Pour mixture evenly over the bread. Add raisins if desired. Drizzle melted butter on top. Bake for 30-35 minutes. Sprinkle with powdered sugar and serve with maple syrup.",
                500, 35,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2FFree%20French%20Toast.png?alt=media&token=2c5b7687-1a60-44c1-a3e0-04084926944d",
                "French Toast Casserole"
        ));
        meals.add(new Meal(
                11, 2,
                "200g Penne Pasta*2 tbsp Pesto Sauce*1/4 cup Cherry Tomatoes (halved)*1/4 cup Parmesan Cheese (grated)*2 tbsp Olive Oil*1/4 tsp Salt*1/4 tsp Black Pepper*1 tbsp Pine Nuts (toasted)*1/4 cup Fresh Basil (chopped)*1 clove Garlic (minced)",
                "Cook pasta according to package instructions. Drain and toss with olive oil, minced garlic, pesto sauce, cherry tomatoes, and salt. Top with parmesan cheese, pine nuts, and fresh basil. Serve warm.",
                450, 20,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2FPesto%20Pasta%20Salad%20with%20Tomatoes%20%26%20Mozzarella.png?alt=media&token=613b44e7-9c1a-41d2-89aa-0d2d7915955c",
                "Pesto Pasta Bowl"
        ));

        meals.add(new Meal(
                12, 2,
                "4 Tomatoes (chopped)*1 Onion (chopped)*2 cloves Garlic (minced)*2 cups Vegetable Broth*1/4 cup Heavy Cream*2 tbsp Olive Oil*1 tsp Salt*1/2 tsp Black Pepper*1/4 tsp Sugar*2 slices Baguette (toasted)",
                "Heat olive oil in a pot. Sauté onion and garlic until softened. Add tomatoes and cook for 5 minutes. Pour in vegetable broth, salt, pepper, and sugar. Simmer for 15 minutes. Blend until smooth. Stir in heavy cream. Serve with toasted baguette slices.",
                300, 25,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2FEasy%20tomato%20soup%20with%20grilled%20cheese.png?alt=media&token=d38d0074-7edd-4b6f-9ec4-a60b66ce9f70",
                "Tomato Soup with Bread"
        ));

        meals.add(new Meal(
                13, 2,
                "4 Eggs*4 Tomatoes (chopped)*1 Bell Pepper (diced)*1 Onion (chopped)*2 cloves Garlic (minced)*2 tbsp Olive Oil*1 tsp Paprika*1/2 tsp Cumin*1/4 tsp Chili Flakes*1/4 tsp Salt*1/4 cup Fresh Parsley (chopped)",
                "Heat olive oil in a skillet. Sauté onion, garlic, and bell pepper until softened. Add tomatoes, paprika, cumin, chili flakes, and salt. Simmer for 10 minutes. Make 4 small wells in the sauce and crack an egg into each. Cover and cook until eggs are set. Garnish with parsley. Serve with bread.",
                350, 20,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2FShakshuka.png?alt=media&token=e18c4968-bcb2-48de-96e7-d060dacd08f8",
                "Best Shakshuka"
        ));

        meals.add(new Meal(
                14, 2,
                "1 Whole Grain Bagel (sliced)*2 tbsp Cream Cheese*50g Smoked Salmon*1 tbsp Capers*1 tsp Lemon Juice*1 tbsp Red Onion (thinly sliced)*1 tbsp Fresh Dill (chopped)*1/4 tsp Black Pepper*1/4 tsp Salt*1/2 cup Arugula",
                "Toast bagel halves. Spread cream cheese on each half. Top with smoked salmon, capers, red onion, dill, and arugula. Drizzle with lemon juice and sprinkle with salt and pepper. Serve fresh.",
                400, 10,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2FSmoked%20Salmon%20Bagel%20Recipe.png?alt=media&token=b81d5161-7d4e-48cf-a1a1-9f8b2568f9d5",
                "Bagel with Smoked Salmon"
        ));

        meals.add(new Meal(
                15, 2,
                "1 Butter Croissant (fresh)*2 tbsp Strawberry Jam*1 tbsp Butter (optional)*1/4 cup Fresh Berries (optional)*1/2 tsp Powdered Sugar (for garnish)",
                "Slice the croissant in half and lightly toast if desired. Spread butter and jam on each half. Serve with fresh berries on the side and a sprinkle of powdered sugar for garnish.",
                300, 5,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2Fcroissant%20with%20jam.png?alt=media&token=99597126-5662-497b-9f61-0520ed847ff1",
                "Croissant with Jam"
        ));
        meals.add(new Meal(
                16, 2,
                "2 slices White Bread*2 slices Cheddar Cheese*1 tbsp Butter*1/4 tsp Garlic Powder*1/4 tsp Black Pepper",
                "Spread butter on one side of each bread slice. Place cheddar cheese slices between the unbuttered sides. Heat a skillet over medium heat. Grill the sandwich until golden brown on each side and the cheese is melted. Sprinkle with garlic powder and black pepper before serving.",
                450, 10,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2FGrilled%20Cheese%20Sandwich%20-%20Love%20and%20Lemons.png?alt=media&token=9597ef73-2b2a-4f96-9019-8cd5d7214baf",
                "Grilled Cheese Sandwich"
        ));

        meals.add(new Meal(
                17, 2,
                "2 cups Romaine Lettuce (chopped)*1 Grilled Chicken Breast (sliced)*1/4 cup Caesar Dressing*1/4 cup Parmesan Cheese (shaved)*1/4 cup Croutons*1/4 tsp Salt*1/4 tsp Black Pepper*1/2 Lemon (juice)",
                "Toss lettuce with Caesar dressing, salt, and pepper. Top with grilled chicken, croutons, and shaved parmesan. Drizzle with lemon juice and serve chilled.",
                300, 15,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2FChicken%20Ceasor%20salad.png?alt=media&token=831942ad-d100-4cad-ba5d-3ee55f08975c",
                "Chicken Caesar Salad"
        ));

        meals.add(new Meal(
                18, 2,
                "2 Fried Eggs*2 slices Bacon*1/2 cup Sauteed Mushrooms*1/4 cup Grilled Tomatoes*2 slices Toast*1/4 cup Hashbrowns*1/4 tsp Salt*1/4 tsp Black Pepper*1 tbsp Butter*1 tbsp Ketchup (optional)",
                "Fry eggs in butter until sunny-side up. Cook bacon until crispy. Sauté mushrooms and grill tomatoes. Serve with toast, hashbrowns, and optional ketchup. Sprinkle with salt and pepper before serving.",
                550, 20,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2Fchicken%20brunch%20platter.png?alt=media&token=eda7ac40-29a6-488c-9b83-4c3189f81a1b",
                "Classic Brunch Platter"
        ));

        meals.add(new Meal(
                19, 2,
                "1 1/2 cups All-Purpose Flour*1 tbsp Sugar*1 tsp Baking Powder*1/2 tsp Baking Soda*1/4 tsp Salt*1 1/4 cups Milk*2 Eggs (separated)*1/4 cup Unsalted Butter (melted)*1 tsp Vanilla Extract*1/4 cup Maple Syrup (for serving)",
                "In a bowl, mix flour, sugar, baking powder, baking soda, and salt. In another bowl, whisk milk, egg yolks, melted butter, and vanilla extract. Combine wet and dry ingredients. Beat egg whites until stiff peaks form, then fold them into the batter. Heat a waffle iron and cook batter in batches. Serve warm with maple syrup.",
                450, 20,
                "https://firebasestorage.googleapis.com/v0/b/spotify-c3754.appspot.com/o/cookifyImages%2FBelgian%20Waffles%20with%20syrup.png?alt=media&token=7b0cd18e-a86a-4726-b231-bef8f9861a98",
                "Belgian Waffles with Syrup"
        ));

        meals.add(new Meal(
                20, 2,
                "500g Baby Potatoes (quartered)*1 tbsp Olive Oil*1/2 tsp Garlic Powder*1/2 tsp Paprika*1/4 tsp Salt*1/4 tsp Black Pepper*1/4 tsp Dried Oregano*1/4 tsp Dried Thyme*1 tbsp Fresh Parsley (chopped, for garnish)*1 tbsp Parmesan Cheese (optional)",
                "Preheat oven to 200°C (400°F). Toss potatoes with olive oil, garlic powder, paprika, salt, pepper, oregano, and thyme. Spread evenly on a baking sheet. Roast for 25-30 minutes, flipping halfway through, until golden and crispy. Garnish with parsley and parmesan cheese if desired.",
                350, 30,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/Roasted%20Breakfast%20Potatoes.jpg",
                "Roasted Breakfast Potatoes"
        ));
        meals.add(new Meal(
                21, 3,
                "1 cup Unsalted Butter (melted)*1 cup Granulated Sugar*1 cup Brown Sugar*4 Large Eggs*1 tsp Vanilla Extract*1 cup All-Purpose Flour*1/2 cup Unsweetened Cocoa Powder*1/4 tsp Baking Powder*1/4 tsp Salt*1 cup Chocolate Chips",
                "Preheat oven to 180°C (350°F). Grease a baking pan. In a bowl, whisk melted butter, sugars, eggs, and vanilla. Add flour, cocoa, baking powder, and salt. mix until combined. Fold in chocolate chips. Pour batter into the pan and bake for 25-30 minutes. Cool before cutting.",
                450, 40,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/chocolate%20brownies.jpg",
                "Chocolate Brownies"
        ));

        meals.add(new Meal(
                22, 3,
                "2 cups Heavy Cream*1 cup Whole Milk*3/4 cup Granulated Sugar*1 tbsp Vanilla Extract*Pinch of Salt",
                "In a bowl, mix heavy cream, milk, sugar, vanilla extract, and salt until the sugar dissolves. Chill the mixture for 2 hours. Pour into an ice cream maker and churn according to the manufacturers instructions. Freeze for 4 hours before serving.",
                200, 180,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/vanilla%20ice%20cream.jpg",
                "Vanilla Ice Cream"
        ));

        meals.add(new Meal(
                23, 3,
                "1 cup Unsalted Butter (softened)*3/4 cup Granulated Sugar*1 Egg Yolk*1 tsp Vanilla Extract*2 cups All-Purpose Flour*1/4 tsp Salt",
                "Preheat oven to 175°C (350°F). Cream butter and sugar until fluffy. Add egg yolk and vanilla, mix well. Gradually add flour and salt, mixing until combined. Shape into small cookies and place on a baking sheet. Bake for 10-12 minutes or until golden.",
                160, 25,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/butter%20cookies.jpg",
                "Butter Cookies"
        ));

        meals.add(new Meal(
                24, 3,
                "3 cups All-Purpose Flour*2 1/2 tsp Baking Powder*1/2 tsp Salt*1 cup Unsalted Butter (softened)*2 cups Sugar*4 Large Eggs*2 tsp Vanilla Extract*1 1/4 cups Milk*1 cup Frosting of Choice",
                "Preheat oven to 180°C (350°F). Grease and flour cake pans. Mix flour, baking powder, and salt. In another bowl, cream butter and sugar until fluffy. Add eggs one at a time, then vanilla. Alternate adding dry ingredients and milk to the butter mixture. Pour batter into pans and bake for 25-30 minutes. Cool completely before frosting.",
                450, 60,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/birthday%20cake.jpg",
                "Birthday Cake"
        ));

        meals.add(new Meal(
                25, 3,
                "1 cup Greek Yogurt*1 cup Granola*1/2 cup Fresh Strawberries (sliced)*1/2 cup Blueberries*1/2 cup Kiwi (diced)*1 tbsp Honey",
                "Layer yogurt, granola, and fruits in a glass. Repeat the layers until the glass is full. Drizzle with honey on top and serve immediately.",
                250, 10,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/fruit%20yogurt%20parfait.jpg",
                "Fruit Parfait"
        ));
        meals.add(new Meal(
                26, 3,
                "2 cups All-Purpose Flour*2 cups Sugar*3/4 cup Cocoa Powder*2 tsp Baking Powder*1 1/2 tsp Baking Soda*1 tsp Salt*1 cup Milk*1/2 cup Vegetable Oil*2 Large Eggs*2 tsp Vanilla Extract*1 cup Boiling Water",
                "Preheat oven to 175°C (350°F). Grease and flour cake pans. Combine dry ingredients in a large bowl. Add milk, oil, eggs, and vanilla, mix until smooth. Stir in boiling water. Pour batter into pans and bake for 30-35 minutes. Cool completely before frosting.",
                550, 45,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/chocolate%20cake.jpg",
                "Chocolate Cake"
        ));

        meals.add(new Meal(
                27, 3,
                "2 cups Graham Cracker Crumbs*1/2 cup Butter (melted)*3 packs Cream Cheese (softened)*1 cup Sugar*3 Large Eggs*1 tsp Vanilla Extract*1 cup Sour Cream",
                "Preheat oven to 175°C (350°F). Mix graham cracker crumbs and melted butter; press into a springform pan. Beat cream cheese and sugar until smooth. Add eggs one at a time, then vanilla and sour cream. Pour mixture over crust. Bake for 50-60 minutes. Cool and refrigerate before serving.",
                600, 90,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/cheese%20cake.jpg",
                "Cheesecake"
        ));

        meals.add(new Meal(
                28, 3,
                "1 pack Phyllo Dough*1 cup Butter (melted)*2 cups Chopped Walnuts or Pistachios*1 tsp Ground Cinnamon*1/2 cup Sugar*1 cup Honey*1/2 cup Water*1 tsp Lemon Juice",
                "Preheat oven to 175°C (350°F). Layer phyllo sheets in a greased pan, brushing each with butter. Sprinkle nut mixture between layers. Cut into diamond shapes and bake for 45 minutes. Boil sugar, honey, water, and lemon juice for syrup. Pour over baked baklava and let soak.",
                450, 120,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/baklava.jpg",
                "Baklava"
        ));

        meals.add(new Meal(
                29, 3,
                "2 cups Whole Milk*1/2 cup Sugar*3 tbsp Cornstarch*Pinch of Salt*3 Egg Yolks*1 tsp Vanilla Extract",
                "In a saucepan, heat milk, sugar, cornstarch, and salt until thickened. Temper the egg yolks by adding some hot milk mixture, then stir back into the saucepan. Cook until thick and smooth. Remove from heat and stir in vanilla. Chill before serving.",
                300, 25,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/Vanilla%20Custard.jpg",
                "Vanilla Custard"
        ));

        meals.add(new Meal(
                30, 3,
                "2 cups Heavy Cream*1/2 cup Milk*1/3 cup Sugar*1 tsp Vanilla Extract*1 tbsp Gelatin Powder*3 tbsp Water",
                "Soften gelatin in water for 5 minutes. Heat cream, milk, and sugar in a saucepan until sugar dissolves. Remove from heat, stir in vanilla and softened gelatin. Pour into molds and refrigerate for 4 hours before serving.",
                350, 20,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/panna%20cotta.jpg",
                "Panna Cotta"
        ));
        meals.add(new Meal(
                31, 4,
                "500g Chicken*2 cups Basmati Rice*1 cup Yogurt*2 Onions (sliced)*2 Tomatoes (chopped)*2 tbsp Biryani Masala*4 Cloves*2 Cardamom Pods*1 Cinnamon Stick*1/4 tsp Saffron*Salt to taste",
                "Marinate chicken with yogurt and spices. Fry onions until golden, set aside. Cook marinated chicken with tomatoes and biryani masala. Layer partially cooked rice over the chicken, add saffron water, and cook on low heat for 30 minutes.",
                700, 60,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/biryani.jpg",
                "Chicken Biryani"
        ));

        meals.add(new Meal(
                32, 4,
                "200g Spaghetti*250g Ground Beef*1 Onion (diced)*2 Garlic Cloves (minced)*1 Carrot (grated)*400g Canned Tomatoes*2 tbsp Tomato Paste*1 tsp Oregano*Salt and Pepper to taste*Grated Parmesan (optional)",
                "Cook spaghetti according to package instructions. In a pan, sauté onions, garlic, and carrots. Add ground beef and cook until browned. Stir in tomatoes, tomato paste, and oregano. Simmer for 20 minutes. Serve over spaghetti.",
                600, 45,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/Spaghetti%20Bolognese.png",
                "Spaghetti Bolognese"
        ));

        meals.add(new Meal(
                33, 4,
                "2 Slices Bread*100g Cooked Chicken (shredded)*2 tbsp Mayonnaise*1 Lettuce Leaf*2 Tomato Slices*1 Cheese Slice*Salt and Pepper to taste",
                "Mix chicken with mayonnaise, salt, and pepper. Place lettuce, tomatoes, and chicken mixture between bread slices. Add cheese slice and serve.",
                350, 10,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/Chicken%20Sandwich.jpg",
                "Chicken Sandwich"
        ));

        meals.add(new Meal(
                34, 4,
                "200g Salmon Fillet*2 tbsp Olive Oil*1 Lemon (sliced)*1 Garlic Clove (minced)*1 tsp Dill (chopped)*Salt and Pepper to taste",
                "Preheat grill. Rub salmon with olive oil, garlic, dill, salt, and pepper. Place lemon slices on top and grill for 6-8 minutes per side. Serve hot.",
                450, 20,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/grilled%20salmonjpg.jpg",
                "Grilled Salmon"
        ));

        meals.add(new Meal(
                35, 4,
                "500g Beef Cubes*2 Potatoes (diced)*2 Carrots (sliced)*1 Onion (chopped)*2 Garlic Cloves (minced)*500ml Beef Broth*2 tbsp Tomato Paste*1 tsp Thyme*Salt and Pepper to taste",
                "Brown beef in a pot. Add onions, garlic, and tomato paste. Stir in broth, thyme, potatoes, and carrots. Simmer for 1.5 hours until tender.",
                550, 120,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/beef%20stew.jpg",
                "Beef Stew"
        ));

        meals.add(new Meal(
                36, 4,
                "1 cup Rice*1 cup Cooked Beans (any type)*1 Onion (chopped)*2 Garlic Cloves (minced)*1 tbsp Olive Oil*1 tsp Cumin*Salt and Pepper to taste*1/2 cup Chicken or Vegetable Broth",
                "Cook rice separately. Sauté onions and garlic in olive oil. Add beans, cumin, and broth. Mix cooked rice with beans and season to taste.",
                400, 30,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/Rice-and-beans-recipe.jpg",
                "Rice and Beans"
        ));

        meals.add(new Meal(
                37, 4,
                "200g Cooked Chicken (shredded)*2 cups Lettuce (chopped)*1 Tomato (diced)*1/2 Cucumber (sliced)*1/4 cup Croutons*2 tbsp Caesar Dressing*Salt and Pepper to taste",
                "Toss chicken, lettuce, tomato, cucumber, and croutons with Caesar dressing. Add salt and pepper to taste. Serve chilled.",
                300, 15,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/chicken%20salad.jpg",
                "Chicken Salad"
        ));

        meals.add(new Meal(
                38, 4,
                "200g Fettuccine*1 cup Heavy Cream*2 tbsp Butter*1/2 cup Parmesan Cheese (grated)*1 Garlic Clove (minced)*Salt and Pepper to taste*1 tbsp Parsley (chopped)",
                "Cook fettuccine according to package instructions. In a pan, melt butter, add garlic, and sauté. Stir in cream and Parmesan. Mix cooked fettuccine with sauce and season with salt, pepper, and parsley.",
                600, 20,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/fettuccine%20alfredo.jpg",
                "Fettuccine Alfredo"
        ));

        meals.add(new Meal(
                39, 4,
                "1 Pizza Dough*1/2 cup Tomato Sauce*100g Mozzarella Cheese (shredded)*1/2 cup Toppings (e.g., Pepperoni, Vegetables)*1 tsp Oregano*Salt to taste",
                "Preheat oven to 220°C (425°F). Spread tomato sauce on pizza dough. Add cheese and toppings. Sprinkle oregano and salt. Bake for 15-20 minutes until crust is golden.",
                700, 30,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/pizza.jpg",
                "Pizza"
        ));

        meals.add(new Meal(
                40, 4,
                "200g Lo Mein Noodles*1 cup Mixed Vegetables (e.g., Carrots, Bell Peppers)*200g Protein (e.g., Chicken, Shrimp)*2 tbsp Soy Sauce*1 tbsp Oyster Sauce*1 tsp Sesame Oil*1 Garlic Clove (minced)",
                "Cook noodles according to package instructions. Sauté garlic and protein in sesame oil. Add vegetables, soy sauce, and oyster sauce. Toss noodles with the mixture and serve.",
                450, 25,
                "https://storage.googleapis.com/personalblog-364f2.appspot.com/images/lo%20mein.jpg",
                "Lo Mein"
        ));


        return meals;
    }
}
