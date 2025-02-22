package com.example.nutritionscanning.presenatation.core.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpacerLowV() {
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun SpacerMedV() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun DynamicSpacer(height: Int = 0, width: Int = 0) {
    Spacer(
        modifier = Modifier
            .height(height.dp)
            .width(width.dp)
    )
}

