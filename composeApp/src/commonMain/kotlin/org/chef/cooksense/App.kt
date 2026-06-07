package org.chef.cooksense

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stevdza_san.swipeable.Swipeable
import com.stevdza_san.swipeable.domain.ActionAnimationConfig
import com.stevdza_san.swipeable.domain.ActionCustomization
import com.stevdza_san.swipeable.domain.HapticFeedbackConfig
import com.stevdza_san.swipeable.domain.SwipeAction
import com.stevdza_san.swipeable.domain.SwipeBackground
import com.stevdza_san.swipeable.domain.SwipeBehavior
import org.chef.cooksense.mvi.Intent
import org.chef.cooksense.mvi.Recipe
import org.chef.cooksense.mvi.RecipeViewModel
import org.chef.cooksense.ui.CooksenseTheme
import cooksense.composeapp.generated.resources.Res
import cooksense.composeapp.generated.resources.compose_multiplatform
import org.chef.cooksense.auth.AuthViewModel
import org.chef.cooksense.auth.LoginScreen

@Composable
fun App() {
    LoginScreen(
        AuthViewModel(),
        {}, //TODO
        {}, //TODO
    )


//    CooksenseTheme {
//        val viewModel = remember { RecipeViewModel() }
//        val viewState by viewModel.viewState
//
//        LaunchedEffect(Unit) {
//            viewModel.onIntent(Intent.Initialize)
//        }
//
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//        ) {
//            if (viewState.showingEmpty) {
//                Text("No more recipes!", fontSize = 24.sp)
//            } else {
//                viewState.currentRecipe?.let { recipe ->
//                    RecipeCard(recipe, viewModel)
//                }
//            }
//        }
//    }
}

@Composable
fun RecipeCard(recipe: Recipe, viewModel: RecipeViewModel) {
    Swipeable(
        threshold = 0.8f,
        actionAnimation = ActionAnimationConfig.Materialize,
        behavior = SwipeBehavior.DISMISS,
        leftDismissAction = SwipeAction(
            label = "Blacklist",
            customization = ActionCustomization(
                icon = Res.drawable.compose_multiplatform,
                iconColor = Color.White,
                containerColor = Color.Red
            ),
            onAction = { viewModel.onIntent(Intent.SwipeLeft(recipe.id)) }
        ),
        rightDismissAction = SwipeAction(
            label = "Favorite",
            customization = ActionCustomization(
                icon = Res.drawable.compose_multiplatform,
                iconColor = Color.White,
                containerColor = Color.Green
            ),
            onAction = { viewModel.onIntent(Intent.SwipeRight(recipe.id)) }
        ),
        leftBackground = SwipeBackground.solid(Color.Red),
        rightBackground = SwipeBackground.solid(Color.Green),
        hapticFeedbackConfig = HapticFeedbackConfig.Default
    ) {
        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 20.dp, vertical = 50.dp)
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.secondary),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                recipe.image?.let {
                    Image(
                        painter = recipe.image,
                        contentDescription = "Recipe image",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Text(recipe.title)
                Spacer(modifier = Modifier.width(20.dp))

                Text(recipe.description)
                Spacer(modifier = Modifier.width(20.dp))

                Row {
                    recipe.tags.forEach { tag ->
                        Text(
                            tag,
                            modifier = Modifier
                                .border(
                                    width = 1.5.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                }
            }
        }
    }
}
