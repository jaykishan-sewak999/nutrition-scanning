package com.example.nutritionscanning.presenatation.imageprocessing

import com.example.nutritionscanning.data.model.Nutrition

data class ImageProcessingUiState(
    val progress : Float = 0f,
    val nutrition : Nutrition? = null
)