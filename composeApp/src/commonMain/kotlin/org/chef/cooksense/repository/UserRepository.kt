package org.chef.cooksense.repository

import org.chef.cooksense.recipe.Recipe
import kotlin.uuid.Uuid

interface UserRepository {
    suspend fun fetchFavorites(): List<Recipe>
    suspend fun addToFavorites(recipe: Recipe)
    suspend fun removeFromFavorites(recipe: Recipe)
    suspend fun fetchBlacklist(): List<Recipe>
    suspend fun addToBlacklist(recipe: Recipe)
    suspend fun removeFromBlacklist(recipe: Recipe)
}