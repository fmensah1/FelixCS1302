package edu.westga.cs1302.project2.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Loads recipes from files.
 * 
 * @author fmensah1
 * @version Fall 2024
 */
public class RecipeLoader {
    private String fileName;

    /**
     * Initializes file loader with the specified file name.
     *
     * @param fileName the name of the file to load recipes from
     */
    public RecipeLoader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Returns the file path where recipes are loaded from.
     *
     * @return the file path
     */
    public String getFilePath() {
        return this.fileName;
    }

    /**
     * Loads all recipes from a file.
     *
     * @return a list of Recipe objects, or an empty list if the file is not found
     *         or empty
     */
    public List<Recipe> loadRecipesFromFile() {
        List<Recipe> recipes = new ArrayList<>();
        File file = new File(this.fileName);

        if (!file.exists() || file.length() == 0) {
            System.out.println("File not found or is empty: " + this.fileName);
            return recipes;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String recipeName = scanner.nextLine();
                
                if (!scanner.hasNextLine()) {
                    System.out.println("Warning: Missing ingredients line for recipe: " + recipeName);
                    break;
                }

                String ingredientsLine = scanner.nextLine();
                Recipe recipe = new Recipe();
                recipe.setRecipeName(recipeName); 
                String[] ingredients = ingredientsLine.split(", ");
                for (String ingredientName : ingredients) {
                    recipe.addItem(new Ingredient(ingredientName, "Unknown"));
                }
                
                recipes.add(recipe);
 
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        } catch (IOException error) {
            System.err.println("Error reading file: " + this.fileName);
            error.printStackTrace();
        }

        return recipes;
    }

    /**
     * Gets a list of recipes that contain the specified ingredient.
     *
     * @param ingredientName the name of the ingredient to search for
     * @return a list of recipes containing the specified ingredient, or an empty
     *         list if none found
     */
    public List<Recipe> getAllRecipesWithIngredient(String ingredientName) {
        List<Recipe> recipes = this.loadRecipesFromFile();
        List<Recipe> filteredRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            for (Ingredient ingredient : recipe.getItems()) {
                if (ingredient.getName().equalsIgnoreCase(ingredientName.trim())) {
                    filteredRecipes.add(recipe);
                    break;
                }
            }
        }

        return filteredRecipes;
    }
}