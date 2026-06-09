package org.chef.cooksense.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    ) {
        // header
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Discover",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Find new meals",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // card stack
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            // card 3 — furthest back
            if (recipes.size > 2) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
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
                        .height(420.dp)
                        .graphicsLayer {
                            rotationZ = 2f
                            translationY = -10f
                            scaleX = 0.96f
                            scaleY = 0.96f
                        }
                ) {}
            }

            // card 1 — front swipeable card
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
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RecipeCard(recipe = recipes.first())
                }
            } else {
                // empty state
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "🍽",
                        fontSize = 48.sp
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "No more recipes!",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Check back later for more",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        // action buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            // dismiss button
            FilledIconButton(
                onClick = {
                    if (recipes.isNotEmpty()) {
                        viewModel.addToBlackList(recipes.first())
                        viewModel.removeTopCard()
                    }
                },
                modifier = Modifier.size(56.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color(0xFFFFF0F0)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Dismiss",
                    tint = Color(0xFFF09595)
                )
            }

            // bookmark button
            FilledIconButton(
                onClick = { /* TODO: bookmark */ },
                modifier = Modifier.size(56.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = "Bookmark",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // like button
            FilledIconButton(
                onClick = {
                    if (recipes.isNotEmpty()) {
                        viewModel.addToFavorite(recipes.first())
                        viewModel.removeTopCard()
                    }
                },
                modifier = Modifier.size(56.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color(0xFFFFF0F0)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Like",
                    tint = Color(0xFFA32D2D)
                )
            }
        }
    }
}

