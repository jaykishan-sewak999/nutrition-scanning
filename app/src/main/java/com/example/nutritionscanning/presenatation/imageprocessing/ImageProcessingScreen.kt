package com.example.nutritionscanning.presenatation.imageprocessing

import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nutritionscanning.R
import com.example.nutritionscanning.presenatation.core.utils.SpacerMedV
import com.example.nutritionscanning.ui.theme.Black
import com.example.nutritionscanning.ui.theme.White
import com.example.nutritionscanning.ui.theme.dimenLow
import com.example.nutritionscanning.ui.theme.dimenMed
import com.example.nutritionscanning.ui.theme.Secondary
import com.example.nutritionscanning.ui.theme.GrayTextColor
import kotlinx.coroutines.delay

@Composable
fun ImageProcessingScreen(
    imageUri: Uri,
    onProcessCompleted: () -> Unit,
) {
    val viewModel = hiltViewModel<ImageProcessingViewModel>()

    LaunchedEffect(Unit) {
        viewModel.setUri(imageUri)
        delay(750)
        viewModel.getFoodInfo()
    }

    val uiState by viewModel.imageProcessingState.collectAsState()

    Scaffold(
        containerColor = White
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(R.drawable.nutrak_logo),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
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
                            .padding(dimenMed),
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
                            text = if (uiState.progress < 1) {
                                stringResource(R.string.txt_scanning_in_progress)
                            } else {
                                stringResource(R.string.txt_complete)
                            },
                            color = GrayTextColor
                        )

                        SpacerMedV()

                        LinearProgressIndicator(
                            progress = { animatedProgress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimenLow),
                            color = Secondary,
                            trackColor = Color.LightGray,
                            strokeCap = StrokeCap.Round
                        )

                        SpacerMedV()

                        Text(
                            text = "$animatedProgressInt%",
                            color = Black,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        if (animatedProgress == 1f) {
                            LaunchedEffect(Unit) {
                                onProcessCompleted()
                            }
                        }
                    }
                }
            }
        }
    }
}