package com.example.nutritionscanning.presenatation.core

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavBackStackEntry

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitToRight(): @JvmSuppressWildcards ExitTransition? {
    return slideOut(
        targetOffset = { IntOffset(it.width, 0) },
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitToLeft(): @JvmSuppressWildcards ExitTransition? {
    return slideOut(
        targetOffset = { IntOffset(0 - it.width, 0) },
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitToBottom(): @JvmSuppressWildcards ExitTransition? {
    return slideOut(
        targetOffset = { IntOffset(0, 0 - it.height) },
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.enterFromLeft(): @JvmSuppressWildcards EnterTransition? {
    return slideIn(
        initialOffset = { IntOffset(0 - it.width, 0) },
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.enterFromRight(): @JvmSuppressWildcards EnterTransition? {
    return slideIn(
        initialOffset = { IntOffset(it.width, 0) },
    )
}