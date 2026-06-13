package org.chef.cooksense.recipe

import androidx.compose.ui.graphics.painter.Painter
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    @Contextual
    val image: Painter?
)