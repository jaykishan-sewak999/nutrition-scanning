package com.example.nutritionscanning.presenatation.streak

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar
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

    fun getWeeksInMonthWithPreviousMonth(year: Int, month: Int): List<List<Int?>> {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, 1) // Set to the 1st day of the given month

        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        // Adjust the first day of the week to start from Monday
        val adjustedFirstDayOfWeek =
            if (firstDayOfWeek == Calendar.SUNDAY) 7 else firstDayOfWeek - 1

        // Add previous month's dates if needed
        val prevMonthCalendar = Calendar.getInstance()
        prevMonthCalendar.set(year, month - 1, 1)
        val daysInPrevMonth = prevMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        val weeks = mutableListOf<MutableList<Int?>>()
        var currentWeek: MutableList<Int?> = MutableList(adjustedFirstDayOfWeek - 1) { index ->
            daysInPrevMonth - (adjustedFirstDayOfWeek - 2) + index
        }

        for (day in 1..daysInMonth) {
            if (currentWeek.size == 7) {
                weeks.add(currentWeek)
                currentWeek = mutableListOf()
            }
            currentWeek.add(day)
        }

        // Add the last week if there are remaining days
        if (currentWeek.isNotEmpty()) {
            val remainingSlots = 7 - currentWeek.size
            for (i in 1..remainingSlots) {
                currentWeek.add(i) // Add next month's dates
            }
            weeks.add(currentWeek)
        }

        return weeks
    }
}