package org.chef.cooksense.recipe

class RecipeRepository {
    fun fetch(): List<Recipe> {
        return listOf(
            Recipe(
                id = "1",
                title = "Pasta ala Gigi",
                description = "Pâtes sauce rosé et bacon",
                tags = listOf("Pâtes", "Sauce rosé", "Bacon"),
                image = null
            ),
            Recipe(
                id = "2",
                title = "Coq au Vin",
                description = "Traditionnel chef français",
                tags = listOf("Viande", "Vin rouge", "Mijoté"),
                image = null
            ),
            Recipe(
                id = "3",
                title = "Ratatouille",
                description = "Légumes du sud de la France",
                tags = listOf("Végétarien", "Légumes", "Sain"),
                image = null
            ),
            Recipe(
                id = "4",
                title = "Beef Wellington",
                description = "Bœuf en croûte élégant",
                tags = listOf("Viande", "Fêté", "Luxueux"),
                image = null
            ),
            Recipe(
                id = "5",
                title = "Caesar Salad",
                description = "Salade crue avec dressing crémeux",
                tags = listOf("Salade", "Léger", "Appétit"),
                image = null
            )
        )
    }
}
