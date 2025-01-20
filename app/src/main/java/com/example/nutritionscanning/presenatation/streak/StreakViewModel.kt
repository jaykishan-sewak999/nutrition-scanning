package com.example.nutritionscanning.presenatation.streak

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.collections.first

@HiltViewModel
class StreakViewModel @Inject constructor() : ViewModel() {
    private val _streaksUiState = MutableStateFlow(StreaksUiState())
    val streaksUiState = _streaksUiState.asStateFlow()

    init {
        val milestones = listOf(
            Milestone(MilestoneLevel.SILVER, true),
            Milestone(MilestoneLevel.BRONZE, false),
            Milestone(MilestoneLevel.GOLD, false),
            Milestone(MilestoneLevel.PLATINUM, false)
        )
        _streaksUiState.update {
            it.copy(
                currentStreak = 5,
                milestones = milestones,
                nextMilestone = milestones.first { it.isAchieved.not() }
            )
        }
    }
}