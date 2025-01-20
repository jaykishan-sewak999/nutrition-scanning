package com.example.nutritionscanning.data.repository

import android.net.Uri
import com.example.nutritionscanning.data.model.Macronutrients
import com.example.nutritionscanning.data.model.Micronutrients
import com.example.nutritionscanning.data.model.Nutrition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodProcessingRepository @Inject constructor() {
    suspend fun getNutritionByFoodImage(uri: Uri): Nutrition = withContext(Dispatchers.IO) {
        delay(2000)
        val dummyNutrition = Nutrition(
            calories = "320 kcal",
            macronutrients = Macronutrients(
                proteins = "20g",
                carbs = "40g",
                fats = "10g"
            ),
            micronutrients = Micronutrients(
                vitaminA = "10%",
                calcium = "15%"
            ),
        )
        return@withContext dummyNutrition
    }
}