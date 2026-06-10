package org.chef.cooksense.discover

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.chef.cooksense.recipe.Recipe
import org.chef.cooksense.recipe.RecipeRepository

data class DiscoverUiState(
    val currentRecipe: Recipe? = null,
    val favoriteIds: HashSet<String> = hashSetOf(),
    val blacklistIds: HashSet<String> = hashSetOf(),
    val showingEmpty: Boolean = false
)

class DiscoverViewModel : ViewModel() {
    private val repository = RecipeRepository()
    private val _uiState = MutableStateFlow(DiscoverUiState())

    // Normally, this should be fetched!
    val _recipes = MutableStateFlow(repository.fetch())

    val uiState = _uiState.asStateFlow()
    val recipes = _recipes.asStateFlow()

    fun addToFavorite(recipe: Recipe) {
        //TODO: set the DB instead
        uiState.value.favoriteIds.add(recipe.id)
    }

    fun addToBlackList(recipe: Recipe) {
        //TODO: set the DB instead
        uiState.value.blacklistIds.add(recipe.id)
    }

    fun removeFromFavorites(recipe: Recipe) {
        uiState.value.favoriteIds.remove(recipe.id)
    }

    fun removeTopCard() {
        //TODO: we would use the DB and have paging logic for requesting only some part of the db recipes
        _recipes.value = _recipes.value.drop(1)
    }
}