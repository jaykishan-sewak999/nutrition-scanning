package com.example.nutritionscanning.presenatation.imageprocessing

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritionscanning.data.repository.FoodProcessingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageProcessingViewModel @Inject constructor(
    private val foodProcessingRepository: FoodProcessingRepository
): ViewModel() {

    private val imgUri = MutableStateFlow<Uri?>(null)
    fun setUri(uri: Uri){
        imgUri.value = uri
    }

    private val uiState = MutableStateFlow(ImageProcessingUiState())
    val imageProcessingState = uiState.asStateFlow()

    fun getFoodInfo(){
        val imgUri = imgUri.value ?: return
        viewModelScope.launch {
            uiState.update {
                it.copy(progress = 0.3f)
            }
            val nutrition = foodProcessingRepository.getNutritionByFoodImage(imgUri)
            uiState.update {
                it.copy(progress = 1f, nutrition = nutrition)
            }
        }
    }
}