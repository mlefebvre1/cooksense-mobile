package org.chef.cooksense.recipe

import androidx.compose.ui.graphics.painter.Painter

data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    val image: Painter?
)