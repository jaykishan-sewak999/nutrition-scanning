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
            calories = "200",
            macronutrients = Macronutrients(
                proteins = "10g",
                carbs = "30g",
                fats = "5g"
            ),
            micronutrients = Micronutrients(
                vitaminA = "500mcg",
                calcium = "100mg"
            )
        )
        return@withContext dummyNutrition
    }
}