package org.chef.cooksense.repository.firestore

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.chef.cooksense.recipe.Recipe
import org.chef.cooksense.repository.RecipeRepository

class FirestoreRecipeRepository : RecipeRepository {
    private val firestore = Firebase.firestore
    private val recipesCollection = firestore.collection("recipes")

    override suspend fun fetchRecipes(): List<Recipe> {
        return recipesCollection
            .get()
            .documents
            .map { it.data<Recipe>() }
    }

}