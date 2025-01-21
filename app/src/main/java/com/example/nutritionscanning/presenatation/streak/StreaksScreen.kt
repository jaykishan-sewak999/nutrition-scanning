package com.example.nutritionscanning.presenatation.streak

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nutritionscanning.R
import com.example.nutritionscanning.presenatation.core.utils.SpacerLowV
import com.example.nutritionscanning.presenatation.core.utils.SpacerMedV
import com.example.nutritionscanning.ui.theme.Black
import com.example.nutritionscanning.ui.theme.BlackTextColor
import com.example.nutritionscanning.ui.theme.Gray
import com.example.nutritionscanning.ui.theme.Secondary
import com.example.nutritionscanning.ui.theme.Transparent
import com.example.nutritionscanning.ui.theme.White
import com.example.nutritionscanning.ui.theme.dimenLow
import com.example.nutritionscanning.ui.theme.dimenMed
import com.example.nutritionscanning.ui.theme.GrayTextColor
import java.util.Calendar

@Composable
fun StreaksScreen() {
    val context = LocalContext.current
    val viewModel = hiltViewModel<StreakViewModel>()
    val uiState by viewModel.streaksUiState.collectAsState()
    val streakDays = listOf(31, 1, 2, 3, 4)

    val milestoneIconMap = remember {
        mapOf(
            MilestoneLevel.SILVER to R.drawable.milestone_badge_silver,
            MilestoneLevel.BRONZE to R.drawable.milestone_badge_bronze,
            MilestoneLevel.GOLD to R.drawable.milestone_badge_gold,
            MilestoneLevel.PLATINUM to R.drawable.milestone_badge_platinum
        )
    }

    Scaffold(
        topBar = { StreaksAppBar() },
        containerColor = White,
    ) { paddingValues ->
        Column(
            Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .padding(horizontal = dimenMed)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpacerMedV()

            Box {
                Image(
                    painter = painterResource(R.drawable.streak_fire_yellow),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
                Text(
                    text = "${uiState.currentStreak}",
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = dimenLow)
                )
            }

            SpacerMedV()

            Text(
                text = stringResource(R.string.txt_you_re_on_a),
                color = GrayTextColor,
                fontSize = 15.sp
            )
            Text(
                text = "${uiState.currentStreak} ${if (uiState.currentStreak == 1) "day" else "days"} Streak!",
                fontSize = 18.sp,
                fontWeight = FontWeight.W600
            )
            Text(
                text = stringResource(R.string.txt_keep_it_up),
                color = GrayTextColor,
                fontSize = 15.sp
            )

            SpacerMedV()

            Box {
                StreakCalendarUI(streakDays, viewModel = viewModel)

                uiState.nextMilestone?.let { milestone ->
                    Card(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        elevation = CardDefaults.elevatedCardElevation(2.dp),
                        shape = RoundedCornerShape(40),
                    ) {
                        Row(
                            Modifier
                                .background(
                                    brush = Brush.linearGradient(milestone.gradient),
                                    shape = RoundedCornerShape(40)
                                )
                                .padding(horizontal = dimenLow, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = rememberVectorPainter(
                                    ImageVector.vectorResource(
                                        id = milestoneIconMap[milestone.level]
                                            ?: R.drawable.milestone_badge_silver
                                    )
                                ),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = stringResource(
                                    R.string.X_day_streak_achiever, milestone.level.days
                                ),
                                fontSize = 12.sp,
                                modifier = Modifier.padding(start = 4.dp),
                                color = BlackTextColor
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
                        color = GrayTextColor
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                        contentDescription = null,
                        Modifier
                            .size(24.dp)
                            .padding(4.dp),
                        tint = GrayTextColor
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
            titleContentColor = Black,
            actionIconContentColor = Black
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
        modifier = modifier,
        shape = cardShape,
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {

        Row(
            modifier = Modifier
                .background(Brush.linearGradient(milestone.gradient))
                .padding(dimenMed),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = rememberVectorPainter(image = ImageVector.vectorResource(icon)),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .padding(4.dp)
            )
            Text(
                text = stringResource(R.string.X_day_streak_achiever, milestone.level.days),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dimenLow),
                fontSize = 15.sp
            )
            if (milestone.isAchieved) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Secondary)
                        .padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun StreakCalendarUI(streakDays: List<Int>, viewModel: StreakViewModel) {
    val calendar = Calendar.getInstance()
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentYear = calendar.get(Calendar.YEAR)

    val streakAchieved = viewModel.streaksUiState.collectAsState()
    val nextStreak = streakAchieved.value.nextMilestone?.level?.days ?: 0

    // Get the first two weeks of the current month with adjusted previous month's days
    val weeksInMonth =
        viewModel.getWeeksInMonthWithPreviousMonth(currentYear, currentMonth).take(2)
    Column {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .border(
                    width = 1.dp,
                    color = Gray.copy(0.2f),
                    shape = RoundedCornerShape(dimenMed + dimenLow)
                )
                .padding(16.dp)

        ) {
            // Weekday Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("M", "T", "W", "T", "F", "S", "S").forEach { day ->
                    Box(
                        modifier = Modifier.width(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day,
                            color = Gray,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Display calendar days for the first two weeks
            weeksInMonth.forEach { week ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val subList =
                        weeksInMonth.flatten().let { month ->
                            val index = month.indexOf(streakDays.first())
                            month.subList(index, nextStreak + 1)
                        }

                    week.forEach { day ->
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(
                                    if (day != null && streakDays.contains(day)) Secondary else Transparent
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (day != null && streakDays.contains(day)) {
                                Icon(
                                    painter = painterResource(R.drawable.streak_fire_yellow),
                                    contentDescription = null,
                                    tint = White,
                                    modifier = Modifier.size(24.dp)
                                )
                            } else {
                                Text(
                                    text = day?.toString() ?: "",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = if (subList.contains(day)) {
                                        Black
                                    } else {
                                        Gray.copy(0.3f)
                                    },
                                    textAlign = TextAlign.Center
                                )
                            }

                        }
                    }
                }
            }
            SpacerMedV()
        }
        SpacerLowV()
    }
}