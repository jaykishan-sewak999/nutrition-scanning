package com.example.nutritionscanning.data.model

data class Nutrition(
    val calories: String,
    val macronutrients: Macronutrients,
    val micronutrients: Micronutrients,
)

data class Macronutrients(
    val proteins: String,
    val carbs: String,
    val fats: String,
)

data class Micronutrients(
    val vitaminA: String,
    val calcium: String,
)

