package edu.westga.cs1302.project2.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;

	class RecipeTest {
	 
	    @Test
	    public void testConstructorCreatesEmptyRecipeWithDefaultName() {
	    	Recipe recipe = new Recipe();
	        assertEquals("No name set", recipe.getRecipeName());
	        assertTrue(recipe.getItems().isEmpty());
	        assertEquals(0, recipe.getSize());
	    }

	    @Test
	    public void testSetRecipeNameValidName() {
	    	Recipe recipe = new Recipe();
	        recipe.setRecipeName("Pasta");
	        assertEquals("Pasta", recipe.getRecipeName());
	    }

	    @Test
	    public void testSetRecipeNameEmptyNameThrowsException() {
	    	Recipe recipe = new Recipe();
	        Exception exception = assertThrows(IllegalArgumentException.class, () -> recipe.setRecipeName(""));
	        assertEquals("name must not be empty.", exception.getMessage());
	    }

	    @Test
	    public void testSetRecipeNameNullThrowsException() {
	    	Recipe recipe = new Recipe();
	        Exception exception = assertThrows(IllegalArgumentException.class, () -> recipe.setRecipeName(null));
	        assertEquals("name must not be null.", exception.getMessage());
	    }

	    @Test
	    public void testAddValidIngredient() {
	    	Recipe recipe = new Recipe();
	        Ingredient ingredient = new Ingredient("Tomato", "Vegetable");
	        recipe.addItem(ingredient);
	        assertEquals(1, recipe.getSize());
	        assertTrue(recipe.getItems().contains(ingredient));
	    }

	    @Test
	    public void testAddNullIngredientThrowsException() {
	    	Recipe recipe = new Recipe();
	        Exception exception = assertThrows(IllegalArgumentException.class, () -> recipe.addItem(null));
	        assertEquals("item must not be null.", exception.getMessage());
	    }

	    @Test
	    public void testAddDuplicateIngredientThrowsException() {
	    	Recipe recipe = new Recipe();
	        Ingredient ingredient = new Ingredient("Tomato", "Vegetable");
	        recipe.addItem(ingredient);
	        
	        Exception exception = assertThrows(IllegalArgumentException.class, () -> recipe.addItem(ingredient));
	        assertEquals("item already exists", exception.getMessage());
	        assertEquals(1, recipe.getSize());
	    }

	    @Test
	    public void testGetItemsReturnsCorrectIngredients() {
	    	Recipe recipe = new Recipe();
	        Ingredient ingredient1 = new Ingredient("Tomato", "Vegetable");
	        Ingredient ingredient2 = new Ingredient("Lettuce", "Vegetable");
	        recipe.addItem(ingredient1);
	        recipe.addItem(ingredient2);
	        
	        assertEquals(2, recipe.getItems().size());
	        assertTrue(recipe.getItems().contains(ingredient1));
	        assertTrue(recipe.getItems().contains(ingredient2));
	    }

	    @Test
	    public void testGetSizeReturnsCorrectCount() {
	    	Recipe recipe = new Recipe();
	        assertEquals(0, recipe.getSize());
	        
	        recipe.addItem(new Ingredient("Tomato", "Vegetable"));
	        recipe.addItem(new Ingredient("Lettuce", "Vegetable"));
	        
	        assertEquals(2, recipe.getSize());
	    }
	}



