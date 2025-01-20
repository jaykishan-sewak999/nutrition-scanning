package com.example.nutritionscanning.presenatation.imageprocessing

import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutritionscanning.R
import com.example.nutritionscanning.presenatation.core.utils.SpacerMedV
import com.example.nutritionscanning.ui.theme.dimenLow
import com.example.nutritionscanning.ui.theme.dimenMed
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageProcessingScreen(
    imageUri : Uri
){
    val viewModel = hiltViewModel<ImageProcessingViewModel>()

    LaunchedEffect(Unit) {
        viewModel.setUri(imageUri)
        delay(750)
        viewModel.getFoodInfo()
    }

    val uiState by viewModel.imageProcessingState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.title_food_analysis),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = dimenMed)
        ) {
            Card(
                modifier = Modifier
                    .align(Alignment.Center),
                colors = CardDefaults.cardColors().copy(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimenMed)
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val animatedProgress by animateFloatAsState(
                        targetValue = uiState.progress,
                        label = "progress",
                        animationSpec = tween(1000)
                    )

                    val animatedProgressInt = remember(animatedProgress) {
                        (animatedProgress * 100).toInt()
                    }

                    Text(
                        if (uiState.progress < 1) {
                            stringResource(R.string.txt_scanning_in_progress)
                        } else {
                            stringResource(R.string.txt_complete)
                        }
                    )

                    SpacerMedV()

                    LinearProgressIndicator(
                        progress = { animatedProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimenLow),
                        color = Color.Green,
                        trackColor = Color.Gray,
                        strokeCap = StrokeCap.Round
                    )

                    SpacerMedV()

                    Text(
                        "$animatedProgressInt%",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}