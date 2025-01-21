package com.example.nutritionscanning.presenatation.scanning.screen

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutritionscanning.R
import com.example.nutritionscanning.presenatation.core.utils.SpacerLowV
import com.example.nutritionscanning.presenatation.scanning.components.CameraPreviewScreen
import com.example.nutritionscanning.presenatation.scanning.components.CustomOverlay
import com.example.nutritionscanning.presenatation.scanning.components.captureImage
import com.example.nutritionscanning.ui.theme.Secondary
import com.example.nutritionscanning.ui.theme.dimenLow
import com.example.nutritionscanning.ui.theme.dimenMed

@Composable
fun ScanningScreen(
    onNavBack: () -> Unit,
    onNavigateToImageProcessing: (Uri) -> Unit,
) {
    val context = LocalContext.current
    val imageCapture = remember { ImageCapture.Builder().build() }
    val transGray = Color.LightGray.copy(alpha = 0.2f)

    val imagePickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { uri ->
            onNavigateToImageProcessing(uri)
        }
    }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Camera Preview
            CameraPreviewScreen(imageCapture)

            // Overlay
            Box(Modifier.fillMaxSize()) {
                CustomOverlay()
            }

            // Top Instructions
            Column(
                Modifier
                    .padding(16.dp)
                    .padding(paddingValues)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onNavBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            tint = Color.White
                        )
                    }

                    Icon(
                        painter = rememberVectorPainter(
                            ImageVector.vectorResource(R.drawable.ic_flash_off)
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }
                SpacerLowV()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimenLow)
                        .background(
                            color = transGray,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(dimenMed),
                ) {
                    Image(
                        painter = rememberVectorPainter(
                            ImageVector.vectorResource(R.drawable.ic_scanner)
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 4.dp, end = 12.dp)
                            .size(28.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Column {
                        Text(
                            text = stringResource(R.string.title_scan_your_food),
                            color = Color.White,
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            textAlign = TextAlign.Start
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = stringResource(R.string.description_scan_your_food),
                            color = Color.White,
                            fontSize = 12.sp,
                            lineHeight = 13.sp,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }

            // Bottom Capture Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp)
                    .padding(paddingValues)
                    .padding(bottom = 36.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Icon(
                    painter = painterResource(R.drawable.ic_gallery),
                    contentDescription = stringResource(R.string.cd_choose_from_gallery),
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .clickable {
                            imagePickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                        .background(transGray)
                        .padding(12.dp),
                    tint = Color.White
                )

                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Secondary)
                        .border(dimenLow, Color.White, CircleShape)
                        .clickable {
                            captureImage(
                                context, imageCapture, onSuccess = {
                                    onNavigateToImageProcessing(it)
                                },
                                onFailure = {
                                    Log.d(javaClass.name, "ScanningScreen: ${it.message} ")
                                }
                            )
                        }
                ) {}

                Text(
                    text = "1x",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 44.sp,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(transGray)
                )
            }
        }
    }
}
