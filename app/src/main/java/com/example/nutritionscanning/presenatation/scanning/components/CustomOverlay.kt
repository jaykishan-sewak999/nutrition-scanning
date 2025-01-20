package com.example.nutritionscanning.presenatation.scanning.components

import android.R.attr.height
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.nutritionscanning.R

@Composable
fun CustomOverlay() {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    var width by remember { mutableFloatStateOf(0f) }
    var height by remember { mutableFloatStateOf(0f) }
    var left by remember { mutableFloatStateOf(0f) }
    var top by remember { mutableFloatStateOf(0f) }

    val cornerRadius = 16.dp
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        width = size.width * 0.8f
        height = size.height * 0.4f
        left = (size.width - height) / 2
        top = (size.height - height) / 2.25f

        drawRoundRect(
            color = Color.Black.copy(alpha = 0.5f),
            topLeft = androidx.compose.ui.geometry.Offset(0f, 0f),
            size = Size(size.width, size.height)
        )
        drawRoundRect(
            color = Color.Transparent,
            topLeft = androidx.compose.ui.geometry.Offset(left, top),
            size = Size(height, height),
            blendMode = BlendMode.Src,
            cornerRadius = CornerRadius(cornerRadius.toPx())
        )
    }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.scanning_animation))
    LottieAnimation(
        composition = composition,
        modifier = Modifier
            .size(
            width = (height / displayMetrics.density).dp ,
            height = (height / displayMetrics.density).dp
        )
            .offset(
                x = (left / displayMetrics.density).dp ,
                y = (top / displayMetrics.density).dp
            )
            .clip(RoundedCornerShape(cornerRadius))
        ,
        iterations = Int.MAX_VALUE
    )
}