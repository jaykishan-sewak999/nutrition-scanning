package com.example.nutritionscanning.presenatation.nutritionresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.nutritionscanning.R
import com.example.nutritionscanning.data.model.Macronutrients
import com.example.nutritionscanning.data.model.Micronutrients
import com.example.nutritionscanning.ui.theme.Black
import com.example.nutritionscanning.ui.theme.Blue
import com.example.nutritionscanning.ui.theme.Gray
import com.example.nutritionscanning.ui.theme.Green
import com.example.nutritionscanning.ui.theme.Orange
import com.example.nutritionscanning.ui.theme.Purple
import com.example.nutritionscanning.ui.theme.Yellow
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.model.GradientColor

@Composable
fun NutritionResultsScreen() {
    Scaffold(
        topBar = { TopAppBarUI() }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    // Food Image and Title Section
                    FoodImageSection()
                }
                item {
                    val macronutrients = Macronutrients(
                        proteins = "20g",
                        carbs = "40g",
                        fats = "10g"
                    )
                    val micronutrients = Micronutrients(
                        vitaminA = "10%",
                        calcium = "15%"
                    )
                    // Nutritional Overview
                    NutritionalOverview(
                        calories = "320 kcal",
                        macronutrients = macronutrients,
                        micronutrients = micronutrients
                    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarUI() {
    TopAppBar(
        title = {
            Text(
                text = "Nutrition Results",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun FoodImageSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        // Food Image
        Image(
            painter = painterResource(id = R.drawable.outline_fire), // Replace with your image resource
            contentDescription = "Pepperoni Pizza",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        // Food Title
        Text(
            text = "Pepperoni Pizza",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
        )
    }
}

@Composable
fun NutritionalOverview(
    calories: String,
    macronutrients: Macronutrients,
    micronutrients: Micronutrients,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Header Title
        Text(
            text = "Nutritional Overview",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Calories Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),

            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
//            Icon(
//                imageVector = Icons.Default.Call, // Use flame icon
//                contentDescription = "Calories Icon",
//                tint = Orange,
//                modifier = Modifier.size(24.dp)
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(
//                text = "Calories: $calories",
//                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
//            )
            NutrientCard(
                icon = Icons.Default.Face,
                title = "Calories",
                value = calories,
                color = Green
            )
        }

        HorizontalDivider(thickness = 1.dp, color = Color.Gray.copy(alpha = 0.5f))

        // Macronutrients
        Text(
            text = "Macronutrients",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        ) {
            NutrientCard(
                icon = Icons.Default.Face,
                title = "Proteins",
                value = macronutrients.proteins,
                color = Green
            )
            NutrientCard(
                icon = Icons.Default.Favorite,
                title = "Carbs",
                value = macronutrients.carbs,
                color = Yellow
            )
            NutrientCard(
                icon = Icons.Default.FavoriteBorder,
                title = "Fats",
                value = macronutrients.fats,
                color = Blue
            )
        }

        HorizontalDivider(thickness = 1.dp, color = Color.Gray.copy(alpha = 0.5f))

        // Micronutrients
        Text(
            text = "Micronutrients",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        ) {
            NutrientCard(
                icon = Icons.Default.Favorite,
                title = "Vitamin A",
                value = micronutrients.vitaminA,
                color = Orange
            )
            NutrientCard(
                icon = Icons.Default.FavoriteBorder,
                title = "Calcium",
                value = micronutrients.calcium,
                color = Purple
            )
        }
    }
}

@Composable
fun NutrientCard(icon: ImageVector, title: String, value: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Gray.copy(0.2f), shape = RoundedCornerShape(20))
            .padding(horizontal = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "$title Icon",
                tint = color,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }


    }
}

@Composable
fun WeeklyMealNutritionChart() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(1.dp, Gray.copy(0.5f), shape = RoundedCornerShape(8.dp))
            .background(color = Gray.copy(0.1f), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = "Weekly Meal Nutrition",
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
                            listOf(
                                BarEntry(0f, 20f),
                                BarEntry(1f, 30f),
                                BarEntry(2f, 25f),
                                BarEntry(3f, 35f),
                                BarEntry(4f, 30f),
                                BarEntry(5f, 40f),
                                BarEntry(6f, 50f)
                            ),
                            "Nutrition"
                        ).apply {
                            gradientColors = listOf(
                                GradientColor(
                                    android.graphics.Color.rgb(244, 67, 54),
                                    android.graphics.Color.rgb(76, 175, 80),
                                ),
                                GradientColor(
                                    android.graphics.Color.rgb(244, 67, 54),
                                    android.graphics.Color.rgb(76, 175, 80),
                                ),
                            )
                        }
                    )
                    data.barWidth = 0.3f
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
        colors = ButtonDefaults.buttonColors(
            containerColor = Yellow,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Save to Daily Log",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PremiumButton(modifier: Modifier = Modifier) {

    val insightText = "Want more insights?"
    val upgradeText = "Upgrade to Premium"

    val newText = buildAnnotatedString {
        append(insightText + upgradeText)
        addLink(
            LinkAnnotation.Clickable(
                upgradeText,
                TextLinkStyles(
                    SpanStyle(
                        color = Black,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                )
            ) { },
            start = insightText.length,
            end = insightText.length + upgradeText.length
        )
    }

    Text(
        text = newText,
        textAlign = TextAlign.Center,
        fontSize = 15.sp,
        modifier = modifier.fillMaxSize()
    )
}
