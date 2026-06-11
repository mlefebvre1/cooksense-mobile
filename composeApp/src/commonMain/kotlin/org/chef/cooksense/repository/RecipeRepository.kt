package org.chef.cooksense.repository

import org.chef.cooksense.recipe.Recipe

interface RecipeRepository {
    suspend fun fetchRecipes(): List<Recipe>
}