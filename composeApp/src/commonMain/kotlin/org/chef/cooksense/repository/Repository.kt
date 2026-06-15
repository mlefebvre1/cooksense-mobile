package org.chef.cooksense.repository

class Repository(recipe: RecipeRepository, user: UserRepository) : RecipeRepository by recipe,
    UserRepository by user {
}


