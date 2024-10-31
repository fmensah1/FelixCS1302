package edu.westga.cs1302.project2.tests;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeTextifier;

class TestRecipeTextifier {

		@Test
		public void testRecipeToTextValidRecipe() {
		    // Arrange
		    Recipe recipe = new Recipe();
		    recipe.setRecipeName("Pasta");
		    recipe.addItem(new Ingredient("Tomato", "Vegetable"));
		    recipe.addItem(new Ingredient("Basil", "vegetable"));
		    recipe.addItem(new Ingredient("Olive Oil", "oil"));

		    // Act
		    String result = RecipeTextifier.recipeToText(recipe);

		    // Assert
		    String expected = "Recipe Name : Pasta" + System.lineSeparator() + "Ingredients: Tomato, Basil, Olive Oil, ";
		    assertEquals(expected, result);
		
	}
    
		@Test
		public void testRecipeToTextNullRecipe() {
			// Arrange
			assertThrows(IllegalArgumentException.class, () -> {
				 RecipeTextifier.recipeToText(null);
			});
		   }
		
		@Test
		public void testRecipeToTextRecipeWithNullIngredient() {
		    // Arrange
		    Recipe recipe = new Recipe();
		    recipe.setRecipeName("Salad");
		    recipe.addItem(new Ingredient("Lettuce", "Vegetable"));
		    recipe.addItem(null);
		    recipe.addItem(new Ingredient("Croutons", "Bread"));

		    assertThrows(IllegalArgumentException.class, () -> {
		      RecipeTextifier.recipeToText(recipe);
		    });
		   
		}

}
