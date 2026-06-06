package org.chef.cooksense.mvi

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class RecipeViewModel {
    private val _viewState = mutableStateOf(ViewState())
    val viewState: MutableState<ViewState> = _viewState

    private val recipes = RecipeRepository.getAllRecipes().shuffled()
    private var recipeIndex = 0

    fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.Initialize -> initialize()
            is Intent.SwipeRight -> handleSwipeRight(intent.recipeId)
            is Intent.SwipeLeft -> handleSwipeLeft(intent.recipeId)
        }
    }

    private fun initialize() {
        if (recipes.isEmpty()) {
            _viewState.value = ViewState(showingEmpty = true)
            return
        }
        showNextRecipe()
    }

    private fun showNextRecipe() {
        if (recipeIndex >= recipes.size) {
            _viewState.value = ViewState(
                currentRecipe = null,
                favoriteIds = _viewState.value.favoriteIds,
                blacklistIds = _viewState.value.blacklistIds,
                showingEmpty = true
            )
            return
        }

        val recipe = recipes[recipeIndex]
        _viewState.value = ViewState(
            currentRecipe = recipe,
            favoriteIds = _viewState.value.favoriteIds,
            blacklistIds = _viewState.value.blacklistIds,
            showingEmpty = false
        )
        recipeIndex++
    }

    private fun handleSwipeRight(recipeId: String) {
        val newFavorites = _viewState.value.favoriteIds + recipeId
        _viewState.value = _viewState.value.copy(favoriteIds = newFavorites)
        showNextRecipe()
    }

    private fun handleSwipeLeft(recipeId: String) {
        val newBlacklist = _viewState.value.blacklistIds + recipeId
        _viewState.value = _viewState.value.copy(blacklistIds = newBlacklist)
        showNextRecipe()
    }
}
