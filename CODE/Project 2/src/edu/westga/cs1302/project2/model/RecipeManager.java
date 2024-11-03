package edu.westga.cs1302.project2.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Supports saving recipe data
 * 
 *  @author CS 1302
 * @version Fall 2024
 */
public class RecipeManager {
	public static final String DATA_FILE = "data.txt";
	
	/** Save the bill!
	 * 
	 * Writes all bill data to DATA_FILE
	 * 
	 * @precondition bill != null
	 * @postcondition none
	 * 
	 * @param recipe the recipe to save
	 * @throws IOException 
	 */
	public void saveRecipeData(Recipe recipe) throws IOException, IllegalArgumentException, IllegalStateException {
		if (recipe == null) {
			throw new IllegalArgumentException("Must provide a valid recipe");
		}
		if (this.recipeExists(recipe.getRecipeName())) {
			throw new IllegalStateException("Recipe already exists"); 
		}
		
		try (FileWriter writer = new FileWriter(DATA_FILE, true)) {
			writer.write(RecipeTextifier.recipeToText(recipe) + System.lineSeparator() + System.lineSeparator());
			 		
		} catch (IOException error) {
            throw new IOException("An error occurred while writing to the file.", error);
        }
		 
	}
	
	private boolean recipeExists(String recipeName) throws IOException {
        try (Scanner scanner = new Scanner(DATA_FILE)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Recipe Name: " + recipeName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
