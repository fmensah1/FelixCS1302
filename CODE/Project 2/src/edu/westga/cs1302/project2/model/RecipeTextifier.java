package edu.westga.cs1302.project2.model;

/**
 * Generates text to summarize a Recipe
 * 
 * @author fmensah1
 * @version Fall 2024
 */
public class RecipeTextifier {
	
	/**
	 * Return a String for the recipe.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param recipe the recipe to be converted into text
	 * 
	 * @return a String for the recipe
	 */
	public static String recipeToText(Recipe recipe) {
		if (recipe == null) {
			throw new IllegalArgumentException("Must provide a valid recipe");
		}
		 
		String text = "Recipe Name : " + recipe.getRecipeName() + System.lineSeparator();
		text += "Ingredients: " + System.lineSeparator();
		
		for (Ingredient ingredient : recipe.getItems()) {
			if (ingredient != null) {
				text += ingredient.getName() + ", ";
			}
			text +=   System.lineSeparator();
		}
		return text;
	}
}
