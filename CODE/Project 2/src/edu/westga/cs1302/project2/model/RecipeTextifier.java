package edu.westga.cs1302.project2.model;

import java.util.List;

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
	
	/**
	 * Return a String for a list of  recipes.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param recipes the list of recipes to be converted into text
	 * 
	 * @return a String for the recipe
	 */
	public static String recipesToString(List<Recipe> recipes) {
		 if (recipes == null) {
		        throw new IllegalArgumentException("Recipe list cannot be null");
		    }
		String result = "";
		 for (int index = 0; index < recipes.size(); index++) {
			 Recipe recipe = recipes.get(index);
			 
			 if (recipe != null) {
		            result += recipeToText(recipe);
		       }
			 result += System.lineSeparator() + System.lineSeparator();  
		 }
		 return result;
	}
}
