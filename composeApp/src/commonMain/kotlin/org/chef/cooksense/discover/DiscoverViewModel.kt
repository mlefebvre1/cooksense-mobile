package org.chef.cooksense.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.chef.cooksense.firebase.createFirestore
import org.chef.cooksense.recipe.Recipe
import org.chef.cooksense.repository.RecipeRepository
import org.chef.cooksense.repository.UserRepository
import org.chef.cooksense.repository.firestore.FirestoreRecipeRepository
import org.chef.cooksense.repository.firestore.FirestoreUserRepository

data class DiscoverUiState(
    val currentRecipe: Recipe? = null,
    val favoriteIds: HashSet<String> = hashSetOf(),
    val blacklistIds: HashSet<String> = hashSetOf(),
    val showingEmpty: Boolean = false
)

class DiscoverViewModel(
    repository: FirebaseFirestore,
) : ViewModel() {
    private val recipeRepository = FirestoreRecipeRepository(repository = repository)
    private val userRepository = FirestoreUserRepository(repository = repository)

    private val _uiState = MutableStateFlow(DiscoverUiState())

    // Normally, this should be fetched!
    val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes = _recipes.asStateFlow()

    val _favorites = MutableStateFlow<List<Recipe>>(emptyList())
    val favourites = _favorites.asStateFlow()

    val uiState = _uiState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _favorites.value =
                    userRepository.fetchFavorites()
                val favoriteIds = _favorites.value.map { it.id }

                val blacklistedIds =
                    userRepository.fetchBlacklist().map { it.id }

                _recipes.value =
                    recipeRepository.fetchRecipes()
                        .filter { it.id !in blacklistedIds && it.id !in favoriteIds }

            } catch (e: Exception) {
                println(e)
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun addToFavorite(recipe: Recipe) {
        viewModelScope.launch {
            if (_favorites.value.none { it.id == recipe.id }) {
                _favorites.value += recipe
                userRepository.addToFavorites(recipe)
            }
            removeTopCard()
        }
    }

    fun removeFromFavorites(recipe: Recipe) {
        viewModelScope.launch {
            if (recipe in _favorites.value) {
                userRepository.removeFromFavorites(recipe)

                _favorites.value = _favorites.value.filter { favorite -> favorite.id != recipe.id }

                // Put the recipe back into the recipes list
                _recipes.value += recipe
            }

        }
    }


    fun addToBlackList(recipe: Recipe) {
        viewModelScope.launch {
            userRepository.addToBlacklist(recipe)
            removeTopCard()
        }
    }


    private fun removeTopCard() {
        _recipes.value = _recipes.value.drop(1)
    }
}