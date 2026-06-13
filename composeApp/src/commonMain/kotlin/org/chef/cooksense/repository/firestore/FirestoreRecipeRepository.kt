package org.chef.cooksense.repository.firestore

import dev.gitlive.firebase.firestore.FirebaseFirestore
import org.chef.cooksense.recipe.Recipe
import org.chef.cooksense.repository.RecipeRepository

class FirestoreRecipeRepository(repository: FirebaseFirestore) : RecipeRepository {
    private val recipesCollection = repository.collection("recipes")

    override suspend fun fetchRecipes(): List<Recipe> {
        return recipesCollection
            .get()
            .documents
            .map { it.data<Recipe>() }
    }

}