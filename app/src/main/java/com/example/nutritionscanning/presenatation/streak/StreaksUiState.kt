package com.example.nutritionscanning.presenatation.streak

import androidx.compose.ui.graphics.Color

data class StreaksUiState(
    val currentStreak: Int = 0,
    val milestoneMessage: String? = null,
    val milestones: List<Milestone> = emptyList(),
    val nextMilestone: Milestone? = milestones.firstOrNull(),
)

data class Milestone(
    val level: MilestoneLevel,
    val isAchieved: Boolean,
) {
    val gradient
        get() = when (level) {
            MilestoneLevel.SILVER ->
                listOf(Color(0xFFF0F4F7), Color(0xFFF2F5F7))

            MilestoneLevel.BRONZE ->
                listOf(Color(0xFFF5E5D3), Color(0xFFFFF8F0))

            MilestoneLevel.GOLD ->
                listOf(Color(0xFFDCEDF5), Color(0xFFEDF6FA))

            MilestoneLevel.PLATINUM ->
                listOf(Color(0xFFF5F0FF), Color(0xFFF3F0FA))
        }

}

enum class MilestoneLevel(val days: Int) {
    SILVER(7), BRONZE(10), GOLD(20), PLATINUM(30)
}
