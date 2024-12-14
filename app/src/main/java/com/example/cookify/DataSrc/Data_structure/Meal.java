package com.example.cookify.DataSrc.Data_structure;

public class Meal implements MealBase {
    private int mealId;
    private int categoryId;
    private String mealIngredients;
    private String mealPrepWay;
    private int mealCalories;
    private int mealDuration;
    private String mealImage;
    private String mealName;

    // Constructor
    public Meal(int mealId, int categoryId, String mealIngredients, String mealPrepWay,
                int mealCalories, int mealDuration, String mealImage, String mealName) {
        this.mealId = mealId;
        this.categoryId = categoryId;
        this.mealIngredients = mealIngredients;
        this.mealPrepWay = mealPrepWay;
        this.mealCalories = mealCalories;
        this.mealDuration = mealDuration;
        this.mealImage = mealImage;
        this.mealName = mealName;
    }

    // Getters
    public int getMealId() { return mealId; }
    public int getCategoryId() { return categoryId; }
    public String getMealIngredients() { return mealIngredients; }
    public String getMealPrepWay() { return mealPrepWay; }
    public int getMealCalories() { return mealCalories; }
    public int getMealDuration() { return mealDuration; }
    public String getMealImage() { return mealImage; }
    public String getMealName() { return mealName; }
    public int getUserId() {
        throw new UnsupportedOperationException("getUserId is not applicable for Meal.");
    }
    public String toString() {
        return "Meal{" +
                "mealId=" + mealId +
                ", categoryId=" + categoryId +
                ", mealIngredients='" + mealIngredients + '\'' +
                ", mealPrepway='" + mealPrepWay + '\'' +
                ", mealCalories=" + mealCalories +
                ", mealDuration=" + mealDuration +
                ", mealImage='" + mealImage + '\'' +
                ", mealName='" + mealName + '\'' +
                '}';
    }
}
