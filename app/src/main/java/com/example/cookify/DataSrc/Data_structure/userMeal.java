package com.example.cookify.DataSrc.Data_structure;
public class userMeal implements  MealBase {
    private int mealId;
    private int userId;
    private String mealIngredients;
    private String mealPrepWay;
    private int mealCalories;
    private int mealDuration;
    private String mealImage;
    private String mealName;

    // Constructor
    public userMeal(int mealId, int userId, String mealIngredients, String mealPrepWay,
                int mealCalories, int mealDuration, String mealImage, String mealName) {
        this.mealId = mealId;
        this.userId = userId;
        this.mealIngredients = mealIngredients;
        this.mealPrepWay = mealPrepWay;
        this.mealCalories = mealCalories;
        this.mealDuration = mealDuration;
        this.mealImage = mealImage;
        this.mealName = mealName;
    }

    // Getters
    public int getMealId() { return mealId; }
    public int getUserId() { return userId; }
    public String getMealIngredients() { return mealIngredients; }
    public String getMealPrepWay() { return mealPrepWay; }
    public int getMealCalories() { return mealCalories; }
    public int getMealDuration() { return mealDuration; }
    public String getMealImage() { return mealImage; }
    public String getMealName() { return mealName; }
    public int getCategoryId() {
        throw new UnsupportedOperationException("getCategoryId is not applicable for userMeal.");
    }

    public String toString() {
        return "Meal{" +
                "mealId=" + mealId +
                ", userId=" + userId +
                ", mealIngredients='" + mealIngredients + '\'' +
                ", mealPrepway='" + mealPrepWay + '\'' +
                ", mealCalories=" + mealCalories +
                ", mealDuration=" + mealDuration +
                ", mealImage='" + mealImage + '\'' +
                ", mealName='" + mealName + '\'' +
                '}';
    }
}
