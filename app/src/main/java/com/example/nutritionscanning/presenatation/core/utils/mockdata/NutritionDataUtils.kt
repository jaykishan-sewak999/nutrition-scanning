package com.example.nutritionscanning.presenatation.core.utils.mockdata

import com.example.nutritionscanning.data.model.Macronutrients
import com.example.nutritionscanning.data.model.Micronutrients
import com.example.nutritionscanning.data.model.Nutrition
import com.github.mikephil.charting.data.BarEntry

val nutritionResult = Nutrition(
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

fun calculateTotalMacronutrients(macronutrients: Macronutrients): String {
    val proteins = macronutrients.proteins.removeSuffix("g").toIntOrNull() ?: 0
    val carbs = macronutrients.carbs.removeSuffix("g").toIntOrNull() ?: 0
    val fats = macronutrients.fats.removeSuffix("g").toIntOrNull() ?: 0
    val total = proteins + carbs + fats
    return total.toString() + "g"
}

fun calculateTotalMicronutrients(micronutrients: Micronutrients): String {
    val vitaminA = micronutrients.vitaminA.removeSuffix("%").toIntOrNull() ?: 0
    val calcium = micronutrients.calcium.removeSuffix("%").toIntOrNull() ?: 0
    val total = vitaminA + calcium
    return "$total%"
}

val nutritionBarChartData = listOf(
    BarEntry(0f, 20f),
    BarEntry(1f, 30f),
    BarEntry(2f, 25f),
    BarEntry(3f, 35f),
    BarEntry(4f, 30f),
    BarEntry(5f, 40f),
    BarEntry(6f, 50f)
)