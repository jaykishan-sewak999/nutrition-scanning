package com.example.nutritionscanning.presenatation.streak

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nutritionscanning.R
import com.example.nutritionscanning.presenatation.core.utils.SpacerMedV
import com.example.nutritionscanning.ui.theme.dimenLow
import com.example.nutritionscanning.ui.theme.dimenMed
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import com.example.nutritionscanning.ui.theme.Transparent
import com.example.nutritionscanning.ui.theme.White
import com.example.nutritionscanning.ui.theme.greyTextColor

@Composable
fun StreaksScreen() {
    val context = LocalContext.current
    val viewModel = hiltViewModel<StreakViewModel>()
    val uiState by viewModel.streaksUiState.collectAsState()

    val milestoneIconMap = remember {
        mapOf(
            MilestoneLevel.SILVER to R.drawable.milestone_badge_silver,
            MilestoneLevel.BRONZE to R.drawable.milestone_badge_bronze,
            MilestoneLevel.GOLD to R.drawable.milestone_badge_gold,
            MilestoneLevel.PLATINUM to R.drawable.milestone_badge_platinum
        )
    }

    Scaffold(
        topBar = {
            StreaksAppBar()
        },
        containerColor = White
    ) { paddingValues ->

        Column(
            Modifier
                .padding(
                    top = paddingValues.calculateTopPadding()
                )
                .padding(horizontal = dimenMed)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpacerMedV()
            Box {
                Image(
                    painter = painterResource(R.drawable.streak_fire_yellow),
                    contentDescription = null,
                    Modifier.size(128.dp)
                )
                Text(
                    "${uiState.currentStreak}",
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = dimenLow)
                )
            }

            SpacerMedV()
            Text(stringResource(R.string.txt_you_re_on_a), fontSize = 15.sp, color = greyTextColor)
            Text(
                "${uiState.currentStreak} ${
                    if (uiState.currentStreak == 1) "day"
                    else "days"
                } Streak!",
                fontSize = 18.sp,
                fontWeight = FontWeight.W600
            )
            Text(stringResource(R.string.txt_keep_it_up), fontSize = 15.sp, color = greyTextColor)

            SpacerMedV()

            // TODO : Replace with Streak calender
            Box {
                Column {
                    Spacer(
                        Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(dimenMed + dimenLow)
                            )
                    )
                    SpacerMedV()
                }
                uiState.nextMilestone?.let { milestone ->
                    Card(
                        Modifier
                            .align(Alignment.BottomCenter),
                        elevation = CardDefaults.elevatedCardElevation(
                            2.dp
                        )
                    ) {
                        Row(
                            Modifier
                                .background(
                                    brush = Brush.linearGradient(
                                        milestone.gradient
                                    ),
                                    shape = RoundedCornerShape(40)
                                )
                                .padding(horizontal = dimenLow, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = rememberVectorPainter(
                                    ImageVector.vectorResource(
                                        milestoneIconMap[milestone.level]
                                            ?: R.drawable.milestone_badge_silver
                                    )
                                ),
                                contentDescription = null,
                                Modifier.size(20.dp)
                            )
                            Text(
                                text = stringResource(
                                    R.string.X_day_streak_achiever,
                                    milestone.level.days
                                ),
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                            )
                        }
                    }
                }
            }

            SpacerMedV()

            Row {
                Text(
                    stringResource(R.string.label_milestones),
                    Modifier.weight(1f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600
                )
                Row {
                    Text(
                        stringResource(R.string.action_view_all),
                        fontSize = 15.sp,
                        color = greyTextColor
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                        contentDescription = null,
                        Modifier
                            .size(24.dp)
                            .padding(4.dp),
                        tint = greyTextColor
                    )
                }
            }
            SpacerMedV()

            uiState.milestones.forEach {
                StreakMileStoneCard(
                    milestone = it,
                    icon = milestoneIconMap[it.level] ?: R.drawable.milestone_badge_silver,
                    modifier = Modifier.fillMaxWidth()
                )
                SpacerMedV()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StreaksAppBar() {
    val context = LocalContext.current
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = White,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black
        ),
        title = {
            Text(
                text = stringResource(R.string.title_streaks),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(
                onClick = {
                    Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(imageVector = Icons.Outlined.Share, contentDescription = null)
            }
        }
    )
}

@Composable
fun StreakMileStoneCard(
    milestone: Milestone,
    icon: Int,
    modifier: Modifier = Modifier,
) {
    val cardShape = RoundedCornerShape(dimenMed)
    Card(
        modifier,
        shape = cardShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(
            Modifier
                .background(
                    Brush.linearGradient(
                        milestone.gradient
                    )
                )
                .padding(dimenMed),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = rememberVectorPainter(image = ImageVector.vectorResource(icon)),
                contentDescription = null,
                Modifier
                    .size(36.dp)
                    .padding(4.dp)
            )
            Text(
                stringResource(
                    R.string.X_day_streak_achiever,
                    milestone.level.days
                ),
                Modifier
                    .weight(1f)
                    .padding(start = dimenLow),
                fontSize = 15.sp
            )
            if (milestone.isAchieved) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(4.dp)
                )
            }
        }
    }
}