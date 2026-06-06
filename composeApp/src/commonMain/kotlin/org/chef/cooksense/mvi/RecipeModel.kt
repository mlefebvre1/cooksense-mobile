package org.chef.cooksense.mvi

import androidx.compose.ui.graphics.painter.Painter

data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    val image: Painter?
)

sealed interface Intent {
    data object Initialize : Intent
    data class SwipeRight(val recipeId: String) : Intent
    data class SwipeLeft(val recipeId: String) : Intent
}

data class ViewState(
    val currentRecipe: Recipe? = null,
    val favoriteIds: List<String> = emptyList(),
    val blacklistIds: List<String> = emptyList(),
    val showingEmpty: Boolean = false
)

sealed interface Effect
