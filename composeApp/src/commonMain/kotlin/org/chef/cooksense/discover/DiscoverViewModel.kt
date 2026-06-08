package org.chef.cooksense.discover

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DiscoverUiState(
    val currentRecipe: Recipe? = recipe1,
    val favoriteIds: List<String> = emptyList(),
    val blacklistIds: List<String> = emptyList(),
    val showingEmpty: Boolean = false
)

class DiscoverViewModel : ViewModel() {
    private val respository = DiscoverRepository()
    private val _uiState = MutableStateFlow(DiscoverUiState())
    val uiState = _uiState.asStateFlow()

    fun addToFavorite(recipe: Recipe) {
        //TODO:
    }

    fun addtoBlackList(recipe: Recipe) {
        //TODO:
    }
}