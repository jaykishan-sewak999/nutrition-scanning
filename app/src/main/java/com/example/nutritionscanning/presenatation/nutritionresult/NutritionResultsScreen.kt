package com.example.nutritionscanning.presenatation.nutritionresult

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import com.example.nutritionscanning.R
import com.example.nutritionscanning.presenatation.core.utils.DynamicSpacer
import com.example.nutritionscanning.presenatation.core.utils.mockdata.calculateTotalMacronutrients
import com.example.nutritionscanning.presenatation.core.utils.mockdata.calculateTotalMicronutrients
import com.example.nutritionscanning.presenatation.core.utils.mockdata.nutritionBarChartData
import com.example.nutritionscanning.presenatation.core.utils.mockdata.nutritionResult
import com.example.nutritionscanning.ui.theme.Black
import com.example.nutritionscanning.ui.theme.Gray
import com.example.nutritionscanning.ui.theme.LightGray
import com.example.nutritionscanning.ui.theme.Transparent
import com.example.nutritionscanning.ui.theme.White
import com.example.nutritionscanning.ui.theme.Yellow
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.model.GradientColor

val COLLAPSED_TOP_BAR_HEIGHT = 56.dp
val EXPANDED_TOP_BAR_HEIGHT = 300.dp

@Composable
fun NutritionResultsScreen() {

    val listState = rememberLazyListState()

    val overlapHeightPx = with(LocalDensity.current) {
        EXPANDED_TOP_BAR_HEIGHT.toPx() - COLLAPSED_TOP_BAR_HEIGHT.toPx()
    }

    val isCollapsed: Boolean by remember {
        derivedStateOf {
            val isFirstItemHidden =
                listState.firstVisibleItemScrollOffset > overlapHeightPx
            isFirstItemHidden || listState.firstVisibleItemIndex > 0
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                Modifier.fillMaxSize()
            ) {
                CollapsedTopBar(modifier = Modifier.zIndex(2f), isCollapsed = isCollapsed)

                LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
                    item {
                        // Food Image and Title Section
                        ExpandedTopBar()
                    }
                    item {
                        // Nutritional Overview
                        NutritionalOverview()
                    }
                    item {
                        // Weekly Meal Nutrition Chart
                        WeeklyMealNutritionChart()
                    }
                    item {
                        // Save Button
                        SaveToDailyLogButton()
                    }
                    item {
                        // Upgrade to Premium
                        PremiumButton()
                    }
                }
            }
        }
    }
}

@Composable
fun ExpandedTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(EXPANDED_TOP_BAR_HEIGHT),
        contentAlignment = Alignment.BottomStart
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = painterResource(R.drawable.pepperoni_pizza),
            contentDescription = stringResource(R.string.txt_nutrition_result),
            contentScale = ContentScale.Crop,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Transparent, Transparent, Transparent, White
                        )
                    )
                )
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.txt_pepperoni_pizza),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        )

    }
}

@Composable
fun CollapsedTopBar(modifier: Modifier = Modifier, isCollapsed: Boolean) {
    val color: Color by animateColorAsState(
        targetValue = if (isCollapsed) White else Color.Transparent,
        label = stringResource(R.string.txt_nutrition_result)
    )
    Box(
        modifier = modifier
            .background(color)
            .fillMaxWidth()
            .height(COLLAPSED_TOP_BAR_HEIGHT + 32.dp)
            .padding(horizontal = 24.dp)
            .padding(top = 32.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        AnimatedVisibility(visible = isCollapsed) {
            Text(
                text = stringResource(R.string.txt_pepperoni_pizza),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun NutritionalOverview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        // Nutritional Overview
        Text(
            text = stringResource(R.string.txt_nutritional_overview),
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            NutrientCard(
                icon = painterResource(R.drawable.calories),
                title = stringResource(R.string.txt_calories),
                value = nutritionResult.calories,
                modifier = Modifier.weight(1f)
            )

            DynamicSpacer(width = 16)

            Spacer(modifier = Modifier.weight(1f))
        }

        // Macronutrients
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.txt_macronutrients),
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(
                    R.string.txt_total,
                    calculateTotalMacronutrients(nutritionResult.macronutrients)
                ),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold, color = Gray
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NutrientCard(
                icon = painterResource(R.drawable.proteins),
                title = stringResource(R.string.txt_protein),
                value = nutritionResult.macronutrients.proteins,
                modifier = Modifier.weight(1f)
            )

            DynamicSpacer(width = 16)

            NutrientCard(
                icon = painterResource(R.drawable.carbs),
                title = stringResource(R.string.txt_carbs),
                value = nutritionResult.macronutrients.carbs,
                modifier = Modifier.weight(1f)
            )
        }

        DynamicSpacer(height = 8)

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            NutrientCard(
                icon = painterResource(R.drawable.fats),
                title = stringResource(R.string.txt_fats),
                value = nutritionResult.macronutrients.fats,
                modifier = Modifier.weight(1f)
            )

            DynamicSpacer(width = 16)

            Spacer(modifier = Modifier.weight(1f))
        }

        // Micronutrients
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.txt_micronutrients),
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(
                    R.string.txt_total,
                    calculateTotalMicronutrients(nutritionResult.micronutrients)
                ),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold, color = Gray
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NutrientCard(
                icon = painterResource(R.drawable.iron),
                title = stringResource(R.string.txt_vitamin_a),
                value = nutritionResult.micronutrients.vitaminA,
                modifier = Modifier.weight(1f)
            )

            DynamicSpacer(width = 16)

            NutrientCard(
                icon = painterResource(R.drawable.calcium),
                title = stringResource(R.string.txt_calcium),
                value = nutritionResult.micronutrients.calcium,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun NutrientCard(
    icon: Painter, title: String, value: String, modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(LightGray)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = icon,
            contentDescription = stringResource(R.string.txt_nutrition_result),
            modifier = Modifier.size(24.dp)
        )

        DynamicSpacer(width = 8)

        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(color = Gray)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun WeeklyMealNutritionChart() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .background(color = LightGray, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(R.string.txt_weekly_meal_nutrition),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

        AndroidView(
            factory = { context ->
                BarChart(context).apply {
                    setTouchEnabled(false)
                    description.isEnabled = false
                    setPinchZoom(false)
                    setDrawBarShadow(false)
                    setDrawGridBackground(false)
                    legend.isEnabled = false

                    // X-Axis Configuration
                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    xAxis.setDrawGridLines(false)
                    xAxis.setDrawAxisLine(false)
                    xAxis.granularity = 1f
                    xAxis.valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return listOf("S", "M", "T", "W", "T", "F", "S")[value.toInt()]
                        }
                    }

                    // Y-Axis Configuration
                    axisLeft.setDrawGridLines(false)
                    axisLeft.isEnabled = false
                    axisRight.isEnabled = false

                    data = BarData(
                        BarDataSet(
                            nutritionBarChartData,
                            "Nutrition"
                        ).apply {
                            val colors = listOf(
                                GradientColor(
                                    android.graphics.Color.rgb(226, 149, 35),
                                    android.graphics.Color.rgb(92, 166, 96),
                                ),
                                GradientColor(
                                    android.graphics.Color.rgb(226, 149, 35),
                                    android.graphics.Color.rgb(92, 166, 96),
                                ),
                            )
                            gradientColors = colors
                        }
                    )
                    data.barWidth = 0.2f
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
    }
}

@Composable
fun SaveToDailyLogButton() {
    Button(
        onClick = { /* Handle Save Action */ },
        colors = ButtonDefaults.buttonColors(containerColor = Yellow),
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = RoundedCornerShape(24)
    ) {
        Text(
            text = stringResource(R.string.txt_save_to_daily_log),
            fontSize = 16.sp,
            color = Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PremiumButton(modifier: Modifier = Modifier) {

    val wantToMoreText = stringResource(R.string.txt_want_more_insights)
    val upgradeToPremiumText = stringResource(R.string.txt_upgrade_to_premium)

    val newText = buildAnnotatedString {
        append(wantToMoreText + upgradeToPremiumText)
        addLink(
            LinkAnnotation.Clickable(
                upgradeToPremiumText,
                TextLinkStyles(
                    SpanStyle(
                        color = Black,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                )
            ) { },
            start = wantToMoreText.length,
            end = wantToMoreText.length + upgradeToPremiumText.length
        )
    }

    Text(
        text = newText,
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        modifier = modifier.fillMaxSize()
    )
}