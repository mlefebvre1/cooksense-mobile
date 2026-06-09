package org.chef.cooksense.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stevdza_san.swipeable.Swipeable
import com.stevdza_san.swipeable.domain.ActionCustomization
import com.stevdza_san.swipeable.domain.HapticFeedbackConfig
import com.stevdza_san.swipeable.domain.SwipeAction
import com.stevdza_san.swipeable.domain.SwipeBackground
import com.stevdza_san.swipeable.domain.SwipeBehavior
import cooksense.composeapp.generated.resources.Res
import cooksense.composeapp.generated.resources.ic_close
import cooksense.composeapp.generated.resources.ic_heart

@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel
    //TODO: add callbacks
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val recipes by viewModel.recipes.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.currentRecipe) {
    }
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            "Discover",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.Start)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // card 3 — furthest back
            if (recipes.size > 2) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .height(420.dp)
                        .graphicsLayer {
                            rotationZ = -4f
                            translationY = -20f
                            scaleX = 0.92f
                            scaleY = 0.92f
                        }
                ) {}
            }

            // card 2 — middle
            if (recipes.size > 1) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(420.dp)
                        .graphicsLayer {
                            rotationZ = 2f
                            translationY = -10f
                            scaleX = 0.96f
                            scaleY = 0.96f
                        }
                ) {}
            }

            // card 1 — front, the actual swipeable card
            if (recipes.isNotEmpty()) {
                Swipeable(
                    behavior = SwipeBehavior.DISMISS,
                    threshold = 0.4f,
                    leftDismissAction = SwipeAction(
                        label = "Blacklist",
                        customization = ActionCustomization(
                            icon = Res.drawable.ic_close,
                            iconColor = Color.White,
                            containerColor = Color.Red
                        ),
                        onAction = {
                            viewModel.addToBlackList(recipes.first())
                            viewModel.removeTopCard()
                        }
                    ),
                    rightDismissAction = SwipeAction(
                        label = "Favorite",
                        customization = ActionCustomization(
                            icon = Res.drawable.ic_heart,
                            iconColor = Color.White,
                            containerColor = Color(0xFFA32D2D)
                        ),
                        onAction = {
                            viewModel.addToFavorite(recipes.first())
                            viewModel.removeTopCard()
                        }
                    ),
                    leftBackground = SwipeBackground.solid(Color.Red),
                    rightBackground = SwipeBackground.solid(Color(0xFFA32D2D)),
                    hapticFeedbackConfig = HapticFeedbackConfig.Default,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    RecipeCard(recipe = recipes.first())
                }
            }
        }
    }

}