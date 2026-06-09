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

    // Normally, this should be fetched!
    private val _recipes = MutableStateFlow(
        listOf(
            Recipe(
                id = "1",
                title = "Pasta ala Gigi",
                description = "Pâtes sauce rosé et bacon",
                tags = listOf("Pâtes", "Sauce rosé", "Bacon"),
                image = null
            ),
            Recipe(
                id = "2",
                title = "Coq au Vin",
                description = "Traditionnel chef français",
                tags = listOf("Viande", "Vin rouge", "Mijoté"),
                image = null
            ),
            Recipe(
                id = "3",
                title = "Ratatouille",
                description = "Légumes du sud de la France",
                tags = listOf("Végétarien", "Légumes", "Sain"),
                image = null
            ),
            Recipe(
                id = "4",
                title = "Beef Wellington",
                description = "Bœuf en croûte élégant",
                tags = listOf("Viande", "Fêté", "Luxueux"),
                image = null
            ),
            Recipe(
                id = "5",
                title = "Caesar Salad",
                description = "Salade crue avec dressing crémeux",
                tags = listOf("Salade", "Léger", "Appétit"),
                image = null
            )
        )
    )

    val uiState = _uiState.asStateFlow()
    val recipes = _recipes.asStateFlow()

    fun addToFavorite(recipe: Recipe) {
        //TODO:
    }

    fun addToBlackList(recipe: Recipe) {
        //TODO:
    }

    fun removeTopCard() {
        _recipes.value = _recipes.value.drop(1)
    }
}