package org.chef.cooksense.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.chef.cooksense.discover.DiscoverViewModel
import org.chef.cooksense.recipe.Recipe
import org.chef.cooksense.recipe.RecipeRepository
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color

@Composable
fun FavoriteScreen(viewModel: DiscoverViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var selectedRecipe by remember { mutableStateOf<Recipe?>(null) }

    val recipes = RecipeRepository().fetch().asSequence()
        .filter { recipe -> uiState.favoriteIds.contains(recipe.id) }.toList()


    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // header
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
                Text(
                    text = "Favourites",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "${recipes.size} saved recipes",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (recipes.isEmpty()) {
                // empty state
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "🍽", fontSize = 48.sp)
                        Text(
                            text = "No favourites yet",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Start swiping to save recipes",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(recipes, key = { it.id }) { recipe ->
                        FavouriteListItem(
                            recipe = recipe,
                            onClick = { selectedRecipe = recipe }
                        )
                    }
                }
            }
        }

        // dimmer
        if (selectedRecipe != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .clickable { selectedRecipe = null }
            )
        }

        // bottom sheet
        selectedRecipe?.let { recipe ->
            RecipeBottomSheet(
                recipe = recipe,
                onDismiss = { selectedRecipe = null },
                onUnfavourite = {
                    viewModel.removeFromFavorites(recipe)
                    selectedRecipe = null
                },
                onStartCooking = {
                    // TODO: navigate to cooking screen
                }
            )
        }
    }
}